package kreuzung;

import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.scheduler.Simulation;

/*
 * simulation of an intersection containing 4 traffic lights
 */
public class Intersection implements Simulation{
	private Integer processCount;
	private TrafficLight trafficLight1;
	private TrafficLight trafficLight2;
	private TrafficLight trafficLight3;
	private TrafficLight trafficLight4;
	
	public Intersection(Integer processCount) {
		this.processCount = processCount;
		this.trafficLight1 = new TrafficLight();
		this.trafficLight2 = new TrafficLight();
		this.trafficLight3 = new TrafficLight();
		this.trafficLight4 = new TrafficLight();
	}

	@Override
	public void injectStart() {
		for (int i = 0; i < this.processCount; i++) {
			ModelProcess.scheduleProcess(new TrafficLightProcess("Ampel 1 ", this.trafficLight1));
			ModelProcess.scheduleProcessToFuture(new TrafficLightProcess("Ampel 2", this.trafficLight2), 25);
			ModelProcess.scheduleProcessToFuture(new TrafficLightProcess("Ampel 3", this.trafficLight3), 50);
			ModelProcess.scheduleProcessToFuture(new TrafficLightProcess("Ampel 4", this.trafficLight4), 75);
		}
			ModelProcess.scheduleProcess(new CarProcess(this.trafficLight1.getWaitingCars()));
			ModelProcess.scheduleProcess(new CarProcess(this.trafficLight2.getWaitingCars()));
			ModelProcess.scheduleProcess(new CarProcess(this.trafficLight3.getWaitingCars()));
			ModelProcess.scheduleProcess(new CarProcess(this.trafficLight4.getWaitingCars()));
	}


	@Override
	public void start() {
		System.out.println("Start...");
	}

	@Override
	public void finish() {
		System.out.println("Fertig");
	}

}
