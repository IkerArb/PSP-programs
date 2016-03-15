/**
 * OrdenadorDeArchivo
 *
 * Clase que ordena los objetos de tipo LectorDeArchivo e imprime ordenados a estos objetos.
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 16/01/2016
 * @version 1.0
 */
//&p-OrdenadorDeArchivo
//&b=80
import java.util.*;
import java.io.*;

public class OrdenadorDeArchivo{
  LinkedList<LectorDeArchivo> ldaLinkListArchivos;
  LinkedList<Parte> ptLinkListPartes;
  int iCantLineasTotales;

  /**
   * OrdenadorDeArchivo
   *
   * Constructor para la clase OrdenadorDeArchivo en el que no recibe parámetros
   * pero crea las estructuras ordenadas vacías.
   *
   */
  public OrdenadorDeArchivo(){
    ldaLinkListArchivos = new LinkedList<LectorDeArchivo>();
    ptLinkListPartes = new LinkedList<Parte>();
    iCantLineasTotales = 0;
  }

  /**
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

  /**
   * addParte
   *
   * Método que permite agregar elementos de tipo Parte para ser incluidos en la impresión final
   *
   * @param ptParte es el objeto de tipo <code>Parte</code> que recibe para imprimir
   *
   */
  public void addParte(Parte ptParte ){

    ptLinkListPartes.add(ptParte);
  }

  /**
   * agregaCantLineasTotales
   *
   * Método que permite agregar líneas de código a las líneas totales de todo el programa guardados
   * en el ordenador de archivos
   *
   */
  public void agregaLDCTotales(){
    iCantLineasTotales++;
  }

  /**
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

  /**
   * printPartList
   *
   * Método que permite imprimir el arreglo de partes con sus líneas de código y las líneas
   * de código totales del archivo
   *
   */
  //&i
  public void printPartList(){
    //Abrir buffer para imprimir en archivo
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ConteoLDC.txt"), "utf-8"))){
      System.out.println("PARTES BASE:");
      writer.write("PARTES BASE:\n");
      //Hago el ciclo para recorrer todos los elementos de la estructura de archivos
      for (int i = 0;i<ptLinkListPartes.size() ;i++ ) {
        //Revisamos las partes base y las imprimimos al archivo y en pantalla
        if(ptLinkListPartes.get(i).generaResultado()=='b'){
          System.out.println("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems()+", B="+ptLinkListPartes.get(i).getLDCBase()+
                          ", D="+ptLinkListPartes.get(i).getLDCBorradas()+", M="+ptLinkListPartes.get(i).getLDCModificadas()+
                          ", A="+ptLinkListPartes.get(i).getLDCAgregadas());
          writer.write("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems()+", B="+ptLinkListPartes.get(i).getLDCBase()+
                          ", D="+ptLinkListPartes.get(i).getLDCBorradas()+", M="+ptLinkListPartes.get(i).getLDCModificadas()+
                          ", A="+ptLinkListPartes.get(i).getLDCAgregadas()+"\n");
        }
      }
      System.out.println("--------------------------------------------------------------------");
      writer.write("--------------------------------------------------------------------\n");
      System.out.println("PARTES NUEVAS:\n");
      writer.write("PARTES NUEVAS:\n");
      //Hago el ciclo para recorrer todos los elementos de la estructura de archivos
      for (int i = 0;i<ptLinkListPartes.size() ;i++ ) {
        //Revisamos partes nuevoas y las imprimimos al archivo y en pantalla
        if(ptLinkListPartes.get(i).generaResultado()=='n'){
          System.out.println("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems());
          writer.write("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems()+"\n");
        }
      }
      System.out.println("--------------------------------------------------------------------");
      writer.write("--------------------------------------------------------------------\n");
      System.out.println("PARTES REUSADAS:");
      writer.write("PARTES REUSADAS:\n");
      //Hago el ciclo para recorrer todos los elementos de la estructura de archivos
      for (int i = 0;i<ptLinkListPartes.size() ;i++ ) {
        //Revisamos partes reusadas y las imprimimos al archivo y en pantalla
        if(ptLinkListPartes.get(i).generaResultado()=='r'){
          System.out.println("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems()+", B="+ptLinkListPartes.get(i).getLDCBase());
          writer.write("   "+ptLinkListPartes.get(i).getNombreParte()+":  T="+ptLinkListPartes.get(i).getLDCTotales()+
                          ", I="+ptLinkListPartes.get(i).getCantItems()+", B="+ptLinkListPartes.get(i).getLDCBase()+"\n");
        }
      }
      System.out.println("--------------------------------------------------------------------");
      writer.write("--------------------------------------------------------------------\n");
      //Sección de impresión de datos totales

      //Imprimo la cantidad total de líneas de código
      System.out.println("Total de LDC: "+iCantLineasTotales);
      writer.write("Total de LDC: "+iCantLineasTotales+"\n");
      writer.close();

    }
    catch(IOException e){
      System.out.println("Couldn't open BufferedWriter"+e);
    }


  }
}
