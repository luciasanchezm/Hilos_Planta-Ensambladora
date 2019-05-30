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
	private static Semaforo totalCarsSemaphore;
	private static int totalCars = 0;
	private static int [] operationPerStation = {10000, 3000, 2000, 5000, 2500, 2500, 5000};
	
	public Linea(int lineNumber, LineaView view) {
		this.lineNumber = lineNumber;
		this.view = view;
		if(totalCarsSemaphore == null)
			totalCarsSemaphore = new Semaforo(1);
		initializeStations();
	}

	private void initializeStations() {
		stations = new Vector<Estacion>();
		stations.add(new Estacion(1, operationPerStation[0],true));
		stations.add(new Estacion(2, operationPerStation[1],true));
		stations.add(new Estacion(3, operationPerStation[2],true));
		stations.add(new Estacion(4, operationPerStation[3],true));
		stations.add(new Estacion(5, operationPerStation[4],true));
		stations.add(new Estacion(6, operationPerStation[5],true));
		stations.add(new Estacion(7, operationPerStation[6],true));
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
				station = stations.get(i);
				robot = station.chooseRobot();
				
				if(robot == null) {
					i--;
					continue;
				}
				
				if(station.getStationNumber()==3)
					lastRobot.setReady(true);
				
				robot.setLastLine(this);
				view.changeStation();
				if(station.getStationNumber()!=2)
					robot.setReady(true);
				lastRobot = robot;
			}
		}
	}
	
	public static int getOperationTime(int stationNumber) {
		return operationPerStation[stationNumber - 1];
	}
}