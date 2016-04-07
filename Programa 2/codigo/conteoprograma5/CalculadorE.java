/**
 * CalculadorE
 *
 * Clase que calcula la P hasta que cumpla con la precision E deseada, se utiliza para calcular la integracion
 * de Simpson
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 5/04/2016
 * @version 2.0
 */
//&p-CalculadorE
//&b=31
public class CalculadorE {
	double dP1;
	double dP2;
	double dE;
	double dX;
	int iDof;
	int iNum_seg;
	double dP;
	CalculadorP cpCalcP;

	/**
	   * CalculadorE
	   *
	   * Constructor para la clase CalculadorE en el que recibe como parámetro la X y los Grados de Libertad.
	   *
	   * @param dX es el <code>double</code> que representa la X ingresada
	   * @param iDof es el <code>int</code> que representa los grados de libertad
	   *
	   */
	//&i
	public CalculadorE(double dX, int iDof){
		this.dX = dX;
		this.iDof = iDof;
		dE = 0.0000001;
		calculaP();
	}

	/**
	   * CalculaP
	   *
	   * Metodo utilizado para calcular la p con el grado de precision E que se desea, se cicla hasta lograrlo
	   *
	   */
	//&i
	public void calculaP(){
		//Empezamos con un numero de segmentos arbitrario, en nuestro caso sera 10
		iNum_seg = 100;

		double dGammaDenominador;
    double dGammaNumerador;
		double dGammaConst;
		double dPotenciaDof;
		//Se calcula el valor de gamma constante
    if(iDof%2 == 0){
      dGammaDenominador = calculaGammaEntera(iDof/2);
    }
    else{
      dGammaDenominador = calculaGammaFraccion(iDof/2.0);
    }
    if((iDof+1)%2==0){
      dGammaNumerador = calculaGammaEntera((iDof+1)/2);
    }
    else{
      dGammaNumerador = calculaGammaFraccion((iDof+1)/2.0);
    }
		//Aqui se saca el resultado
		dGammaConst = dGammaNumerador/((Math.pow(iDof*Math.PI,0.5))*dGammaDenominador);
		//Se calcula el valor de Potencia constante
		dPotenciaDof = -1*((iDof+1)/2.0);
		//Se crea el objeto de tipo CalculadoraP para aplicar la formula necesaria con las sumatorias
		cpCalcP = new CalculadorP(dX,iDof,iNum_seg,dGammaConst,dPotenciaDof); //&m

		//Se asigna el valor calculado para la p dado el numero de segmentos, la x y los grados de libertad
		dP1 = cpCalcP.calculaP();
		//Se aumenta al doble el numero de segmentos
		iNum_seg *= 2;
		//Se cambia el numero de segmentos en el objeto
		cpCalcP.setNum_seg(iNum_seg);
		//Se calcula otra vez la p pero se asigna en una segunda variable
		dP2 = cpCalcP.calculaP();
		//Analizamos si el valor absoluto entre estas dos cumple con el grado de precision, sino se cicla
		while(Math.abs(dP1-dP2)>=dE){
			//Se asigna la P1 con la ultima que hicimos
			dP1 = dP2;
			//Se aumenta al doble el numero de segmentos
			iNum_seg *= 2;
			//Se cambia el numero de segmentos enel objeto
			cpCalcP.setNum_seg(iNum_seg);
			//Se asigna la P2 con el valor mas reciente
			dP2 = cpCalcP.calculaP();
		}
		//Cuando cumplimos con el grado de precision, se asigna el valor calculado mas reciente o bien P2
		dP = dP2;
	}

	/**
	   * Print
	   *
	   * Metodo utilizado para imprimir los valores de X, grados de libertad y la p que ya queda con los grados de
	   * libertad deseados
	   *
	   */
	//&i
	public void print(){
		System.out.println("x   = "+ String.format("%.5f", dX));
		System.out.println("dof = "+ iDof);
		System.out.println("p   = "+ String.format("%.5f", dP));
	}

	/**
	   * setX
	   *
	   * Metodo set de la variable x para que cambie el valor de la instancia, al momento de cambiarla se ejecuta el calculaP
	   *
	   * @param dX es el <code>double</code> que representa la X ingresada que se va a cambiar por la anterior
	   *
	   */
	public void setX(double dX){
		this.dX = dX;
		//Cuando se cambia el valor de x lo más conveniente es cambiar el valor de p con el metodo calculaP()
		calculaP();
	}

	/**
	   * getP
	   *
	   * Metodo que te permite sacar el valor de p guardado en la instancia
	   *
	   * @return un valor <code>double</code> que representa el valor de la variable p de la clase
	   */
	public double getP(){
		return dP;
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
