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
	private String customerType;
	private double pTime;
	private double qTime;
	private double dTime;
	
	//LinkedList<Customer> customersWaiting = new LinkedList<Customer>(); // LinkedList of customers in line
	PriorityQueue<Customer> customersWaiting = new PriorityQueue<Customer>();
	//LinkedList<Customer> customersComplete = new LinkedList<Customer>(); // LinkedList of customers complete
	PriorityQueue<Customer> customersComplete = new PriorityQueue<Customer>();
	//LinkedList<Event> eventList = new LinkedList<Event>(); // LinkedList of event objects
	PriorityQueue<Event> eventList = new PriorityQueue<Event>();
	
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
	public Time(double endTime, double pTime, double qTime, double dTime){
		
		this.endTime = (endTime * 60); // convert minutes to seconds
		this.qTime = qTime;
		this.pTime = pTime;
		this.dTime = dTime;
		currentTimeSim();	
	}
	
	/**
	 * main driver of the time keeping class. 
	 */
	private void currentTimeSim(){
		int total = 0;

		System.out.println("Start");
		
		customerCallTime = getPhoneCallPoisson();
		customerDoorTime = getDoorArrivalPoisson();
		questionTime = Double.MAX_VALUE;
		currentTime = 0;		// 0 seconds
		Event event;
		
		System.out.println("Current time is " + currentTime + ", and end time is "+ endTime);
		
		while(currentTime < endTime){
			System.out.println("loop " + currentTime);
			System.out.println(customersWaiting.size());
			Customer customer = null;
			
			getNextEvent(); // get next customer type
			
			if(customerType == "Door Customer"){
				
				System.out.println("Door customer");
				
				currentTime = customerDoorTime;
				questionTime = getGuestionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, questionTime, 2, customersWaiting.size());
				total++;
				////////////////////customersWaiting.add(newCustomer);
				customersWaiting.enqueue(newCustomer, total+2);
				event = new Event(" came through the door", newCustomer.getName(), currentTime, customersWaiting.size());
				////////////////////eventList.add(event);
				eventList.enqueue(event, 1);
				
				if(customer == null){
					if(customersWaiting.size() == 0){
						continue;
					}
					/////////customer = customersWaiting.remove(0);
					customer = (Customer) customersWaiting.dequeue();
					questionTime = customer.getRemaining();
					System.out.println("customer");
				}
				customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
				
				//This loop is if a customer is scheduled to walk through the door while a customer door customer question is being answered. 
				if((currentTime + questionTime) > customerDoorTime){
					currentTime = customerDoorTime;
					double tempQuestionTime = getGuestionTimePoisson();
					Customer tempCustomer  = new Customer(customerType, currentTime, tempQuestionTime, tempQuestionTime, 2, customersWaiting.size());
					total++;
					//customersWaiting.add(tempCustomer);
					customersWaiting.enqueue(tempCustomer, total+2);
					event = new Event(" came through the door", tempCustomer.getName(), currentTime, customersWaiting.size());
					/////////////eventList.add(event);
					eventList.enqueue(event, total);
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					
				}
				
				//This loop is if a customer is scheduled to call while a door customer question is being answered. 
				if((currentTime + questionTime) > customerCallTime){
					
					double remainingQuestionTime = ((currentTime + questionTime) - customerCallTime); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					System.out.println("Door customer interrupted");
					///////////////customersWaiting.add(customer);
					customersWaiting.enqueue(customer, 1);
					continue;
					
				}
				
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				customer.setLineRemainingSize(customersWaiting.size());
				//////////////customersComplete.add(customer); // add customer to list of completed customers

				customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
				System.out.println("Door Customer complete");
				System.out.println(customer);
				/////////////customersComplete.add(customer); // add customer to list of completed customers
				customersComplete.enqueue(customer, total);
				event = new Event("'s question has been answered", customer.getName(), customer.getAnswerTime(), customersWaiting.size());
				////////////eventList.add(event);
				eventList.enqueue(event, 1);
				
			}
			
			
			if(customerType == "Phone Customer"){
				
				currentTime = customerCallTime;
				questionTime = getGuestionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, questionTime, 1, customersWaiting.size());
				total++;
				
				/////////customersWaiting.add(0, newCustomer);
				customersWaiting.enqueue(newCustomer, 1);
				event = new Event(" called", newCustomer.getName(), currentTime, customersWaiting.size());
				//////////eventList.add(event);
				eventList.enqueue(event, 1);
				/////////customer = customersWaiting.remove(0);
				customer = (Customer) customersWaiting.dequeue();
				System.out.println("Phone Customer");
				
				customerCallTime = customerCallTime + getPhoneCallPoisson();
				
				//This loop is if a customer is scheduled to walk through the door while a customer door customer question is being answered. 
				if((currentTime + questionTime) > customerDoorTime){
					
					double tempQuestionTime = getGuestionTimePoisson();
					Customer tempCustomer  = new Customer(customerType, customerDoorTime, tempQuestionTime, tempQuestionTime, 2, customersWaiting.size());
					total++;
					///////////customersWaiting.add(tempCustomer);
					customersWaiting.enqueue(tempCustomer, total + 2);
					event = new Event(" came through the door", tempCustomer.getName(), currentTime, customersWaiting.size());
					///////////eventList.add(event);
					eventList.enqueue(event, 1);
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					
				}
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					
					double remainingQuestionTime = ((currentTime + questionTime) - customerCallTime); //get remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime); //get question time - remaining question time
					customer.setRemaining(remainingQuestionTime); // set customer question time as remaining question time
					System.out.println("Phone Customer interrupted");
					/////////customersWaiting.add(0, customer);
					customersWaiting.enqueue(customer, 1);
					continue;
					
				}
				
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				customer.setLineRemainingSize(customersWaiting.size());
				//customersComplete.add(customer); // add customer to list of completed customers

				customerCallTime = customerCallTime + getPhoneCallPoisson();
				System.out.println("Phone Customer complete");
				System.out.println(customer);
				///////////customersComplete.add(customer); // add customer to list of completed customers
				customersComplete.enqueue(customer, total);
				event = new Event("'s question has been answered", customer.getName(), customer.getAnswerTime(), customersWaiting.size());
				//////////eventList.add(event);
				eventList.enqueue(event, 1);
			}
			
		}
		/*
		//sort list
		for(int i = 0; i<customersComplete.size()-1; i++){			
			Customer curr = customersComplete.get(i);
			Customer next = customersComplete.get(i+1);
			if(curr.getAnswerTime() > next.getAnswerTime()){
				customersComplete.add(i, next);
				customersComplete.add(i+1, curr);
				break;
			}
			
		}
		*/
		System.out.println(customersComplete.size());
		System.out.println(customersWaiting.size());
		System.out.println(total);
		
		/*
		int count = 0;
		for(int i = 0; i< customersComplete.size(); i++){
			System.out.println(customersComplete.get(i));
			count++;
			System.out.println(count);
		}
		*/
	 
	}
	
	
	/**
	 * Returns the next scheduled event
	 */
	private void getNextEvent(){
		
		if(customerDoorTime < customerCallTime){
			customerType = "Door Customer";
			return;
		}
		customerType = "Phone Customer";
	}

	/**
	 * @return random number with a mean of pTime for phone call customers
	 */
	private double getPhoneCallPoisson() {

		double p,U;
		U = Math.random();
		p = pTime *  -Math.log(U);
		return p;

	}

	/**
	 * @return random number with a mean of qTime for customer questions
	 */
	private double getGuestionTimePoisson() {

		double p,U;
		U = Math.random();
		p = qTime *  -Math.log(U);
		return p;

	}

	/**
	 * @return random number with a mean of dTime for door arrival customers
	 */
	private double getDoorArrivalPoisson() {

		double p,U;
		U = Math.random();
		p = dTime *  -Math.log(U);
		return p;

	}
	

	/**
	 * 
	 * @return LinkedList of customers waiting in line
	 */
	protected PriorityQueue<Customer> getCustomersRemaining() {
	
		return customersWaiting;
	}
	

	/**
	 * 
	 * @return LinkedList of customers with questions answered
	 */
	protected PriorityQueue<Customer> getCustomersComplete(){
		
		return customersComplete;
	}
	
	/**
	 * 
	 * @return LinkedList of events
	 */
	protected PriorityQueue<Event> getEventList(){
		
		return eventList;
	}
	
}
