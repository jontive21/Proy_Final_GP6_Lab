/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Cliente;
import Entidades.Conexion;
import Entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteData {
    private Connection con;

    public ClienteData() {
        con = Conexion.getConexion();
    }

    
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nombre, mesa_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setInt(3, cliente.getMesaAsignada().getIdMesa());
            ps.executeUpdate();
            System.out.println("Cliente agregado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar cliente: " + ex.getMessage());
        }
    }

 
    public void eliminarCliente(int clienteId) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ps.executeUpdate();
            System.out.println("Cliente eliminado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar cliente: " + ex.getMessage());
        }
    }

    // Método para modificar un cliente
    public void modificarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, mesa_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getMesaAsignada().getIdMesa());
            ps.setInt(3, cliente.getId());
            ps.executeUpdate();
            System.out.println("Cliente modificado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar cliente: " + ex.getMessage());
        }
    }

    // Método para buscar un cliente por ID
    public Cliente buscarClientePorId(int clienteId) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("mesa_id"), 0, "desconocido");
                cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), mesa);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cliente: " + ex.getMessage());
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("mesa_id"), 0, "desconocido");
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), mesa);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar clientes: " + ex.getMessage());
        }
        return clientes;
    }

    public void asignarMesa(Cliente cliente, Mesa mesa) {
        cliente.setMesaAsignada(mesa);
        modificarCliente(cliente);
        System.out.println("Mesa asignada al cliente " + cliente.getNombre() + ".");
    }
}

