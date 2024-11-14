/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Pedido;
import Entidades.Producto;
import Entidades.Mesero;
import Entidades.Cliente;
import Entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class PedidoData {
    private Connection con;

    public PedidoData() {
        con = Conexion.getConexion();
    }

    public int crearPedido(int idMesa, int idMesero, int idCliente, LocalDateTime fechaPedido,
                           String estado, String pagado, double monto, List<Producto> productos) {
        int pedidoId = -1;

        try {
        // 1. Insert the pedido into the pedido table
        String queryCrearPedido = "INSERT INTO pedido (id_mesa, id_mesero, id_cliente, fecha_pedido, estado, pagado, monto) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement psInsertar = con.prepareStatement(queryCrearPedido, Statement.RETURN_GENERATED_KEYS)) {
            psInsertar.setInt(1, idMesa);
            psInsertar.setInt(2, idMesero);
            psInsertar.setInt(3, idCliente);
            psInsertar.setString(4, fechaPedido.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            psInsertar.setString(5, estado);
            psInsertar.setString(6, pagado);
            psInsertar.setDouble(7, monto);

            psInsertar.executeUpdate();
            ResultSet rs = psInsertar.getGeneratedKeys();
            if (rs.next()) {
                pedidoId = rs.getInt(1); // Retrieve the generated ID for the pedido
            }
        }


            // 2. Insertar los detalles de los productos en la tabla detalle_pedido
            String queryDetallePedido = "INSERT INTO detalle_producto (id_pedido, id_producto, cantidad, monto) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psDetalle = con.prepareStatement(queryDetallePedido)) {
                    for (Producto producto : productos) {
                        psDetalle.setInt(1, pedidoId); // Asigna el id del pedido recién creado
                        psDetalle.setInt(2, producto.getIdProducto());
                        psDetalle.setInt(3, producto.getCantidad());
                        psDetalle.setDouble(4, producto.getPrecio());
                        psDetalle.executeUpdate();
                    }
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear pedido: " + e.getMessage());
            e.printStackTrace();
        }

        return pedidoId;
    }

    public void agregarProductosAlPedido(int idPedido, List<Producto> productos) {
        String queryInsertarProducto = "INSERT INTO detalle_producto (id_pedido, id_producto, cantidad, monto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psInsertar = con.prepareStatement(queryInsertarProducto)) {
            for (Producto producto : productos) {
                double montoTotal = producto.getPrecio() * producto.getCantidad();
            psInsertar.setInt(1, idPedido); // Asigna el ID del pedido
            psInsertar.setInt(2, producto.getIdProducto());
            psInsertar.setInt(3, producto.getCantidad());
            psInsertar.setDouble(4, montoTotal);
            psInsertar.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar productos al pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public List<Pedido> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                int idCliente = rs.getInt("id_cliente");
                int idMesero = rs.getInt("id_mesero");
                int idMesa = rs.getInt("id_mesa");
                double montoTotal = rs.getDouble("monto");
                boolean pagado = rs.getBoolean("pagado");
                boolean entregado = rs.getString("estado").equals("Entregado");
                LocalDateTime fecha = rs.getTimestamp("fecha_pedido").toLocalDateTime();

                Cliente cliente = new ClienteData().buscarClientePorId(idCliente);
                Mesero mesero = new MeseroData().buscarMeseroPorId(idMesero);
                Mesa mesa = new MesaData().buscarMesaPorId(idMesa);

                Pedido pedido = new Pedido(idPedido, cliente, mesero, mesa, null, montoTotal, pagado, entregado, fecha);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los pedidos: " + e.getMessage());
        }

        return pedidos;
    }
    
        public void cambiarEstadoPago(int idPedido, boolean pagado) {
            String sql = "UPDATE pedido SET pagado = ? WHERE id_pedido = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, pagado ? "si" : "no"); // "si" para pagado, "no" para no pagado
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado de pago: " + e.getMessage());
            }
        }

        
        public void cambiarEstadoEntrega(int idPedido, boolean entregado) {
            String sql = "UPDATE pedido SET estado = ? WHERE id_pedido = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, entregado ? "Entregado" : "No entregado"); // "Entregado" o "No entregado"
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado de entrega: " + e.getMessage());
            }
        }

        
        public void borrarPedido(int idPedido) {
            String sqlDeleteDetalle = "DELETE FROM detalle_producto WHERE id_pedido = ?";
            String sqlDeletePedido = "DELETE FROM pedido WHERE id_pedido = ?";

            try (PreparedStatement psDetalle = con.prepareStatement(sqlDeleteDetalle);
                 PreparedStatement psPedido = con.prepareStatement(sqlDeletePedido)) {

                //primero elimina los registros relacionados en detalle
                psDetalle.setInt(1, idPedido);
                psDetalle.executeUpdate();

                // dsp elimina el pedido
                psPedido.setInt(1, idPedido);
                psPedido.executeUpdate();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el pedido: " + e.getMessage());
            }
        }
}

    

