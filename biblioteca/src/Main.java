import model.Libro;
import model.Usuario;
import model.Prestamo;
import service.BibliotecaService;

import dao.UsuarioDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;


import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    // mysql
    /*UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario nuevo = new Usuario(1,"Lucas","Lucas@gmail.com");
    usuarioDAO.agregarUsuario(nuevo);
    usuarioDAO.listarUsuarios();*/

    Scanner sc = new Scanner(System.in);
    // ==================== BD   =====================
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    LibroDAO libroDAO = new LibroDAO();
    PrestamoDAO prestamoDAO = new PrestamoDAO();



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
          int idLibro = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del libro: ");
          String titulo = InputUtils.leerTexto(sc, "Ingrese título: ");
          String autor = InputUtils.leerTexto(sc, "Ingrese autor: ");


          Libro nuevoLibro = new Libro(idLibro, titulo, autor);
          libroDAO.agregarLibro(nuevoLibro);
          break;

        case 2:
          libroDAO.listarLIbros();
          break;

        case 3:
          int idUsuario = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del usuario: ");
          String nombre = InputUtils.leerTexto(sc, "Ingrese nombre: ");
          String email = InputUtils.leerEmail(sc, "Ingrese email: ");
          Usuario nuevoUsuario = new Usuario(idUsuario, nombre, email);
          usuarioDAO.agregarUsuario(nuevoUsuario);
          break;

        case 4:
          usuarioDAO.listarUsuarios();
          break;

        case 5:
          int idPrestamo = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del préstamo: ");
          int uId = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del usuario: ");
          int lId = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del libro: ");

          Usuario usuario = usuarioDAO.buscarPorId(uId);
          Libro libro = libroDAO.buscarPorId(lId);

          if (usuario != null && libro != null) {
            Prestamo nuevoPrestamo = new Prestamo(idPrestamo, usuario, libro);
            prestamoDAO.registrarPrestamo(nuevoPrestamo); // acá usás el DAO para guardar en MySQL
          } else {
            System.out.println("Usuario o Libro no encontrado en la base de datos.");
          }
          break;

        case 6:
          int idDev = InputUtils.leerEnterosPositivo(sc, "Ingrese ID del préstamo a devolver: ");
          prestamoDAO.devolverPrestamo(idDev);
          break;

        case 7:
          prestamoDAO.listarPrestamos();
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
