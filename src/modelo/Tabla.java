package modelo;

import java.util.Arrays;

public class Tabla {

	// AÑADIR EL NUMERO DE EQUIPOS A LA INICIALIZACION DE ARRAYS
	private String[] equipo;
	private int[][] estadisticas;

	public Tabla(int numeroEquipos) {
		super();
		this.equipo = new String[numeroEquipos];
		this.estadisticas = new int[numeroEquipos][5];
	}

	public int[][] getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(int[][] estadisticas) {
		this.estadisticas = estadisticas;
	}

	public String[] getEquipo() {
		return equipo;
	}

	public void setEquipo(String[] equipo) {
		this.equipo = equipo;
	}

	public void mostrarEstadisticas() {
		System.out.println(Arrays.toString(equipo) + Arrays.deepToString(estadisticas));
		/*
		 * Arrays.deepToString sacado de:
		 * https://docs.oracle.com/javase/7/docs/api/java/util/
		 * Arrays.html#deepToString(java.lang.Object[])
		 */
	}

	public void insertarEquipoEnTabla(String nombreEquipo) {// a partir de aquí metodos individuales llamados en
		for (int i = 0; i < equipo.length; i++) {// cambiar el numero 3 por el numero de equipos

			equipo[i] = nombreEquipo;

		}

	}

	public void insertarPartidosJugadosEnTabla(int partidosJugados) {
		int columna = 0;
		for (int i = 0; i < equipo.length; i++) {

			estadisticas[i][columna] = partidosJugados;

		}
	}

	public void insertarGolesFavorablesEnTabla(int golesFavorables) {
		int columna = 1;
		for (int i = 0; i < equipo.length; i++) {

			estadisticas[i][columna] = golesFavorables;

		}
	}

	public void insertarGolesContraEnTabla(int golesContrarios) {
		int columna = 2;
		for (int i = 0; i < equipo.length; i++) {

			estadisticas[i][columna] = golesContrarios;

		}
	}

	public void insertarDiferenciaGolesEnTabla(int diferencia) {
		int columna = 3;
		for (int i = 0; i < equipo.length; i++) {

			estadisticas[i][columna] = diferencia;

		}
	}

	public void insertarPuntosEnTabla(int puntos) {
		int columna = 4;
		for (int i = 0; i < equipo.length; i++) {

			estadisticas[i][columna] = puntos;

		}
	}

	public void insertarDatosEnTabla(String nombreEquipo, int partidosJugados, int golesFavorables, int golesContrarios,
			int diferencia, int puntos) {// metodo con llamadas a metodos para insetar todos los datos dentro de tabla
		for (int i = 0; i < equipo.length; i++) {
			insertarEquipoEnTabla(nombreEquipo);
			insertarPartidosJugadosEnTabla(partidosJugados);
			insertarGolesFavorablesEnTabla(golesFavorables);
			insertarGolesContraEnTabla(golesContrarios);
			insertarDiferenciaGolesEnTabla(diferencia);
			insertarPuntosEnTabla(puntos);
		}

	}

	@Override
	public String toString() {
		return "Tabla [equipo=" + Arrays.toString(getEquipo()) + ", estadisticas=" + Arrays.toString(getEstadisticas())
				+ "]";
	}

}
