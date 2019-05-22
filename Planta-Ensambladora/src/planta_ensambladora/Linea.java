package planta_ensambladora;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Linea extends Thread{
	private int lineNumber;
	private LineaView view;
	private Vector<Estacion> stations;
	private static Vector<Vector<Robot>> robots;
	private static Semaforo totalCarsSemaphore;
	private static int totalCars = 0;
	
	public Linea(int lineNumber, Vector<Vector<Robot>> robots, LineaView view) {
		this.lineNumber = lineNumber;
		this.robots = robots;
		this.view = view;
		if(totalCarsSemaphore == null)
			totalCarsSemaphore = new Semaforo(1);
		initializeStations();
	}

	private void initializeStations() {
		stations = new Vector<Estacion>();
		stations.add(new Estacion("Chasis y cableado",1,10000,true));
		stations.add(new Estacion("Motor",2,10000,true));
		stations.add(new Estacion("Transmision",3,10000,true));
		stations.add(new Estacion("Carroceria",4,10000,true));
		stations.add(new Estacion("Interiores",5,10000,true));
		stations.add(new Estacion("Llantas",6,10000,true));
		stations.add(new Estacion("Puertas",7,10000,true));
	}
	
	@Override
	public void run() {
		Vector<Robot> currentStationsRobots;
		Robot robot = null, lastRobot = null;
		Estacion station;
		while(true) {
			//Verifica si ya están haciendo el último carro
			totalCarsSemaphore.Espera();
			if(totalCars >= 20) {
				totalCarsSemaphore.Libera();
				return;
			}
			totalCars++;
			System.out.println("Total cars :"+totalCars);
			totalCarsSemaphore.Libera();
			
			for(int i = 0; i < stations.size(); i++) {
				currentStationsRobots = robots.get(i);
				station = stations.get(i);
				
				if(i == 5 || i == 6) {
					robot = currentStationsRobots.get(station.getStationNumber()-1);
					view.changeStation();
					continue;
				}
				
				for(int j = 0; j < currentStationsRobots.size(); j++) {
					robot = currentStationsRobots.get(j);
					robot.getSemaphore().Espera();
					if(!robot.isReady()) {
						robot.getSemaphore().Libera();
						robot = null;
						continue;
					}
					robot.setReady(false);
					robot.getSemaphore().Libera();
					if(station.getStationNumber()==3)
						lastRobot.setReady(true);
					break;
				}
				
				if(robot == null) {
					i--;
					continue;
				}
				
				robot.setLastLine(this);
				view.changeStation();
				if(station.getStationNumber()!=2)
					robot.setReady(true);
				lastRobot = robot;
			}
		}
	}
}