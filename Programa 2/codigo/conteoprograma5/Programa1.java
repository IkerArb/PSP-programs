/**
 * Programa1
 *
 * Clase principal del Programa1 del PSP que ayuda a leer las líneas de información y en blanco de distintos
 * archivos y las ordena por cantidad de líneas de información de maner ascendente, al final imprime este orden
 *
 * Ahora se reutiliza la clase del Programa1 para el Programa 5 y se utiliza para sacar la integral de simpson
 *
 * @author Iker Arbulu Lozano   A01190690
 * @date 5/04/2016
 * @version 4.0
 */
//&p-Programa1
//&b=35
import java.io.*;
import java.util.regex.*;

public class Programa1 {
  //&i
  public static void main(String []args){

    //Se crea el objeto de la clase BufferedReader para leer el input del usuario
    try (BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in))){

    	//Se crea la variable para hacer el calculo e impresion de P con precision de E
    	CalculadorX ceCalculadorX; //&m

    	//Se crea el flotante para leer el input de X del usuario
    	double dP = 0; //&m

    	//Se crea el entero para leer los DOF del usuario
    	int iDof = 0;

    	//Se crea la variable para recibir los inputs en forma de string
    	String sCurrentLine;

        //Pido el ingreso de la P
        System.out.println("Dame la P:"); //&m
        //Recibe la X de entrada
        sCurrentLine = brInput.readLine();
        //Revisa que lo que haya recibido cumpla con el formato de un flotante
        if(Pattern.matches("\\d+(\\.\\d+)?", sCurrentLine)){
        	//Si estos caracteres representan un flotante mayor o igual a 0
        	if(Double.parseDouble(sCurrentLine) >= 0 && Double.parseDouble(sCurrentLine) <=  0.5){ //&m
        		//Asignalo a la variable que maneja la entrada de X
        		dP = Double.parseDouble(sCurrentLine);//&m
        	}
        	//Si no
        	else{
        		//Error en la entrada, salgamos
        		System.out.println("P incorrecta");//&m
        		System.exit(0);
        	}
        }
        //Si no
        else{
        	//Error en la entrada, salgamos
        	System.out.println("P incorrecta");//&m
    		System.exit(0);
        }

      //Pido el ingreso de los Dof
        System.out.println("Dame los DOF:");
        //Recibe los Dof de entrada
        sCurrentLine = brInput.readLine();
        //Revisa que lo que haya recibido cumpla con el formato de un entero
        if(Pattern.matches("\\d+", sCurrentLine)){
        	//Si estos caracteres representan un entero mayor a 0
        	if(Integer.parseInt(sCurrentLine)>0){
        		//Asignalo a la variable que maneja los dof
        		iDof = Integer.parseInt(sCurrentLine);
        	}
        	//Si no
        	else{
        		//Error en la entrada, salgamos
        		System.out.println("DOF incorrectos");
        		System.exit(0);
        	}
        }
        //Si no
        else{
        	//Error en la entrada, salgamos
        	System.out.println("DOF incorrectos");
    		System.exit(0);
        }

        //Creamos el objeto de CalculadoraE
        ceCalculadorX = new CalculadorX(dP,iDof);//&m

        //Mandamos a imprimir
        ceCalculadorX.print();//&m

    }catch(IOException e){
      System.out.println("Couldn't open BufferReader"+e);
    }
  }
}
