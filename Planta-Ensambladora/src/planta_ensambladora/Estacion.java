package planta_ensambladora;

public class Estacion{

	private String stationName;
	private int stationNumber;
	private int operationTime;
	private boolean produce;
	
	public Estacion(String name, int stationNumber, int operationTime, boolean produce) {
		this.stationName = name;
		this.stationNumber = stationNumber;
		this.operationTime = operationTime;
		this.produce = produce;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String name) {
		this.stationName = name;
	}
	
	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public int getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(int operationTime) {
		this.operationTime = operationTime;
	}

	public boolean isProduce() {
		return produce;
	}

	public void setProduce(boolean produce) {
		this.produce = produce;
	}
}