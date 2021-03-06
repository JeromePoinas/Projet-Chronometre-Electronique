package vueChronometre;

import java.awt.Dimension;
import javax.swing.JFrame;
import utilitaire.ConstanteMVC;

/**
 * La classe FenetreChronometre.
 * 
 * @author Jerome Poinas
 */
public class FenetreChronometre extends JFrame implements ConstanteMVC
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Constructeur complet de la FenetreChronometre.
	 */
	public FenetreChronometre()
	{
		setTitle(ConstanteMVC.TITLE);
		setSize(new Dimension(350, 120));
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		add(new PanneauChrono());
		setVisible(true);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	@SuppressWarnings("unused")
	public static void main(final String[] args)
	{
		new FenetreChronometre();
	}
}
