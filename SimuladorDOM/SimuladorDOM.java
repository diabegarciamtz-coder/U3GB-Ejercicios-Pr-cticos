package practicos.SimuladorDOM;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Simulador de DOM - Aplicación Principal
 * 
 * Esta aplicación simula la estructura del DOM de una página HTML
 * usando un árbol visual y mostrando el resultado HTML en tiempo real.
 * 
 * Arquitectura:
 * - DOMNode: Modelo de datos del nodo
 * - DOMTreeManager: Lógica de negocio del árbol
 * - DOMTreePanel: Vista del árbol
 * - HTMLPreviewPanel: Vista del HTML
 * - HTMLViewerWindow: Ventana de visualización HTML completa
 * - ControlPanel: Controles de usuario
 * - SimuladorDOM: Controlador principal
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
public class SimuladorDOM extends JFrame {
    
    private DOMTreeManager treeManager;
    private DOMTreePanel treePanel;
    private HTMLPreviewPanel htmlPanel;
    private ControlPanel controlPanel;
    private HTMLViewerWindow htmlViewerWindow;
    
    /**
     * Constructor principal que inicializa la aplicación
     */
    public SimuladorDOM() {
        setTitle("Simulador DOM - Creación de Página Web");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Inicializar el gestor del árbol
        treeManager = new DOMTreeManager();
        
        // Crear los paneles de la interfaz
        treePanel = new DOMTreePanel(treeManager.getRoot());
        htmlPanel = new HTMLPreviewPanel();
        controlPanel = new ControlPanel(
            e -> agregarNodo(),
            e -> eliminarNodo(),
            e -> abrirVisualizadorHTML()
        );
        
        // Organizar la interfaz
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            treePanel,
            htmlPanel
        );
        splitPane.setDividerLocation(400);
        
        add(controlPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        
        // Actualizar vista HTML inicial
        updateHTMLView();
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Agrega un nuevo nodo al árbol DOM
     */
    private void agregarNodo() {
        String tag = controlPanel.getTag();
        String text = controlPanel.getText();
        
        // Validar que la etiqueta no esté vacía
        if (tag.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese una etiqueta HTML", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener nodo seleccionado o usar root como predeterminado
        TreePath selectedPath = treePanel.getTree().getSelectionPath();
        DefaultMutableTreeNode selectedNode = (selectedPath != null) 
            ? (DefaultMutableTreeNode) selectedPath.getLastPathComponent() 
            : treeManager.getRoot();
        
        // Agregar el nuevo nodo
        treeManager.addNode(selectedNode, tag, text);
        
        // Actualizar la vista
        treePanel.reloadTree();
        treePanel.getTree().expandPath(new TreePath(selectedNode.getPath()));
        updateHTMLView();
        
        // Limpiar campos y mostrar confirmación
        controlPanel.clearFields();
        JOptionPane.showMessageDialog(this, 
            "Nodo agregado correctamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Elimina el nodo seleccionado del árbol DOM
     */
    private void eliminarNodo() {
        TreePath selectedPath = treePanel.getTree().getSelectionPath();
        
        // Validar que hay un nodo seleccionado
        if (selectedPath == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un nodo para eliminar", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DefaultMutableTreeNode selectedNode = 
            (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
        
        // Intentar eliminar
        boolean eliminado = treeManager.removeNode(selectedNode);
        
        if (!eliminado) {
            JOptionPane.showMessageDialog(this, 
                "No se puede eliminar el nodo raíz (html)", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Confirmar eliminación
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este nodo y sus hijos?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Actualizar la vista
            treePanel.reloadTree();
            updateHTMLView();
            
            JOptionPane.showMessageDialog(this, 
                "Nodo eliminado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Abre o actualiza la ventana de visualización HTML
     */
    private void abrirVisualizadorHTML() {
        String html = treeManager.generateHTML();
        
        if (htmlViewerWindow == null || !htmlViewerWindow.isVisible()) {
            htmlViewerWindow = new HTMLViewerWindow(html);
            htmlViewerWindow.setVisible(true);
        } else {
            htmlViewerWindow.updateHTML(html);
            htmlViewerWindow.toFront();
            htmlViewerWindow.requestFocus();
        }
    }
    
    /**
     * Actualiza la vista HTML usando el gestor del árbol
     */
    private void updateHTMLView() {
        String html = treeManager.generateHTML();
        htmlPanel.updateHTML(html);
        
        // Actualizar ventana externa si está abierta
        if (htmlViewerWindow != null && htmlViewerWindow.isVisible()) {
            htmlViewerWindow.updateHTML(html);
        }
    }
    
    /**
     * Método principal para ejecutar la aplicación
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            SimuladorDOM app = new SimuladorDOM();
            app.setVisible(true);
        });
    }
}