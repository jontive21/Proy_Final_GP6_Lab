
package Persistencia;

import Entidades.Conexion;
import Entidades.DetalleProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DetalleProductoData {
       private Connection con;

    public DetalleProductoData() {
        con = Conexion.getConexion();
    }

    public void agregarDetalleProducto (DetalleProducto dp){
        
           try {
               String sql="INSERT INTO detalle_producto(id_producto, cantidad, monto)"
                       +"VALUES(?,?,?)";
               PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//iddetproduct
               ps.setInt(1,dp.getProducto().getIdProducto());
               ps.setInt(2,dp.getCantidad());
               ps.setDouble(3,dp.getMonto());
               ps.executeUpdate();
               
               ResultSet rs=ps.getGeneratedKeys(); //va a venir una matriz con una sola conlumna del id 
               if(rs.next()){ //si hay un elemento
                   dp.setIdDetalleProducto(rs.getInt(1)); //devolviendo con referencia id
                   JOptionPane.showMessageDialog(null, "agregaste el detalle producto");
                   
               }
               
           } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "error al acceder al detalle del producto" + ex.getMessage());
           }    
    }
    
    public void modificarDetalleProducto(DetalleProducto dp){
           try {
               //ya existe en la base de datos (tiene id) tenemos que tomar los datos y mandarlo con una sentencia update
               String sql="UPDATE detalle_producto SET id_producto=?, cantidad=?, monto=?"
                       + "WHERE id_detalle_productos=?";
               PreparedStatement ps=con.prepareStatement(sql);
               ps.setInt(1,dp.getProducto().getIdProducto());
               ps.setInt(2,dp.getCantidad());
               ps.setDouble(3,dp.getMonto());
               ps.setInt(4, dp.getIdDetalleProducto());
               int logrado = ps.executeUpdate();
               if (logrado==1){
                    JOptionPane.showMessageDialog(null, "Detalle del producto modificado");
               }                             
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "error al acceder al detalle del producto"+ ex.getMessage());
           }
    }
    
    public void eliminarDetalleProducto(int id_detalle_productos){
        
        String sql="DELETE FROM detalle_producto WHERE id_detalle_productos=?";
           try {
               PreparedStatement ps=con.prepareStatement(sql);
               ps.setInt(1, id_detalle_productos);
                int logrado = ps.executeUpdate();
               if (logrado==1){
                    JOptionPane.showMessageDialog(null, "Detalle del producto eliminado");
               }
           } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "error al eliminar el detalle del producto"+ ex.getMessage());   
           }
    }
    
    public void buscarDetalleProducto(int id_detalle_productos){
        String sql= "SELECT id_producto, cantidad, monto FROM Detalle_Producto WHERE id_detalle_productos=?"; //estado=?
           try {
               PreparedStatement ps=con.prepareStatement(sql);
               ps.setInt(1, id_detalle_productos);
               int logrado = ps.executeUpdate();
               if (logrado==1){
                    JOptionPane.showMessageDialog(null, "Detalle del producto encontrado");
               }                    
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "error al acceder al detalle del producto"+ ex.getMessage());   
           }
    }
    
}
