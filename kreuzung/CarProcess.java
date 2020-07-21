package kreuzung;

import java.util.List;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.modelling.ProcessStep;
import de.fhdw.tm.des.modelling.ProcessStepDelay;
import de.fhdw.tm.des.scheduler.DESScheduler;

public class CarProcess {
	private List<Car> waitingCars;
	private ExponentialDistribution delay;
	private Integer count;
	
	public CarProcess(List<Car> waitingCars) {
		this.waitingCars = waitingCars;
		this.delay = new ExponentialDistribution(DESScheduler.getRandom(), 15);
		this.count = 0;
	}
	
	@ProcessStepDelay(0)
	public long startDelay() {
		return 0;
	}
	
	@ProcessStep(0)
	public void start() {
	// no car is generated	
	}
	
	@ProcessStepDelay(1)
	public long startTo1Delay() {
		return (long) this.delay.sample();
	}
	
	@ProcessStep(1)
	public void step1() {
		this.waitingCars.add(new Car(DESScheduler.getSimulationTime(), "Car" + this.count));
		//System.out.println("Auto hinzugefügt");
		this.count++;
		if(DESScheduler.getSimulationTime() < 100000000) {
			ModelProcess.scheduleProcess(this);
		}
//		else {
//			for(Car current: this.waitingCars) {
//				System.out.println(current.printArrival());
//			}
//		}
	}

}
