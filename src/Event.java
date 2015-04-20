/**
 * Anytime something happens, create an event object and store it in a linked list in order of what times they happened
 * @author Ryan Newsom
 *
 */
public class Event {
	/** This represents the type of the action. OPTIONS (1. Phone call 2. Walk-in 3. Question Answered" */
	private String type;
	/** The name of the customer */
	private String name;
	/** The time the event happens  in seconds */
	private double time;
	
	protected Event(String type, String name, double time)
	{
		this.type = type;
		this.name = name;
		this.time = time;
	}
	
	
}
