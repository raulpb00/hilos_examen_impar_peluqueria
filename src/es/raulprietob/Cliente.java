package es.raulprietob;

public class Cliente extends Thread {

    private Peluqueria peluqueria;
    private int id;

    Cliente(int id, Peluqueria peluqueria) {
        this.id = id + 1; // Evito que haya un cliente con id = 0
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {

        try {
            Thread.sleep((long) (Math.random() * 5000 + 1));
            peluqueria.PonerseEnCola(id);

            Thread.sleep((long) (Math.random() * 5000 + 1000));
            peluqueria.DespacharCliente(id);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
