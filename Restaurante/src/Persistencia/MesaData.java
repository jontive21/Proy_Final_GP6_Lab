/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaData {
    private Connection con;

    public MesaData() {
        con = Conexion.getConexion();
    }

    public void crearMesa(Mesa mesa) {
        String sql = "INSERT INTO mesas (numero, capacidad, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mesa.getNumero());
            ps.setInt(2, mesa.getCapacidad());
            ps.setString(3, mesa.getEstado());
            ps.executeUpdate();
            System.out.println("Mesa creada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al crear mesa: " + ex.getMessage());
        }
    }

    public void modificarMesa(Mesa mesa) {
        String sql = "UPDATE mesas SET capacidad = ?, estado = ? WHERE numero = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mesa.getCapacidad());
            ps.setString(2, mesa.getEstado());
            ps.setInt(3, mesa.getNumero());
            ps.executeUpdate();
            System.out.println("Mesa modificada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar mesa: " + ex.getMessage());
        }
    }

    public List<Mesa> listarMesasPorEstado(String estado) {
        String sql = "SELECT * FROM mesas WHERE estado = ?";
        List<Mesa> mesas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("numero"), rs.getInt("capacidad"), rs.getString("estado"));
                mesas.add(mesa);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar mesas: " + ex.getMessage());
        }
        return mesas;
    }

    public void cambiarEstadoMesa(int mesaId, String nuevoEstado) {
        String sql = "UPDATE mesas SET estado = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, mesaId);
            ps.executeUpdate();
            System.out.println("Estado de la mesa actualizado a: " + nuevoEstado);
        } catch (SQLException ex) {
            System.out.println("Error al cambiar el estado de la mesa: " + ex.getMessage());
        }
    }
}