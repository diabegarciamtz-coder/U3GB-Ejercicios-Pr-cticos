package practicos.SimuladorDOM;
/**
 * Clase que representa un nodo del DOM
 * Contiene la etiqueta HTML y el texto del elemento
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class DOMNode {
    private String tag;
    private String text;
    
    /**
     * Constructor del nodo DOM
     * @param tag Etiqueta HTML (ej: div, p, h1)
     * @param text Contenido de texto del elemento
     */
    public DOMNode(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }
    
    /**
     * Obtiene la etiqueta HTML
     * @return String con la etiqueta
     */
    public String getTag() {
        return tag;
    }
    
    /**
     * Obtiene el texto del nodo
     * @return String con el texto
     */
    public String getText() {
        return text;
    }
    
    /**
     * Establece la etiqueta HTML
     * @param tag Nueva etiqueta
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    /**
     * Establece el texto del nodo
     * @param text Nuevo texto
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Representación en texto del nodo para el árbol
     * @return String con formato "etiqueta - texto"
     */
    @Override
    public String toString() {
        if (text.isEmpty()) {
            return tag;
        }
        return tag + " - " + text;
    }
}
