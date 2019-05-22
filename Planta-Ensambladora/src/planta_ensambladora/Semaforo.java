package planta_ensambladora;

public class Semaforo {
	private int Recursos;
	public Semaforo(int Recursos){
		this.Recursos=Recursos;
	}
	public synchronized void Espera(){
		while (Recursos<1)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		Recursos--;
	
	}
	public synchronized void Libera(){
		Recursos++;
		notifyAll();
	}
}