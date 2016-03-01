/**
 * LectorDeArchivo
 *
 * Clase que lee la cantidad de lineas en blanco y la cantidad de lineas de información de un archivo
 * al igual que su nombre. En la version se le agrego la funcionalidad de contar líneas de código y
 * asociar el OrdenadorDeArchivo asignado a el programa, al igual utiliza los regexes para definir
 * como se suman las líneas. Ahora se le agregó la funcionalidad de detectar parejas ordenadas de
 * números para poder calcular factores de correlacion
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 29/02/2016
 * @version 3.0
 */

//&p-LectorDeArchivo
//&b=83
import java.io.*;
import java.util.regex.*;

public class LectorDeArchivo{
  FileReader frArchivo;
  int iCantLineasBlanco;
  int iCantLineasInfo;
  String sNombreArchivo;

  OrdenadorDeArchivo odaOrdenador;
  
  Correlacion coCalculador;

  /**
   * LectorDeArchivo
   *
   * Constructor para la clase LectorDeArchivo en el que recibe como parámetro el nombre del archivo
   * y construye el objeto.
   *
   * @param sPathArchivo es el nombre <code>String</code> del archivo
   *
   */
  //&i
  public LectorDeArchivo(FileReader frArchivo, String sPathArchivo,OrdenadorDeArchivo odaOrdenador, Correlacion coCorrelacion){

    //Se saca el nombre del archivo y se abre el mismo
    sNombreArchivo = sPathArchivo;
    this.frArchivo = frArchivo;

    //Se saca el ordenador asignado para variables globales
    this.odaOrdenador = odaOrdenador;
    
    //Se agrega el objeto de correlaciones
    this.coCalculador = coCorrelacion;

    //Se inicializan la cantidad de líneas en 0s
    iCantLineasInfo = 0;
    iCantLineasBlanco = 0;

    //Se manda a llamar el método para contar líneas en blanco y de información
    //cuentaLineas();
    
    //Se manda a llamar el método para contar archivo de correlacion
    cuentaCorrelacion();
  }

  /**
   * getCantLineasInfo
   *
   * Método que te permite acceder a la cantidad de lineas de información que tiene el archivo
   *
   * @return un valor <code>int</code> que representa la cantidad de Lineas de Información
   */
  public int getCantLineasInfo(){
    return iCantLineasInfo;
  }

  /**
   * getCantLineasBlanco
   *
   * Método que te permite acceder a la cantidad de lineas en blanco que tiene el archivo
   *
   * @return un valor <code>int</code> que representa la cantidad de Lineas en Blanco que tiene el archivo
   */
  public int getCantLineasBlanco(){
    return iCantLineasBlanco;
  }

  /**
   * getNombreArchivo
   *
   * Método que te permite acceder al nombre del archivo
   *
   * @return un valor <code>String</code> que representa el nombre del archivo
   */
  public String getNombreArchivo(){
    return sNombreArchivo;
  }

