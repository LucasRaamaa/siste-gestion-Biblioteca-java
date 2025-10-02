package model;

public class Libro {

  private int id;
  private String titulo;
  private String autor;
  private boolean disponible;

  public Libro(int id, String titulo, String autor){
    this.id = id;
    this.titulo = titulo;
    this.autor = autor;
    this.disponible = true;
  }

  public int getId(){ return id; }
  public String getTitulo() { return titulo; }
  public String getAutor() { return autor; }
  public boolean isDisponible() { return disponible; }
  public void setDisponible(boolean disponible){ this.disponible = disponible; }

  @Override
  public String toString(){
    return "Libro{" +
        "id: " + id +
        ", Titulo: "+ titulo + '\''+
        ", Autor: "+ autor + '\'' +
        ", Disponible: "+ (disponible ? "Si" : "No") +
        '}';
  }
}
