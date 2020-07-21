package kreuzung;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.scheduler.DESScheduler;
import de.fhdw.tm.des.scheduler.Simulation;
import de.fhdw.tm.des.scheduler.Simulator;

public class CarProcessTest {
	private static final int SIMULATION_COUNT = 1;
	private static final int PROCESS_COUNT = 1;

	public static void main(String[] args) {
	
	List<Car> waitingCars = new ArrayList<Car>(); 
		
	DESScheduler.setDebug(false);
	
	Simulator simulator = new Simulator(0);
	
	Simulation sim = new Simulation() {

		@Override
		public void injectStart() {
			for (int i = 0; i < PROCESS_COUNT; i++) {
				ModelProcess.scheduleProcess(new CarProcess(waitingCars));
			}
		}

		@Override
		public void start() {
			System.out.println("Start...");
		}
		
		@Override
		public void finish() {
			System.out.println("Fertig");
		}
		
	};
			
	simulator.simulate(sim, SIMULATION_COUNT);
	simulator.terminate();
	
	System.out.println(waitingCars.size());
	
	for(Car current: waitingCars) {
		System.out.println(current.toString());
	}
	}
}