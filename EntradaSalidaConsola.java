/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;
 import java.util.Scanner;
/**
 *
 * @author Daniel
 */

/*String leerCadena(String mensaje);

    void mostrarCadena(String mensaje);

    int leerDatoEntero(String mensaje);
    
    public float leerDatoReal(String mensaje);
    
    public long leerDatoLong(String mensaje);
    
    public char leerDatoChar(String mensaje);*/
public class EntradaSalidaConsola implements InterfaceES{
    
    private Scanner a = new Scanner( System.in);
    
    
    @Override
    public String leerCadena(String mensaje){
        System.out.println(mensaje);
        return a.nextLine();
    }
    @Override
    public void mostrarCadena(String mensaje){
        System.out.println(mensaje);
    }
    @Override
    public int leerDatoEntero(String mensaje){
        System.out.println(mensaje);
        return a.nextInt();
    }    

    @Override
    public float leerDatoReal(String mensaje) {
        System.out.println(mensaje);
        return a.nextFloat();
        
    }
    @Override
    public char leerDatoChar(String mensaje){
        return leerCadena(mensaje).charAt(0);
    }
}
