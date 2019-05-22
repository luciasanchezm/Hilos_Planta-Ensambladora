package planta_ensambladora;

import java.awt.*;
import javax.swing.*;

public class LineaView extends JPanel {
	
	/**
	 * @author Lucía Sánchez Manjarrez
	 */
	
	private int lineNumber, stationNumber;
	private Image [] stations;
	private int [] operationPerStation = {0, 10000, 10000, 10000, 10000, 10000, 10000, 10000};
	private Image[] bases;
	private Image finishStation;
	private Image robot;
	private Image fondo;
	private Image backbuffer;
	private Graphics g;

	public LineaView(int noLinea, String color) {
		setSize(300,300);
		this.lineNumber = noLinea;
		stationNumber=-1;
		stations = new Image[8];
		bases = new Image [5];
		for (int i = 0; i < bases.length; i++)
			bases[i] = Rutinas.changeSize("Base" + (i+1) + ".png", 300, 200).getImage();
		
		finishStation = Rutinas.changeSize("Pruebas" + lineNumber + ".png", 195, 195).getImage();
		fondo = Rutinas.changeSize("Fondo.png", 285, 280).getImage();
		initializeStations(color);
	}
	
	public void createGraphics() {
		backbuffer = createImage(getWidth(), getHeight());
		g = backbuffer.getGraphics();
		changeStation();
	}
	
	public void initializeStations(String color) {
		stations[0] = (Rutinas.changeSize("Base0.png", 300, 200).getImage());
		stations[1] = (Rutinas.changeSize("Chasis.png", 300, 300).getImage());
		stations[2] = (Rutinas.changeSize("Motor.png", 300, 300).getImage());
		stations[3] = (Rutinas.changeSize("Transmision.png", 300, 300).getImage());
		stations[4] = (Rutinas.changeSize("Carroceria" + color + ".png", 300, 300).getImage());
		stations[5] = (Rutinas.changeSize("Interiores" + color + ".png", 300, 300).getImage());
		stations[6] = (Rutinas.changeSize("Llantas" + color + ".png", 300, 300).getImage());
		stations[7] = (Rutinas.changeSize("Pruebas" + color + ".png", 300, 300).getImage());
	}

	public void paint(Graphics g) {
		g.drawImage(backbuffer, 0, 0, getHeight(), getWidth(),this);
	}
	
	public void changeStation() {
		stationNumber++;
		drawRobot(stationNumber);
		if(stationNumber==2) {
			waitRobot();
			return;
		}
		draw();
		g.drawImage(stations[stationNumber], 0, stationNumber==0? 40 : 0, this);
		repaint();
		if(stationNumber == 7) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {e.printStackTrace();}
			finishCar();
		}
	}
	
	public void waitRobot() {
		draw();
		robot = Rutinas.changeSize("Robot2.png", 200, 200).getImage();
		g.drawImage(stations[stationNumber], 0, 0, this);
		g.drawImage(robot, 50, 0, this);
		repaint();
	}
	
	public void drawRobot(int noRobot) {
		if (stationNumber==0)
			return;
		int time = 0, operationPerStation = this.operationPerStation[stationNumber];
		boolean band = true;
		while(time < operationPerStation) {
			draw();
			g.drawImage(stations[stationNumber-1], 0, stationNumber==1? 40 : 0, this);
			if(band) {
				robot = Rutinas.changeSize("Robot" + noRobot + ".png", 200, 200).getImage();
				band = false;
			}
			else {
				robot = Rutinas.changeSize("Robot" + noRobot + "-.png", 200, 200).getImage();
				band = true;
			}
			g.drawImage(robot, 50, 0, this);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
			time+=100;
		}
	}
	
	public void finishCar() {
		for (int i = 0; i < bases.length; i++) {
			finishCar(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		
		int coordenadaX = 50;
		while(coordenadaX<300) {
			draw();
			g.drawImage(bases[bases.length-1], 0, 50, this);
			g.drawImage(finishStation, coordenadaX+=10, 130, this);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace();}
		stationNumber=-1;
		changeStation();
	}
	
	public void finishCar(int pos) {
		draw();
		g.drawImage(bases[pos], 0, 50, this);
		g.drawImage(finishStation, 50, 50+(pos*20), this);
		repaint();
	}
	
	public void draw() {
		super.paint(g);
		g.drawImage(fondo, 5, 25, this);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("LÍNEA #" + lineNumber + "                   Estación: " + (stationNumber>=3?stationNumber-1:stationNumber), 10, 20);
	}
	
	public int getStationNumber() {
		return stationNumber;
	}
	
	public JPanel getView() {
		return this;
	}
}