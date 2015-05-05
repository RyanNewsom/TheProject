import java.util.Random;

/**
 * This class represents the customer. The customer knows many things about himself
 * and will be either a phone-caller or walk-in. He will get his question from the message class
 * and the secretary will respond with a relative answer.
 * @author Ryan Newsom
 *
 */
class Customer {
	/** Name of the customer */
	String name;
	/** Phone-call or walk-in */
	String type;
	/** The time the customer called or walked in */
	double startTime;
	/** The time their question was answered */
	double answerTime;
	/** The time remaining for the customers question to be answered */
	double remainingTime;
	/** The customers question */
	String phrase;
	/** The answer the customer was given */
	String answer;
	/** The customers priority */
	int priority; 
	/** The length of the line after the event */
	int lineLength;
	
	/**
	 * It's important the customer knows everything about them-self. The question they ask will be randomly generated. 
	 * @param name - the name of the customer
	 * @param type - phone-call or walk-in
	 * @param sTime - start time formatted as such 00:00:00 i.e. (H:M:S)
	 * @param answerTime - answer time formatted as such 00:00:00 i.e. (H:M:S)
	 * @param prior - priority of the customer
	 * @param lineLength - Length of the line after the event
	 */
	protected Customer(String type, double sTime, double answerTime, double remainingTime, int prior, int lineLength){
		Random rand = new Random();// Will be used to generate a random integer
		int question = rand.nextInt(14);
		name = Message.getName(rand.nextInt(37));
		this.type = type;
		this.startTime = sTime;
		this.answerTime = answerTime;
		this.remainingTime = remainingTime;
		phrase = Message.getQuestion(question);// Generates a random int which will allow for a random question to be returned
		answer = Message.getAnswer(question);// Generates the appropriate answer for the question
		this.priority = prior;
		this.lineLength = lineLength;
	}
	/**
	 * Set the time the customer question was answered
	 * @param answerTime - the time the customer question was answered
	 */
	protected String getName(){
		return name;
	}
	/**
	 * Set the time the customer question was answered
	 * @param answerTime - the time the customer question was answered
	 */
	protected void setLineRemainingSize(int lineLength){
		this.lineLength = lineLength;
	}
	/**
	 * Set the time the customer question was answered
	 * @param answerTime - the time the customer question was answered
	 */
	protected void setAnswerTime(double answerTime){
		this.answerTime = answerTime;
	}
	/**
	 * Set the time the customer question was answered
	 * @param answerTime - the time the customer question was answered
	 */
	protected double getAnswerTime(){
		return answerTime;
	}
	/**
	 * Set the time left in order for the question to be answered
	 * @param remainingTime - the time left to answer the question
	 */
	protected void setRemaining(double remainingTime){
		this.remainingTime = remainingTime;
	}
	/**
	 * Get's the remaining time for the customer's question to be answered
	 * @return - the time left for the customer to have their question answered
	 */
	protected double getRemaining(){
		return remainingTime;
	}
	/**
	 * Returns the start time nicely formatted
	 * @return - the start time 
	 */
	protected String getStartTimeF(){
		return timeConversion((int)startTime);
	}
	
	/**
	 * Returns the answer time nicely formatted
	 * @return - the answer time
	 */
	protected String getAnswerTimeF(){
		return timeConversion((int)answerTime);
	}
	/**
	 * Formats all the information of the customer so it can be easily obtained and used 
	 */
	public String toString(){
		String theString;
		String startTimeF = timeConversion((int)startTime);
		String answerTimeF = timeConversion((int)answerTime);
		theString = (name + ": " + type + ":" + "Start-Time - " + startTimeF + "Answer-time - " + answerTimeF + "Her question was... " + phrase + " The Secretary responded with: " + answer + 
				". The line was " + lineLength + " long after the customer left.");
		return theString;
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

	    return hours + ":" + minutes + ":" + seconds + ":";
	}
}