
import java.util.Scanner;

public class InputUtils {

  public static int leerEntero (Scanner sc, String mensaje){
    while(true){
      System.out.print(mensaje);
      String entrada = sc.nextLine();
      try {
        return Integer.parseInt(entrada);
      } catch(NumberFormatException e){
        System.out.println("Debe ingresar un numero valido");
      }
    }
  }

    public static String leerTexto(Scanner sc,String mensaje){
      String texto;
      do{
        System.out.print(mensaje);
        texto = sc.nextLine().trim();
        if(texto.isEmpty()){
          System.out.println("No puede ser vacio.");
        }
      }while(texto.isEmpty());
      return texto;
    }

    public static int leerEnterosPositivo(Scanner sc,String mensaje){
    int valor;
    do{
      valor = leerEntero(sc, mensaje);
      if(valor <= 0) {
        System.out.println("El numero debe ser positivo");
      }
    }while(valor <= 0);
    return valor;
    }

    public static String leerEmail(Scanner sc, String mensaje){
      while (true){
        String email = leerTexto(sc, mensaje);
        if (email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
          return email;
        }
        System.out.println("Email invalido, intente nuevamente.");
      }
    }


}


