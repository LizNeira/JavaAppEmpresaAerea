/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;

import java.io.Serializable;

public class Vuelo implements Serializable{
    private int numVuelo;
    private int totalLug;
    private int lugOcup;
    private String destino;

    public Vuelo(int num, int totalLug, int lugOcup) {
        this.numVuelo = num;
        this.totalLug = totalLug;
        this.lugOcup = lugOcup;
    }

    public Vuelo(int numVuelo, int totalLug, int lugOcup, String destino) {
        this.numVuelo = numVuelo;
        this.totalLug = totalLug;
        this.lugOcup = lugOcup;
        this.destino = destino;
    }

    public Vuelo(int num, int totalLug) {
        this(num,totalLug,0);
    }

    public int getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

    public int getTotalLug() {
        return totalLug;
    }

    public void setTotalLug(int totalLug) {
        this.totalLug = totalLug;
    }

    public int getLugOcup() {
        return lugOcup;
    }

    public void setLugOcup(int lugOcup) {
        this.lugOcup = lugOcup;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    

    @Override
    public String toString() {
        return "numVuelo=" + numVuelo + ", totalLug=" + totalLug + ", lugOcup=" + lugOcup + ", destino=" + destino +"\n";
    }

    
    
    
    
    
    
    
    
    
}