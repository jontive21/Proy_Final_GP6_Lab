/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Mesero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeseroData {
    private Connection con;

    public MeseroData() {
        con = Conexion.getConexion();
    }

    public boolean crearMesero(Mesero mesero) {
        String sql = "INSERT INTO mesero (nombre, dni) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mesero.getNombre());
            ps.setString(2, mesero.getDni());
            ps.executeUpdate();
            System.out.println("Mesero registrado con éxito.");
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al registrar mesero: " + ex.getMessage());
            return false;
        }
    }

    public void eliminarMesero(int meseroId) {
        String sql = "DELETE FROM mesero WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, meseroId);
            ps.executeUpdate();
            System.out.println("Mesero eliminado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar mesero: " + ex.getMessage());
        }
    }

    public void modificarMesero(Mesero mesero) {
        String sql = "UPDATE mesero SET nombre = ?, dni = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mesero.getNombre());
            ps.setString(2, mesero.getDni());
            ps.setInt(3, mesero.getId());
            ps.executeUpdate();
            System.out.println("Mesero modificado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar mesero: " + ex.getMessage());
        }
    }

    public Mesero buscarMeseroPorDNI(String dni) {
        String sql = "SELECT * FROM mesero WHERE dni = ?";
        Mesero mesero = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mesero = new Mesero(rs.getInt("id_mesero"), rs.getString("nombre"), rs.getString("dni"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar mesero: " + ex.getMessage());
        }
        return mesero;
    }
    
    public Mesero buscarMeseroPorNombreYDni(String nombre, String dni) {
        String sql = "SELECT * FROM mesero WHERE nombre = ? AND dni = ?";
        Mesero mesero = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                mesero = new Mesero(rs.getInt("id_mesero"), rs.getString("nombre"), rs.getString("dni"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar mesero: " + ex.getMessage());
        }

        return mesero;
    }
}