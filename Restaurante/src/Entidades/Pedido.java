/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;
import java.util.List;



public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private Mesero mesero;
    private Mesa mesa;
    private List<DetalleProducto> productos;
    private double montoTotal;
    private boolean pagado;
    private boolean entregado;
    private LocalDateTime fecha;

    public Pedido(int idPedido, Cliente cliente, Mesero mesero, Mesa mesa, List<DetalleProducto> productos, double montoTotal, boolean pagado, boolean entregado, LocalDateTime fecha) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.mesero = mesero;
        this.mesa = mesa;
        this.productos = productos;
        this.montoTotal = montoTotal;
        this.pagado = pagado;
        this.entregado = entregado;
        this.fecha = fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    public List<DetalleProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleProducto> productos) {
        this.productos = productos;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

  

  

  

    
}
