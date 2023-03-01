package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import modelo.Enfrentamiento;
import modelo.Equipo;
import modelo.Jornada;
import modelo.Jugador;
import modelo.Tabla;
import vista.Grafico;

public class Liga {

	static int cantidadJugadores = 0;
	static int cantidadEquipos = 0;
	static int cantidadJornadas = 0;
	static int enfrentamientosPorJornada = 0;
	static ArrayList<Jugador> jugadores = new ArrayList<>();
	static ArrayList<Equipo> equipos = new ArrayList<>();
	static ArrayList<Jornada> jornadas = new ArrayList<>();
	static String[] listaEquipos = { "Real Madrid", "Barcelona", "Bayern Munich", "Paris Saint-Germain", "Juventus",
			"Manchester United", "Manchester City", "Liverpool", "Chelsea", "Arsenal", "Atletico Madrid",
			"Borussia Dortmund", "AC Milan", "Inter Milan", "Roma", "Napoli", "Valencia", "Tottenham", "Ajax",
			"PSV Eindhoven", "FC Porto", "Benfica", "Sporting Lisbon", "Celtic", "Rangers", "Shakhtar Donetsk",
			"Dynamo Kiev", "CSKA Moscow", "Zenit St. Petersburg", "Galatasaray", "Besiktas", "Olympiakos", "PAOK",
			"AEK Athens", "Fenerbahce", "Boca Juniors", "River Plate", "Flamengo", "Palmeiras", "Corinthians", "Santos",
			"Club America", "Guadalajara", "Cruz Azul", "Tigres UANL", "Monterrey", "Pumas UNAM", "Tijuana",
			"Club Leon", "Club Tijuana", "Club Atlas", "Toluca", "Pachuca", "America de Cali", "Junior",
			"Atletico Nacional", "Millonarios", "Deportivo Cali", "Colo Colo", "Universidad de Chile",
			"Universidad Catolica", "Universidad de Concepcion", "Club Universidad Nacional", "Club Santos Laguna",
			"Club Deportivo Guadalajara", "Club Atlas", "Club Toluca", "Club Pachuca", "Club America de Cali",
			"Club Junior", "Club Atletico Nacional", "Club Millonarios", "Club Deportivo Cali", "Club Colo Colo",
			"Club Universidad de Chile", "Club Universidad Catolica", "Club Universidad de Concepcion",
			"Club Universidad Nacional", "Club Santos Laguna" };
	static Tabla tabla;

	public static void main(String[] args) {

		cantidadEquipos = Grafico.bienvenida();

		añadirEquiposManual(cantidadEquipos);

		añadirEquiposAleatorio(cantidadEquipos - equipos.size());

		tabla = new Tabla(cantidadEquipos);

		menuPrincipal();

	}

	public static void añadirEquiposAleatorio(int n_equipos) {

		Collections.shuffle(Arrays.asList(listaEquipos));

		for (int i = 0; i < n_equipos; i++) {

			equipos.add(new Equipo(listaEquipos[i]));

		}

	}

	public static void añadirEquiposManual(int n_equipos) {

		int contador = 0;
		String nombreEquipo;

		do {

			nombreEquipo = Grafico.pideString("Introduce nombre o pulsa enter para generar automaticamente",
					"nombre: ");

			if (nombreEquipo != "") {

				equipos.add(new Equipo(nombreEquipo));
				contador++;

			}

		} while (nombreEquipo.length() > 0 && contador < n_equipos);

	}

