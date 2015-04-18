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
	private double endTime = 0;
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
	public Time(double endTime){
		
		this.endTime = (endTime * 60); // convert minutes to seconds
		currentTimeSim();	
	}
	
	/**
	 * main driver of the time keeping class. 
	 */
	private void currentTimeSim(){

		System.out.println("Start");
		
		customerCallTime = phoneCallPoisson();
		customerDoorTime = doorArrivalPoisson();
		questionTime = Double.MAX_VALUE;
		currentTime = 0;		// 0 seconds
		
		System.out.println("Current time is " + currentTime + ", and end time is "+ endTime);
		
		while(currentTime < endTime){
			System.out.println("loop " + currentTime);
			System.out.println(customersWaiting.size());
			Customer customer = null;
			nextEvent(); // get next customer type
			
			if(customerType == "Door Customer"){
				
				System.out.println("Door customer");
				
				currentTime = customerDoorTime;
				questionTime = questionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, (int) questionTime, 10, 10);
				customersWaiting.add(newCustomer);
				if(customer == null){
					customer = customersWaiting.remove();
					System.out.println("customer");
				}
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					int remainingQuestionTime = (int)(customerCallTime - (currentTime + questionTime)); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					customerDoorTime = customerDoorTime + doorArrivalPoisson();
					
					System.out.println("Door customer interrupted");
					
					continue;
				}
				customersComplete.add(customer); // add customer to list of completed customers
				customer = customersWaiting.remove(0);
				customerDoorTime = customerDoorTime + doorArrivalPoisson();
				
				System.out.println("Door Customer complete");
				
			}
			
			
			if(customerType == "Phone Customer"){
				
				currentTime = customerCallTime;
				questionTime = questionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, (int) questionTime, 10, 10);
				customersWaiting.add(0, newCustomer);
				customer = newCustomer;
				
				System.out.println("Phone Customer");
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					int remainingQuestionTime = (int)(customerCallTime - (currentTime + questionTime)); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					customerCallTime = customerCallTime + phoneCallPoisson();
					
					System.out.println("Phone Customer interrupted");
					
					continue;
				}
				customersComplete.add(customer); // add customer to list of completed customers
				customer = customersWaiting.remove(0);
				customerCallTime = customerCallTime + phoneCallPoisson();
				System.out.println("Phone Customer complete");
				
			}
			
		}
		
	}
	
	private void nextEvent(){
		
		if(customerDoorTime < customerCallTime){
			customerType = "Door Customer";
		}
		customerType = "Phone Customer";
	}

	/**
	 * @return random number with a mean of 55 for phone call customers
	 */
	private double phoneCallPoisson() {

		double p,U;
		U = Math.random();
		p = 155.0 *  -Math.log(U);
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
	

	/**
	 * 
	 * @return LinkedList of customers waiting in line
	 */
	protected LinkedList customersRemaining() {
	
		return customersWaiting;
		
	}
	

	/**
	 * 
	 * @return LinkedList of customers with questions answered
	 */
	protected LinkedList customersComplete(){
		
		return customersComplete;
	}
	
}
