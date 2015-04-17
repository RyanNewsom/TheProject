/**
 * This class will create formatted messages for the log printout
 * They keep track of someone comes in the door or makes a phone call or has finally gotten her question answered. 
 * Each message should indicate at what time the event happened, to whom it happened (number each customer from 1 to however high they reach), 
 * and how long the line in the office is after the event. (The person currently being served counts as a member of the line, as do waiting phone callers). 
 * @author Ryan Newsom
 *
 */
public class Message {
	String time; //The time the event STARTED at
	String eventType;
	String timeEventStart;
	int lineLength;
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return time;
		
	}
	
	public void setEventType(String eventType){
		this.eventType = eventType;
	}
	
	public String getEventType(){
		return eventType;
	}
	
	public void setTimeEventStart(){
		this.timeEventStart = timeEventStart;
	}
	
	public String getTimeEventStart(){
		return timeEventStart;
	}
	
	public void setLineLength(int lineLength){
		this.lineLength = lineLength;
	}
	
	public int getLineLength(){
		return lineLength;
	}
	
}
