package planta_ensambladora;

import java.util.Vector;

public class Estacion{

	private Vector<Robot> robots;
	private int stationNumber;
	private int operationTime;
	private int noRobots;
	private boolean produce;
	private static Semaforo semaphore;
	
	public Estacion(int stationNumber, int operationTime, boolean produce) {
		this.stationNumber = stationNumber;
		this.operationTime = operationTime;
		this.produce = produce;
		this.noRobots = PlantaEnsambladoraModel.getRobotsAt(stationNumber);
		this.semaphore = new Semaforo(1);
		this.robots = PlantaEnsambladoraModel.getStationRobots(stationNumber);
	}
	
	public Robot chooseRobot() {
		Robot robot = null;
		for(int i = 0; i < noRobots; i++) {
			robot = robots.get(i);
			semaphore.Espera();
			if(!robot.isReady()) {
				semaphore.Libera();
				robot = null;
				continue;
			}
			robot.setReady(false);
			semaphore.Libera();
			break;
		}
		return robot;
	}
	
	public int getStationNumber() {
		return stationNumber;
	}

	public int getOperationTime() {
		return operationTime;
	}

	public boolean isProduce() {
		return produce;
	}

	public void setProduce(boolean produce) {
		this.produce = produce;
	}

	public Semaforo getSemaphore() {
		return semaphore;
	}
}