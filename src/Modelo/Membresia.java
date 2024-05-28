/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Joan Miam Chan
 */
import java.time.LocalDate;

public class Membresia {
    private String nombreCliente;
    private String tipoMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaExpiracion;
    private String estado;

    public Membresia(String nombreCliente, String tipoMembresia, LocalDate fechaInicio, LocalDate fechaExpiracion, String estado) {
        this.nombreCliente = nombreCliente;
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaExpiracion = fechaExpiracion;
        this.estado = estado;
    }

    // Getters y Setters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Membresia{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaExpiracion=" + fechaExpiracion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
