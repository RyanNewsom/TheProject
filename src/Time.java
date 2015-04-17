import java.util.LinkedList;

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

	private double currentTime = 0;
	private double endTime;
	private double customerDoorTime = 0;
	private double questionTime = Double.MAX_VALUE;
	private double customerCallTime = 0;
	String customerType;
	
	LinkedList<Customer> customersWaiting = new LinkedList<Customer>(); // LinkedList of customers in line
	LinkedList<Customer> customersComplete = new LinkedList<Customer>(); // LinkedList of customers complete
	
	/**
	 * Blank constructor for time class
	 */
	public Time(){
		
	}
	
	/**
	 * Constructor that takes an int for the simulation endTime
	 * 
	 * @param endTime - Length of the simulation time in minutes, converts it to seconds in the constructor
	 */
	public Time(int endTime){
		
		this.endTime = endTime * 60; // convert minutes to seconds
		Time time = new Time();
		time.currentTimeSim();
		
	}
	
	/**
	 * main driver of the time keeping class. 
	 */
	private void currentTimeSim(){
		
		customerCallTime = phoneCallPoisson();
		customerDoorTime = doorArrivalPoisson();
		questionTime = Double.MAX_VALUE;
		currentTime = 0;		// 0 seconds
		
		while(currentTime < endTime){
			
			nextEvent(); // get next customer type
			
			if(customerType == "Door Customer"){
				
				currentTime = currentTime + customerDoorTime;
				questionTime = questionTimePoisson();
				Customer customer  = new Customer(customerType, currentTime, questionTime, 10, 10);
				customersWaiting.add(customer);
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					Double remainingQuestionTime = (customerCallTime - (currentTime + questionTime)); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setQuestionTime(remainingQuestionTime); // set customer question time as remaning question time
					customerDoorTime = customerDoorTime + doorArrivalPoisson();
					continue;
				}
				customer = customersWaiting.remove(0);
				customersComplete.add(customer); // add customer to list of completed customers
				customerDoorTime = customerDoorTime + doorArrivalPoisson();
				
			}
			
			
			if(customerType == "Phone Customer"){
				
				currentTime = currentTime + customerCallTime;
				questionTime = questionTimePoisson();
				Customer customer  = new Customer(customerType, currentTime, questionTime, 10, 10);
				customersWaiting.add(customer);
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					Double remainingQuestionTime = (customerCallTime - (currentTime + questionTime)); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setQuestionTime(remainingQuestionTime); // set customer question time as remaning question time
					customerCallTime = customerCallTime + phoneCallPoisson();
					continue;
				}
				customer = customersWaiting.remove(0);
				customersComplete.add(customer); // add customer to list of completed customers
				customerCallTime = customerCallTime + phoneCallPoisson();
				
			}
			
		}
		
	}
	
	private double nextEvent(){
		
		if(customerDoorTime < customerCallTime){
			customerType = "Door Customer";
			return customerDoorTime;
			
		}
		customerType = "Phone Customer";
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
	
	private LinkedList customersRemaining() {
	
		return customersWaiting;
		
	}
	
	private LinkedList customersComplete(){
		
		return customersComplete;
	}
	
}
