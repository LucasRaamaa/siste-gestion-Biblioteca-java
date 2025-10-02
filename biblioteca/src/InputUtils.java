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

  public static double leerDouble(Scanner sc, String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String entrada = sc.nextLine();
      try {
        return Double.parseDouble(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Debe ingresar un numero valido (ej: 6.5).");
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

}


