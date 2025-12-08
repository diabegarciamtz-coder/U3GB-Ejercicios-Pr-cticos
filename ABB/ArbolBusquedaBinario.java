package practicos.ABB;

import java.util.ArrayList;

/**
 * Clase Árbol Binario de Búsqueda (ABB)
 * Implementa todas las operaciones básicas del ABB.
 *
 * Propiedades del ABB:
 * - El subárbol izquierdo contiene valores menores.
 * - El subárbol derecho contiene valores mayores.
 * - No permite valores duplicados.
 *
 * @author Diana Mabel Garcia
 *         diabegarciamtz@gmail.com  05/12/2025
 */
public class ArbolBusquedaBinario {
    private Nodo raiz;
    private Nodo nodoBuscado; // Para resaltar el nodo encontrado

    /**
     * Constructor del ABB
     */
    public ArbolBusquedaBinario() {
        this.raiz = null;
        this.nodoBuscado = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }
    
    
    /**
     * Inserta un valor en el árbol.
     * @param dato Valor a insertar.
     * @return true si se insertó, false si ya existía.
     */
    public boolean insertar(int dato) {
        if (buscar(dato)) {
            return false; // No permitir duplicados
        }
        raiz = insertarRecursivo(raiz, dato);
        nodoBuscado = null;
        return true;
    }

    /**
     * Método recursivo para insertar.
     */
    private Nodo insertarRecursivo(Nodo nodo, int dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }

        return nodo;
    }

    /**
     * Elimina un nodo del árbol.
     * @param dato Valor a eliminar.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminar(int dato) {
        if (!buscar(dato)) {
            return false;
        }
        raiz = eliminarRecursivo(raiz, dato);
        nodoBuscado = null;
        return true;
    }

    /**
     * Método recursivo para eliminar.
     * Maneja los 3 casos: hoja, un hijo, dos hijos.
     */
    private Nodo eliminarRecursivo(Nodo nodo, int dato) {
        if (nodo == null) return null;

        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), dato));
        } else {
            // Caso 1: Nodo hoja
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            // Caso 2: Nodo con un hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // Caso 3: Nodo con dos hijos
            Nodo sucesor = encontrarMinimo(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getDato()));
        }

        return nodo;
    }

    /**
     * Encuentra el nodo con valor mínimo en un subárbol.
     */
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    /**
     * Busca un valor en el árbol.
     * @param dato Valor a buscar.
     * @return true si existe, false si no.
     */
    public boolean buscar(int dato) {
        nodoBuscado = buscarRecursivo(raiz, dato);
        return nodoBuscado != null;
    }

    private Nodo buscarRecursivo(Nodo nodo, int dato) {
        if (nodo == null || nodo.getDato() == dato) {
            return nodo;
        }
        if (dato < nodo.getDato()) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        }
        return buscarRecursivo(nodo.getDerecho(), dato);
    }

    /**
     * Recorrido InOrden (Izquierda-Raíz-Derecha).
     */
    public ArrayList<Integer> recorridoInOrden() {
        ArrayList<Integer> resultado = new ArrayList<>();
        inOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void inOrdenRecursivo(Nodo nodo, ArrayList<Integer> resultado) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), resultado);
            resultado.add(nodo.getDato());
            inOrdenRecursivo(nodo.getDerecho(), resultado);
        }
    }

    /**
     * Recorrido PreOrden (Raíz-Izquierda-Derecha).
     */
    public ArrayList<Integer> recorridoPreOrden() {
        ArrayList<Integer> resultado = new ArrayList<>();
        preOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void preOrdenRecursivo(Nodo nodo, ArrayList<Integer> resultado) {
        if (nodo != null) {
            resultado.add(nodo.getDato());
            preOrdenRecursivo(nodo.getIzquierdo(), resultado);
            preOrdenRecursivo(nodo.getDerecho(), resultado);
        }
    }

    /**
     * Recorrido PostOrden (Izquierda-Derecha-Raíz).
     */
    public ArrayList<Integer> recorridoPostOrden() {
        ArrayList<Integer> resultado = new ArrayList<>();
        postOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void postOrdenRecursivo(Nodo nodo, ArrayList<Integer> resultado) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo(), resultado);
            postOrdenRecursivo(nodo.getDerecho(), resultado);
            resultado.add(nodo.getDato());
        }
    }

    /**
     * Limpia el árbol completamente.
     */
    public void limpiar() {
        raiz = null;
        nodoBuscado = null;
    }
}