
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

    public int agregarDetalleProducto(DetalleProducto detalleProducto) {
        int detalleProductoId = -1;
        String query = "INSERT INTO detalle_producto (id_producto, cantidad, subtotal) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, detalleProducto.getProducto().getIdProducto());
            ps.setInt(2, detalleProducto.getCantidad());
            ps.setDouble(3, detalleProducto.getMonto());  // Si el monto es el subtotal

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                detalleProductoId = rs.getInt(1); // Obtener el ID generado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalleProductoId;
    }
    
    public void modificarDetalleProducto(DetalleProducto dp) {
        try {
            String sql = "UPDATE detalle_producto SET id_producto=?, cantidad=?, subtotal=? WHERE id_detalle_productos=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dp.getProducto().getIdProducto());
            ps.setInt(2, dp.getCantidad());
            ps.setDouble(3, dp.getMonto()); // Suponiendo que el monto es el subtotal
            ps.setInt(4, dp.getIdDetalleProducto());

            int logrado = ps.executeUpdate();
            if (logrado == 1) {
                JOptionPane.showMessageDialog(null, "Detalle del producto modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el detalle del producto: " + ex.getMessage());
        }
    }
    
    public void eliminarDetalleProducto(int id_detalle_productos) {
        String sql = "DELETE FROM detalle_producto WHERE id_detalle_productos=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_detalle_productos);

            int logrado = ps.executeUpdate();
            if (logrado == 1) {
                JOptionPane.showMessageDialog(null, "Detalle del producto eliminado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el detalle del producto: " + ex.getMessage());
        }
    }
    
   public void buscarDetalleProducto(int id_detalle_productos) {
        String sql = "SELECT id_producto, cantidad, subtotal FROM detalle_producto WHERE id_detalle_productos=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_detalle_productos);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                double subtotal = rs.getDouble("subtotal");

                // Aquí puedes crear un objeto de tipo DetalleProducto y usar esos datos, por ejemplo:
                // DetalleProducto detalle = new DetalleProducto(id_detalle_productos, new Producto(idProducto), cantidad, subtotal);
                JOptionPane.showMessageDialog(null, "Detalle del producto encontrado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder al detalle del producto: " + ex.getMessage());
        }
}
    
}
