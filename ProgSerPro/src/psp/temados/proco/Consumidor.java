package psp.temados.proco;
/**Ejemplo de sincronización para el modelo productor-consumidor
 * , esta clase representa al productor.
 * @author rafa
 * @version 1.0 
 */
public class Consumidor extends Thread {
	private Cola cola;
	private int hilo;
	private int tiempoProceso;
	/**
	 * Constructor específico del consumidor para inicializar el
	 * objeto compartido con el productor y el número de consumidor
	 * @param cola Objeto compartido con los hilos productores
	 * @param hilo Número del hilo consumidor
	 */
	public Consumidor(Cola cola, int hilo, int tiempoProceso) {
		this.cola = cola;
		this.hilo = hilo;
		this.tiempoProceso = tiempoProceso;
	}
	public void run() {
		int valor = 0;
		for (int i = 0; i < 5; i++) {
			//consume el valor de la cola, pero después no se usa
			valor = cola.get(i,this.hilo);
			try {
				this.sleep(tiempoProceso);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}