/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Vistas;

import Entidades.Mesa;
import Entidades.Producto;
import Persistencia.MesaData;
import Persistencia.ProductoData;


/**
 *
 * @author Esquina del Vidrio
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
    Mesa mesa = new Mesa(1, 5, "Ocupada");
    MesaData mesa1 = new MesaData();
    mesa1.crearMesa(mesa);
    mesa1.listarMesasPorEstado("Ocupada");
    mesa1.cambiarEstadoMesa(1, "libre");
    
    
    Producto prod = new Producto(12, "pizaaaa",10,500,"comida");
    ProductoData prodData = new ProductoData();
    prodData.agregarProducto(prod);
    }
    
}
