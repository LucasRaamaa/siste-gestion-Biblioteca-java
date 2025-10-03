package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
  private static final String USER = "root";
  private static final String PASSWORD = "root123";

  public static Connection getConnection(){
    try{
      return DriverManager.getConnection(URL, USER, PASSWORD);
    }catch (SQLException e){
      System.out.println("ERROR, al conectar a la base de datos");
      e.printStackTrace();
      return null;
    }
  }

  // testing with conexion

  public static void main (String[] args){
    Connection conn = DBConnection.getConnection();
    if (conn != null){
      System.out.println("Conexion Exitosa...");
    }
  }

}
