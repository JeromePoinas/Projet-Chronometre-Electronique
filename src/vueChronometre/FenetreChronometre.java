package vueChronometre;
import java.awt.Dimension;

import javax.swing.JFrame;

import utilitaire.ConstanteMVC;




/**
 * La classe FenetreChronometre
 * @author Jérôme
 *
 */
public class FenetreChronometre extends JFrame implements ConstanteMVC{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Constructeur complet de la FenetreChronometre.
	 */
	public FenetreChronometre() {
		setTitle(ConstanteMVC.TITLE) ;
		setSize(new Dimension(350,120));
		setLocationRelativeTo(this.getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE) ;
		setResizable(false);
		add(new PanneauChrono());
		setVisible(true) ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FenetreChronometre();
	}

}
