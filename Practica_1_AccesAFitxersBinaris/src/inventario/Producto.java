package inventario;

import java.io.Serializable;

public class Producto implements Serializable {
    private int ID = 0;
    private String Name = "";

    public Producto(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nNombre: " + Name;
    }
}

