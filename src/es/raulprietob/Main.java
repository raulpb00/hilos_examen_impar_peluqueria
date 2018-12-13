package es.raulprietob;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Lectura de datos
       /* Scanner sc = new Scanner(System.in);
        int nClientes = sc.nextInt();
        int nPeluqueros = sc.nextInt();
        sc.close();*/

        int nClientes = Integer.parseInt(args[0]);
        int nPeluqueros = Integer.parseInt(args[1]);

        // Instancio Lista de Hilos y un Monitor con el dato
        ArrayList<Cliente> clientes = new ArrayList<>();
        Peluqueria peluqueria = new Peluqueria(nPeluqueros);

        // Instancio cada Hilo y lo inicio
        for (int i = 0; i < nClientes; i++) {
            clientes.add(new Cliente(i, peluqueria));
            clientes.get(i).start();
        }

    }

}
