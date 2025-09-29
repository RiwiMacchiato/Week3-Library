package library.model;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private Usuario usuario;
    private Item item;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(int id,Usuario usuario, Item item, LocalDate fechaPrestamo) {
        this.id = id;
        this.usuario = usuario;
        this.item = item;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
        item.setDisponible(false);
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public void setUsuario(Usuario usuario) {
        if(usuario != null){
            this.usuario = usuario;
        }
    }

    public void setItem(Item item) {
        if(item != null){
            this.item = item;
        }
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        if(fechaPrestamo != null){
            this.fechaPrestamo = fechaPrestamo;
        }
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void devolver(){
        fechaDevolucion = LocalDate.now();
        item.setDisponible(true);
    }



}
