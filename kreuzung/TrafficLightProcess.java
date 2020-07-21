package kreuzung;

import de.fhdw.tm.des.evaluation.EvaluationInterval;
import de.fhdw.tm.des.evaluation.aggregation.CountCharacteristic;
import de.fhdw.tm.des.evaluation.aggregation.MeanCharacteristic;
import de.fhdw.tm.des.evaluation.aggregation.StandardDeviationCharacteristic;
import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.modelling.ProcessStep;
import de.fhdw.tm.des.modelling.ProcessStepDelay;
import de.fhdw.tm.des.scheduler.DESScheduler;

/*
 * process to simulate a traffic light
 */
public class TrafficLightProcess {
	
	private String name;
	private EvaluationInterval triggerStats;
	private TrafficLight myTrafficLight;
	
	public TrafficLightProcess(String name, TrafficLight trafficLight) {
		this.name = name;
		this.myTrafficLight = trafficLight;
		this.triggerStats = new EvaluationInterval("Process runtime", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new CountCharacteristic());
	}
	
	@ProcessStepDelay(0)
	public long startdelay() {
		return 0;
	}
	
	@ProcessStep(0)
	public void start() {
		System.out.println("Ampel " + this.name + " schaltet um auf grün bei Simulationszeit: " + DESScheduler.getSimulationTime());
	}
	
	@ProcessStepDelay(1)
	public long startToDelay1() {
		return 5;
	}
	
	@ProcessStep(1)
	public void step1() {
		System.out.println("Ampel " + this.name + " ist grün bei Simulationszeit: " + DESScheduler.getSimulationTime());
		if(this.myTrafficLight.getWaitingCars().size() == 0) {
			System.out.println("no waiting cars at traffic light " + this.name);
		}else {
			long simulationTime = DESScheduler.getSimulationTime();
			long durationGreen = simulationTime + 20;
			for(Integer i=0; i<10; i++) {
				if(this.myTrafficLight.getWaitingCars().size() > 0) {
					Car current = this.myTrafficLight.getWaitingCars().get(0);
					if(current.getArrival() < durationGreen) {
						current.setPassingTime(simulationTime);
						System.out.println(current.toString() + "passed " + this.name + " at "  + simulationTime);
						this.myTrafficLight.addPassedCar(current);
						this.myTrafficLight.getWaitingCars().remove(current);
						simulationTime += 2;
					}
				}
			}
		}
		
	}
	
	@ProcessStepDelay(2)
	public long step1ToDelay2() {
		return 20;
	}
	
	@ProcessStep(2) 
	public void step2() {
		System.out.println("Ampel " + this.name + " schaltet um auf rot bei Simulationszeit: " + DESScheduler.getSimulationTime());
	}
	
	@ProcessStepDelay(3)
	public long step2ToDelay3() {
		return 5;
	}
	
	@ProcessStep(3)
	public void step3() {
		System.out.println("Ampel " + this.name + " ist rot bei Simulationszeit: " + DESScheduler.getSimulationTime());	
	}
	
	@ProcessStepDelay(4)
	public long step3ToDelay4() {
		return 70;
	}
	
	@ProcessStep(4)
	public void step4() {
		if(DESScheduler.getSimulationTime() < 300) {
			ModelProcess.scheduleProcess(this);
		}
		
	}
	
	
	

}
