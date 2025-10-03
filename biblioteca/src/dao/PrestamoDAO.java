package dao;

import database.DBConnection;
import model.Libro;
import model.Prestamo;
import model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class PrestamoDAO {

  public void registrarPrestamo(Prestamo prestamo) {
    String sql = "INSERT INTO prestamos (id, id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, prestamo.getId());
      stmt.setInt(2, prestamo.getUsuario().getId());
      stmt.setInt(3, prestamo.getLibro().getId());
      stmt.setDate(4, Date.valueOf(prestamo.getFechaPrestamo()));
      if (prestamo.getFechaDevolucion() != null) {
        stmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucion()));
      } else {
        stmt.setNull(5, Types.DATE);
      }
      stmt.executeUpdate();
      System.out.println("Préstamo registrado: " + prestamo);
    } catch (SQLException e) {
      System.out.println("ERROR al registrar préstamo: " + e.getMessage());
    }
  }

  public void devolverPrestamo(int idPrestamo) {
    String sql = "UPDATE prestamos SET fecha_devolucion = ? WHERE id = ? AND fecha_devolucion IS NULL";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setDate(1, Date.valueOf(LocalDate.now()));
      stmt.setInt(2, idPrestamo);
      int filas = stmt.executeUpdate();
      if (filas > 0) {
        System.out.println("Préstamo devuelto (ID=" + idPrestamo + ")");
      } else {
        System.out.println("No se encontró el préstamo o ya fue devuelto.");
      }
    } catch (SQLException e) {
      System.out.println("ERROR al devolver préstamo: " + e.getMessage());
    }
  }

  public void listarPrestamos() {
    String sql = "SELECT * FROM prestamos";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        int id = rs.getInt("id");
        int idUsuario = rs.getInt("id_usuario");
        int idLibro = rs.getInt("id_libro");
        Date fechaPrestamo = rs.getDate("fecha_prestamo");
        Date fechaDevolucion = rs.getDate("fecha_devolucion");
        System.out.println("Prestamo [ID=" + id +
            ", Usuario=" + idUsuario +
            ", Libro=" + idLibro +
            ", Prestado=" + fechaPrestamo +
            ", Devuelto=" + fechaDevolucion +
            "]");
      }
    } catch (SQLException e) {
      System.out.println("ERROR al listar préstamos: " + e.getMessage());
    }
  }
}
