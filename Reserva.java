/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;

import java.io.Serializable;

public class Reserva implements Serializable {

    private int numR;
    private int nVuelo;
    private int cantReserva;

    public Reserva(int numR, int nVuelo, int cantReserva) {
        this.numR = numR;
        this.nVuelo = nVuelo;
        this.cantReserva = cantReserva;
    }

    public int getNumR() {
        return numR;
    }

    public void setNumR(int numR) {
        this.numR = numR;
    }

    public int getnVuelo() {
        return nVuelo;
    }

    public void setnVuelo(int nVuelo) {
        this.nVuelo = nVuelo;
    }

    public int getCantReserva() {
        return cantReserva;
    }

    public void setCantReserva(int cantReserva) {
        this.cantReserva = cantReserva;
    }

    @Override
    public String toString() {
        return "numR = " + numR + ", nVuelo = " + nVuelo + ", cantReserva = " + cantReserva + "\n";
    }

    
    
    
    
    
}