/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import Entidades.Pedido;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Esquina del Vidrio
 */
public class PedidoData {
    private Connection con;

    public PedidoData() {
        con = Conexion.getConexion();
    }
    
     public double calcularMontoTotal(int pedidoId) {
        return 1.00;
    }
     
    public List<Pedido> listarPedidosCobradosPorMesero(int meseroId, String fecha) {
        
        return null;
    }
    
    public double listarIngresosPorFecha(String fecha) {
        return 1.00;
    }
    
}
