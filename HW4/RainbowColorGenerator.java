package hw4;

import java.awt.Color;
import java.util.Random;

import main.Config;
/**
 * Randomly creates the colors of the rainbow.
 * @author Nathan Shull
 *
 */
public class RainbowColorGenerator implements color.ColorGenerator {

	private static final Color Red = new Color(.25f, 0.0f, 0.0f);
	private static final Color Orange = new Color(.25f, .125f, 0.0f);
	private static final Color Yellow = new Color(.25f, .25f, 0.0f);
	private static final Color Green = new Color(0.0f, .25f, 0.0f);
	private static final Color Blue = new Color(0.0f, 0.0f, .25f);
	private static final Color Purple = new Color(.25f, 0.0f, .25f);
	
	private Random rand = Config.RANDOM;
	public Color createColor(){
		int next = rand.nextInt(6);
		switch(next){
		case 0:
			return Red;
		case 1:
			return Orange;
		case 2:
			return Yellow;
		case 3:
			return Green;
		case 4:
			return Blue;
		default:
			return Purple;
		}
	}
}
