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
	 * Constructor that takes an integer for the simulation endTime
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
		int total = 0;

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
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, (int) questionTime, 2, customersWaiting.size());
				total++;
				customersWaiting.add(newCustomer);
				if(customer == null){
					if(customersWaiting.size() == 0){
						continue;
					}
					customer = customersWaiting.remove();
					System.out.println("customer");
				}
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					int remainingQuestionTime = (int)((currentTime + questionTime) - customerCallTime); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					customerDoorTime = customerDoorTime + doorArrivalPoisson();
					
					System.out.println("Door customer interrupted");
					customersWaiting.add(customer);
					customer = null;
					continue;
				}
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				//customersComplete.add(customer); // add customer to list of completed customers

				customerDoorTime = customerDoorTime + doorArrivalPoisson();
				System.out.println("Door Customer complete");
				System.out.println(customer);
				customersComplete.add(customer); // add customer to list of completed customers
				
			}
			
			
			if(customerType == "Phone Customer"){
				
				currentTime = customerCallTime;
				customerCallTime = customerCallTime + phoneCallPoisson();
				questionTime = questionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, (int) questionTime, 1, customersWaiting.size());
				total++;
				customer = newCustomer;
				
				System.out.println("Phone Customer");
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					int remainingQuestionTime = (int)((currentTime + questionTime) - customerCallTime); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					customerCallTime = customerCallTime + phoneCallPoisson();
					
					System.out.println("Phone Customer interrupted");
					customersWaiting.add(0, customer);
					customer = null;
					continue;
				}
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				//customersComplete.add(customer); // add customer to list of completed customers

				customerCallTime = customerCallTime + phoneCallPoisson();
				System.out.println("Phone Customer complete");
				System.out.println(customer);
				customersComplete.add(customer); // add customer to list of completed customers
				
			}
			
		}
		
		/**
		for(int i = 0; i<customersComplete.size()-1; i++){
			Customer curr = customersComplete.get(i);
			Customer next = customersComplete.get(i+1);
				if(curr.getAnswerTime() > next.getAnswerTime()){
					customersComplete.add(i, next);
					customersComplete.add(i+1, curr);
				}
		}
		
		*/
		
		System.out.println(customersComplete.size());
		System.out.println(customersWaiting.size());
		System.out.println(total);
		
		int count = 0;
		for(int i = 0; i< customersComplete.size(); i++){
			System.out.println(customersComplete.get(i));
			count++;
			System.out.println(count);
		}

	}
	
	/**
	 * Returns the next scheduled event
	 */
	private void nextEvent(){
		
		if(customerDoorTime < customerCallTime){
			customerType = "Door Customer";
			return;
		}
		customerType = "Phone Customer";
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
	

	/**
	 * 
	 * @return LinkedList of customers waiting in line
	 */
	protected LinkedList<Customer> customersRemaining() {
	
		return customersWaiting;
		
	}
	

	/**
	 * 
	 * @return LinkedList of customers with questions answered
	 */
	protected LinkedList<Customer> customersComplete(){
		
		return customersComplete;
	}
	
}
