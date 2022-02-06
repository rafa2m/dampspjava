package psp.temados.hilos.synch;
/**
 * Contador compartido por todos los hilos incrementadores
 * @author rafa
 *
 */
public class Contador {
	private int valorActual, maximo = 0;
	public Contador(int maximo) {
		this.maximo = maximo;
	}
	public void incrementaValor() {
		valorActual = valorActual + 1;
	}
	public int getValor() {
		return valorActual;
	}
	public int getMaximo() {
		return maximo;
	}
}
