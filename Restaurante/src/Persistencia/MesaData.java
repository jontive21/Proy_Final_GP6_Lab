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
import javax.swing.JOptionPane;

public class MesaData {
    private Connection con;

    public MesaData() {
        con = Conexion.getConexion();
    }

    public List <Mesa> listarMesas(){
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesa";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("id_mesa"), rs.getInt("capacidad"), rs.getString("estado"));
                mesas.add(mesa);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al listar mesas: " + e.getMessage());
        }
        return mesas;
    }
    public void crearMesa(Mesa mesa) {
        String sql = "INSERT INTO mesa (id_mesa,capacidad, estado) VALUES (?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mesa.getIdMesa());
            ps.setInt(2, mesa.getCapacidad());
            ps.setString(3, mesa.getEstado());
            ps.executeUpdate();
            System.out.println("Mesa creada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al crear mesa: " + ex.getMessage());
        }
    }

    public void modificarMesa(Mesa mesa) {
        String sql = "UPDATE mesa SET capacidad = ?, estado = ? WHERE id_mesa = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mesa.getCapacidad());
            ps.setString(2, mesa.getEstado());
            ps.setInt(3, mesa.getIdMesa());
            ps.executeUpdate();
            System.out.println("Mesa modificada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar mesa: " + ex.getMessage());
        }
    }

    public List<Mesa> listarMesasPorEstado(String estado) {
        String sql = "SELECT * FROM mesa WHERE estado = ?";
        List<Mesa> mesas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("id_mesa"), rs.getInt("capacidad"), rs.getString("estado"));
                mesas.add(mesa);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar mesas: " + ex.getMessage());
        }
        return mesas;
    }

    public void cambiarEstadoMesa(int mesaId, String nuevoEstado) {
        String sql = "UPDATE mesa SET estado = ? WHERE id_mesa = ?";
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
    
    public void borrarMesa(int idMesa) {
        String sql = "DELETE FROM mesa WHERE id_mesa = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMesa);
            ps.executeUpdate();
            System.out.println("Mesa eliminada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar mesa: " + ex.getMessage());
        }
    }
    
    public Mesa buscarMesaPorId(int idMesa) {
        Mesa mesa = null;
        String sql = "SELECT * FROM mesa WHERE id_mesa = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMesa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_mesa");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");

                mesa = new Mesa(id, capacidad, estado); // Ajusta al constructor de Mesa
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mesa: " + e.getMessage());
        }
        return mesa;
    }
}