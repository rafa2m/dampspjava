package psp.temados.proco;
/**
 * Clase para probar el modelo de sincronización 
 * productor-consumidor
 * @author rafa
 * @version 1.0
 */
public class TestProductorConsumidor {
	public static void main(String[] args) {
		Cola cola = new Cola();
		Productor productor = 
				new Productor(cola, 1);	
		Consumidor consumidor = 
				new Consumidor(cola, 1);
		productor.start();
		consumidor.start();
	}
}