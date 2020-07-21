package kreuzung;

/*
 * car that can pass an intersection
 * containing two time steps (arrival and passing time)
 */
public class Car {
	private long arrivalTime;
	private long passingTime;
	private String name;
	
	/*
	 * constructor
	 */
	public Car(long arrivalTime, String name) {
		this.arrivalTime = arrivalTime;
		this.passingTime = 0; 
		this.name = name;
	}
	
	/*
	 * setter for passing time
	 */
	public void setPassingTime(long simulationTime) {
		this.passingTime = simulationTime;
	}
	
	/*
	 * returns time car had to wait at intersection
	 */
	public long getWaitingPeriod() {
		return this.passingTime - this.arrivalTime;
	}
	
	/*
	 * getter for arival time
	 */
	public long getArrival() {
		return this.arrivalTime;
	}
	
	public String printArrival() {
		return this.name + " arrived at " + this.arrivalTime;
	}
	
	public String toString() {
		return this.name;
	}
 
}
