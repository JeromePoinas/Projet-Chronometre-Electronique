package coucheApplicative;

import java.util.Observable;
import utilitaire.ConstanteMVC;

/**
 * La classe ModeleChronometre.
 * 
 * @author Jérôme
 *         Charles Neau
 */
public class ModeleChronometre extends Observable
{
	/**
	 * Methode de notifier les Observers des actions a faire.
	 * 
	 * @param info
	 *            L'information à transmettre.
	 */
	public void informer(final Object info)
	{
		setChanged();
		notifyObservers(info);
	}

	/**
	 * Start.
	 */
	public void start()
	{
		informer(ConstanteMVC.DEMARRER);
	}

	/**
	 * Stop.
	 */
	public void stop()
	{
		informer(ConstanteMVC.ARRETER);
	}

	/**
	 * Reinit.
	 */
	public void reinit()
	{
		informer(ConstanteMVC.REINIT);
	}
}
