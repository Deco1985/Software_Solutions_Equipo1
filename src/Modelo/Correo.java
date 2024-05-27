
package Modelo;


public class Correo {
    private String correoDado;

    public Correo(String correoDado) {
        if (correoDado.endsWith("@gmail.com")){
           this.correoDado = correoDado; 
        }
        else{
            System.out.println("Porfavor Digite un correo valido");
        }
    }

    //Getter
    public String getCorreoDado() {
        return correoDado;
    }
    
    //Setter

    public void setCorreoDado(String correoDado) {
        this.correoDado = correoDado;
    }
    
    
    
    
    
    
    
    
}
