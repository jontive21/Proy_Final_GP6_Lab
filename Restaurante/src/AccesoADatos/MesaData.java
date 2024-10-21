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
public class MesaData {
    private Connection con;

    public MesaData() {
        con = Conexion.getConexion();
    }
    
    public void cambiarEstadoMesa(int mesaId, String nuevoEstado) {
        
    }
    
    public List<Pedido> listarPedidosPorMesaYHora(int mesaId, String fecha, String horaInicio, String horaFin) {
        List<Pedido> pedidos = null;
        return pedidos;
    }
}
