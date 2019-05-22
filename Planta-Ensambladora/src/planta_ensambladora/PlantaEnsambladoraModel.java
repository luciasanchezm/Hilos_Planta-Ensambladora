package planta_ensambladora;

import java.util.Vector;

public class PlantaEnsambladoraModel {
	
	private PlantaEnsambladoraView view;
	private int totalLines;
	private static Vector<Vector<Robot>> robots;
	private static int[] robotsPerStation = {5,4,2,3,3,0,0};
	private Vector<Linea> lines;
	
	public PlantaEnsambladoraModel() {
		totalLines = Rutinas.nextInt(8,15);
		robotsPerStation[5] = totalLines;
		robotsPerStation[6] = totalLines;
		view = new PlantaEnsambladoraView(totalLines);
		initializeRobots();
		
		lines = new Vector<Linea>();
		for(int i = 0; i < totalLines; i++)
			lines.add(new Linea(i+1, robots, view.getLineaView(i)));
		lines.forEach(linea ->{
			linea.start();
		});
	}
	
	private void  initializeRobots() {
		robots = new Vector<Vector<Robot>>();
		Vector<Robot> temp;
		for(byte i = 0; i < robotsPerStation.length ; i++) {
			temp = new Vector<Robot>();
			for(byte j = 0;j < robotsPerStation[i]; j++)
				temp.add(new Robot(j+1));
			robots.add(temp);
		}
	}
	
}
