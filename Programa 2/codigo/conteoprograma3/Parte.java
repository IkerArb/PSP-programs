/**
 * Parte
 *
 * Clase que mantiene una parte de acuerdo a las proxies de conteo en el método del PSP
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 13/02/2016
 * @version 1.0
 */

//&p-Parte
//&b=54
public class Parte{
  int iCantItems;
  int iLDCTotales;
  int iLDCBase;
  int iLDCBorradas;
  int iLDCModificadas;
  int iLDCAgregadas;
  char cTipoParte;
  String sNombreParte;

/**
 * Parte
 *
 * Constructor para la clase Parte en el que recibe como parámetro el nombre de la parte y nombra a la parte.
 *
 * @param sNombreParte es el nombre <code>String</code> de la parte
 *
 */
//&i
public Parte(String sNombreParte){

  //Se saca el nombre de la parte
  this.sNombreParte = sNombreParte;

  //Se inicializan la cantidad de líneas de código en 0s
  iCantItems = 0;
  iLDCBase = 0;
  iLDCBorradas = 0;
  iLDCAgregadas = 0;
  iLDCModificadas = 0;
  iLDCTotales = 0;
}


/**
 * agregaItem
 *
 * Método que permite agregar un ítem a la parte
 *
 */
public void agregaItem(){
  iCantItems++;
}

/**
 * agregaLDCBase
 *
 * Método que permite agregar líneas de código base a la parte
 *
 * @param iLDC es el objeto de tipo <code>int</code> que recibe para definir la cantidad a sumar de líneas base
 *
 */
public void agregaLDCBase(int iLDC){
  iLDCBase += iLDC;
}

/**
 * agregaLDCBorradas
 *
 * Método que permite agregar líneas de código borradas a la parte
 *
 * @param iLDC es el objeto de tipo <code>int</code> que recibe para definir la cantidad a sumar de líneas borradas
 *
 */
public void agregaLDCBorradas(int iLDC){
  iLDCBorradas += iLDC;
}

/**
 * generaResultado
 *
 * Método que permite calcular el total de agregadas y definir el tipo de parte
 *
 * @return un valor <code>char</code> que representa el tipo al que se encuentra
 */
//&i
public char generaResultado(){
  //Hacemos la fórmula para calcular líneas agregadas
  iLDCAgregadas = iLDCTotales - iLDCBase + iLDCBorradas;
  //Revisamos si cayó en error el tipo de parte
  if(iLDCAgregadas<0||iLDCBase<iLDCBorradas||iLDCBase<iLDCModificadas){
    cTipoParte = 'e';
  }
  else{
    //Revisamos si es nueva la parte
    if(iLDCBase==0&&iLDCBorradas==0&&iLDCModificadas==0&&iLDCAgregadas>0){
      cTipoParte = 'n';
    }
    //Revisamos si tienes lineas base
    else if(iLDCBase>0){
      //Revisamos si es reusada
      if(iLDCBorradas==0&&iLDCAgregadas==0&&iLDCModificadas==0){
        cTipoParte = 'r';
      }
      //Revisamos si es base
      else if(iLDCBorradas>0||iLDCAgregadas>0||iLDCModificadas>0){
        cTipoParte = 'b';
      }
    }
  }
  return cTipoParte;
}



/**
 * setLDCModificadas
 *
 * Método que permite establecer las líneas de código modificadas de la parte
 *
 * @param iLDC es el objeto de tipo <code>int</code> que recibe para definir la cantidad de líneas modificadas
 *
 */
public void agregaLDCModificada(){
  iLDCModificadas++;
}

/**
 * setLDCTotales
 *
 * Método que permite agregar las líneas de código totales a la parte
 *
 */
public void agregaLDC(){
  iLDCTotales++;
}

/**
 * getLDCTotales
 *
 * Método que te permite acceder a la cantidad de lineas de codigo totales de la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Lineas de Código Totales
 */
public int getLDCTotales(){
  return iLDCTotales;
}

/**
 * getLDCModificadas
 *
 * Método que te permite acceder a la cantidad de lineas de código modificadas de la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Lineas de Código Modificadas
 */
public int getLDCModificadas(){
  return iLDCModificadas;
}

/**
 * getLDCAgregadas
 *
 * Método que te permite acceder a la cantidad de lineas de código agregadas de la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Lineas de Código Agregadas
 */
public int getLDCAgregadas(){
  return iLDCAgregadas;
}

/**
 * getLDCBorradas
 *
 * Método que te permite acceder a la cantidad de lineas de código borradas de la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Lineas de Código Borradas
 */
public int getLDCBorradas(){
  return iLDCBorradas;
}

/**
 * getLDCBase
 *
 * Método que te permite acceder a la cantidad de lineas de código base de la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Lineas de Código Base
 */
public int getLDCBase(){
  return iLDCBase;
}


/**
 * getCantItems
 *
 * Método que te permite acceder a la cantidad de ítems que tiene la parte
 *
 * @return un valor <code>int</code> que representa la cantidad de Ítems de la parte
 */
public int getCantItems(){
  return iCantItems;
}


/**
 * getNombreParte
 *
 * Método que te permite acceder al nombre de la parte
 *
 * @return un valor de tipo <code>String</code> que representa el nombre de la parte
 */
public String getNombreParte(){
  return sNombreParte;
}
}
