/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Joan Miam Chan
 */


import java.util.ArrayList;
import java.util.List;

public class RegistroClientes {
    private List<Cliente> listaClientes;

    public RegistroClientes() {
        this.listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    // Método para obtener la lista de clientes
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
