package es.raulprietob;

public class Peluqueria {

    private int nLibres;
    private boolean hayLibres;
    private int[] plazas;

    public Peluqueria(int nPeluqueros) {
        this.nLibres = nPeluqueros;
        this.plazas = new int[nPeluqueros];
        this.hayLibres = true;
    }

    synchronized void PonerseEnCola(int id) {

        try {
            while (!hayLibres) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nLibres--;

        if (nLibres == 0)
            hayLibres = false;

        AtenderCliente(id);
    }

    private synchronized void AtenderCliente(int id) {
        boolean atendido = false;

        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == 0 && !atendido) {
                plazas[i] = id; // Asigno el cliente a la plaza libre
                atendido = true;
            }
        }

        System.out.println(getPeluqueria()); // Imprimo el estado de la peluqueria

    }

    synchronized void DespacharCliente(int id) {

        for (int i = 0; i < plazas.length; i++) {
            if (id == plazas[i]) {
                plazas[i] = 0; // Dejo la plaza libre
                System.out.println(String.format("Sitio:%1$s ha quedado libre", i));
            }
        }

        nLibres++;
        hayLibres = true;
        notify();

        System.out.println(getPeluqueria()); // Imprimo el estado de la peluqueria

    }

    /**
     * Método que dibuja las plazas con sus respectivos clientes
     * cliente será 0 si la plaza está libre
     *
     * @return
     */
    private StringBuilder getPeluqueria() {
        StringBuilder str = new StringBuilder();
        boolean hayAlguien = false;

        if (nLibres == plazas.length)
            str.append("PELUQUERIA VACIA\n");

        for (Integer plaza : plazas) {
            str.append(" [")
                    .append(plaza)
                    .append("]");
        }

        return str;
    }


}
