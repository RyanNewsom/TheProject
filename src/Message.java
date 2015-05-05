/**
 * This class will contain possible questions and answers for the customer and secretary as well as randomly generated names
 * @author Ryan Newsom
 * @version 2
 */
class Message {
	/**
	 * Generates a message for the customer to ask.
	 * @param theQuestion - this should be a randomly generated int from 0-14 on YOUR end.
	 * @return - The question the customer has
	 */
	protected static String getQuestion(int theQuestion){
		String question = "your ? index is out of bounds";
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
	/**
	 * Will return the corresponding answer with the appropriate question for the customer
	 * @param theAnswer - the int that matches the randomly generate question
	 * @return - the correct answer that goes with a certain question
	 */
		protected static String getAnswer(int theAnswer){
			String answer = "Your answer index is out of bounds";
			switch(theAnswer){
				case 0 :answer = "Well... that depends on many things";
						break;
				case 1:	answer = "We are located at 12345 swift circle?";
						break;
				case 2:	answer = "I like teal or any shade of blue";
						break;
				case 3: answer = "Get the android turbo, you won't be dissapointed";
						break;
				case 4: answer = "$400. It's overpriced, I know.";
						break;
				case 5: answer = "Black, silver, pink, green, red, we have it all";
						break;
				case 6: answer = "Well, android is more customizable, but apple is trendy.";
						break;
				case 7: answer = "Only the best, a Droid Turbo.";
						break;
				case 8: answer = "...(crickets)";
						break;
				case 9: answer = "Too damn long... I mean 6";
						break;
				case 10:answer = "I'm pretty sure that's you";
						break;
				case 11:answer = "Anything not made by Apple";
						break;
				case 12:answer = "I love to golf, paintball is fun too";
						break;
				case 13:answer = "Five guys";
						break;
				case 14:answer = "To support small business... merica";
						break;
				}
			return answer;
		}
		/**
		 * Picks a customer name between int range 0-36 based off what is passed and returns it
		 * @param randomInt - a randomly generated int which will pick a name
		 * @return - a randomly generated name
		 */
		protected static String getName(int randomInt){
			String name = "Your name index is out of bounds";
			switch(randomInt){
				case 0 :name = "Samantha";
						break;
				case 1:	name = "Cathleen";
						break;
				case 2:	name = "Riwanda";
						break;
				case 3: name = "Lafonda";
						break;
				case 4: name = "Sarah";
						break;
				case 5: name = "Brittany";
						break;
				case 6: name = "Anastasia";
						break;
				case 7: name = "Piper";
						break;
				case 8: name = "Candice";
						break;
				case 9: name = "Shaniqua";
						break;
				case 10:name = "Candy";
						break;
				case 11:name = "Aurora";
						break;
				case 12:name = "Lexus";
						break;
				case 13:name = "Tammy";
						break;
				case 14:name = "Veronica";
						break;
				case 15:name = "Brittany";
						break;
				case 16:name = "Elizabeth";
						break;
				case 17:name = "Karen";
						break;
				case 18:name = "Amanda";
						break;
				case 19:name = "Strawberries";
						break;
				case 20:name = "Mackenzie";
						break;
				case 21:name = "Helen";
						break;
				case 22:name = "Martha";
						break;
				case 23:name = "Trish";
						break;
				case 24:name = "Justin Bieber";
						break;
				case 25:name = "Tapioca";
						break;
				case 26:name = "Paris";
						break;
				case 27:name = "Bruce";
						break;
				case 28:name = "Latifah";
						break;
				case 29:name = "Bonifa";
						break;
				case 30:name = "Sha'Nay Nay";
						break;
				case 31:name = "Obamaniqua";
						break;
				case 32:name = "Dixie";
						break;
				case 33:name = "Brownie";
						break;
				case 34:name = "Bentley";
						break;
				case 35:name = "Porsche";
						break;
				case 36:name = "Mufisa";
						break;
				}
			return name;
		}
}