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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteData {
    private Connection con;

    public ClienteData() {
        con = Conexion.getConexion();
    }

    
    public int agregarCliente(String nombre, int idMesa) {
        int clienteId = -1;

        // Verificar si la mesa ya está asignada a otro cliente activo
        String queryVerificarMesa = "SELECT id_cliente FROM cliente WHERE id_mesa = ? AND estado = 'activo'";
        try (PreparedStatement psVerificar = con.prepareStatement(queryVerificarMesa)) {
            psVerificar.setInt(1, idMesa);
            ResultSet rs = psVerificar.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "La mesa " + idMesa + " ya está ocupada.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        // Insertar nuevo cliente
        String queryInsertarCliente = "INSERT INTO cliente (nombre, id_mesa, estado) VALUES (?, ?, 'activo')";
        try (PreparedStatement psInsertar = con.prepareStatement(queryInsertarCliente, Statement.RETURN_GENERATED_KEYS)) {
            psInsertar.setString(1, nombre);
            psInsertar.setInt(2, idMesa);
            psInsertar.executeUpdate();

            ResultSet rs = psInsertar.getGeneratedKeys();
            if (rs.next()) {
                clienteId = rs.getInt(1); // Obtener el ID del cliente insertado
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
        }

        return clienteId;
    }

 
    public void eliminarCliente(int clienteId) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
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
        String sql = "UPDATE cliente SET nombre = ?, mesa_id = ? WHERE id_cliente = ?";
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
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("id_mesa"), 0, "desconocido");
                cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), mesa);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cliente: " + ex.getMessage());
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("id_mesa"), 0, "desconocido");
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), mesa);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar clientes: " + ex.getMessage());
        }
        return clientes;
    }

    public void reasignarMesa(Cliente cliente, Mesa mesa) {
        cliente.setMesaAsignada(mesa);
        modificarCliente(cliente);
        System.out.println("Mesa asignada al cliente " + cliente.getNombre() + ".");
    }
}

