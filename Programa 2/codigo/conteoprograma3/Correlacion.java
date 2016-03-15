/**
 * Correlacion
 *
 * Clase que recibe parejas ordenadas para calcular estadísticas y luego poder imprimir las estadísticas
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 29/02/2016
 * @version 1.0
 */

//&p-Correlacion

public class Correlacion{
	float fPromx;
	float fPromy;
	float fSumx;
	float fSumy;
	float fSumx2;
	float fSumy2;
	float fSumxy;
	int iNumParejas;
	float fxk;
	float fBeta1;
	float fBeta0;
	float fr;
	float fr2;
	float fyk;
	
	/**
	  * Correlacion
	  *
	  * Constructor para la clase Correlacion que incializa todos sus valores en 0s
	  *
	  */
	  public Correlacion(){
	    fPromx = 0;
	    fPromy = 0;
	    fSumx = 0;
	    fSumy = 0;
	    fSumx2 = 0;
	    fSumy2 = 0;
	    fSumxy = 0;
	    iNumParejas = 0;
	    fxk = 0;
	    fBeta1 = 0;
	    fBeta0 = 0;
	    fr = 0;
	    fr2 = 0;
	    fyk = 0;
	  }
	  
	  /**
	   * setxk
	   * 
	   * Método que recibe el valor de xk y lo asigna a la variable de la clase para poderlo utilizar después
	   * 
	   * @param fxk
	   */
	  
	  public void setxk(float fxk){
		  this.fxk = fxk;
	  }
	  
	  /**
	   * pareja
	   * 
	   * Metodo que recibe la pareja a analizar en la correlacion y calcula las variables globales que tienen
	   * que ser actualizadas con cada entrada nueva de datos (sumatorias, cantidad de parejas y promedios)
	   * 
	   * @param x es un dato de tipo <code>float</code> que representa un dato más en la columna de las x
	   * @param y es un dato de tipo <code>float</code> que representa un dato más en la columna de las y
	   */
	  
	  //&i
	  public void pareja(float x, float y){
		  iNumParejas++;
		  fSumx+=x;
		  fSumy+=y;
		  fSumxy+=(x*y);
		  fSumx2+=Math.pow(x, 2);
		  fSumy2+=Math.pow(y, 2);
		  fPromx = fSumx/iNumParejas;
		  fPromy = fSumy/iNumParejas;
	  }
	  
	  /**
	   * printResultado
	   * 
	   * Método que se llama para imprimir los resultados de las parejas, este método ya calcula las betas, indices de correlación,
	   * indice de correlación cuadrado y el yk, imprimiéndolos al final según el formato solicitado
	   * 
	   */
	  
	  //&i
	  public void printResultado(){
		  if((fSumx2-(iNumParejas*Math.pow(fPromx, 2)))!=0){
			  fBeta1=(float) ((fSumxy-(iNumParejas*fPromx*fPromy))/(fSumx2-(iNumParejas*Math.pow(fPromx, 2))));
			  fBeta0=fPromy-(fBeta1*fPromx);
		  }
		  if((Math.sqrt((iNumParejas*fSumx2-Math.pow(fSumx, 2))*(iNumParejas*fSumy2-Math.pow(fSumy, 2))))!=0){
			  fr = (float) ((iNumParejas*fSumxy-(fSumx*fSumy))/(Math.sqrt((iNumParejas*fSumx2-Math.pow(fSumx, 2))*(iNumParejas*fSumy2-Math.pow(fSumy, 2)))));
			  fr2 = (float) Math.pow(fr, 2);
		  }
		  fyk = fBeta0 + fBeta1*fxk;
		  
		  System.out.println("N = "+iNumParejas);
		  System.out.println("xk = "+String.format("%.0f", fxk));
		  System.out.println("r = "+String.format("%.5f", fr));
		  System.out.println("r2 = "+String.format("%.5f", fr2));
		  System.out.println("b0 = "+String.format("%.5f", fBeta0));
		  System.out.println("b1 = "+String.format("%.5f", fBeta1));
		  System.out.println("yk = "+String.format("%.5f", fyk));
	  }
}