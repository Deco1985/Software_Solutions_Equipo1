
package Modelo;

/**
 *
 * @author Joan Miam Chan
 */

public class Correo {
    private String correoDado;

    public Correo(String correoDado) throws IllegalArgumentException {
        if (correoDado != null && correoDado.endsWith("@gmail.com")) {
            this.correoDado = correoDado;
        } else {
            throw new IllegalArgumentException("Por favor, digite un correo válido que termine en '@gmail.com'.");
        }
    }

    // Getter
    public String getCorreoDado() {
        return correoDado;
    }

    // Setter
    public void setCorreoDado(String correoDado) {
        if (correoDado != null && correoDado.endsWith("@gmail.com")) {
            this.correoDado = correoDado;
        } else {
            System.out.println("Correo inválido, asegúrese de que termine en '@gmail.com'.");
        }
    }
}
