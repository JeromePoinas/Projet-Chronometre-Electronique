package vueChronometre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilitaire.ConstanteMVC;
import utilitaire.DigitsChronometre;
import coucheApplicative.ModeleChronometre;

/**
 * La classe PanneauChrono qui contiendra le chronometre.
 * 
 * @author Jérôme Poinas & Charles Neau !
 *         Et Jean.
 * 
 * 
 */
public class PanneauChrono extends JPanel implements ConstanteMVC, Observer,
		Runnable
{
	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID	= 1L;
	/** The time cur. */
	private int							timeCur;
	/** The retard. */
	private long						retard;
	/** Notre tableau de LcdDigit correspondant à la valeur du chronometre. */
	private DigitsChronometre[]			lcd;
	/** La valeur en string du chronometre. */
	private String						timeS				= "00000";
	/** Heure a laquelle on a declenché le chronometre. */
	private long						timeStart;
	/** Le nombre de secondes en cours du chronometre. */
	private int							sec					= 0;
	/** Le nombre de centiemes de secondes en cours du chronometre. */
	private int							cent				= 0;
	/** Permet de connaitre si oui ou non le chronometre est lancé. */
	private boolean						isLaunched			= false;
	/**
	 * Permet de connaitre si le chronometre est mis en pause ou reellement
	 * arreté.
	 */
	private boolean						isPaused;
	/** La couleur des chiffre du chronometre et de l'ombre des chiffres. */
	private Color						couleurLabelChronometre;
	/** The couleur ombre label chronometre. */
	private Color						couleurOmbreLabelChronometre;
	/** Notre thread qui compte. */
	private Thread						runner				= null;
	/** Moment ou on fait la pause. */
	private long						timePause;
	/**
	 * Le controleur du chronometre.
	 */
	private final ControleurChrono		controleur;
	/**
	 * Le modele du chronometre.
	 */
	private final ModeleChronometre		mdl;
	/**
	 * Les boutons.
	 */
	private final Map<String, JButton>	mesBoutons			= new HashMap<>();

	/**
	 * Constructeur complet du PanneauChrono.
	 */
	public PanneauChrono()
	{
		setLayout(new BorderLayout());
		mdl = new ModeleChronometre();
		mdl.addObserver(this);
		controleur = new ControleurChrono(mdl);
		/**************************************/
		add(nouveauPanelBouton(controleur), BorderLayout.SOUTH);
		setupChrono();
		dessinerHorloge();
	}

	/**
	 * Methode qui permet de gérer les couleurs.
	 */
	private void setupChrono()
	{
		couleurLabelChronometre = Color.YELLOW;
		couleurOmbreLabelChronometre = Color.DARK_GRAY;
		setBackground(Color.BLACK);
	}

	/**
	 * Methode qui permet de generer un panel se composant exclusivement de
	 * boutons.
	 * 
	 * @param controleur
	 *            Le controleur responsable de chaque bouton.
	 * @return Le JPanel se composant des boutons.
	 */
	private Component nouveauPanelBouton(final ControleurChrono controleur)
	{
		final JPanel pnBoutons = new JPanel();
		pnBoutons.add(nouveauBouton(ConstanteMVC.DEMARRER, controleur, true));
		pnBoutons.add(nouveauBouton(ConstanteMVC.ARRETER, controleur, false));
		pnBoutons.add(nouveauBouton(ConstanteMVC.REINIT, controleur, false));
		return pnBoutons;
	}

	/**
	 * Methode qui permet de creer un bouton.
	 * 
	 * @param nomBouton
	 *            Le nom du bouton.
	 * @param controleur
	 *            Le controleur responsable des actions menées sur le bouton.
	 * @param activated
	 *            Le bouton est activé ou desactivé.
	 * @return Le bouton créé.
	 */
	private Component nouveauBouton(final String nomBouton,
			final ControleurChrono controleur, final boolean activated)
	{
		final JButton bouton = new JButton();
		bouton.setText(nomBouton);
		bouton.setActionCommand(nomBouton);
		bouton.addActionListener(controleur);
		bouton.setEnabled(activated);
		mesBoutons.put(nomBouton, bouton);
		return bouton;
	}

	/**
	 * Methode qui permet de lancer le chronometre.
	 */
	public void start()
	{
		retard = 0; // Le retard est remis à zéro.
		timeStart = System.currentTimeMillis();
		isLaunched = true;
		isPaused = false;
		if (runner == null)
		{
			runner = new Thread(this);
			runner.start();
		}
		activateBoutons(false, true, false);
	}

	/**
	 * Methode qui active ou desactive certain bouton.
	 * 
	 * @param demarrer
	 *            active ou desactive le bouton demarrer.
	 * @param arreter
	 *            active ou desactive le bouton arreter.
	 * @param reinit
	 *            active ou desactive le bouton reinitialiser.
	 */
	private void activateBoutons(final boolean demarrer, final boolean arreter,
			final boolean reinit)
	{
		mesBoutons.get(ConstanteMVC.DEMARRER).setEnabled(demarrer);
		mesBoutons.get(ConstanteMVC.ARRETER).setEnabled(arreter);
		mesBoutons.get(ConstanteMVC.REINIT).setEnabled(reinit);
	}

	/**
	 * Methode qui arrete definitivement le chronometre.
	 */
	public void stop()
	{
		timePause = System.currentTimeMillis();
		isPaused = true;
		setTime(System.currentTimeMillis(), retard);
		timeS = toString();
		lcd = dessinerHorloge();
		repaint();
		// Modification du label
		mesBoutons.get(ConstanteMVC.DEMARRER).setText(ConstanteMVC.REPRISE);
		activateBoutons(true, false, true);
	}

	/**
	 * Methode qui redeclenche le chronometre.
	 */
	public void reprise()
	{
		isPaused = false;
		activateBoutons(false, true, false);
		retard += System.currentTimeMillis() - timePause;
		setTime(System.currentTimeMillis(), retard);
		timeS = toString();
		lcd = dessinerHorloge();
		repaint();
	}

	/**
	 * Methode qui remet a zero le chronometre.
	 */
	public void reinit()
	{
		mesBoutons.get(ConstanteMVC.DEMARRER).setText(ConstanteMVC.DEMARRER);
		runner = null;
		isLaunched = false;
		isPaused = false;
		sec = cent = 0;
		timeS = toString();
		lcd = dessinerHorloge();
		repaint();
		activateBoutons(true, false, true);
	}

	/**
	 * To string.
	 * 
	 * @return le nombre de centiemes de secondes écoulés
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		if (sec < 10)
		{
			builder.append("00").append(sec);
		}
		else if (sec < 100)
		{
			builder.append("0").append(sec);
		}
		else
		{
			builder.append("").append(sec);
		}
		if (cent < 10)
		{
			builder.append("0").append(cent);
		}
		else
		{
			builder.append("").append(cent);
		}
		return builder.toString();
	}

	/**
	 * Permet de modifier l'heure du chronometre.
	 * 
	 * @param sysTime
	 *            l'heure systeme
	 * @param retard
	 *            Le retard (le plus souvent ce sera l'heure systeme du
	 *            declenchement du chronometre).
	 */
	public void setTime(final long sysTime, final long retard)
	{
		timeCur = ((int) (sysTime - timeStart - retard)) / 10;
		cent = timeCur % 100;
		sec = (timeCur % 100000) / 100;
	}

	/**
	 * Positionne les composant graphique du chronometre dans la fenetre.
	 * 
	 * @param g
	 *            the g
	 */
	@Override
	public void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		lcd[0].draw(g, 120, 10);
		lcd[1].draw(g, 135, 10);
		lcd[2].draw(g, 150, 10);
		lcd[3].draw(g, 165, 10);
		lcd[4].draw(g, 180, 10);
		lcd[5].draw(g, 195, 10);
	}

	/**
	 * Methode qui permet de dessiner a tous moment l'ecran du chronometre.
	 * 
	 * @return Le tableau de LcdDigit du chronometres.
	 */
	public DigitsChronometre[] dessinerHorloge()
	{
		lcd = new DigitsChronometre[7];
		lcd[0] = new DigitsChronometre(Integer.parseInt(timeS.substring(0, 1)),
				10, 20, couleurLabelChronometre, couleurOmbreLabelChronometre);
		lcd[1] = new DigitsChronometre(Integer.parseInt(timeS.substring(1, 2)),
				10, 20, couleurLabelChronometre, couleurOmbreLabelChronometre);
		lcd[2] = new DigitsChronometre(Integer.parseInt(timeS.substring(2, 3)),
				10, 20, couleurLabelChronometre, couleurOmbreLabelChronometre);
		lcd[3] = new DigitsChronometre(10, 10, 20, couleurLabelChronometre,
				new Color(0, 0, 0)); // Correspond au caractère " ' "
		lcd[4] = new DigitsChronometre(Integer.parseInt(timeS.substring(3, 4)),
				10, 20, couleurLabelChronometre, couleurOmbreLabelChronometre);
		lcd[5] = new DigitsChronometre(Integer.parseInt(timeS.substring(4, 5)),
				10, 20, couleurLabelChronometre, couleurOmbreLabelChronometre);
		return lcd;
	}

	/* _________________________________________________________ */
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		while (isLaunched)
		{
			if (!isPaused)
			{
				setTime(System.currentTimeMillis(), retard);
				timeS = toString();
				lcd = dessinerHorloge();
				repaint();
				try
				{
					Thread.sleep(10);
				}
				catch (final InterruptedException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/* _________________________________________________________ */
	/**
	 * Update.
	 * 
	 * @param source
	 *            the source
	 * @param info
	 *            the info
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable source, final Object info)
	{
		if (source instanceof ModeleChronometre)
		{
			if (info.equals(ConstanteMVC.DEMARRER)
					&& mesBoutons.get(ConstanteMVC.DEMARRER).getText()
							.equals(ConstanteMVC.DEMARRER))
			{
				start();
			}
			if (info.equals(ConstanteMVC.DEMARRER)
					&& mesBoutons.get(ConstanteMVC.DEMARRER).getText()
							.equals(ConstanteMVC.REPRISE))
			{
				reprise();
			}
			if (info.equals(ConstanteMVC.ARRETER))
			{
				stop();
			}
			if (info.equals(ConstanteMVC.REINIT))
			{
				reinit();
			}
		}
	}
}
