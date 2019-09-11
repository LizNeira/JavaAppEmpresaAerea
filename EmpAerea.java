/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappempresaaerea;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class EmpAerea  {

    private ArrayList<Vuelo> alVuelos;
    private ArrayList<Reserva> alReservas;
    private ArrayList<String> alDestinos;
    private InterfaceES modo;
    private CrearArchSecuencialSerializable crearArchArrayList;
    private LeerArchSecuencialSerializable leerArchArrayList;

    public EmpAerea(InterfaceES m) {    // constructor
        modo = m;
        alVuelos = new ArrayList();
        alReservas = new ArrayList();
        alDestinos = new ArrayList();
        char resp = modo.leerDatoChar("Está creado el archivo de vuelos?(s/n): ");
        if (resp == 's') {
            leerArchArrayList = new LeerArchSecuencialSerializable("Vuelos.ser");
            leerArchArrayList.abrirArchivo();
            alVuelos = (ArrayList<Vuelo>) leerArchArrayList.leerArrayList();
            leerArchArrayList.cerrarArchivo();

            leerArchArrayList = new LeerArchSecuencialSerializable("Destinos.ser");
            leerArchArrayList.abrirArchivo();
            alDestinos = (ArrayList<String>) leerArchArrayList.leerArrayList();
            leerArchArrayList.cerrarArchivo();
            modo.mostrarCadena(alVuelos.toString());
        } else {
            leerVuelosDesdeTeclado();
        }
        resp = modo.leerDatoChar("Está creado el archivo de reservas?(s/n): ");
        if (resp == 's') {
            leerArchArrayList = new LeerArchSecuencialSerializable("Reservas.ser");
            leerArchArrayList.abrirArchivo();
            alReservas = (ArrayList<Reserva>) leerArchArrayList.leerArrayList();
            leerArchArrayList.cerrarArchivo();
            modo.mostrarCadena(alReservas.toString());
        } else {
            grabarArrayListReservas();
        }

    }

    public void leerVuelosDesdeTeclado() {
        String des = "";
        char resp = 's';
        while (resp == 's') {
            Vuelo v = new Vuelo(modo.leerDatoEntero("Ingrese numero de vuelo: "),
                    modo.leerDatoEntero("Ingrese cantidad de lugares del vuelo: "),
                    0, des = modo.leerCadena("ingrese destino del vuelo"));
            alVuelos.add(v);
            int pos = alDestinos.indexOf(des.toLowerCase());
            if (pos == -1) {
                alDestinos.add(des.toLowerCase());
            }
            resp = modo.leerDatoChar("Desea seguir ingresando vuelos?(s/n): ");
        }
        grabarArrayListVuelos();
        grabarArrayListDestinos();
        modo.mostrarCadena(alVuelos.toString());
    }

    public void menu() {
        String menuOpciones = "Ingrese una opción: \n"
                + "1 - Efectuar una reserva.\n"
                + "2 - Consultar lugares disponibles de un vuelo.\n"
                + "3 - Mostrar todos los vuelos.\n"
                + "4 - Mostrar todas las reservas.\n"
                + "5 - Mostrar vuelos totalmente ocupados. \n"
                + "6 - Dar de baja un reserva \n"
                + "7 - Mostrar vuelos con determinado destino\n"
                + "8 - Salir.\n";
        int opcion = modo.leerDatoEntero(menuOpciones);
        while (opcion > 0 && opcion < 8) {
            switch (opcion) {
                case 1:
                    efectuarReserva();
                    break;
                case 2:
                    consultarDisponibles();
                    break;
                case 3:
                    modo.mostrarCadena(alVuelos.toString());
                    break;
                case 4:
                    modo.mostrarCadena(alReservas.toString());
                    break;
                case 5:
                    mostrarVuelosCompletos();
                    break;
                case 6:
                    darDeBajaUnaReserva();
                    break;
                case 7:
                    mostrarVuelosDeUnDestino();
                    break;
                case 8:
                    break;
            }
            opcion = modo.leerDatoEntero(menuOpciones);
        }
    }

    public void efectuarReserva() {
        int sigRes = alReservas.isEmpty() ? 1 : (alReservas.get(alReservas.size() - 1)).getNumR() + 1;
        int numVuelo = modo.leerDatoEntero("Ingrese numero de vuelo: ");
        int posNumVuelo = -1;
        String mensaje = "En este vuelo ya no se pueden hacer reservas.\n";
        while ((posNumVuelo = buscarNumVuelo(numVuelo)) >= alVuelos.size()) {
            numVuelo = modo.leerDatoEntero("Vuelo inexistente ingrese nuevamente: ");
        }
        int cantDisponibles = alVuelos.get(posNumVuelo).getTotalLug() - alVuelos.get(posNumVuelo).getLugOcup();
        if (cantDisponibles == 0) {
            modo.mostrarCadena(mensaje);
        } else {
            int cantRes = modo.leerDatoEntero("Lugares Disponibles: " + cantDisponibles + "\n Cuantos quiere reservar?: ");
            if (cantDisponibles > 0) {
                while (cantRes > cantDisponibles) {
                    cantRes = modo.leerDatoEntero("Lugares Disponibles: " + cantDisponibles + "\n Ingrese nuevamente: ");
                }
                alReservas.add(new Reserva(sigRes, numVuelo, cantRes));
                alVuelos.get(posNumVuelo).setLugOcup(alVuelos.get(posNumVuelo).getLugOcup() + cantRes);
                grabarArrayListVuelos();
                grabarArrayListReservas();
            }
        }
    }

    public void consultarDisponibles() {
        int numVuelo = modo.leerDatoEntero("Ingrese numero de vuelo: ");
        int posNumVuelo = -1;
        //String mensaje="Este vuelo ya no tiene asientos disponibles.\n";
        while ((posNumVuelo = buscarNumVuelo(numVuelo)) >= alVuelos.size()) {
            numVuelo = modo.leerDatoEntero("Vuelo inexistente. Ingrese nuevamente: ");
        }
        int asientos = alVuelos.get(posNumVuelo).getLugOcup();
        int cantDisponibles = alVuelos.get(posNumVuelo).getTotalLug() - alVuelos.get(posNumVuelo).getLugOcup();
        String note;
        note = "Asientos ya reservados = " + asientos + "\nCantidad de lugares disponibles = " + cantDisponibles + "\n";
        modo.mostrarCadena(note);
    }

    public void grabarArrayListReservas() {
        crearArchArrayList = new CrearArchSecuencialSerializable("Reservas.ser");
        crearArchArrayList.abrirArchivo();
        crearArchArrayList.grabarArrayList(alReservas);
        crearArchArrayList.cerrarArchivo();
//          grabarArrayList(alReservas);
    }

    public void grabarArrayListVuelos() {
        crearArchArrayList = new CrearArchSecuencialSerializable("Vuelos.ser");
        crearArchArrayList.abrirArchivo();
        crearArchArrayList.grabarArrayList(alVuelos);
        crearArchArrayList.cerrarArchivo();
        //grabarArrayList(alVuelos);
    }

    private void grabarArrayListDestinos() {
        crearArchArrayList = new CrearArchSecuencialSerializable("destinos.ser");
        crearArchArrayList.abrirArchivo();
        crearArchArrayList.grabarArrayList(alDestinos);
        crearArchArrayList.cerrarArchivo();
    // grabarArrayList(alDestinos);
    }
    
//    private void grabarArrayList(ArrayList al) {
//        crearArchArrayList = new CrearArchSecuencialSerializable("Vuelos.ser");
//        crearArchArrayList.abrirArchivo();
//        crearArchArrayList.grabarArrayList(al);
//        crearArchArrayList.cerrarArchivo();
//    }

    public int buscarNumVuelo(int n) { // 
        int i = 0;
        while (i < alVuelos.size() && alVuelos.get(i).getNumVuelo() != n) {
            i++;
        }
        return i;
    }

    private void mostrarVuelosCompletos() {
        String s = "";
        for (int i = 0; i < alVuelos.size(); i++) {
            if (alVuelos.get(i).getLugOcup() == alVuelos.get(i).getTotalLug()) {
                s += alVuelos.get(i).toString() + "\n";
            }
        }
        modo.mostrarCadena(s);
    }

    private void darDeBajaUnaReserva() {
        String mensaje = alReservas.toString();
        mensaje += "\ningrese nro de reserva";
        int nroRes = modo.leerDatoEntero(mensaje);
        int posNroResBuscada = buscarNumReserva(nroRes);
        if (posNroResBuscada >= alReservas.size()) {
            modo.mostrarCadena("nro de reserva inexistente");
        } else {

            int numVuelo = alReservas.get(posNroResBuscada).getnVuelo();
            int cantRes = alReservas.get(posNroResBuscada).getCantReserva();
            alReservas.remove(posNroResBuscada);
            int posNumVuelo = buscarNumVuelo(numVuelo);
            alVuelos.get(posNumVuelo).setLugOcup(alVuelos.get(posNumVuelo).getLugOcup() - cantRes);
            grabarArrayListVuelos();
            grabarArrayListReservas();
            modo.mostrarCadena("nro reserva " + nroRes + " dado de baja exitosamente");

        }
    }

    public int buscarNumReserva(int n) { // 
        int i = 0;
        while (i < alReservas.size() && alReservas.get(i).getNumR() != n) {
            i++;
        }
        return i;
    }

    private void mostrarVuelosDeUnDestino() {
        String s = "";
        String vuelos ="";
        for (int i = 0; i < alDestinos.size(); i++) {

            s += i + 1 + "-" + alDestinos.get(i).toString() + "\n";
        }
         s+="ingrese numero de destino\n";
       int op= modo.leerDatoEntero(s);
       String destSolicitado=alDestinos.get(op-1);
       
       for (int i = 0; i < alVuelos.size(); i++) {
             if(destSolicitado==alVuelos.get(i).getDestino())
            vuelos+= alVuelos.get(i).toString() + "\n";
            
        }
    modo.mostrarCadena(vuelos);
    }

}
