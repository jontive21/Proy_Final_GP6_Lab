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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class PedidoData {
    private Connection con;

    public PedidoData() {
        con = Conexion.getConexion();
    }

    public int crearPedido(int idMesa, int idMesero, int idCliente, String fechaPedido, String estado, String pagado, double monto) {
        String queryCrearPedido = "INSERT INTO pedido (id_mesa, id_mesero, id_cliente, fecha_pedido, estado, pagado, monto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int pedidoId = -1;

        try (PreparedStatement psInsertar = con.prepareStatement(queryCrearPedido, Statement.RETURN_GENERATED_KEYS)) {
            psInsertar.setInt(1, idMesa);
            psInsertar.setInt(2, idMesero);
            psInsertar.setInt(3, idCliente);
            psInsertar.setString(4, fechaPedido);             // fecha_pedido (datetime)
            psInsertar.setString(5, estado);                  // estado
            psInsertar.setString(6, pagado);                  // pagado
            psInsertar.setDouble(7, monto);                   // monto

            psInsertar.executeUpdate();
            ResultSet rs = psInsertar.getGeneratedKeys();
            if (rs.next()) {
                pedidoId = rs.getInt(1); // Obtener el ID del pedido insertado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // O manejar más detalles del error aquí
        }

        return pedidoId;
    }
    public double calcularMontoTotal(int pedidoId) {
        double total = 0.0;
        String sql = "SELECT p.precio, pp.cantidad FROM productos p INNER JOIN pedidos_productos pp ON p.id = pp.producto_id WHERE pp.pedido_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getDouble("precio") * rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.out.println("Error al calcular el monto total: " + ex.getMessage());
        }
        return total;
    }
    public void agregarProductosAlPedido(int idPedido, List<Producto> productos) {
        String queryAgregarProducto = "INSERT INTO pedidos_productos (pedido_id, producto_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psInsertar = con.prepareStatement(queryAgregarProducto)) {
            for (Producto producto : productos) {
                psInsertar.setInt(1, idPedido);
                psInsertar.setInt(2, producto.getIdProducto());
                psInsertar.setInt(3, producto.getCantidad());
                psInsertar.setDouble(4, producto.getPrecio());
                psInsertar.addBatch();  // Para agregar todos los productos a la vez
            }
            psInsertar.executeBatch();  // Ejecutar la inserción de todos los productos en el pedido
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
    

