package kreuzung;

import java.util.concurrent.ExecutionException;

import de.fhdw.tm.des.scheduler.DESScheduler;
import de.fhdw.tm.des.scheduler.Simulator;

public class TrafficLightTest {
	private static final int SIMULATION_COUNT = 1;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		DESScheduler.setDebug(false);
		
		Simulator simulator = new Simulator(0);
		
		Intersection intersection = new Intersection(1);
		
		simulator.simulate(intersection, SIMULATION_COUNT);
		simulator.terminate();
	}

}
