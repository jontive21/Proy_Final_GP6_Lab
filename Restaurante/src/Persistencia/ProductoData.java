/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductoData {
    private Connection con;

    public ProductoData() {
        con = Conexion.getConexion();
    }

    public void agregarProductoAPedido(int pedidoId, Producto producto) {
        String sql = "INSERT INTO pedidos_productos (pedido_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoId);
            ps.setInt(2, producto.getCodigo());
            ps.setInt(3, producto.getCantidad());
            ps.executeUpdate();
            System.out.println("Producto agregado al pedido.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar producto: " + ex.getMessage());
        }
    }

    public void quitarProductoDePedido(int pedidoId, int productoId) {
        String sql = "DELETE FROM pedidos_productos WHERE pedido_id = ? AND producto_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoId);
            ps.setInt(2, productoId);
            ps.executeUpdate();
            System.out.println("Producto eliminado del pedido.");
        } catch (SQLException ex) {
            System.out.println("Error al quitar producto: " + ex.getMessage());
        }
    }

    public double calcularSubtotal(Producto producto) {
        return producto.getPrecio() * producto.getCantidad();
    }
}