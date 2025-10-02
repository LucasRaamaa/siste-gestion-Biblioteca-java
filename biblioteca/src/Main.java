import model.Libro;
import model.Usuario;
import service.BibliotecaService;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BibliotecaService biblioteca = new BibliotecaService();
    int opcion;

    do {
      System.out.println("\n --- Sistema de Gestion de Biblioteca ---");
      System.out.println("1. Agregar Libro");
      System.out.println("2. Listar Libros");
      System.out.println("3. Agregar Usuarios");
      System.out.println("4. Listar Usuarios");
      System.out.println("5. Prestar Libro");
      System.out.println("6. Devolver Libro");
      System.out.println("7. Listar Prestamos");
      System.out.println("0. Salir");
      System.out.println("Seleccione una opcion: ");

      opcion = sc.nextInt();
      sc.nextLine(); // limpiamos el buffer

      switch (opcion) {
        case 1:
          int idLibro = InputUtils.leerEntero(sc, "Ingrese ID del libro: ");
          String titulo = InputUtils.leerTexto(sc, "Ingrese título: ");
          String autor = InputUtils.leerTexto(sc, "Ingrese autor: ");
          biblioteca.agregarLibro(new Libro(idLibro, titulo, autor));
          break;

        case 2:
          biblioteca.listarLibros();
          break;

        case 3:
          int idUsuario = InputUtils.leerEntero(sc, "Ingrese ID del usuario: ");
          String nombre = InputUtils.leerTexto(sc, "Ingrese nombre: ");
          String email = InputUtils.leerTexto(sc, "Ingrese email: ");
          biblioteca.agregarUsuario(new Usuario(idUsuario, nombre, email));
          break;

        case 4:
          biblioteca.listarUsuarios();
          break;

        case 5:
          int idPrestamo = InputUtils.leerEntero(sc, "Ingrese ID del préstamo: ");
          int uId = InputUtils.leerEntero(sc, "Ingrese ID del usuario: ");
          int lId = InputUtils.leerEntero(sc, "Ingrese ID del libro: ");
          biblioteca.prestarLibro(idPrestamo, uId, lId);
          break;

        case 6:
          int idDev = InputUtils.leerEntero(sc, "Ingrese ID del préstamo a devolver: ");
          biblioteca.devolverLibro(idDev);
          break;

        case 7:
          biblioteca.listarPrestamos();
          break;

        case 0:
          System.out.println("Saliendo del sistema...");
          break;

        default:
          System.out.println("Opción inválida.");
      }
    } while (opcion != 0);

    sc.close();
  }
}
