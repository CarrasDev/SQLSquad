package modelo;

// Grupo 2 - SQL SQUAD


import modelo.Cliente.Cliente;

import java.util.ArrayList;
import java.util.HashMap;


public class Modelo {

    private HashMap<String, Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;
    private Integer proximoPedido;


    public Modelo() {
        clientes = new HashMap<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();
        proximoPedido = 0;
    }

    public Integer generarProximoPedido() {
        return ++proximoPedido;
    }

    //Añaden a listados
    public void addArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getEmail(), cliente);
    }

    //Getters individuales
    public Cliente getCliente(String email) {
        return clientes.get(email);
    }

    public Cliente getPedido(Integer numeroPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                return pedido.getCliente();
            }
        }
        return null;
    }

    public Articulo getArticulo(String codigoArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return articulo;
            }
        }
        return null;
    }

    //Getters de listados
    public HashMap<String, Cliente> getListaClientes() {
        if (clientes == null) {
            throw new IllegalStateException("Mapa de clientes no inicializado");
        }
        return new HashMap<String, Cliente>(clientes);
    }

    public ArrayList<Articulo> getArticulos() {
        if (articulos == null) {
            throw new IllegalStateException("Lista de articulos no inicializada");
        }
        return articulos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public String toString() {
        return "Tienda OnLineStore de SQL Squad";
    }

    public boolean findItem(String codigoArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarPedidos() {
        if (pedidos == null) {
            throw new IllegalStateException("Lista de pedidos no inicializada");
        }
        for (Pedido pedido : pedidos) {
            pedido.actualizarEstadoPreparacion();
        }
    }

    public boolean actualizarPedido(Integer numeroPedido) {
        if (pedidos == null) {
            throw new IllegalStateException("Lista de pedidos no inicializada");
        }
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                pedido.actualizarEstadoPreparacion();
                return true;
            }
        }
        return false;
    }
}
