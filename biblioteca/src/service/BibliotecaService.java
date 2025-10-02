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

  //Validamos duplicados
  public boolean existeLibro(int id){
    return libros.stream().anyMatch(l -> l.getId() == id);
  }
  public boolean existeUsuario(int id){
    return usuarios.stream().anyMatch(u -> u.getId() == id);
  }
  public boolean existePrestamo(int id){
    return prestamos.stream().anyMatch(p -> p.getId() == id);
  }

  public BibliotecaService() {
    libros = new ArrayList<>();
    usuarios = new ArrayList<>();
    prestamos = new ArrayList<>();
  }

  public void agregarLibro(Libro libro) {
    if(existeLibro(libro.getId())){
      System.out.println("El ID del libro ya existe");
      return;
    }
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
    for (Libro l : libros){
      if(l.getId() == id) return l;
    }
    return null;
    //return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
  }

  public void agregarUsuario(Usuario usuario) {
    if(existeUsuario(usuario.getId())){
      System.out.println("Ya existe usuario con ese ID");
      return;
    }
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
    if(existePrestamo(idPrestamo)){
      System.out.println("El libro se encuentra prestado.");
      return;
    }

    Usuario usuario = buscarUsuarioPorId(idUsuario);
    if(usuario == null){
      System.out.println("Usuario no encontrado.");
    }


    Libro libro = buscarLibroPorId(idLibro);
    if (usuario == null){
      System.out.println("Usuario no encontrado.");
      return;
    }
    if (libro == null ){
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
    for (Prestamo p : prestamos) {
      if (p.getId() == idPrestamo) {
        if (p.getFechaDevolucion() != null) {
          System.out.println("El prestamo fue devuelto");
          return;
        }
        p.devolver();
        System.out.println("Libro devuelto: " + p.getLibro().getTitulo());
        return;
      }
    }
    System.out.println("No encontramos el prestado.");
  }


  public void listarPrestamos(){
    if(prestamos.isEmpty()){
      System.out.println("No hay prestamos registrados.");
    }else {
      prestamos.forEach(System.out::println);
    }
  }
}
