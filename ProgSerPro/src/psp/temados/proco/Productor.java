package psp.temados.proco;
/**
 * Ejemplo de sincronización para el modelo productor-consumidor
 * , esta clase representa al productor.
 * @author rafa
 * @version 1.0
 *
 */
public class Productor extends Thread {
	private Cola cola;
	private int hilo;
	private int tiempoProceso;
	/**
	 * Constructor específico del productor para inicializar
	 * el objeto compartido y el número de productor
	 * @param cola
	 * @param hilo
	 */
	public Productor(Cola cola, int hilo, int tiempoProceso) {
		this.cola = cola;
		this.hilo = hilo;
		this.tiempoProceso = tiempoProceso;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			//produce un nuevo valor en la cola
			this.cola.put(i, this.hilo); 
			try {
				sleep(this.tiempoProceso);
			} catch (InterruptedException e) { }    
		}
	}
}