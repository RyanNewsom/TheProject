//Goodluck Kris!!!!
public class Time {
	// Using the discreet method talked about in class for now
	private double startTime;
	private double endTime;
	private double arriveTime;
	private double questionTime;
	private double callTime;

	private void startTimeSim{

		// 0 seconds
		startTime = 0;
		// 3000 seconds in 50 minutes
		endTime = 3000;

	}
	
	private int getTime(){
		
		//time placeholder
		return 5;
	}

	// random number for phone call;
	private double phoneCallPoisson() {

		double p,U;
		U = Math.random();
		p = 55.0 *  -Math.log(U);
		return p;

	}

	// random number for question length;
	private double questionTimePoisson() {

		double p,U;
		U = Math.random();
		p = 24.0 *  -Math.log(U);
		return p;

	}

	// random number for door arrival;
	private double doorArrivalPoisson() {

		double p,U;
		U = Math.random();
		p = 45.0 *  -Math.log(U);
		return p;

	}

}
