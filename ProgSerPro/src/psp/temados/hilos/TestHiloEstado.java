package psp.temados.hilos;
/**
 * Prueba de estados de un hilo
 * @author rafa
 * @version 1.0
 */
public class TestHiloEstado {
	public static void main(String[] args) {
		long tiempo = System.currentTimeMillis();
		HiloEstado hiloEstado = new HiloEstado("Hilo 1",tiempo);
		System.out.println("El hilo " + hiloEstado.getName() 
		+ " ha sido creado");
		
		hiloEstado.start();
		System.out.println("El hilo " + hiloEstado.getName() 
		+ " ha sido propuesto para ejecución");
		try {
			hiloEstado.join();
		} catch (InterruptedException e) {
			System.out.println("El hilo " + hiloEstado.getName() 
			+ " ha sido interrumpido");
		}
		System.out.println("El hilo " + hiloEstado.getName() 
		+ " ha finalizado, su ejecución ha durado " + (System.currentTimeMillis()-tiempo)/1000
		+ " segundos.");
	}
}
