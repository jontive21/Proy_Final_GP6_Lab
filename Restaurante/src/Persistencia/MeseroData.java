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

    public void crearMesero(Mesero mesero) {
        String sql = "INSERT INTO meseros (id, nombre, dni) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mesero.getId());
            ps.setString(2, mesero.getNombre());
            ps.setString(3, mesero.getDni());
            ps.executeUpdate();
            System.out.println("Mesero creado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al crear mesero: " + ex.getMessage());
        }
    }

    public void eliminarMesero(int meseroId) {
        String sql = "DELETE FROM meseros WHERE id = ?";
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
        String sql = "UPDATE meseros SET nombre = ?, dni = ? WHERE id = ?";
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
        String sql = "SELECT * FROM meseros WHERE dni = ?";
        Mesero mesero = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mesero = new Mesero(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar mesero: " + ex.getMessage());
        }
        return mesero;
    }
}