	public static void menuPrincipal() {

		int opcion;
		String[] opciones = { "1. Equipos/Jugadores", "2. Iniciar Liga", "3. Ver tabla", "4. Salir del programa" };

		do {

			Grafico.mostrarEquipos(equipos);

			System.out.println("Escoge una de las opciones");
			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				menuEquiposJugadores();

				break;

			case 2:

				hacerJornadas();
				menuJornadas();

				break;

			case 3:

				tabla.mostrarEstadisticas();

				break;

			}

		} while (opcion != 4);

	}

	public static void menuEquiposJugadores() {

		int opcion;
		String[] opciones = { "1. Equipos", "2. Jugadores", "3. Volver al menú anterior" };

		do {

			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				menuEquipos();

				break;

			case 2:

				if (cantidadJugadores > 0) {

					menuJugador();

				} else {

					System.out.println("No hay jugadores");

				}

				break;

			}

		} while (opcion != 3);

	}

	public static void menuEquipos() {

		int opcion;
		String[] opciones = { "1. Fichar jugador", "2. Modificar nombre", "3. Eliminar equipo", "4. Mostrar plantilla",
				"5. Mostrar equipos", "6. Volver al menú anterior" };

		do {

			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				ficharJugador();

				break;

			case 2:

				modificarNombreEquipo();

				break;

			case 3:

				eliminarEquipo();

				break;

			case 4:

				mostrarPlantilla();

				break;

			case 5:

				Grafico.mostrarEquipos(equipos);

				break;

			}

		} while (opcion != 6);

	}

	public static void menuJugador() {

		int opcion;
		String[] opciones = { "1. Modificar jugador", "2. Despedir jugador", "3. Mostrar datos",
				"4. Volver al menú anterior" };

		do {

			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				modificarJugador();

				break;

			case 2:

				despedirJugador();

				break;

			case 3:

				mostrarJugador();

				break;

			}

		} while (opcion != 4);

	}

	public static void menuJornadas() {

		int jornadaActual = 0;
		int enfrentamientoActual = cantidadEquipos % 2 == 0 ? 0 : 1;
		int opcion;
		String[] opciones = { "1. Mostrar enfrentamiento", "2. Simular jornada", "3. Simular resto de liga" };
		Jornada jornada;

		do {

			jornada = jornadas.get(jornadaActual);
			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				enfrentamientoActual = menuEnfrentamientos(jornada, enfrentamientoActual);

				break;

			case 2:

				for (int i = enfrentamientoActual; i < enfrentamientosPorJornada; i++) {

					jornada.getEnfrentamientos().get(i).simularEnfrentamiento();

				}

				jornadaActual++;
				enfrentamientoActual = cantidadEquipos % 2 == 0 ? 0 : 1;

				break;

			case 3:

				for (int i = jornadaActual; i < cantidadJornadas; i++, jornadaActual++) {

					jornada = jornadas.get(i);

					for (int j = enfrentamientoActual; j < enfrentamientosPorJornada; j++) {

						jornada.getEnfrentamientos().get(j).simularEnfrentamiento();

					}

					enfrentamientoActual = cantidadEquipos % 2 == 0 ? 0 : 1;

				}

				break;
			}

			if (enfrentamientoActual >= enfrentamientosPorJornada) {

				jornadaActual++;
				enfrentamientoActual = cantidadEquipos % 2 == 0 ? 0 : 1;

			}

		} while (jornadaActual < cantidadJornadas);

	}

	public static int menuEnfrentamientos(Jornada jornada, int enfrentamientoActual) {

		int opcion;
		String[] opciones = { "1. Asignar puntos", "2. Simular enfrentamiento", "3. Volver al menú anterior" };
		Enfrentamiento enfrentamiento;

		do {

			enfrentamiento = jornada.getEnfrentamientos().get(enfrentamientoActual);
			System.out.println(enfrentamiento.getEquipo1() + " vs " + enfrentamiento.getEquipo2());
			System.out.println();
			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				enfrentamiento.crearResultado();
				enfrentamientoActual++;

				break;

			case 2:

				enfrentamiento.simularEnfrentamiento();
				enfrentamientoActual++;

				break;
			}

		} while (opcion != 3 && enfrentamientoActual < enfrentamientosPorJornada);

		return enfrentamientoActual;

	}

	public static void ficharJugador() {

		int indice = buscarEquipo(Grafico.pideString("Introduce el nombre del equipo", "Equipo: "));
		int dorsal;
		String nombre;
		String posicion;
		Jugador jugador;
		Equipo equipo;

		equipo = equipos.get(indice);

		try {

			nombre = Grafico.pideString("Ingresa el nombre del jugador", "Nombre: ");
			System.out.println("Ingresa su dorsal");
			dorsal = Grafico.pideEnteroRango(1, 99, "Dorsal: ");
			posicion = Grafico.pideString("Ingresa su posicion ", "Posicion: ");

			jugador = new Jugador(nombre, dorsal, posicion);
			equipo.addIntegrante(jugador);
			jugadores.add(jugador);
			cantidadJugadores++;

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	public static void modificarJugador() {

		boolean esNombre = Grafico.nombreODorsal("Modificar");
		int opcion;
		int posicion;
		String[] opciones = { "1. Nombre", "2. Dorsal", "3. Posicion" };

		if (esNombre) {

			posicion = buscarJugador(Grafico.pideString("Ingresa el nombre del jugador", "Jugador: "));

		} else {

			System.out.println("Ingresa el dorsal del jugador");
			posicion = buscarJugador(Grafico.pideEnteroRango(1, 99, "Dorsal"));

		}

		try {

			opcion = Grafico.menu(opciones, "Opcion: ");

			switch (opcion) {
			case 1:

				jugadores.get(posicion).set_nombre(Grafico.pideString("Introduce el nuevo nombre", "Nombre: "));

				break;

			case 2:

				System.out.println("Introduce la nueva dorsal");
				jugadores.get(posicion).set_dorsal(Grafico.pideEnteroRango(1, 99, "Dorsal: "));

				break;

			case 3:

				jugadores.get(posicion).set_posicion(Grafico.pideString("Introduce la nueva posicion", "Posicion: "));

				break;

			}

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	public static void despedirJugador() {

		boolean esNombre = Grafico.nombreODorsal("Despedir");

		if (esNombre) {

			despedirSiExisteEnEquipo(Grafico.pideString("Ingresa el nombre del jugador", "Jugador: "));

		} else {

			System.out.println("Ingresa el dorsal del jugador");
			despedirSiExisteEnEquipo(Grafico.pideEnteroRango(1, 99, "Dorsal"));

		}

	}

	public static void modificarNombreEquipo() {

		int indice = buscarEquipo(Grafico.pideString("Introduce el nombre del equipo", "Equipo: "));
		String nombre;
		Equipo equipo;

		try {

			equipo = equipos.get(indice);

			nombre = Grafico.pideString("Ingresa el nuevo nombre", "Nombre: ");

			equipo.set_nombreEquipo(nombre);

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	public static void eliminarEquipo() {

		int posicion = buscarEquipo(Grafico.pideString("Introduce el nombre del equipo", "Equipo: "));

		try {

			equipos.remove(posicion);
			System.out.println("Equipo borrado correctamente");

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	public static void mostrarPlantilla() {

		int posicion = buscarEquipo(Grafico.pideString("Introduce el nombre del equipo", "Equipo: "));

		try {

			System.out.println(equipos.get(posicion).get_nombreEquipo() + ":");

			equipos.get(posicion).mostrarPlantilla();

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	public static int buscarEquipo(String nombre) {

		boolean encontrado = false;
		int posicion = 0;
		String nombreABuscar = nombre.toLowerCase();

		do {

			if (equipos.get(posicion).get_nombreEquipo().toLowerCase().equals(nombreABuscar)) {

				encontrado = true;

			} else {

				posicion++;

			}

		} while (posicion < cantidadEquipos && !encontrado);

		return posicion;

	}

	public static int buscarJugador(String nombre) {

		boolean encontrado = false;
		int posicion = 0;
		String nombreABuscar = nombre.toLowerCase();

		while (posicion < cantidadJugadores && !encontrado) {

			if (jugadores.get(posicion).get_nombre().toLowerCase().equals(nombreABuscar)) {

				encontrado = true;

			} else {

				posicion++;

			}

		}

		return posicion;

	}

	public static int buscarJugador(int dorsal) {

		boolean encontrado = false;
		int posicion = 0;

		while (posicion < cantidadJugadores && !encontrado) {

			if (jugadores.get(posicion).get_dorsal() == dorsal) {

				encontrado = true;

			} else {

				posicion++;

			}

		}

		return posicion;

	}

	public static void despedirSiExisteEnEquipo(String nombre) {

		boolean encontrado = false;
		int tamañoEquipos = equipos.size();
		int tamañoJugadores;
		int contador = 0;
		int posicion;
		Equipo equipo;

		do {

			equipo = equipos.get(contador);
			tamañoJugadores = equipo.get_plantilla().size();
			posicion = 0;

			do {

				if (equipo.getJugador(posicion).get_nombre().toLowerCase().equals(nombre.toLowerCase())) {

					encontrado = true;
					equipo.removeIntegrante(posicion);

				} else {

					posicion++;

				}

			} while (tamañoJugadores > contador && !encontrado);

			if (!encontrado) {

				contador++;

			}

		} while (tamañoEquipos > contador && !encontrado);

		if (!encontrado) {

			System.out.println("No es ha encontrado el jugador en ningún equipo");

		}

	}

	public static void despedirSiExisteEnEquipo(int dorsal) {

		boolean encontrado = false;
		int tamañoEquipos = equipos.size();
		int tamañoJugadores;
		int contador = 0;
		int posicion;
		Equipo equipo;

		do {

			equipo = equipos.get(contador);
			tamañoJugadores = equipo.get_plantilla().size();
			posicion = 0;

			do {

				if (equipo.getJugador(posicion).get_dorsal() == dorsal) {

					encontrado = true;
					equipo.removeIntegrante(posicion);

				} else {

					posicion++;

				}

			} while (tamañoJugadores > contador && !encontrado);

			if (!encontrado) {

				contador++;

			}

		} while (tamañoEquipos > contador && !encontrado);

		if (!encontrado) {

			System.out.println("No es ha encontrado el jugador en ningún equipo");

		}

	}

	public static void mostrarJugador() {

		int posicion = buscarJugador(Grafico.pideString("Ingresa el nombre del jugador", "Jugador: "));
		Jugador jugador;

		try {

			jugador = jugadores.get(posicion);
			System.out.println(jugador.get_nombre() + " " + jugador.get_dorsal() + " " + jugador.get_posicion());

		} catch (IndexOutOfBoundsException e) {

			System.out.println("No existe el equipo ingresado");
			System.out.println();

		} catch (Exception e) {

			System.out.println("Error to raro 0.o");
			System.out.println();

		}

	}

	// hace las jornadas con los equipos formados
	public static void hacerJornadas() {

		// esta jornada se usa como base para formar las demas
		Enfrentamiento[] jornadaBase;

		cantidadJornadas = cantidadEquipos % 2 == 0 ? cantidadEquipos - 1 : cantidadEquipos;

		// crea las jornadas vacias
		inicializarJornadas();

		// genera la jornada base
		jornadaBase = jornadaBase();

		ciclarEnfrentamientos(jornadaBase);

	}

	private static void inicializarJornadas() {

		// elimina jornadas anteriormente hechas
		jornadas.clear();

		for (int i = 0; i < cantidadJornadas; i++) {

			jornadas.add(new Jornada(i + 1));

		}

	}

	// genera la jornada base
	private static Enfrentamiento[] jornadaBase() {

		/*
		 * se necesitan copias de la cantidad de equipos y de los equipos mismos para no
		 * modificar los originales
		 */
		int copiaNumEquipos = cantidadEquipos;
		/*
		 * uso un array de Enfrentamiento en vez de Jornada ya que así es menos
		 * complicado el asignar los equipos en un orden especifico
		 */
		Enfrentamiento[] jornada;
		ArrayList<Equipo> copiaEquipos = new ArrayList<>(equipos);

		// si no es par, agrega un equipo con el nombre descansa
		if (cantidadEquipos % 2 != 0) {

			copiaEquipos.add(0, new Equipo("Descansa"));
			copiaNumEquipos++;

		}

		enfrentamientosPorJornada = copiaNumEquipos / 2;
		jornada = new Enfrentamiento[enfrentamientosPorJornada];

		// genera la jornada base
		for (int i = 0; i < enfrentamientosPorJornada; i++) {

			jornada[i] = new Enfrentamiento(copiaEquipos.get(i), copiaEquipos.get(copiaNumEquipos - 1 - i));

		}

		// añade la jornada
		añadirJornada(jornada, 0);

		/*
		 * el .clone() se usa para devolver una copia independiente de la jornada si no
		 * uso el .clone() cualquier cambio que se haga a jornada fuera de este método,
		 * afectará a la jornada guardada en la lista y no queremos eso
		 */
		return jornada.clone();

	}

	private static void ciclarEnfrentamientos(Enfrentamiento[] jornadaBase) {

		int contador;
		Equipo aux;
		/*
		 * ahora uso un array bidimesional de equipos debido a la naturaleza del
		 * algoritmo, hacer esto con Jornadas sería una pesadilla
		 */
		Equipo[][] jornada;
		Equipo[][] jornadaAuxiliar = new Equipo[enfrentamientosPorJornada][2];

		// copia los enfrentamientos a la jornada auxiliar
		for (int i = 0; i < enfrentamientosPorJornada; i++) {

			jornadaAuxiliar[i] = jornadaBase[i].getEnfrentamiento();

		}

		// cicla los enfrentamientos
		for (int i = 1; i < cantidadJornadas; i++) {

			contador = 0;
			jornada = new Equipo[enfrentamientosPorJornada][2];

			// el primer equipo del primer enfrentamiento se queda estático en su posicion
			jornada[0][0] = jornadaAuxiliar[0][0];
			// copia el segundo equipo del primer enfrentamiento
			aux = jornadaAuxiliar[0][1];

			// intercambia los segundos equipos con los que le siguen
			for (int j = contador; j < enfrentamientosPorJornada - 1; j++, contador++) {

				jornada[j][1] = jornadaAuxiliar[j + 1][1];

			}

			/*
			 * intercambia el segundo equipo del ultimo enfrentamiento con el primero del
			 * mismo
			 */
			jornada[contador][1] = jornadaAuxiliar[contador][0];

			// intercambia los primeros equipos con los que le siguen
			for (int j = contador; j > 1; j--, contador--) {

				jornada[j][0] = jornadaAuxiliar[j - 1][0];

			}

			// asigna el ultimo primer equipo con el guardado
			jornada[contador][0] = aux;

			// añade la jornada
			añadirJornada(jornada, i);

			// copia la jornada a la jornada auxiliar
			jornadaAuxiliar = jornada.clone();

		}

	}

	private static void añadirJornada(Enfrentamiento[] jornada, int indice) {

		for (int i = 0; i < jornada.length; i++) {

			jornadas.get(indice).añadirEnfrentamiento(jornada[i]);

		}

	}

	private static void añadirJornada(Equipo[][] jornada, int indice) {

		for (int i = 0; i < jornada.length; i++) {

			jornadas.get(indice).añadirEnfrentamiento(new Enfrentamiento(jornada[i][0], jornada[i][1]));

		}

	}

}
