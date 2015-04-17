import java.util.Random;

/**
 * This class represents the customer. The customer knows many things about himself
 * and will be either a phone-caller or walk-in. He will get his question from the message class
 * and the secretary will respond with a relative answer.
 * @author 
 *
 */
public class Customer {
	/** Name of the customer */
	String name;
	/** Phone-call or walk-in */
	String type;
	/** The time the customer called or walked in */
	String startTime;
	/** The time their question was answered */
	String answerTime;
	/** The customers question */
	String phrase;
	/** The answer the customer was given */
	String answer;
	/** The customers priority */
	int priority; 
	/** The length of the line after the customer is done with their question*/
	int lineLength;
	
	/**
	 * It's important the customer knows everything about them-self. The question they ask will be randomly generated. 
	 * @param name - the name of the customer
	 * @param type - phone-call or walk-in
	 * @param sTime - start time formatted as such 00:00:00 i.e. (H:M:S)
	 * @param answerTime - answer time formatted as such 00:00:00 i.e. (H:M:S)
	 * @param prior - priority of the customer
	 * @param lineLength - After the customer has their question answered and leaves i.e. don't include this customer in the count
	 */
	public Customer(String name, String type, String sTime, String answerTime, int prior, int lineLength){
		Random rand = new Random();// Will be used to generate a random integer
		int question = rand.nextInt(14);
		this.name = name;
		this.type = type;
		this.startTime = startTime;
		this.answerTime = answerTime;
		phrase = Message.getQuestion(question);// Generates a random int which will allow for a random question to be returned
		answer = Message.getAnswer(question);// Generates the appropriate answer for the question
		this.priority = prior;
		this.lineLength = lineLength;
	}
}

