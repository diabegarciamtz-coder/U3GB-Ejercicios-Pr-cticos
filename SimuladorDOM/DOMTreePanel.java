package practicos.SimuladorDOM;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Panel que muestra el árbol DOM visual
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class DOMTreePanel extends JPanel {
    private JTree tree;
    private DefaultMutableTreeNode root;
    
    /**
     * Constructor del panel del árbol
     * @param root Nodo raíz del árbol
     */
    public DOMTreePanel(DefaultMutableTreeNode root) {
        this.root = root;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Estructura DOM (Árbol)"));
        
        initializeTree();
    }
    
    /**
     * Inicializa el componente JTree
     */
    private void initializeTree() {
        tree = new JTree(root);
        tree.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Expandir todos los nodos por defecto
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
        
        JScrollPane treeScroll = new JScrollPane(tree);
        add(treeScroll, BorderLayout.CENTER);
    }
    
    /**
     * Obtiene el componente JTree
     * @return JTree del DOM
     */
    public JTree getTree() {
        return tree;
    }
    
    /**
     * Recarga el modelo del árbol
     */
    public void reloadTree() {
        ((DefaultTreeModel) tree.getModel()).reload();
        expandAllNodes();
    }
    
    /**
     * Expande todos los nodos del árbol
     */
    private void expandAllNodes() {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }
}

