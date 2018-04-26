package hw1;

/**
 * Model of a simple television.
 * @author nshull16
 *
 */
public class Television {
	/**
	 * Constant, defined value for how 
	 * much the volume increases/decreases.
	 */
	public static final double VOLUME_INCREMENT = 0.07;
	/**
	 * The current channel that the television
	 * is on. This value should never be below
	 * zero, nor more than givenChannelMax - 1.
	 */
	private int channel;
	/**
	 * The previous channel that the television
	 * was on. If there hasn't been a previous channel,
	 * it's set to zero.
	 */
	private int previousChannel;
	/**
	 * The volume of the television. Incremented
	 * by 0.07 each time. Can't be lower than zero,
	 * nor greater than 1.
	 */
	private double volume;
	/**
	 * The highest channel that the television
	 * is allowed to be set at. Essentially it's
	 * givenChannelMax - 1.
	 */
	private int channelMax;
	
	/**
	 * Constructs a television with the given
	 * amount of channels. The volume is initally 0.5
	 * @param givenChannelMax
	 * 	maximum amount of channels for the television
	 */
	public Television(int givenChannelMax)
	{
		channelMax = givenChannelMax - 1;
		volume = 0.5;
	}
	/**
	 * Causes the current channel to be lowered by one.
	 * If the channel is at zero, the channel will loop around
	 * to givenChannelMax - 1. 
	 */ 
	public void channelDown()
	{
		if(channel == 0){
			channel = channelMax;
			previousChannel = 0;
		}
		else {
			previousChannel = channel;
			channel = channel - 1;
		}
		
	}
	/**
	 * Causes the current channel to be increased by one.
	 * If the channel is at givenChannelMax - 1, it will 
	 * loop around to 0.
	 */
	public void channelUp()
	{
		if(channel == channelMax){
			channel = 0;
			previousChannel = channelMax;
		}
		else{
			previousChannel = channel;
			channel = channel + 1;
		}
		
	}
	/**
	 * Prints the current channel and volume of the television.
	 * @return
	 * 	The channel as an integer, and the volume as a rounded percent.
	 */
	public String display()
	{
		return String.format("Channel "+ channel + " Volume "+ Math.round(volume*100) + "%%" );
	}
	/**
	 * Returns the current channel of the television.
	 * @return
	 * 	current channel of the television
	 */
	public int getChannel()
	{
		return channel;
	}
	/**
	 * Returns the current volume of the television.
	 * @return
	 * 	current volume of the television.
	 */
	public double getVolume()
	{
		return volume;
	}
	/**
	 * Sets the current channel of the television to 
	 * the previous channel. 
	 */
	public void goToPreviousChannel()
	{
		channel = previousChannel;
	}
	/**
	 * Resets the television so that its available channels are
	 * now from 0 through givenMax - 1. If the current channel is
	 * greater than givenMax - 1, it is automatically adjusted
	 * to givenMax - 1. If the previous channel is greater than
	 * givenMax - 1, it is automatically adjusted to givenMax -1.
	 * @param givenMax
	 * 	the new maximum amount of channels.
	 */
	public void resetChannelMax(int givenMax)
	{
		channelMax = givenMax - 1;
		if(channel > channelMax){
			channel = channelMax;
		}
		else if(previousChannel > channelMax){
			previousChannel = channelMax;
		}
	}
	/**
	 * Sets the channel to the given channel number.
	 * If the given value is greater than channelMax - 1; the channel
	 * is set to channelMax - 1. If the given value is negative,
	 * the channel is set to zero.
	 * @param channelNumber
	 * 	the updated current channel for the television.
	 */
	public void setChannel (int channelNumber)
	{
		if(channelNumber < 0 ){
			previousChannel = channel;
			channel = 0;
		}
		else if(channelNumber > channelMax){
			previousChannel = channel;
			channel = channelMax;
		}
		else{
			previousChannel = channel;
			channel = channelNumber;
		}
	}
	/**
	 * Decreases the volume by VOLUME_INCREMENT(0.07).
	 * Never sets the volume below zero
	 */
	public void volumeDown()
	{
		volume = volume - VOLUME_INCREMENT;
		volume = Math.max(0, volume);
	}
	/**
	 * Increases the volume by VOLUME_INCREMENT(0.07).
	 * Never sets the volume above 1.0
	 */
	public void volumeUp()
	{
		volume = volume + VOLUME_INCREMENT;
		volume = Math.min(1.0, volume);
	}
}