package psp.temados.proco;
/**
 * Ejemplo de sincronización para el modelo productor-consumidor
 * , esta clase representa al productor
 * @author rafa
 * @version 1.0
 *
 */
public class Productor extends Thread {
	private Cola cola;
	private int hilo;
	/**
	 * Constructor específico del productor para inicializar
	 * el objeto compartido y el número de productor
	 * @param cola
	 * @param hilo
	 */
	public Productor(Cola cola, int hilo) {
		this.cola = cola;
		this.hilo = hilo;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			//produce un nuevo valor en la cola
			this.cola.put(i, this.hilo); 
			try {
				sleep(100);
			} catch (InterruptedException e) { }    
		}
	}
}