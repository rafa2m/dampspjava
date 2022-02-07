package psp.temados.hilos.synch;
/**
 * Crea una tercera clase para probar el funcionamiento de lo anterior. 
 * En el método main() de dicha clase debes crear un objeto Saldo con valor 
 * inicial de saldo. Debes mostrar el saldo inicial. Después debes crear varios 
 * hilos que compartan ese objeto de tipo Saldo. Cada hilo tendrá un nombre y 
 * le asignaremos una cantidad para el saldo. Lanzaremos los hilos y esperaremos a 
 * que finalicen para visualizar el saldo final del objeto de tipo Saldo compartido. 
 * @author rafa
 *
 */
public class TestHiloSaldo {

	public static void main(String[] args) {
		Saldo saldo = new Saldo(20000);
		HiloSaldo hiloSaldo1 = new HiloSaldo("Fran", saldo,1000);
		HiloSaldo hiloSaldo2 = new HiloSaldo("María", saldo,2345);
		HiloSaldo hiloSaldo3 = new HiloSaldo("Ana", saldo,1234);
		hiloSaldo1.start();
		hiloSaldo2.start();
		hiloSaldo3.start();
		
		try {
			hiloSaldo1.join();
			hiloSaldo2.join();
			hiloSaldo3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
