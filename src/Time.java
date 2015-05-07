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

	private double currentTime = 0;					//Initial end time simulation value in seconds.
	private double endTime = 0;						//Initial end time simulation value in seconds.
	private double customerDoorTime = 0;			//initial customer door time value in seconds
	private double questionTime = Double.MAX_VALUE; //initial question time value in seconds
	private double customerCallTime = 0;			//initial customer call time value in seconds
	private String customerType;					//string of the customer type
	private double pTime; 							//mean phone customer time
	private double qTime;							//mean question time
	private double dTime;							//mean door customer time
	
	//PriorityQueues of customers waiting, customers completed, and events created
	PriorityQueue<Customer> customersWaiting = new PriorityQueue<Customer>();
	PriorityQueue<Customer> customersComplete = new PriorityQueue<Customer>();
	PriorityQueue<Event> eventList = new PriorityQueue<Event>();
	
	/**
	 * Blank constructor for time class
	 */
	public Time(){
		
	}
	
	/**
	 * Main constructor for the time class. takes an end time in minutes, mean door customer
	 * phone customer, and question time, and starts the office simulation.
	 * 
	 * @param endTime - double of end time simulation in minutes
	 * @param pTime - double for calculating mean phone call time
	 * @param qTime - double for calculating mean question time
	 * @param dTime - double for calculating mean door time
	 */
	public Time(double endTime, double pTime, double qTime, double dTime){
		
		 // convert minutes to seconds
		this.endTime = (endTime * 60);
		this.qTime = qTime;
		this.pTime = pTime;
		this.dTime = dTime;
		currentTimeSim();	
	}
	
	/**
	 * main driver of the time keeping class. 
	 */
	private void currentTimeSim(){
		
		int total = 0;									//counts total customer amount
		int doorTotal = 0;								//counts door customer amount
		int phoneTotal = 0;								//counts phone customer amount
		customerCallTime = getPhoneCallPoisson();		//generate first customer phone time
		customerDoorTime = getDoorArrivalPoisson();		//generate first customer door time
		questionTime = Double.MAX_VALUE;				//question time 
		currentTime = 0;								//starts at 0 seconds
		Event event;									//create event object
				
		//loop while currenttime is less than endtime
		while(currentTime < endTime){

			//customer object
			Customer customer = null;
			
			// get next customer type
			getNextEvent();
			
			// loop if the next event is a door customer
			if(customerType == "Door Customer"){
								
				//generate customer door time and question time
				currentTime = customerDoorTime;
				questionTime = getGuestionTimePoisson();
				
				//create a new door customer and add it to the back of the line
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, questionTime, 2, customersWaiting.size());
				total++;
				doorTotal++;
				customersWaiting.enqueue(newCustomer, total+2);
				
				//create and add new event to event list
				event = new Event(" came through the door", newCustomer.getName(), currentTime, customersWaiting.size());
				eventList.enqueue(event, 1);
				
				
				//check for the case if customer or the line is null. If not, grab first customer in line
				if(customer == null){
					if(customersWaiting.size() == 0){
						continue;
					}
					customer = (Customer) customersWaiting.dequeue();
					questionTime = customer.getRemaining();
				}
				
				//generate new custome4 door time
				customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
				
				// check if current time + question time time is higher than endtime sim. Break out if so.
				if((currentTime + questionTime) > endTime){
					customersWaiting.enqueue(customer, 1);
					currentTime = endTime;
					continue;
				}
				
				//This loop is if a customer is scheduled to walk through the door while a customer door customer question is being answered. 
				if((currentTime + questionTime) > customerDoorTime){
					
					// check if door time is higher than endtime sim. Break out if so.
					if(customerDoorTime > endTime){
						currentTime = endTime;
						continue;
					}
					
					//make new question time and door customer object, add to end of queue
					double tempQuestionTime = getGuestionTimePoisson();
					Customer tempCustomer  = new Customer(customerType, currentTime, tempQuestionTime, tempQuestionTime, 2, customersWaiting.size());
					total++;
					doorTotal++;
					customersWaiting.enqueue(tempCustomer, total+2);
					
					//create and add new event to event list
					event = new Event(" came through the door", tempCustomer.getName(), currentTime, customersWaiting.size());
					eventList.enqueue(event, total);
					
					//generate new door time
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					
				}
				
				//This loop is if a customer is scheduled to call while a door customer question is being answered. 
				if((currentTime + questionTime) > customerCallTime){
					
					//get remaining question time
					double remainingQuestionTime = ((currentTime + questionTime) - customerCallTime);
					
					//get question time - remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime);
					
					// set customer question time as remaining question time
					customer.setRemaining(remainingQuestionTime);
					
					//generate new customer door time
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					
					//add current customer to the front of the line
					customersWaiting.enqueue(customer, 1);
					continue;
					
				}
				
				//set current time to current time + remaining question time of current customer
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				customer.setLineRemainingSize(customersWaiting.size());

				// generate new customer door time
				customerDoorTime = customerDoorTime + getDoorArrivalPoisson();

				//add completed customer to customerComplete list
				customersComplete.enqueue(customer, total);
				
				//create and add new event to event list
				event = new Event("'s question has been answered", customer.getName(), customer.getAnswerTime(), customersWaiting.size());
				eventList.enqueue(event, 1);
				
			}
			
			// loop if the next event is a phone customer
			if(customerType == "Phone Customer"){
				
				// update current time, generate new question time and customer, add to queue
				currentTime = customerCallTime;
				questionTime = getGuestionTimePoisson();
				Customer newCustomer  = new Customer(customerType, currentTime, questionTime, questionTime, 1, customersWaiting.size());	
				total++;
				phoneTotal++;
				customersWaiting.enqueue(newCustomer, 1);
				
				//create and add new event to event list
				event = new Event(" called", newCustomer.getName(), currentTime, customersWaiting.size());
				eventList.enqueue(event, 1);

				//dequeue highest priority customer, which is the person who just called
				customer = (Customer) customersWaiting.dequeue();
				
				//generate new call time
				customerCallTime = customerCallTime + getPhoneCallPoisson();
				
				// check if current time + question time time is higher than endtime sim. Break out if so.
				if((currentTime + questionTime) > endTime){
					customersWaiting.enqueue(customer, 1);
					currentTime = endTime;
					continue;
				}
				
				//This loop is if a customer is scheduled to walk through the door while a customer door customer question is being answered. 
				if((currentTime + questionTime) > customerDoorTime){
					
					// check if door time is higher than endtime sim. Break out if so.
					if(customerDoorTime > endTime){
						currentTime = endTime;
						continue;
					}
					
					//make new question time and door customer object, add to end of queue
					double tempQuestionTime = getGuestionTimePoisson();
					Customer tempCustomer  = new Customer(customerType, customerDoorTime, tempQuestionTime, tempQuestionTime, 2, customersWaiting.size());
					total++;
					doorTotal++;
					customersWaiting.enqueue(tempCustomer, total + 2);
					
					//create and add new event to event list
					event = new Event(" came through the door", tempCustomer.getName(), currentTime, customersWaiting.size());
					eventList.enqueue(event, 1);
					
					//generate new customer door time
					customerDoorTime = customerDoorTime + getDoorArrivalPoisson();
					
				}
				
				//This loop is if a customer is scheduled to call while a customer is in line. 
				if((currentTime + questionTime) > customerCallTime){
					
					//get remaining question time
					double remainingQuestionTime = ((currentTime + questionTime) - customerCallTime);
					
					//get question time - remaining question time
					currentTime = currentTime + (questionTime-remainingQuestionTime);
					
					// set customer question time as remaining question time
					customer.setRemaining(remainingQuestionTime);
					
					// add this customer to front of line, then go back to while loop
					customersWaiting.enqueue(customer, 1);
					continue;
					
				}
				
				//set current time to current time + remaining question time of current customer
				currentTime = currentTime + customer.getRemaining();
				customer.setAnswerTime(currentTime);
				customer.setLineRemainingSize(customersWaiting.size());

				//generate new customer call time
				customerCallTime = customerCallTime + getPhoneCallPoisson();
				
				//add completed customer to customerComplete list
				customersComplete.enqueue(customer, total);
				
				//create and add new event to event list
				event = new Event("'s question has been answered", customer.getName(), customer.getAnswerTime(), customersWaiting.size());
				eventList.enqueue(event, 1);
				
			}
			
			//check to break out of loop
			if(customerDoorTime  > endTime || customerCallTime  > endTime){
				currentTime = endTime;
			}
			
		}

		//used for testing
		System.out.println("Customers helped is " + customersComplete.size());
		System.out.println("Customers still ine line is " + customersWaiting.size());
		System.out.println("Total customers is " + total);
		System.out.println("Door customer total is " + doorTotal);
		System.out.println("Phone customer total is " + phoneTotal);
	 
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
	 * @return PriorityQueue of customers waiting in line
	 */
	protected PriorityQueue<Customer> getCustomersRemaining() {
	
		return customersWaiting;
	}
	

	/**
	 * 
	 * @return PriorityQueue of customers with questions answered
	 */
	protected PriorityQueue<Customer> getCustomersComplete(){
		
		return customersComplete;
	}
	
	/**
	 * 
	 * @return PriorityQueue of events
	 */
	protected PriorityQueue<Event> getEventList(){
		
		return eventList;
	}
	
}
