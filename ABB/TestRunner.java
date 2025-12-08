package practicos.ABB;

/**
 * Ejecutor de pruebas predefinidas para el ABB
 * Implementa todas las pruebas de la rúbrica
 * 
 * @author Diana Mabel Garcia
 *         diabegarciamtz@gmail.com  05/12/2025
 */
class TestRunner {
    private ArbolBusquedaBinario bst;
    
    /** 
     * Constructor del ejecutor de pruebas
     * @param bst Referencia al árbol binario de búsqueda
     */
    public TestRunner(ArbolBusquedaBinario bst) {
        this.bst = bst;
    }
    
    /**
     * Prueba P1.1: Árbol completo y balanceado
     * Secuencia: 50, 30, 70, 20, 40, 60, 80
     * @return String con el resultado
     */
    public String runP11() {
        bst.limpiar();
        StringBuilder result = new StringBuilder();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int v : values) {
            bst.insertar(v);
        }
        
        result.append("=== P1.1: Árbol Balanceado ===\n");
        result.append("Insertados: 50, 30, 70, 20, 40, 60, 80\n");
        result.append("InOrden: ").append(bst.recorridoInOrden()).append("\n");
        result.append("PreOrden: ").append(bst.recorridoPostOrden()).append("\n");
        result.append("PostOrden: ").append(bst.recorridoPreOrden()).append("\n");
        
        return result.toString();
    }
    
    /**
     * Prueba P1.2: Árbol degenerado (lista enlazada derecha)
     */
    public String runP12() {
        bst.limpiar();
        StringBuilder result = new StringBuilder();
        int[] values = {10, 20, 30, 40, 50, 60, 70};
        
        for (int v : values) {
            bst.insertar(v);
        }
        
        result.append("=== P1.2: Degenerado Derecha ===\n");
        result.append("Insertados: 10, 20, 30, 40, 50, 60, 70\n");
        result.append("InOrden: ").append(bst.recorridoInOrden()).append("\n");
        
        return result.toString();
    }
    
    /**
     * Prueba P1.3: Árbol degenerado (lista enlazada izquierda)
     */
    public String runP13() {
        bst.limpiar();
        StringBuilder result = new StringBuilder();
        int[] values = {70, 60, 50, 40, 30, 20, 10};
        
        for (int v : values) {
            bst.insertar(v);
        }
        
        result.append("=== P1.3: Degenerado Izquierda ===\n");
        result.append("Insertados: 70, 60, 50, 40, 30, 20, 10\n");
        result.append("InOrden: ").append(bst.recorridoInOrden()).append("\n");
        
        return result.toString();
    }
    
    /**
     * Prueba P1.4: Manejo de duplicados
     */
    public String runP14() {
        bst.limpiar();
        StringBuilder result = new StringBuilder();
        
        bst.insertar(50);
        boolean dup = bst.insertar(50);
        
        result.append("=== P1.4: Duplicados ===\n");
        result.append("Primer 50: insertado\n");
        result.append("Segundo 50: ").append(dup ? "insertado" : "duplicado ignorado").append("\n");
        
        return result.toString();
    }
    
    /**
     * Prueba P4: Casos de eliminación
     */
    public String runP4() {
        bst.limpiar();
        StringBuilder result = new StringBuilder();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int v : values) {
            bst.insertar(v);
        }
        
        result.append("=== P4: Casos de Eliminación ===\n");
        result.append("Árbol inicial: 50, 30, 70, 20, 40, 60, 80\n");
        result.append("\nPuede probar:\n");
        result.append("- Eliminar 20 (hoja)\n");
        result.append("- Eliminar 30 (1 hijo)\n");
        result.append("- Eliminar 70 (2 hijos)\n");
        result.append("- Eliminar 50 (raíz con 2 hijos)\n");
        
        return result.toString();
    }
}

