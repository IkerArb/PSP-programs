/**
 * CalculadorE
 *
 * Clase que calcula la P hasta que cumpla con la precision E deseada, se utiliza para calcular la integracion
 * de Simpson
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 15/04/2016
 * @version 1.0
 */
//&p-CalculadorE

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
	   * @param fX es el <code>float</code> que representa la X ingresada
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
		iNum_seg = 10;
		//Se crea el objeto de tipo CalculadoraP para aplicar la formula necesaria con las sumatorias
		cpCalcP = new CalculadorP(dX,iDof,iNum_seg);
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

}
