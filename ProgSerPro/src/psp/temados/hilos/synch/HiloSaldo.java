package psp.temados.hilos.synch;
/**
 * Crea una clase que derive de Thread, y en el método run() de la 
 * misma vamos a utilizar el método sincronizado de la clase Saldo. 
 * Averigua los parámetros necesarios para el constructor de esta clase.
 * @author rafa
 *
 */
public class HiloSaldo extends Thread{
	private String usuario;
	private Saldo saldo;
	private long incremento;
	public HiloSaldo(String usuario, Saldo saldo, long incremento) {
		this.usuario = usuario;
		this.saldo = saldo;
		this.setName(usuario);
		this.incremento = incremento;
	}
	@Override
	public synchronized void start() {
		super.start();
		System.out.println("El usuario " + this.usuario +
				" va a comenzar a operar con su saldo");
	}

	@Override
	public void run() {
		this.saldo.addSaldo(this.incremento, this.usuario);
	}

}
