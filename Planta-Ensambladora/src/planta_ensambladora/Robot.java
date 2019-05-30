package planta_ensambladora;

public class Robot {
	private boolean isReady;
	private Linea lastLine;
	private final int robotType;
	public static final int TYPE_CHASIS = 1;
	public static final int TYPE_MOTOR = 2;
	public static final int TYPE_TRANSMISION = 3;
	public static final int TYPE_CARROCERIA = 4;
	public static final int TYPE_INTERIORES = 5;
	public static final int TYPE_LLANTAS = 6;
	public static final int TYPE_PRUEBAS = 7;
	
	public Robot(int robotType) {
		this.isReady = true;
		this.robotType = robotType;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public Linea getLastLine() {
		return lastLine;
	}

	public void setLastLine(Linea motherLine) {
		this.lastLine = motherLine;
	}
	
	public int getType() {
		return robotType;
	}
}
