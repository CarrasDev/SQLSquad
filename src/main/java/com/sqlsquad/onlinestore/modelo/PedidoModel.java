package com.sqlsquad.onlinestore.modelo;

import com.sqlsquad.onlinestore.DAO.FactoryDAO;
import com.sqlsquad.onlinestore.DAO.IDao;
import com.sqlsquad.onlinestore.modelo.entity.Pedido;
import com.sqlsquad.onlinestore.modelo.enums.TipoEstado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PedidoModel {

    public void addPedido(Pedido pedido) {
        IDao<Pedido> pedidoDAO = FactoryDAO.getIDAO("PEDIDO");
        pedidoDAO.save(pedido);
    }

    public Pedido getPedido(Integer numeroPedido) {
        IDao<Pedido> pedidoDAO = FactoryDAO.getIDAO("PEDIDO");
        return pedidoDAO.getById(numeroPedido.toString()).orElse(null);
    }

    public ArrayList<Pedido> getPedidos() {
        IDao<Pedido> pedidoDAO = FactoryDAO.getIDAO("PEDIDO");
        Collection<Pedido> listaPedidos = pedidoDAO.getAll();

        if (listaPedidos != null) {
            return (ArrayList<Pedido>) listaPedidos;
        } else {
            throw new IllegalStateException("Lista de pedidos vacía");
        }
    }

    public void actualizarPedidos() {

        //Recuperamos todos los pedidos y solo actualizamos los que hayan cambiado de estado ha enviado.
        // Obtener los pedidos
        IDao<Pedido> pedidoDAO = FactoryDAO.getIDAO("PEDIDO");
        Collection<Pedido> listaPedidos = pedidoDAO.getAll();

        // Recorrer pedidos 1 a 1 y actualizar solo los que requieran cambio.
        if (listaPedidos != null) {
            boolean control = false;
            for (Pedido pedido : listaPedidos) {
                control = pedido.actualizarEstadoPreparacion();
                if (control) {
                    pedidoDAO.update(pedido);
                    control = false;
                }
            }
        } else {
            throw new IllegalStateException("Lista de pedidos vacía");
        }
    }

    public boolean eliminarPedido(Integer numeroPedido) {

        // Verifica si el estado es compatible para eliminar y elimina.
        IDao<Pedido> pedidoDAO = FactoryDAO.getIDAO("PEDIDO");
        Optional<Pedido> pedido = pedidoDAO.getById(numeroPedido.toString());

        if (pedido.isPresent()) {
            Pedido pedidoFinal = pedido.get(); // Se rescata el pedido dentro de Optional
            pedidoFinal.actualizarEstadoPreparacion();
            if (pedidoFinal.getEstado().equals(TipoEstado.PENDIENTE)) {
                pedidoDAO.delete(pedidoFinal);
                return true;
            }
        } else {
            throw new IllegalStateException("Pedido no encontrado");
        }
        return false;
    }
}
