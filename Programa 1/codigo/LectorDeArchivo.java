/*
 * LectorDeArchivo
 *
 * Clase que lee la cantidad de lineas en blanco y la cantidad de lineas de información de un archivo
 * al igual que su nombre.
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 27/01/2016
 * @version 1.0
 */

//&p-LectorDeArchivo
import java.io.*;

public class LectorDeArchivo{
  FileReader frArchivo;
  int iCantLineasBlanco;
  int iCantLineasInfo;
  String sNombreArchivo;

  /*
   * LectorDeArchivo
   *
   * Constructor para la clase LectorDeArchivo en el que recibe como parámetro el nombre del archivo
   * y construye el objeto.
   *
   * @param sPathArchivo es el nombre <code>String</code> del archivo
   *
   */
  //&i
  public LectorDeArchivo(FileReader frArchivo, String sPathArchivo){

    //Se saca el nombre del archivo y se abre el mismo
    sNombreArchivo = sPathArchivo;
    this.frArchivo = frArchivo;

    //Se inicializan la cantidad de líneas en 0s
    iCantLineasInfo = 0;
    iCantLineasBlanco = 0;

    //Se manda a llamar el método para contar líneas en blanco y de información
    cuentaLineas();
  }

  /*
   * getCantLineasInfo
   *
   * Método que te permite acceder a la cantidad de lineas de información que tiene el archivo
   *
   * @return un valor <code>int</code> que representa la cantidad de Lineas de Información
   */
  public int getCantLineasInfo(){
    return iCantLineasInfo;
  }

  /*
   * getCantLineasBlanco
   *
   * Método que te permite acceder a la cantidad de lineas en blanco que tiene el archivo
   *
   * @return un valor <code>int</code> que representa la cantidad de Lineas en Blanco que tiene el archivo
   */
  public int getCantLineasBlanco(){
    return iCantLineasBlanco;
  }

  /*
   * getNombreArchivo
   *
   * Método que te permite acceder al nombre del archivo
   *
   * @return un valor <code>String</code> que representa el nombre del archivo
   */
  public String getNombreArchivo(){
    return sNombreArchivo;
  }

  /*
   * cuentaLineas
   *
   * Método que te permite leer el archivo y contar la cantidad de líneas con las que cuenta el archivo y asignarlas
   * a las variables locales de la clase
   *
   */
  //&i
  private void cuentaLineas(){

    //Se crea el objeto que va a leer el stream del archivo
    BufferedReader brLector = null;

    //Se hace el try catch para ver si se puede abrir el archivo
    try{

      //Se abre el archivo y el stream
      brLector = new BufferedReader(this.frArchivo);

      //Se crea la variable para agarrar línea por línea en un string
      String sCurrentLine;

      //Se inicializa un booleano para ver si la línea está en blanco
      boolean bEnBlanco = true;

      //Mientras que siga teniendo contenido el archivo, agarra una línea del stream y asignala a sCurrentLine
      while ((sCurrentLine = brLector.readLine()) != null) {

        //Se lee el string caracter por caracter y siempre en cuando siga estando en blanco la línea
        for(int i = 0; i<sCurrentLine.length() && bEnBlanco;i++){

          //Si el caracter que estamos leyendo está en blanco o es un tabulador, no hacemos nada
          if(sCurrentLine.charAt(i)==' '||sCurrentLine.charAt(i)=='\t'){

          }

          //En caso de que no esté en blanco significa que ya tenemos información
          else{

            //Paramos el for y agregamos a la cantidad de líneas de información
            bEnBlanco = false;
            this.iCantLineasInfo++;
          }
        }

        //Si toda la línea estuvo en blanco
        if(bEnBlanco){

          //Agrega a la cantidad de líneas en blanco
          this.iCantLineasBlanco++;
        }

        //Vuelve a asignar la variable a verdadero para la siguiente línea
        bEnBlanco = true;
			}
    }
    //Agarramos la excepción en caso de que haya
    catch(IOException e){
      System.out.println("Ocurrió un problema al leer una línea del BufferReader"+e);
    }

    //Hacemo algo después de la excepción
    finally{

      //Tratamos de liberar el stream solo si está en nulo
      try{
        if (brLector!=null){
          brLector.close();
        }
      }

      //Agarramos la excepción en caso de que haya
      catch(IOException ex){
        System.out.println("Error al cerrar el BufferReader"+ex);
      }
    }

  }
}
