/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Esquina del Vidrio
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private Mesero mesero;
    private Mesa mesa;
    private List<Producto> productos;
    private double monto;
    private boolean pagado; /*pagado o impago*/
    private boolean estado; /*pendiente o entregado*/
    private Date fechaPedido;

    public Pedido(int id, Cliente cliente, Mesero mesero, Mesa mesa, List<Producto> productos, double monto, Date fechaPedido, boolean pagado, boolean estado ) {
        this.id = id;
        this.cliente = cliente;
        this.mesero = mesero;
        this.mesa = mesa;
        this.productos = productos;
        this.monto = monto;
        this.pagado = false;
        this.estado= false;
        this.fechaPedido = fechaPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
   
    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
