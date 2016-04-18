/**
 * Programa1
 *
 * Clase principal del Programa1 del PSP que ayuda a leer las líneas de información y en blanco de distintos
 * archivos y las ordena por cantidad de líneas de información de maner ascendente, al final imprime este orden
 *
 * Ahora se reutiliza la clase del Programa1 para el Programa2
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 16/01/2016
 * @version 2.0
 */
//&p-Programa1
//&b=21
import java.io.*;

public class Programa1 {
  //&i
  public static void main(String []args){

    //Se crea el objeto de la clase BufferedReader para leer el input del usuario
    try (BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in))){

      //Se crea el String para poder leer el file path que estará ingresando el usuario
      String sFilePath = null;

      //Objeto de tipo OrdenadorDeArchivo que va a estar agregando los archivos a su lista
      OrdenadorDeArchivo odaOrdenador = new OrdenadorDeArchivo();

      //Objeto de tipo Correlacion que va a llevar los acumulados de las parejas en tal archivo
      Correlacion coCalculador = new Correlacion();

      //Objeto de tipo LectorDeArchivo que estará abriendo y contando las líneas de los archivos
      LectorDeArchivo ldaArchivo;

      //Ciclo principal para estar recibieno nombres de archivos de la terminal del usuario
      do {

        //Pido el ingreso del nombre o path del archivo
        System.out.println("********************************************\nFavor de Ingresar el path de los archivos a buscar,\nal finalizar de agregar archivos ingresa enter\n********************************************");
        sFilePath = brInput.readLine();

        if(sFilePath.length()!=0){

          try (FileReader frFile = new FileReader(new File(sFilePath))){

            //Creo el objeto de tipo archivo con el nombre o path ingresado, le asigna el OrdenadorDeArchivo y el objeto de correlación
            ldaArchivo = new LectorDeArchivo(frFile,sFilePath,odaOrdenador, coCalculador);

            //Agrego el objeto de tipo LectorDeArchivo a mi lista ordenada
            odaOrdenador.addArchivo(ldaArchivo);
          }
          //Agarramos la excepción en caso de que haya
          catch(IOException e){
            System.out.println("Ocurrió un problema al abrir el archivo, checa tu path de archivo"+e);
          }
        }
      }while(sFilePath.length() != 0); //El ciclo termina con el enter

      //Imprimo los resultados de calculos
      coCalculador.printResultado();

    }catch(IOException e){
      System.out.println("Couldn't open BufferReader"+e);
    }
  }
}
