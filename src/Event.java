/**
 * Anytime something happens, create an event object and store it in a linked list in order of what times they happened
 * @author Ryan Newsom
 *
 */
public class Event {
	/** This represents the type of the action. OPTIONS (1. Phone call 2. Walk-in 3. Question Answered 4. Interrupted */
	private String type;
	/** The name of the customer */
	private String name;
	/** The time the event happens  in seconds */
	private double time;
	/** The length of the line after the event */
	private int lineLength;
	/**
	 * When you create an event record all these values. They should NEVER need to be changed, ever.
	 * @param type - The type of the event (Phone call, Walk-in, Question Answered, Interrupted)
	 * @param name - The name of the customer
	 * @param time - The time the event occurred
	 */
	protected Event(String type, String name, double time)
	{
		this.type = type;
		this.name = name;
		this.time = time;
	}
	
	public String toString(){
		return "" + type + " " + name + " " + timeConversion((int) time);
	}
	
	/**
	 * Formats the time in seconds to hours, minutes, seconds
	 * @param totalSeconds - the amount of time in seconds
	 * @return a formatted time consisting of Hours:minutes:seconds
	 */
	private static String timeConversion(int totalSeconds) {

	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
	    int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
	    int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
	    int hours = totalMinutes / MINUTES_IN_AN_HOUR;

	    if (seconds <10)
	    	seconds += 0;
	    return hours + ":" + minutes + ":" + seconds;
	}
	
	
}