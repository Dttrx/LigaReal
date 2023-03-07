package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Tabla {

	String[] nombresEquipos;
	int[][] estadisticas;

	public Tabla(ArrayList<Equipo> equipos, int cantidadEquipos) {
		super();
		this.nombresEquipos = new String[equipos.size()];
		this.estadisticas = new int[equipos.size()][4];

		for (int i = 0; i < cantidadEquipos; i++) {

			this.nombresEquipos[i] = equipos.get(i).get_nombreEquipo();

		}

	}

	public String[] getEquipos() {
		return nombresEquipos;
	}

	public void setEquipos(String[] equipos) {
		this.nombresEquipos = equipos;
	}

	public int[][] getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(int[][] estadisticas) {
		this.estadisticas = estadisticas;
	}

	@Override
	public String toString() {
		return "TablaRepuesto [equipos=" + Arrays.toString(nombresEquipos) + ", estadisticas="
				+ Arrays.deepToString(estadisticas) + "]";
	}

	public void insertarEstadistica(String equipo, int golesFavor, int golesContra, int puntos) {

		boolean encontrado = false;
		int posicion = 0;

		// try catch que exige Carlos
		try {

			// bucle que busca al equipo, sale si lo encuentra
			do {

				/**
				 * por cada posicion del array de nombres comparo con el nombre dado.
				 * 
				 * equalsIgnoreCase compara 2 strings sin tomar en cuenta mayúsculas
				 * 
				 * "hola".equalsIgnoreCase("HOLA");// retorna true
				 * 
				 */
				if (nombresEquipos[posicion].equalsIgnoreCase(equipo)) {

					encontrado = true;

					// como quiero preservar la posicion, esta solo aumenta si no
					// se ha encontrado el equipo
				} else {

					posicion++;

				}

			} while (!encontrado);

			// una vez encontrado el equipo, le sumo las estadisticas
			estadisticas[posicion][0] += golesFavor;
			estadisticas[posicion][1] += golesContra;
			estadisticas[posicion][2] += Math.abs(golesFavor - golesContra);// no sé si así se calcula la diferencia de
																			// goles lol
			estadisticas[posicion][3] += puntos;

			// si no encuentra al equipo, bota esta excepcion
		} catch (IndexOutOfBoundsException e) {

			System.out.println("Error al insertar estadísticas");
			System.out.println("Equipo no encontrado");

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");

		}
		
		ordenarTabla();

	}

	// lo mismo pero con 2 equipos y sus estadisticas
	public void insertarEnfrentamiento(String[] equipos, int[] golesFavor, int[] golesContra, int[] puntos) {

		insertarEstadistica(equipos[0], golesFavor[0], golesContra[0], puntos[0]);
		insertarEstadistica(equipos[1], golesFavor[1], golesContra[1], puntos[1]);

	}

	private void ordenarTabla() {

		int auxNum;
		String auxStr;

		for (int i = 0; i < nombresEquipos.length - 1; i++) {

			for (int j = i + 1; j < nombresEquipos.length - i; j++) {

				if (estadisticas[j][3] > estadisticas[j - 1][3]) {

					auxNum = estadisticas[j][3];
					estadisticas[j][3] = estadisticas[j - 1][3];
					estadisticas[j - 1][3] = auxNum;

					auxStr = nombresEquipos[j];
					nombresEquipos[j] = nombresEquipos[j - 1];
					nombresEquipos[j - 1] = auxStr;

				}

			}

		}

	}

}
