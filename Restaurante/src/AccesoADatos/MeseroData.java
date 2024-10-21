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
public class MeseroData {
    private Connection con;

    public MeseroData() {
        con = Conexion.getConexion();
    }
    
    
   public List<Pedido> listarPedidosCobradosPorMesero(int meseroId, String fecha){
       return null;
   }
   
   
}
