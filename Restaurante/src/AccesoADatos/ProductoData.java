/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import Entidades.Producto;
import java.sql.Connection;

/**
 *
 * @author Esquina del Vidrio
 */
public class ProductoData {
    private Connection con;

    public ProductoData() {
        con = Conexion.getConexion();
    }
    
    public void agregarProductoAPedido(int pedidoId, Producto producto) {
        
    }
    
    public void quitarProductoDePedido(int pedidoId, int productoId) {
        
    }
    
    public double calcularSubtotal(Producto producto){
        return 1.00;
    }
    
    
}