  /**
   * cuentaLineas
   *
   * Método que te permite leer el archivo y contar la cantidad de líneas con las que cuenta el archivo y asignarlas
   * a las variables locales de la clase
   *
   */
  //&i
  private void cuentaLineas(){

    boolean bEnComentario = false;

    //Se crea el objeto que va a leer el stream del archivo
    BufferedReader brLector = null;

    Parte ptParte = null;

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

        //Comienza la secuencia de ifs para checar las expresiones regulares

        //Primero se revisa si se encuentra en estado de comentario de línea múltiple
        if(!bEnComentario){
          //Luego se revisa si se abrirá comentario de línea múltiple
          if(Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\*.*",sCurrentLine)){
            //Luego se revisa si el comentario de línea múltiple no se cierra en la misma línea
            if(!Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\*.*\\*\\/",sCurrentLine)){
              bEnComentario = true;
            }
          }
          //Se revisa si el comentario de línea múltiple viene con contenido antes de él
          else if(Pattern.matches("[ |\\t]*\\S+[ |\\t]*\\/\\*.*",sCurrentLine)){
            //Se revisa si no se cierra en la misma línea
            if(!Pattern.matches("[ |\\t]*\\S+[ |\\t]*\\/\\*.*\\*\\/",sCurrentLine)){
              bEnComentario = true;
            }
            //Hacemos la lógica se sumar líneas de código, primero sumamos a la parte en casa de que exista
            if(ptParte!=null){
              ptParte.agregaLDC();
            }
            //... y luego sumamos al total de líneas de código
            odaOrdenador.agregaLDCTotales();
          }
          //Revisamos si existen comentarios de línea sencilla con contenido antes
          else if(Pattern.matches("([ |\\t]|\\S)*\\S+([ |\\t]|\\S)*\\/\\/.*",sCurrentLine)){
            //Revisamos si existen comentarios de líneas modificadas al final de contenido
            if(Pattern.matches("([ |\\t]|\\S)*\\S+([ |\\t]|\\S)*\\/\\/[&][m][ |\\t]*",sCurrentLine)){
              //Si existe la parte lo sumamos
              if(ptParte!=null){
                ptParte.agregaLDCModificada();
              }
            }
            //Si no fue comentario de linea modificada entonces se suma porque tenía contenido antes al comentario
            if(ptParte!=null){
              ptParte.agregaLDC();
            }
            //... y luego sumamos al total de líneas de código
            odaOrdenador.agregaLDCTotales();
          }
          //Luego revisamos si es un comentario sin contenido antes
          else if(Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\/.*",sCurrentLine)){
            //Revisamos si se está definiendo la parte
            if(Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\/[&][p]-[\\w]*[ |\\t]*",sCurrentLine)){
              //Sacamos el nombre de la parte y se lo asignamos a la parte nueva
              ptParte = new Parte(sCurrentLine.substring(sCurrentLine.indexOf("-")+1).trim());
              //Agregamos la parte al ordenador
              odaOrdenador.addParte(ptParte);
            }
            //Si la parte no está en nulo pueden venir tags para...
            if(ptParte!=null){
              //... declarar items
              if(Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\/[&][i][ |\\t]*",sCurrentLine)){
                ptParte.agregaItem();
              }
              //...declarar líneas borradas
              else if(Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\/[\\&][d][=]\\d+[ |\\t]*",sCurrentLine)){
                ptParte.agregaLDCBorradas(Integer.parseInt(sCurrentLine.substring(sCurrentLine.indexOf("=")+1).trim()));
              }
              //...declarar líneas base
              else if (Pattern.matches("([{|}]?[ |\\t]*|[ |\\t]*[{|}]?)\\/\\/[\\&][b][=]\\d+[ \\t]*",sCurrentLine)){
                ptParte.agregaLDCBase(Integer.parseInt(sCurrentLine.substring(sCurrentLine.indexOf("=")+1).trim()));
              }
            }
          }
          //Si no fue ninguna de las anteriores entonces revisamos si no es una línea en blanco o con llaves sencillas
          else if(!Pattern.matches("[ |\\t]*[{|}]?[ |\\t]*",sCurrentLine)){
            //Revisamos si es una línea con espacios o tabuladores ya que en el pasado eran opcionales
            if(!Pattern.matches("[ |\\t]+",sCurrentLine)){
              //Revisamos que la línea no sea vacía
              if(sCurrentLine!=""){
                //Si la parte no es nula entonces sumamos líneas de código
                if(ptParte!=null){
                  ptParte.agregaLDC();
                }
                //... y luego sumamos al total de líneas de código
                odaOrdenador.agregaLDCTotales();
              }
            }
          }
        }
        //Finalmente si estamos en estado de comentario solo checamos si...
        else{
          //...se cierra el comentario
          if(Pattern.matches(".*\\*\\/.*",sCurrentLine)){
            bEnComentario = false;
          }
        }

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

	/**
	 * cuentaCorrelacion
	 *
	 * Método que te permite leer el archivo de correlacion y poder mandar a llamar el método para
	 * sacar estadísticas
	 *
	 */
	//&i
	private void cuentaCorrelacion(){
	
	  //Se crea el objeto que va a leer el stream del archivo
	  BufferedReader brLector = null;
	
	  //Se hace el try catch para ver si se puede abrir el archivo
	  try{
	
	    //Se abre el archivo y el stream
	    brLector = new BufferedReader(this.frArchivo);
	
	    //Se crea la variable para agarrar línea por línea en un string
	    String sCurrentLine;
	    
	    if((sCurrentLine = brLector.readLine()) != null){
	    	if(Pattern.matches("\\d+", sCurrentLine)){
	    		coCalculador.setxk(Float.parseFloat(sCurrentLine.trim()));
	    	}
	    	else{
	    		System.out.println("Error en la entrada");
	    		System.exit(0);
	    	}
	    }
	    
	    //Mientras que siga teniendo contenido el archivo, agarra una línea del stream y asignala a sCurrentLine
	    while ((sCurrentLine = brLector.readLine()) != null) {
	
	      //Comienza la secuencia de ifs para checar las expresiones regulares
	        //Luego se revisa si se abrirá comentario de línea múltiple
	        if(Pattern.matches("\\d+(\\.\\d+)?,\\d+(\\.\\d+)?",sCurrentLine)){
	        	coCalculador.pareja(Float.parseFloat(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).trim()),Float.parseFloat(sCurrentLine.substring(sCurrentLine.indexOf(",")+1).trim()));
	        }
	        else{
	        	System.out.println("Error en la entrada");
	    		System.exit(0);
	        }
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
