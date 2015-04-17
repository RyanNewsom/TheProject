/**
 * This class will keep track of what's going on at different times. It will also be used for the Log class. Create a Message object whenever an event happens and record ALL FIELDS.
 * It will keep track of the events including - when someone comes in the door OR makes a phone call OR has finally gotten their question answered. 
 * Each message should indicate at what time the event happened, to whom it happened (number each customer from 1 to however high they reach), 
 * and how long the line in the office is after the event. (The person currently being served counts as a member of the line, as do waiting phone callers). 
 * @author Ryan Newsom
 * @version 1
 */
public class Message {
	String startTime;// The startTime the event STARTED at
	String eventType;// The type of the event(phone call, in-person, question answered)
	int lineLength;// The length of the line AFTER the event
	int place;// The customers place in line
	/**
	 * Make sure when you create this message object, you fill out all fields. 
	 * @param startTime The time the Event Starts at
	 * @param eventType The type of the Event. Pick one(phone call, in-person, question answered)
	 * @param lineLength The length of the line AFTER the event
	 * @param place The place of this customer in-line
	 */
	public Message(String startTime, String eventType, int lineLength, int place){
		this.startTime = startTime;
		this.eventType = eventType;
		this.lineLength = lineLength;
		this.place = place;
	}
	
	public void setstartTime(String startTime){
		this.startTime = startTime;
	}
	
	public String getstartTime(){
		return startTime;
	}
	
	public void setEventType(String eventType){
		this.eventType = eventType;
	}
	
	public String getEventType(){
		return eventType;
	}
	
	public void setLineLength(int lineLength){
		this.lineLength = lineLength;
	}
	
	public int getLineLength(){
		return lineLength;
	}
	
	public void setPlace(int place){
		this.place = place;
	}
	
	public int getPlace(){
		return place;
	}
	
}
