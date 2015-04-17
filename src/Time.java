/**
 * 
 * This is the class that keeps track of the overall simulation time and when events occur. When a customer comes in or calls, I create a new
 * customer object with its current time, question time, and then place it either at the head or the tail of our linked
 * list depending on if it was a caller or walk in customer.
 * 
 * TheProject!!!
 * CS2
 * April 16th, 2015
 * 
 * @author Kris West
 *
 */

public class Time {

	private double currentTime;
	private double endTime;
	private double customerDoorTime;
	private double questionTime;
	private double customerCallTime;
	
	/**
	 * main driver of the time keeping class. 
	 */
	private void currentTimeSim(){
		
		customerCallTime = 0;
		customerDoorTime = 0;
		questionTime = Double.MAX_VALUE;
		currentTime = 0;		// 0 seconds
		endTime = 3000;			// 3000 seconds in 50 minutes
		
		while(currentTime <= 3000){
			
			customerDoorTime = customerDoorTime + doorArrivalPoisson();
			customerCallTime = currentTime + phoneCallPoisson();
			
			//Add the full question duration to the current if there are no calls scheduled to interrupt
			if(currentTime + questionTime < customerCallTime){
				
				currentTime = currentTime + questionTime;
				
				// grab the next customer in out linked list
				
				/*---------------------------------------------------------------------------------------------------------------
				Node customer = head.getNext();
				
				if(customer != null){
					questionTime = customer.getQuestionTime();
				}
				---------------------------------------------------------------------------------------------------------------*/

			}
			
			if(customerDoorTime < customerCallTime){
				
				questionTime = questionTimePoisson();
				
				// Node constructor taking the parameters (int timeEnteredDoor, double questionTime)
				
				/*---------------------------------------------------------------------------------------------------------------
				Node customer = new Node(currentTime, questionTime);
				---------------------------------------------------------------------------------------------------------------*/
				
				// Method in Linked list class to add customer object to END of the line;
				
				/*---------------------------------------------------------------------------------------------------------------
				customer.setTail(); 
				---------------------------------------------------------------------------------------------------------------*/
				
				//add customerDoorTime to currentTime
				currentTime = currentTime + customerDoorTime;
				
				//add current customer door time with a new random, to get time next customer walks through the door.
				customerDoorTime = customerDoorTime + doorArrivalPoisson();
				
				//go to top of the loop
				continue;
				
			}
			
			if(customerCallTime <= customerDoorTime){
				
				questionTime = questionTimePoisson();
				
				// Node construstor taking the parameters (int timeEnteredDoor, double questionTime)
				
				/*---------------------------------------------------------------------------------------------------------------
				Node customer = new Node(currentTime, questionTime);
				---------------------------------------------------------------------------------------------------------------*/
				
				// Method in Linked list class to add customer object to FRONT of the line;
				
				/*---------------------------------------------------------------------------------------------------------------
				customer.setHead(); 
				---------------------------------------------------------------------------------------------------------------*/
				
				//add customerDoorTime to currentTime
				currentTime = currentTime + customerCallTime;
				
				//add current customer door time with a new random, to get time next customer walks through the door.
				customerCallTime = customerCallTime + doorArrivalPoisson();
				
				//go to top of the loop
				continue;
				
			}
			
		}
		
	}
	
	private double nextEventTime(){
		
		
		return customerCallTime;
	}
	
	/**
	 * @return double of how many seconds have elapsed
	 */
	private double getTime(){
		
		return currentTime;
		
	}

	/**
	 * @return random number with a mean of 55 for phone call customers
	 */
	private double phoneCallPoisson() {

		double p,U;
		U = Math.random();
		p = 55.0 *  -Math.log(U);
		return p;

	}

	/**
	 * @return random number with a mean of 24 for customer questions
	 */
	private double questionTimePoisson() {

		double p,U;
		U = Math.random();
		p = 24.0 *  -Math.log(U);
		return p;

	}

	/**
	 * @return random number with a mean of 45 for door arrival customers
	 */
	private double doorArrivalPoisson() {

		double p,U;
		U = Math.random();
		p = 45.0 *  -Math.log(U);
		return p;

	}
	
}
