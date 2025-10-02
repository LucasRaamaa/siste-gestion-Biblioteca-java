package model;
import java.time.LocalDate;

public class Prestamo {
  private int id;
  private Usuario usuario;
  private Libro libro;
  private LocalDate fechaPrestamo;
  private LocalDate fechaDevolucion;

  public Prestamo(int id, Usuario usuario, Libro libro){
    this.id = id;
    this.usuario = usuario;
    this.libro = libro;
    this.fechaPrestamo = LocalDate.now(); // Devuelve la fecha actual al crear el prestamo
    this.fechaDevolucion = null; // aun no devuelto
  }
  public int getId() { return id; }
  public Usuario getUsuario(){ return usuario; }
  public Libro getLibro() { return libro; }
  public LocalDate getFechaPrestamo(){ return fechaPrestamo; }
  public LocalDate getFechaDevolucion() { return fechaDevolucion; }

  public void devolver(){
    this.fechaDevolucion = LocalDate.now();
    this.libro.setDisponible(true); // Se libera el libro
  }

  @Override
  public String toString(){
  return "Prestamo{"+
      "Id: "+id+
      ", Usuario: "+ usuario.getNombre() +
      ", Libro: " + libro.getTitulo() +
      ", Fecha Prestado: "+ fechaPrestamo+
      ", Fecha de Devolucion: " + (fechaDevolucion != null ? fechaDevolucion : "Pendiente")+
      '}';
  }
}
