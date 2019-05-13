package planta_ensambladora;

import javax.swing.*;
import java.awt.*;

public class PlantaEnsambladoraView extends JFrame {
	
	/**
	 * @author Luc�a S�nchez Manjarrez
	 */
	
	private final static String [] COLORES = {"Amarillo", "Aqua", "Azul", "Blanco", "Cafe", "Gris", "Morado", "Naranja", "Negro", "Rojo", "Rosa", "Tinto", "Verde", "NaranjaClaro", "VerdeClaro"};
	private LineaView [] lineas;
	static Graphics g;
	private Image backbuffer = null;
	private static int coordenateX = 50, coordenateY = 50;
	
	public PlantaEnsambladoraView(int totalLines) {
		super("Planta Ensambladora");
		createViews();
		backbuffer = createImage(getWidth(), getHeight());
		g = backbuffer.getGraphics();
		lineas = new LineaView[totalLines];
		
		
		for (int i = 0; i < lineas.length; i++) {
			lineas[i] = new LineaView(i+1, COLORES[i]);
			lineas[i].getView().setBounds(coordenateX, coordenateY, 300, 300);
			add(lineas[i].getView());
			lineas[i].createGraphics();
			coordenateX+=300;
			if(coordenateX >= 1500) {
				coordenateX = 50;
				coordenateY+=300;
			}
		}

//PRUEBAS		
//		for (int i = 0; i < lineas.length; i++) {
//			for (int j = 0; j < 7; j++) {
//				changeStation(i);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {e.printStackTrace();}
//			}
//		}
//TERMINAN PRUEBAS		
	}
	
	public LineaView getLineaView(int noLinea) {
		return lineas[noLinea];
	}

	public void createViews() {
		setLayout(null);
		setSize(1600, 1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}