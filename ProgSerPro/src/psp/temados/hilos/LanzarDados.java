package psp.temados.hilos;
public class LanzarDados {

	public static void main(String[] args) {
		Dados dados1 = new Dados();
		Dados dados2 = new Dados();
		Dados dados3 = new Dados();

		try {
			dados1.start();
			dados1.join();
			dados2.start();
			dados2.join();
			dados3.start();
			dados3.join();
			sumaPuntuaciones(dados1,dados2,dados3);
			obtenerGanador(dados1,dados2,dados3);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo suma las puntuaciones de todos los jugadores y muestra el resultado 
	 * @param dados1
	 * @param dados2
	 * @param dados3
	 */
	public static void sumaPuntuaciones(Dados dados1, Dados dados2, Dados dados3) {
		int total = dados1.getResultado() + dados2.getResultado() + dados3.getResultado();
		System.out.println("El resultado total es: " + total);
	}

	/**
	 * Este metodo indica el jugador que ha obtenido la mayor puntuacion
	 * @param dados1
	 * @param dados2
	 * @param dados3
	 */
	public static void obtenerGanador(Dados dados1, Dados dados2, Dados dados3) {
		if(dados1.getResultado() >dados2.getResultado() && dados1.getResultado() > dados3.getResultado()) {
			System.out.println("El ganador es el jugador 1");
		}else if(dados2.getResultado() >dados1.getResultado() && dados2.getResultado() > dados3.getResultado()) {
			System.out.println("El ganador es el jugador 2");
		}else if(dados3.getResultado() > dados1.getResultado() && dados3.getResultado() > dados2.getResultado()) {
			System.out.println("El ganador es el jugador 3");
		}else {
			System.out.println("Se ha producido un empate");
		}
	}
}