package psp.temados.hilos.synch;
/**
 * Prueba del contador sincronizado. Todas las pruebas darán el mismo resultado
 * , es decir, el contador se incrementará correctamente desde 0 hasta 5000. Con
 * incrementos de 1000 por cada hilo
 * @author rafa
 *
 */
public class TestContadorSynch {
	public static void main(String[] args) {
		Contador contador = new Contador(5000);

		for(int i=0;i<5;i++) {
			HiloContadorSynch hiloContador=new HiloContadorSynch("Hilo contador " + (i+1)
					,contador);
			hiloContador.start();
			
		}
		
	}
}
