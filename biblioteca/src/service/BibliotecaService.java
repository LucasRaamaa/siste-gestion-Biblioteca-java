package service;

import model.Libro;
import model.Prestamo;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;


public class BibliotecaService {

  private List<Libro> libros;
  private List<Usuario> usuarios;
  private List<Prestamo> prestamos;

  public BibliotecaService() {
    libros = new ArrayList<>();
    usuarios = new ArrayList<>();
    prestamos = new ArrayList<>();
  }

  public void agregarLibro(Libro libro) {
    libros.add(libro);
    System.out.println("Libro agregado: " + libro);
  }

  public void listarLibros() {
    if (libros.isEmpty()) {
      System.out.println("No hay libros");
    } else {
      libros.forEach(System.out::println);
    }
  }

  public Libro buscarLibroPorId(int id) {
    return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
  }

  public void agregarUsuario(Usuario usuario) {
    usuarios.add(usuario);
    System.out.println("Usuario agregado: " + usuario);
  }

  public void listarUsuarios() {
    if (usuarios.isEmpty()) {
      System.out.println("No hay usuarios aun.");
    } else {
      usuarios.forEach(System.out::println);
    }
  }

  public Usuario buscarUsuarioPorId(int id) {
    for (Usuario u : usuarios) {
      if (u.getId() == id) return u;
    }
    return null;
  }




  // gestionamos los prestamos

  public void prestarLibro(int idPrestamo, int idUsuario, int idLibro){
    Usuario usuario = buscarUsuarioPorId(idUsuario);
    Libro libro = buscarLibroPorId(idLibro);

    if ( usuario == null){
      System.out.println("Usuario no encontrado.");
      return;
    }
    if ( libro == null ){
      System.out.println("Libro no encontrado.");
      return;
    }
    if(!libro.isDisponible()){
      System.out.println("El libro ya esta prestado.");
      return;
    }
    Prestamo prestamo = new Prestamo(idPrestamo, usuario, libro);
    prestamos.add(prestamo);
    libro.setDisponible(false);

    System.out.println("Prestamo realizado: "+ prestamo);
  }
  public void devolverLibro(int idPrestamo) {
    Prestamo prestamo = prestamos.stream()
        .filter(p -> p.getId() == idPrestamo && p.getFechaDevolucion() == null)
        .findFirst()
        .orElse(null);

    if (prestamo == null) {
      System.out.println("Préstamo no encontrado o ya devuelto.");
      return;
    }

    prestamo.devolver();
    System.out.println("Libro devuelto: " + prestamo.getLibro().getTitulo());
  }

  public void listarPrestamos(){
    if(prestamos.isEmpty()){
      System.out.println("No hay prestamos registrados.");
    }else {
      prestamos.forEach(System.out::println);
    }
  }
}
