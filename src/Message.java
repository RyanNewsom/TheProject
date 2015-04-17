/**
 * This class will contain possible questions and answers for the customer and secretary
 * @author Ryan Newsom
 * @version 2
 */
public class Message {
	/**
	 * Generate a message for the customer to ask.
	 * @param theQuestion - this should be a randomly generated int from 0-14 on YOUR end.
	 * @return - The question the customer has
	 */
	public static String getQuestion(int theQuestion){
		String question = "your index is out of bounds";
		switch(theQuestion){
			case 0 :question = "Blondes or burnettes?";
					break;
			case 1:	question = "What is your address?";
					break;
			case 2:	question = "What is your favorite color?";
					break;
			case 3: question = "What's your best product?";
					break;
			case 4: question = "How much does the IPhone cost?";
					break;
			case 5: question = "What different color's are available?";
					break;
			case 6: question = "Iphone or android?";
					break;
			case 7: question = "What kind of phone do you have?";
					break;
			case 8: question = "Want to get dinner later?";
					break;
			case 9: question = "What time are you open until?";
					break;
			case 10:question = "Why do you smell funny?";
					break;
			case 11:question = "What's the best phone?";
					break;
			case 12:question = "What's your favorite hobby?";
					break;
			case 13:question = "What resteaurants are good around here";
					break;
			case 14:question = "Why should I buy from you";
					break;
		}
		return question;
	}
}

