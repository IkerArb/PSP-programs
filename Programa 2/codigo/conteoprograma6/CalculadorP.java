/**
 * CalculadorP
 *
 * Clase que calcula la sumatoria de P, sin considerar la precision
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 15/04/2016
 * @version 1.0
 */
//&p-CalculadorP
//&b=47
public class CalculadorP {
	double dSumP;
	int iDof;
	double dX;
	int iNum_seg;
	double dGammaConst;
	double dPotenciaDof;

	/**
	   * CalculadorP
	   *
	   * Metodo constructor de la clase CalculadorP
	   *
	   * @param dX un valor <code>double</code> que representa el valor inicial de la X
	   * @param iDof un valor <code>int</code> que representa el valor inicial de los grados de libertad
	   * @param iNum_seg un valor <code>int</code> que representa el valor inicial de los segmentos a utilizar
	   */
	public CalculadorP(double dX,int iDof, int iNum_seg, double dGammaConst, double dPotenciaDof){//&m
		this.dX = dX;
		this.iDof = iDof;
		this.iNum_seg = iNum_seg;
		this.dGammaConst = dGammaConst;
		this.dPotenciaDof = dPotenciaDof;
	}

	/**
	   * setNum_seg
	   *
	   * Metodo que te permite cambiar el valor de iNum_seg
	   *
	   * @param iNum_seg un valor <code>int</code> que representa el nuevo valor
	   */
	public void setNum_seg(int iNum_seg){
		this.iNum_seg = iNum_seg;
	}

	/**
	   * calculaP
	   *
	   * Metodo que te permite calcular la p
	   *
	   * @return un valor <code>double</code> que representa el resultado de la funcion
	   */
	//&i
	public double calculaP(){
		//Declaramos una variable para la w
		double dW;
    int cont = 1;
		//Calculamos la w
		dW = (double)(dX/iNum_seg);
		//Inicializamos la sumatoria de p con el valor de 0
		dSumP = dW/3* calculaTDist(0);

		//Hasta que lleguemos una antes del valor de X, aumentando en w unidades
		for(double dXi = dW; dXi < dX; dXi+=dW){
			//si es par
			if((cont % 2)==0){
				//la multiplicacion se hace por 4
				dSumP+=dW/3*4*calculaTDist(dXi);
			}
			//si es non
			else{
				//la multiplicacion se hace por 2
				dSumP+=dW/3*2*calculaTDist(dXi);
			}
      cont++;
		}
		//Calculamos la ultima con el valor de x
		dSumP+=dW/3*calculaTDist(dX);
		//Regresamos la sumatoria
		return dSumP;
	}

	/**
	   * calculaTDist
	   *
	   * Metodo que te permite calcular la distribucion t para un numero dado
	   *
	   * @param dXi representa el valor <code>double</code> de la distribucion t que se quiere calcular
	   * @return un valor <code>double</code> que representa el resultado de la funcion
	   */
	//&i
	public double calculaTDist(double dXi){
		//Se declara una variable para la distribucion t
		double dT;
		//Se realiza el lado derecho de la funcion t
		dT = Math.pow((1 + (dXi*dXi)/iDof),dPotenciaDof)*dGammaConst;
		//Regresamos la distrubucion t
		return dT;
	}

	/**
	   * calculaGammaEntera
	   *
	   * Metodo que te permite calcular la funcion gamma de un numero dado cuando es entero
	   *
	   * @param dNum representa el valor <code>double</code> de la gamma que se quiere calcular
	   * @return un valor <code>double</code> que representa el resultado de la funcion
	   */
	//&i
	public double calculaGammaEntera(double dNum){
		//La gamma siempre empieza en 1
		double dGamma = 1;
		//Se reduce en 1 el numero que nos mandan
		dNum--;
			//Mientras que el numero sea mayor que 1
			while(dNum>1){
				//Haz la multiplicacion con lo que se tiene en gamma
				dGamma*=dNum;
				//Reduce el numero
				dNum--;
			}

		//Regresamos gamma
		return dGamma;
	}


  /**
	   * calculaGammaFraccion
	   *
	   * Metodo que te permite calcular la funcion gamma de un numero dado cuando es fraccion
	   *
	   * @param dNum representa el valor <code>double</code> de la gamma que se quiere calcular
	   * @return un valor <code>double</code> que representa el resultado de la funcion
	   */
  //&i
  public double calculaGammaFraccion(double dNum){
    double dGamma = 1;
    dNum--;
    //Mientras que el numero sea mayor o igual a 0.5
    while(dNum>=0.5){
      //Haz la multiplicacion con lo que se tiene en gamma
      dGamma*=dNum;
      //Reduce el numero
      dNum--;
    }
    //Como es decimal se tiene que multiplicar por la raiz de PI
    dGamma*=Math.sqrt(Math.PI);

    return dGamma;
  }
}
