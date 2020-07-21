package kreuzung;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
	private List<Car> waitingCars;
	private List<Car> passedCars;
	
	public TrafficLight() {
		this.passedCars = new ArrayList<Car>();
		this.waitingCars = new ArrayList<Car>();
	}
	
	public void addWaitingCar(Car car) {
		this.waitingCars.add(car);
	}
	
	public void addPassedCar(Car car) {
		this.passedCars.add(car);
	}
	
	public List<Car> getPassedCars() {
		return this.passedCars;
	}
	
	public List<Car> getWaitingCars() {
		return this.waitingCars;
	}

}
