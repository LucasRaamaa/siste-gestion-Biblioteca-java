package dao;

import database.DBConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

  //insertar usuario
  public void agregarUsuario(Usuario usuario){
    String sql = "INSERT INTO usuarios (id, nombre, email) VALUES (?,?,?)";

    try(Connection conn = DBConnection.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setInt(1, usuario.getId());
      stmt.setString(2, usuario.getNombre());
      stmt.setString(3, usuario.getEmail());

      stmt.executeUpdate();
      System.out.println("Usuario insertado en MySQL: "+ usuario);
    } catch(SQLException e){
      System.out.println("ERROR al insertar usuario");
      e.printStackTrace();
    }
  }

  //Listar usuario
  public void listarUsuarios() {
    String sql = "SELECT * FROM usuarios";

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String email = rs.getString("email");

        System.out.println("Usuario [ID=" + id + ", Nombre=" + nombre + ", Email=" + email + "]");
      }

    } catch (SQLException e) {
      System.out.println("Error al listar usuarios");
      e.printStackTrace();
    }
  }

  public Usuario buscarPorId(int idBuscado) {
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idBuscado);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
      }
    } catch (SQLException e) {
      System.out.println("⚠️ Error al buscar usuario: " + e.getMessage());
    }
    return null;
  }
}


