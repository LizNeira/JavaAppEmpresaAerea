/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;

import javax.swing.JOptionPane;



public class EntradaSalidaVentana implements InterfaceES{
    
    @Override
    public int leerDatoEntero(String mensaje)
	{
		return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
	}
    @Override
    public float leerDatoReal(String mensaje)
	{
		return Float.parseFloat(JOptionPane.showInputDialog(mensaje));
	}
    @Override
    public void mostrarCadena(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
    @Override
    public String leerCadena(String mensaje)
        {
            return JOptionPane.showInputDialog(mensaje);
        }

    /**
     *
     * @param mensaje
     * @return
     */
    @Override
    public char leerDatoChar(String mensaje){
        return leerCadena(mensaje).charAt(0);
    }
}
