
package Entidades;


public class DetalleProducto {
    
    private int idDetalleProducto;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private double monto;

    public DetalleProducto(int idDetalleProducto, Pedido pedido, Producto producto, int cantidad, double monto) {
        this.idDetalleProducto = idDetalleProducto;
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
