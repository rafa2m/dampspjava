package psp.temados.hilos.synch;
/**
 * Prueba del contador sincronizado. Todas las pruebas darán el mismo resultado
 * , es decir, el contador no se incrementará correctamente desde 0 hasta 5000. Los
 * incrementos no comenzarán en cada nuevo millar siempre. 
 * @author rafa
 *
 */
public class TestContador {
	public static void main(String[] args) {
		Contador contador = new Contador(5000);

		for(int i=0;i<5;i++) {
			HiloContador hiloContador=new HiloContador("Hilo contador " + (i+1)
					,contador);
			hiloContador.start();
		}
	}
}
