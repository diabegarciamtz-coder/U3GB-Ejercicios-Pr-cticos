package practicos.ABB;

/**
 * Clase Nodo del Árbol Binario
 * Representa cada nodo con su valor y referencias a hijos.
 * 
 * @author Diana Mabel Garcia
 *         diabegarciamtz@gmail.com  05/12/2025
 */
public class Nodo {
    private int dato;
    private Nodo izquierdo;
    private Nodo derecho;

    /**
     * Constructor del nodo.
     * @param dato Valor entero del nodo.
     */
    public Nodo(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    // --- Getters ---
    public int getDato() {
        return dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    // --- Setters ---
    public void setDato(int dato) {
        this.dato = dato;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}