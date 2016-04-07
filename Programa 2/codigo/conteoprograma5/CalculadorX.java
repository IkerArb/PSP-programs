/**
 * CalculadorX
 *
 * Clase que calcula la X hasta que cumpla con la precision E deseada, se utiliza para calcular la integracion
 * de Simpson
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 04/04/2016
 * @version 1.0
 */
//&p-CalculadorX

public class CalculadorX {
	CalculadorE ceCalcE;
	double dX1;
	double dX2;
	double dP1;
	double dP2;
	double dD;
	double dE;
	double dP;
	int iDof;
	double dX;

	/**
	   * CalculadorX
	   *
	   * Constructor para la clase CalculadorX en el que recibe como parámetro la P y los Grados de Libertad.
	   *
	   * @param dP es el <code>double</code> que representa la P ingresada
	   * @param iDof es el <code>int</code> que representa los grados de libertad
	   *
	   */
	//&i
	public CalculadorX(double dP, int iDof){
		this.dP = dP;
		this.iDof = iDof;
		//Nuestro primer incremento será de 1
		dD = 1;
		//Empezamos con la tolerancia de 0.0000001
		dE = 0.0000001;
		//Mandamos a llamar al metodo calculaX
		calculaX();
	}

	/**
	   * CalculaX
	   *
	   * Metodo utilizado para calcular la x con el grado de precision E que se desea, se cicla hasta lograrlo
	   *
	   */
	//&i
	public void calculaX(){
		if(dP != 0){
			//Empezamos con la x en 0
			dX1 = 0;
			//Esta es la variable que indicará que estamos sumando
			boolean sumando = true;
			//Creamos la instancia del objeto CalculadorE en el que vamos a calcular la p
			ceCalcE = new CalculadorE(dX1,iDof);
			//Calculamos el nuevo valor de dX2 con el incremento inicial
			dX2 = dX1 + dD;
			//Cambiamos el valor de X de la instancia de CalcE
			ceCalcE.setX(dX2);
			//Sacamos nuestro segundo valor de P
			dP2 = ceCalcE.getP();
			//Hacemos el while hasta que sea menor que el grado de precisión
			while(Math.abs(dX1-dX2) > dE){
				//Cambiamos el valor de dX1
				dX1 = dX2;
				//Checamos si dP2 es mayor que dP y estamos sumando o si dP2 es menor que dP y estamos restando
				if((dP2 > dP && sumando) || (dP2 < dP && !sumando)){
					//Se cambia el incremento que estamos haciendo
					dD = dD/-2.0;
					//Cambiamos de sumar a restar o de restar a sumar
					sumando = !sumando;
				}
				//Sacamos el nuevo valor de dX2 sumandoselo al valor anterior (dX1)
				dX2 = dX1 + dD;
				//Le ponemos ese valor a la instancia de CalculadorE
				ceCalcE.setX(dX2);
				//Sacamos el valor de P con esa instancia y se la cambiamos a dP2
				dP2 = ceCalcE.getP();
			}
			//Cuando cumple con la tolerancia, se lo ponemos a dX final
			dX = dX2;
		}
		else{
			dX = 0;
		}
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
		System.out.println("p   = "+ String.format("%.5f", dP));
		System.out.println("dof = "+ iDof);
		System.out.println("x   = "+ String.format("%.5f", dX));
	}

}
