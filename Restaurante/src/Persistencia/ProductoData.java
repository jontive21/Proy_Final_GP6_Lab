/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Conexion;
import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProductoData {
    private Connection con;

    public ProductoData() {
        con = Conexion.getConexion();
    }

    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, cantidad, precio, tipo_producto) VALUES (?, ?, ?, ?)";
        String error = "";

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getTipo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));  // Establece el ID autogenerado en el objeto Producto
            }
            rs.close();

            JOptionPane.showMessageDialog(null, "Producto agregado correctamente con ID: " + producto.getIdProducto());
        } catch (SQLException ex) {
            if ("23000".equals(ex.getSQLState())) {
                error = "No puede agregar un producto con el mismo id";
            } else {
                error = "Error al agregar producto:";
            }
            JOptionPane.showMessageDialog(null, error + ex.getMessage());
        }
    }

    public void eliminarProducto(int idprod){
        String sql="UPDATE producto SET cantidad = 0 WHERE id_producto=? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,idprod );
            ps.executeUpdate();
            System.out.println("Se hizo");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto");
        }
    
    }
    
    public void editarProducto(int idprod,Producto producto){
    String sql= "UPDATE producto  SET nombre = ? ,cantidad = ? ,precio = ?,  tipo_producto = ? WHERE id_producto = ?";
    try{
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, producto.getNombre());
        ps.setInt(2, producto.getCantidad());
        ps.setDouble(3, producto.getPrecio());
        ps.setString(4, producto.getTipo());
        ps.setInt(5, idprod);
        ps.executeUpdate();
        
    }catch(SQLException ex){
        ex.printStackTrace();
    }
    
    
    }
    
        
    public List <Producto> listarProductos(){
        List <Producto> productos  = new ArrayList<>();
            String sql= "SELECT * FROM producto WHERE cantidad > 0";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                Producto producto = new Producto(rs.getInt("id_producto"), rs.getString("nombre"),rs.getInt("cantidad"), rs.getDouble("precio"),rs.getString("tipo_producto") );
                productos.add(producto);
                }
                rs.close();
            }catch(SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al listar productos"  );
                
            }

        
        return productos;
    }
    
    
       public List <Producto> listarProductosSinStock(){
        List <Producto> productos  = new ArrayList<>();
            String sql= "SELECT * FROM producto WHERE cantidad = 0";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                Producto producto = new Producto(rs.getInt("id_producto"), rs.getString("nombre"),rs.getInt("cantidad"), rs.getDouble("precio"),rs.getString("tipo_producto") );
                productos.add(producto);
                }
                
                rs.close();
                
            }catch(SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al listar productos sin stock"  );
                
            }

        
        return productos;
    }
    
    
        public List<Producto> buscarProductoPorNombre(String nombreProducto) {
            List<Producto> productos = new ArrayList<>();
            String sql = "SELECT * FROM producto WHERE nombre LIKE ?";

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + nombreProducto + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getString("tipo_producto")
                    );
                    productos.add(producto);
                }
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al buscar producto por nombre: " + e.getMessage());
            }

            return productos;
        }
    
    

    public double calcularSubtotal(Producto producto) {
        return producto.getPrecio() * producto.getCantidad();
    }
}