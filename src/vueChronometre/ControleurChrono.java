package vueChronometre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import utilitaire.ConstanteMVC;
import coucheApplicative.ModeleChronometre;

/**
 * The Class ControleurChrono.
 */
public class ControleurChrono implements ActionListener, ConstanteMVC
{
	/**
	 * Le modele.
	 */
	private final ModeleChronometre	mdl;

	/**
	 * Contructeur complet du controleur.
	 * 
	 * @param mdl
	 *            Le modele à utiliser.
	 */
	public ControleurChrono(final ModeleChronometre mdl)
	{
		this.mdl = mdl;
	}

	/* _________________________________________________________ */
	/**
	 * Action performed.
	 * 
	 * @param event
	 *            the event
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		final String commande = event.getActionCommand();
		if ((event.getSource() instanceof JButton)
				&& commande.equals(ConstanteMVC.DEMARRER))
		{
			mdl.start();
		}
		if ((event.getSource() instanceof JButton)
				&& commande.equals(ConstanteMVC.ARRETER))
		{
			mdl.stop();
		}
		if ((event.getSource() instanceof JButton)
				&& commande.equals(ConstanteMVC.REINIT))
		{
			mdl.reinit();
		}
	}
}
