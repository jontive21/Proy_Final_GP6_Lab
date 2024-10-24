/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Pedido;
import Entidades.Producto;
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

    public int crearPedido(int mesaId, int meseroId, String fecha, String hora, double monto) {
        String queryVerificarMesa = "SELECT id FROM mesas WHERE id = ?";
        String queryVerificarMesero = "SELECT id FROM meseros WHERE id = ?";
        String queryCrearPedido = "INSERT INTO pedidos (mesa_id, mesero_id, fecha, hora, monto, pagado) VALUES (?, ?, ?, ?, ?, ?)";
        int pedidoId = -1;

        try (PreparedStatement psVerificarMesa = con.prepareStatement(queryVerificarMesa)) {
            psVerificarMesa.setInt(1, mesaId);
            ResultSet rsMesa = psVerificarMesa.executeQuery();

            if (rsMesa.next()) {
                try (PreparedStatement psVerificarMesero = con.prepareStatement(queryVerificarMesero)) {
                    psVerificarMesero.setInt(1, meseroId);
                    ResultSet rsMesero = psVerificarMesero.executeQuery();

                    if (rsMesero.next()) {
                        try (PreparedStatement psInsertar = con.prepareStatement(queryCrearPedido, Statement.RETURN_GENERATED_KEYS)) {
                            psInsertar.setInt(1, mesaId);
                            psInsertar.setInt(2, meseroId);
                            psInsertar.setString(3, fecha);
                            psInsertar.setString(4, hora);
                            psInsertar.setDouble(5, monto);
                            psInsertar.setBoolean(6, false);
                            psInsertar.executeUpdate();

                            ResultSet rs = psInsertar.getGeneratedKeys();
                            if (rs.next()) {
                                pedidoId = rs.getInt(1);
                            }
                        }
                    } else {
                        System.out.println("El mesero con id " + meseroId + " no existe.");
                    }
                }
            } else {
                System.out.println("La mesa con id " + mesaId + " no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
}
    

