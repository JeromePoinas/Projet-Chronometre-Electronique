package vueChronometre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import utilitaire.ConstanteMVC;

import coucheApplicative.ModeleChronometre;

public class ControleurChrono implements ActionListener, ConstanteMVC{

	/**
	 * Le modele.
	 */
	private ModeleChronometre mdl;
	/**
	 * Contructeur complet du controleur.
	 * @param mdl Le modele à utiliser.
	 */
	public ControleurChrono(ModeleChronometre mdl) 
	{
		this.mdl=mdl;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String commande = event.getActionCommand();
		if(event.getSource() instanceof JButton && commande.equals(ConstanteMVC.DEMARRER))
		{
			mdl.start();
		}
		if(event.getSource() instanceof JButton && commande.equals(ConstanteMVC.ARRETER))
		{
			mdl.stop();
		}
		if(event.getSource() instanceof JButton && commande.equals(ConstanteMVC.REINIT))
		{
			mdl.reinit();
		}
	}
}
