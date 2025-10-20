package ejemplo;

import java.io.Serializable;

public class Persona implements Serializable {
    int id = 0;
    String nom = "";
    int edad = 0;
    String direccio = "";

    public Persona(int id, String nom, int edad, String direccio) {
        this.id = id;
        this.nom = nom;
        this.edad = edad;
        this.direccio = direccio;
    }
}

