package vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Enfrentamiento;
import modelo.Equipo;
import modelo.Jornada;
import modelo.Tabla;

public class Grafico {

	private static Scanner sc = new Scanner(System.in);

	public static int pideEnteroRango(int inicio, int fin, String mensaje) {

		boolean valido = false;
		int numero = 0;

		do {

			try {

				System.out.print(mensaje);
				numero = sc.nextInt();

				if (numero >= inicio && numero <= fin) {

					valido = true;

				} else {

					System.out.println("No está en el rango pedido");

				}

			} catch (InputMismatchException e) {

				System.out.println("Primo, pon un número porfa");

			} catch (Exception e) {

				System.out.println("Error to random");

			} finally {

				sc.nextLine();
				System.out.println();

			}

		} while (!valido);

		return numero;

	}

	public static String pideString(String mensaje, String mensajeUsuario) {

		String cadena;

		System.out.println(mensaje);
		System.out.print(mensajeUsuario);
		cadena = sc.nextLine();
		System.out.println();

		return cadena;

	}

	public static int bienvenida() {

		int numeroEquipos;

		// aunque no parezca, es el mensaje de bienvenida
		System.out.println("\033[1;92m");
		System.out.println("  ____  _                           _     _               _                         \r\n"
				+ " |  _ \\(_)                         (_)   | |             | |                        \r\n"
				+ " | |_) |_  ___ _ ____   _____ _ __  _  __| | ___     __ _| |                        \r\n"
				+ " |  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\   / _` | |                        \r\n"
				+ " | |_) | |  __/ | | \\ V /  __/ | | | | (_| | (_) | | (_| | |                        \r\n"
				+ " |____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/   \\__,_|_|                        \r\n"
				+ "      _                 _           _                  _        _ _                 \r\n"
				+ "     (_)               | |         | |                | |      | (_)                \r\n"
				+ "  ___ _ _ __ ___  _   _| | __ _  __| | ___  _ __    __| | ___  | |_  __ _  __ _ ___ \r\n"
				+ " / __| | '_ ` _ \\| | | | |/ _` |/ _` |/ _ \\| '__|  / _` |/ _ \\ | | |/ _` |/ _` / __|\r\n"
				+ " \\__ \\ | | | | | | |_| | | (_| | (_| | (_) | |    | (_| |  __/ | | | (_| | (_| \\__ \\\r\n"
				+ " |___/_|_| |_| |_|\\__,_|_|\\__,_|\\__,_|\\___/|_|     \\__,_|\\___| |_|_|\\__, |\\__,_|___/\r\n"
				+ "                                                                     __/ |          \r\n"
				+ "                                                                    |___/           ");
		System.out.println("\033[0m");
		System.out.println("Bienvenido al creador de ligas, en este programa podrás crear, gestionar, simular");
		System.out.println("y muchas cosas más.");
		System.out.println();
		System.out.println("¿Cuántos equipos habrá en la liga?");

		numeroEquipos = pideEnteroRango(3, 100, "3 - 100: ");

		return numeroEquipos;

	}

	// muestra las jornadas por consola
	public static void mostrarJornadas(ArrayList<Jornada> jornadas) {

		int tamaño = jornadas.size();

		if (tamaño > 2) {

			// muestra cuantas jornadas hay
			System.out.println("hay " + tamaño + " jornadas");
			System.out.println();

			// va mostrando las jornadas
			for (Jornada jornada : jornadas) {

				// muestra el numero de jornada
				System.out.println("jornada " + jornada.getNumeroJornada() + ":");
				System.out.println();
				// muestra los enfrentamientos de cada jornada
				mostrarEnfrentamientos(jornada.getEnfrentamientos());
				System.out.println();

			}

		} else {

			// muestra esto si no hay jornadas
			System.out.println("no hay jornadas formadas");
			System.out.println();

		}

	}

	// muestra los equipos por consola
	public static void mostrarEquipos(ArrayList<Equipo> equipos) {

		int tamaño = equipos.size();
		String plural = "";

		if (tamaño > 0) {

			if (tamaño > 1) {

				// capricho mio
				plural += "s";

			}

			// muestra cuantos equipos hay
			System.out.println("hay " + tamaño + " equipo" + plural + ":");
			System.out.println();

			for (Equipo equipo : equipos) {

				// muestra el nombre de cada equipo
				System.out.println(equipo.get_nombreEquipo());

			}

			System.out.println();

		} else {

			// muestra esto si no hay equipos
			System.out.println("no hay equipos formados");
			System.out.println();

		}

	}

	public static int menu(String[] opciones, String mensaje) {

		int opcion;

		imprimeMenu(opciones);

		opcion = pideEnteroRango(1, opciones.length, mensaje);

		return opcion;

	}

	public static void imprimeTabla(Tabla tabla) {
		
		
		
	}
	
	private static void imprimeMenu(String[] opciones) {

		for (String opcion : opciones) {

			System.out.println(opcion);

		}

	}

	public static boolean nombreODorsal(String mensaje) {

		int opcion;

		imprimeNombreODorsal(mensaje);

		opcion = pideEnteroRango(1, 2, "Opcion: ");

		return opcion == 1;

	}

	private static void mostrarEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) {

		for (Enfrentamiento enfrentamiento : enfrentamientos) {

			if (!enfrentamiento.getEquipo1().get_nombreEquipo().equals("Descansa")) {

				System.out.println(enfrentamiento.getEquipo1().get_nombreEquipo() + " vs "
						+ enfrentamiento.getEquipo2().get_nombreEquipo());

			} else {

				System.out.println(enfrentamiento.getEquipo1().get_nombreEquipo()
						+ enfrentamiento.getEquipo2().get_nombreEquipo());

			}

		}

	}

	private static void imprimeNombreODorsal(String mensaje) {

		System.out.println("1. " + mensaje + " por nombre");
		System.out.println("2. " + mensaje + " por dorsal");
		System.out.println();

	}

}