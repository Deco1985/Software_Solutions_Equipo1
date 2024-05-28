/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Joan Miam Chan
 */
public class Peso {
    private double kilogramos; // Almacenar el peso en kilogramos

    public Peso(double kilogramos) {
        setKilogramos(kilogramos);
    }

    public double getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(double kilogramos) {
        if (kilogramos >= 0 && kilogramos <= 635) { // Asumiendo un rango realista de peso humano
            this.kilogramos = kilogramos;
        } else {
            throw new IllegalArgumentException("El peso proporcionado no es válido.");
        }
    }

    // Método para obtener el peso en libras
    public double getLibras() {
        return kilogramos * 2.20462;
    }

    // Método para establecer el peso en libras
    public void setLibras(double libras) {
        if (libras >= 0 && libras <= 1400) { // Asumiendo un rango realista de peso humano en libras
            this.kilogramos = libras / 2.20462;
        } else {
            throw new IllegalArgumentException("El peso proporcionado no es válido.");
        }
    }
}