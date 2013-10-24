package utilitaire;

import java.awt.Color;
import java.awt.Graphics;

/**
 * La classe LcdDigit.
 * 
 * @author Jerome
 * 
 */
public class DigitsChronometre
{
	/**
	 * Permet de dessiner les chiffres.
	 */
	private static int	atMapChiffre[][]	= { { 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 0 }, { 1, 0, 1, 1, 0, 1, 1 },
			{ 1, 0, 0, 1, 1, 1, 1 }, { 0, 1, 0, 0, 1, 1, 1 },
			{ 1, 1, 0, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 1, 0 } };
	/** Valeur du chiffre a afficher. */
	private final int	valeur;
	/** Largeur du chiffre. */
	private final int	width;
	/** Longueur du chiffre. */
	private final int	height;
	/** Couleur du chiffre. */
	private final Color	lightColor;
	/** Couleur de l'ombre du chiffre. */
	private final Color	darkColor;

	/**
	 * Constructeur complet d'un chiffre.
	 * 
	 * @param valeur
	 *            La valeur du chiffre.
	 * @param width
	 *            Sa largeur.
	 * @param height
	 *            Sa longueur.
	 * @param lightColor
	 *            Couleur du chiffre.
	 * @param darkColor
	 *            Couleur de son ombre.
	 */
	public DigitsChronometre(final int valeur, final int width,
			final int height, final Color lightColor, final Color darkColor)
	{
		this.valeur = valeur;
		this.width = width;
		this.height = height;
		this.lightColor = lightColor;
		this.darkColor = darkColor;
	}

	/**
	 * Permet de dessiner le chiffre aà une position donnée.
	 * 
	 * @param agGraphic
	 *            l'élément graphique.
	 * @param agPositionX
	 *            Ordonnée
	 * @param agPositionY
	 *            Abscisse.
	 */
	public void draw(final Graphics agGraphic, final int agPositionX,
			final int agPositionY)
	{
		final int lcSegmentXS[] = new int[7];
		final int lcSegmentYS[] = new int[7];
		// Valeur des segments
		//
		// ##0##
		// # #
		// 1 5
		// # #
		// ##6##
		// # #
		// 2 4
		// # #
		// ##3##
		//
		// Segment 0
		if (atMapChiffre[valeur][0] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX + (int) (width * 0.1);
		lcSegmentXS[1] = agPositionX + (int) (width * 0.9);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.7);
		lcSegmentXS[3] = agPositionX + (int) (width * 0.3);
		lcSegmentXS[4] = agPositionX + (int) (width * 0.1);
		lcSegmentYS[0] = agPositionY;
		lcSegmentYS[1] = agPositionY;
		lcSegmentYS[2] = agPositionY + (int) (height * 0.1);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.1);
		lcSegmentYS[4] = agPositionY;
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 1
		if (atMapChiffre[valeur][1] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX;
		lcSegmentXS[1] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[3] = agPositionX;
		lcSegmentXS[4] = agPositionX;
		lcSegmentYS[0] = agPositionY + (int) (height * 0.05);
		lcSegmentYS[1] = agPositionY + (int) (height * 0.15);
		lcSegmentYS[2] = agPositionY + (int) (height * 0.35);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.45);
		lcSegmentYS[4] = agPositionY + (int) (height * 0.05);
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 2
		if (atMapChiffre[valeur][2] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX;
		lcSegmentXS[1] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[3] = agPositionX;
		lcSegmentXS[4] = agPositionX;
		lcSegmentYS[0] = agPositionY + (int) (height * 0.55);
		lcSegmentYS[1] = agPositionY + (int) (height * 0.65);
		lcSegmentYS[2] = agPositionY + (int) (height * 0.85);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.95);
		lcSegmentYS[4] = agPositionY + (int) (height * 0.55);
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 3
		if (atMapChiffre[valeur][3] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX + (int) (width * 0.1);
		lcSegmentXS[1] = agPositionX + (int) (width * 0.9);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.7);
		lcSegmentXS[3] = agPositionX + (int) (width * 0.3);
		lcSegmentXS[4] = agPositionX + (int) (width * 0.1);
		lcSegmentYS[0] = agPositionY + height;
		lcSegmentYS[1] = agPositionY + height;
		lcSegmentYS[2] = agPositionY + (int) (height * 0.9);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.9);
		lcSegmentYS[4] = agPositionY + height;
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 4
		if (atMapChiffre[valeur][4] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX + width;
		lcSegmentXS[1] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[3] = agPositionX + width;
		lcSegmentXS[4] = agPositionX + width;
		lcSegmentYS[0] = agPositionY + (int) (height * 0.55);
		lcSegmentYS[1] = agPositionY + (int) (height * 0.65);
		lcSegmentYS[2] = agPositionY + (int) (height * 0.85);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.95);
		lcSegmentYS[4] = agPositionY + (int) (height * 0.55);
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 5
		if (atMapChiffre[valeur][5] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX + width;
		lcSegmentXS[1] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[3] = agPositionX + width;
		lcSegmentXS[4] = agPositionX + width;
		lcSegmentYS[0] = agPositionY + (int) (height * 0.05);
		lcSegmentYS[1] = agPositionY + (int) (height * 0.15);
		lcSegmentYS[2] = agPositionY + (int) (height * 0.35);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.45);
		lcSegmentYS[4] = agPositionY + (int) (height * 0.05);
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 5);
		// Segment 6
		if (atMapChiffre[valeur][6] == 1)
		{
			agGraphic.setColor(lightColor);
		}
		else
		{
			agGraphic.setColor(darkColor);
		}
		lcSegmentXS[0] = agPositionX + (int) (width * 0.1);
		lcSegmentXS[1] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[2] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[3] = agPositionX + (int) (width * 0.9);
		lcSegmentXS[4] = agPositionX + (int) (width * 0.8);
		lcSegmentXS[5] = agPositionX + (int) (width * 0.2);
		lcSegmentXS[6] = agPositionX + (int) (width * 0.1);
		lcSegmentYS[0] = agPositionY + (int) (height * 0.5);
		lcSegmentYS[1] = agPositionY + (int) (height * 0.45);
		lcSegmentYS[2] = agPositionY + (int) (height * 0.45);
		lcSegmentYS[3] = agPositionY + (int) (height * 0.5);
		lcSegmentYS[4] = agPositionY + (int) (height * 0.55);
		lcSegmentYS[5] = agPositionY + (int) (height * 0.55);
		lcSegmentYS[6] = agPositionY + (int) (height * 0.5);
		agGraphic.fillPolygon(lcSegmentXS, lcSegmentYS, 7);
	}
}
