package controlador;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.Equipo;
import modelo.Tabla;

public class Prueba {

	public static void main(String[] args) {

		ArrayList<Equipo> equipos = new ArrayList<>();

		equipos.add(new Equipo("A"));
		equipos.add(new Equipo("B"));
		equipos.add(new Equipo("C"));
		equipos.add(new Equipo("D"));
		equipos.add(new Equipo("E"));

		String[] nombres = new String[equipos.size()];

		for (int i = 0; i < nombres.length; i++) {

			nombres[i] = equipos.get(i).get_nombreEquipo();

		}

		Tabla prueba = new Tabla(equipos, equipos.size());

		prueba.insertarEstadistica("A", 2, 0, 3);
		prueba.insertarEstadistica("A", 3, 1, 3);
		prueba.insertarEnfrentamiento(new String[] { "C", "B" }, new int[] { 3, 2 }, new int[] { 2, 3 },
				new int[] { 3, 0 });

		System.out.println(prueba.getEquipos()[0] + " " + Arrays.toString(prueba.getEstadisticas()[0]));
		System.out.println(prueba.getEquipos()[1] + " " + Arrays.toString(prueba.getEstadisticas()[1]));
		System.out.println(prueba.getEquipos()[2] + " " + Arrays.toString(prueba.getEstadisticas()[2]));
		System.out.println(prueba.getEquipos()[3] + " " + Arrays.toString(prueba.getEstadisticas()[3]));
		System.out.println(prueba.getEquipos()[4] + " " + Arrays.toString(prueba.getEstadisticas()[4]));

		System.out.println();

		System.out.println(prueba.toString());

		System.out.println();

		prueba.insertarEstadistica("Tinga", 3, 1, 3);

	}

}
