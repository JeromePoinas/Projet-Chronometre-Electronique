package coucheApplicative;

import java.util.Observable;

import utilitaire.ConstanteMVC;
/**
 * La classe ModeleChronometre.
 * @author Jérôme
 *
 */
public class ModeleChronometre extends Observable{

	/**
	 * Methode de notifier les Observers des actions a faire.
	 * @param info L'information à transmettre.
	 */
	public void informer(Object info)
	{
		setChanged();
		notifyObservers(info);
	}
	
	public void start() 
	{
		informer(ConstanteMVC.DEMARRER);
	}

	public void stop() 
	{
		informer(ConstanteMVC.ARRETER);
	}

	public void reinit() 
	{
		informer(ConstanteMVC.REINIT);
	}

}
