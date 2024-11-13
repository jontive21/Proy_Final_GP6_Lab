
package Entidades;


public class DetalleProducto {
    
    private int idDetalleProducto;
    private int idPedido;
    private Producto producto;
    private int cantidad;
    private double monto;

    public DetalleProducto(int idDetalleProducto, int idPedido, Producto producto, int cantidad, double monto) {
        this.idDetalleProducto = idDetalleProducto;
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    public int getIdDetalleProducto() {
        return idDetalleProducto;
    }

    public void setIdDetalleProducto(int idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }


        
}
