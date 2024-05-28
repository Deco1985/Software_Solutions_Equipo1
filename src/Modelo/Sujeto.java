package modelo;

import java.util.ArrayList;
import java.util.List;

public class Sujeto {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificarObservadores(Membresia membresia) {
        for (Observador observador : observadores) {
            observador.actualizar(membresia);
        }
    }
}
