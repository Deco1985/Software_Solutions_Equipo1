
package Modelo;

/**
 *
 * @author Joan Miam Chan
 */
public class Cliente {
    private String nombres;
    private String apellidos;
    private String sexo;
    private Fecha fechaDeNacimiento;
    private Correo correoElectronico;
    private Estatura estatura;
    private Peso peso;
    private Huella huellaDigital;

    // Constructor que inicializa todos los campos
    public Cliente(String nombres, String apellidos, String sexo, Fecha fechaDeNacimiento, Correo correoElectronico, Estatura estatura, Peso peso, Huella huellaDigital) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.correoElectronico = correoElectronico;
        this.estatura = estatura;
        this.peso = peso;
        this.huellaDigital = huellaDigital;
    }

    //Getters
    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public Fecha getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Correo getCorreoElectronico() {
        return correoElectronico;
    }

    public Estatura getEstatura() {
        return estatura;
    }

    public Peso getPeso() {
        return peso;
    }


    public Huella getHuellaDigital() {
        return huellaDigital;
    }
    
    //Setters

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechaDeNacimiento(Fecha fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setCorreoElectronico(Correo correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setEstatura(Estatura estatura) {
        this.estatura = estatura;
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }


    public void setHuellaDigital(Huella huellaDigital) {
        this.huellaDigital = huellaDigital;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

