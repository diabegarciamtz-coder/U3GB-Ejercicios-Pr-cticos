package practicos.SimuladorDOM;

import javax.swing.tree.*;

/**
 * Gestor del árbol DOM
 * Maneja la lógica de negocio para crear, modificar y generar HTML
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class DOMTreeManager {
    private DefaultMutableTreeNode root;
    
    /**
     * Constructor que inicializa el árbol con estructura básica
     */
    public DOMTreeManager() {
        initializeDOM();
    }
    
    /**
     * Inicializa la estructura DOM con nodos predeterminados
     * Crea el árbol jerárquico básico: html -> body -> elementos
     */
    private void initializeDOM() {
        // Nodo raíz: html
        root = new DefaultMutableTreeNode(new DOMNode("html", ""));
        
        // Nodo body
        DefaultMutableTreeNode body = new DefaultMutableTreeNode(new DOMNode("body", ""));
        root.add(body);
        
        // Nodo h1 con texto
        DefaultMutableTreeNode h1 = new DefaultMutableTreeNode(new DOMNode("h1", "Bienvenidos"));
        body.add(h1);
        
        // Nodo p con texto
        DefaultMutableTreeNode p = new DefaultMutableTreeNode(new DOMNode("p", "Bienvenido"));
        body.add(p);
        
        // Nodo footer con texto
        DefaultMutableTreeNode footer = new DefaultMutableTreeNode(new DOMNode("footer", "Copyright"));
        body.add(footer);
    }
    
    /**
     * Obtiene el nodo raíz del árbol
     * @return DefaultMutableTreeNode raíz
     */
    public DefaultMutableTreeNode getRoot() {
        return root;
    }
    
    /**
     * Agrega un nuevo nodo al árbol DOM
     * @param parent Nodo padre donde se agregará
     * @param tag Etiqueta HTML del nuevo nodo
     * @param text Texto del nuevo nodo
     * @return El nuevo nodo creado
     */
    public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, String tag, String text) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new DOMNode(tag, text));
        parent.add(newNode);
        return newNode;
    }
    
    /**
     * Elimina un nodo del árbol
     * @param node Nodo a eliminar
     * @return true si se eliminó, false si es la raíz (no se puede eliminar)
     */
    public boolean removeNode(DefaultMutableTreeNode node) {
        if (node == root) {
            return false; // No permitir eliminar el nodo raíz
        }
        node.removeFromParent();
        return true;
    }
    
    /**
     * Genera el código HTML completo a partir de la estructura del árbol
     * @return String con el HTML generado
     */
    public String generateHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background: #f5f5f5; }");
        html.append("h1 { color: #2196F3; margin-bottom: 20px; }");
        html.append("p { color: #333; line-height: 1.6; margin: 10px 0; }");
        html.append("footer { margin-top: 30px; padding-top: 15px; border-top: 2px solid #ccc; color: #666; font-size: 14px; }");
        html.append("div { padding: 10px; margin: 5px 0; background: white; border-left: 3px solid #2196F3; }");
        html.append("</style></head><body>");
        
        generateHTMLRecursive(root, html);
        
        html.append("</body></html>");
        return html.toString();
    }
    
    /**
     * Genera el código HTML de forma recursiva recorriendo el árbol
     * @param node Nodo actual a procesar
     * @param html StringBuilder donde se acumula el HTML generado
     */
    private void generateHTMLRecursive(DefaultMutableTreeNode node, StringBuilder html) {
        DOMNode domNodo = (DOMNode) node.getUserObject();
        
        // No generar etiqueta para el nodo raíz html (ya está en el wrapper)
        if (node == root) {
            for (int i = 0; i < node.getChildCount(); i++) {
                generateHTMLRecursive((DefaultMutableTreeNode) node.getChildAt(i), html);
            }
            return;
        }
        
        // Abrir etiqueta
        html.append("<").append(domNodo.getTag()).append(">");
        
        // Agregar texto si existe
        if (!domNodo.getText().isEmpty()) {
            html.append(domNodo.getText());
        }
        
        // Procesar nodos hijos recursivamente
        for (int i = 0; i < node.getChildCount(); i++) {
            generateHTMLRecursive((DefaultMutableTreeNode) node.getChildAt(i), html);
        }
        
        // Cerrar etiqueta
        html.append("</").append(domNodo.getTag()).append(">");
    }
}

