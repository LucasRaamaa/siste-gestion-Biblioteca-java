package dao;
import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Libro;
import java.sql.*;

public class LibroDAO {
  public void agregarLibro(Libro libro){
    String sql = "INSERT INTO libros (id, titulo, autor, disponible) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, libro.getId());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setBoolean(4, libro.isDisponible());
            stmt.executeUpdate();
      System.out.println("Libro insertado en Base de datos: "+ libro);
    }catch(SQLException e) {
      System.out.println("Error al insertar el libro: "+ e.getMessage());
    }
  }

  public void listarLIbros(){
    String sql = "SELECT * FROM libros";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
      while(rs.next()){
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String autor = rs.getString("autor");
        boolean disponible = rs.getBoolean("disponible");
        System.out.println(
            "Libro [ID: " + id +
                ", Titulo: " + titulo +
                ", Autiro: " + autor +
                ", Diponible: "+ disponible +
                "]");
      }
    }catch (SQLException e){
      System.out.println("ERROR al listar libros: "+ e.getMessage());
    }
  }

  public Libro buscarPorId(int idBuscado) {
    String sql = "SELECT * FROM libros WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idBuscado);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
      }
    } catch (SQLException e) {
      System.out.println("ERROR al buscar libro: " + e.getMessage());
    }
    return null;
  }
}
