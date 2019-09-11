/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CrearArchSecuencialSerializable {

    private ObjectOutputStream archSalida;
    private String nomArch;

    public CrearArchSecuencialSerializable(String nomArch) {
        this.nomArch = nomArch;
    }

    public void abrirArchivo() {
        try {
            archSalida
                    = new ObjectOutputStream(new FileOutputStream(nomArch));
        } // fin de try
        catch (IOException e) {
            System.err.println("Error al abrir el archivo.");
        }
    }

    public void grabarArrayList(ArrayList al) {
        try {

            archSalida.writeObject(al); // graba
        }
        
        catch (IOException e) {
            System.err.println("Error al escribir en elarchivo.");
        } 
        
        catch (NoSuchElementException e) {
            System.err.println("Entrada inválida, otra vez");

        }

    }

    public void cerrarArchivo() {
        try {
            if (archSalida != null) {
                archSalida.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el archivo.");
            //System.exit(1);
        }
    }
}