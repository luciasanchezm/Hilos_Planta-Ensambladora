package planta_ensambladora;

import java.util.Vector;

public class PlantaEnsambladoraModel {
	
	private PlantaEnsambladoraView view;
	private int totalLines;
	private static int[] robotsPerStation = {5,4,2,3,3,0,0};
	private static Vector<Robot> totalRobots [];
	private Vector<Linea> lines;
	
	public PlantaEnsambladoraModel() {
		totalLines = Rutinas.nextInt(8,15);
		robotsPerStation[5] = totalLines;
		robotsPerStation[6] = totalLines;
		view = new PlantaEnsambladoraView(totalLines);
		robotsPerStation[5] = totalLines;
		robotsPerStation[6] = totalLines;
		initializeRobots();
		
		lines = new Vector<Linea>();
		for(int i = 0; i < totalLines; i++)
			lines.add(new Linea(i+1, view.getLineaView(i)));
		lines.forEach(linea ->{
			linea.start();
		});
	}
	
	private void  initializeRobots() {
		totalRobots = new Vector[7];
		Vector<Robot> temp;
		for(byte i = 0; i < totalRobots.length ; i++) {
			temp = new Vector<Robot>();
			for(byte j = 0;j < robotsPerStation[i]; j++)
				temp.add(new Robot(j+1));
			totalRobots[i] = (temp);
		}
	}
	
	public static Vector<Robot> getStationRobots(int stationNumber){
		return totalRobots[stationNumber-1];
	}
	
	public static int getRobotsAt(int stationNumber) {
		return robotsPerStation[stationNumber-1];
	}
}