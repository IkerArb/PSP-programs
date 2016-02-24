/*
 * OrdenadorDeArchivo
 *
 * Clase que ordena los objetos de tipo LectorDeArchivo e imprime ordenados a estos objetos.
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 27/01/2016
 * @version 1.0
 */
//&p-OrdenadorDeArchivo
import java.util.*;

public class OrdenadorDeArchivo{
  LinkedList<LectorDeArchivo> ldaLinkListArchivos;

  /*
   * OrdenadorDeArchivo
   *
   * Constructor para la clase OrdenadorDeArchivo en el que no recibe parámetros
   * pero crea la estructura ordenada vacía.
   *
   */
  public OrdenadorDeArchivo(){
    ldaLinkListArchivos = new LinkedList<LectorDeArchivo>();
  }

  /*
   * addArchivo
   *
   * Método que permite agregar elementos de tipo LectorDeArchivo para ser ordenados de acuerdo a su cantidad
   * de lineas de informacion
   *
   * @param ldaArchivo es el objeto de tipo <code>LectorDeArchivo</code> que recibe para ordenar dentro de la
   * estructura
   *
   */
  //&i
  public void addArchivo(LectorDeArchivo ldaArchivo){

    boolean mayor = true;

    //Primero revisamos si la estructura está vacía
    if(ldaLinkListArchivos.size()==0){
      //Agrego el elemento al primer índice
      ldaLinkListArchivos.add(ldaArchivo);
    }
    //En caso de que no
    else{
      //Ciclo para acomodar a los archivos dentro de la estructura
      for(int i = 0; i<ldaLinkListArchivos.size();i++){

        //Reviso si mi archivo es menor en líneas de información que el archivo en el índice apuntado
        if(ldaArchivo.getCantLineasInfo() < ldaLinkListArchivos.get(i).getCantLineasInfo()){

          //Si es el caso lo agrego en ese índice y termino el ciclo, los demás elementos se recorrerán
          ldaLinkListArchivos.add(i,ldaArchivo);
          mayor = false;
          break;
        }
      }
      //Checo si es el mayor de toda la lista
      if(mayor){
        ldaLinkListArchivos.add(ldaArchivo);
      }
    }
  }

  /*
   * printList
   *
   * Método que permite imprimir el arreglo de archivos ordenados de acuerdo a su cantidad de líneas de información
   * imprime el nombre, la cantidad de líneas en blanco y cantidad de líneas de información
   *
   */
  //&i
  public void printList(){

    int iCantTotalLineasInfo = 0;
    int iCantTotalLineasBlanco = 0;

    //Sección de impresión de datos individuales

    //Hago el ciclo para recorrer todos los elementos de la estructura de archivos
    for (int i = 0;i<ldaLinkListArchivos.size() ;i++ ) {
      //Imprimo nombre
      System.out.println("Nombre del archivo: "+ldaLinkListArchivos.get(i).getNombreArchivo());
      //Imprimo cantidad de líneas en blanco
      System.out.println("Cantidad de líneas en blanco: "+ldaLinkListArchivos.get(i).getCantLineasBlanco());
      //Sumo la cantidad de líneas en blanco
      iCantTotalLineasBlanco+=ldaLinkListArchivos.get(i).getCantLineasBlanco();
      //Imprimo cantidad de líneas con información
      System.out.println("Cantidad de líneas con información: "+ldaLinkListArchivos.get(i).getCantLineasInfo());
      //Sumo la cantidad de líneas con información
      iCantTotalLineasInfo+=ldaLinkListArchivos.get(i).getCantLineasInfo();
      //Imprimo línea separadora
      System.out.println("--------------------------------------------------------------------");
    }

    //Sección de impresión de datos totales

    //Imprimo la cantidad de archivos
    System.out.println("Cantidad de archivos: "+ldaLinkListArchivos.size());
    //Imprimo la cantidad total de líneas en blanco
    System.out.println("Cantidad total de líneas en blanco: "+iCantTotalLineasBlanco);
    //Imprimo la cantidad total de líneas con información
    System.out.println("Cantidad total de líneas con información: "+iCantTotalLineasInfo);
  }
}
