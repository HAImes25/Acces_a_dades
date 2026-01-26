package Ex2;

import java.io.Serializable;

public class Libro implements Serializable {

    int id;
    String titulo;
    String autor;
    Double precio;

    public Libro(int id, String titulo, String autor, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String toString(){
        System.out.println("ID : " + this.getId());
        System.out.println("Titulo : " + this.getTitulo());
        System.out.println("Autor : " + this.getAutor());
        System.out.println("Precio : " + this.getPrecio());
        return null;
    }


}
