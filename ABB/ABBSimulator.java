package practicos.ABB;

import javax.swing.*;
import java.awt.*;

/**
 * Simulador de Árbol Binario de Búsqueda (ABB)
 * Aplicación principal que coordina todos los componentes
 * 
 * Arquitectura MVC:
 * - Model: BinarySearchTree, Node
 * - View: TreePanel, UI Components
 * - Controller: ABBSimulator
 * 
 * @author Diana Mabel Garcia
 *         diabegarciamtz@gmail.com  05/12/2025
 */
public class ABBSimulator extends JFrame {
    
    private ArbolBusquedaBinario bst;
    private ArbolPanel treePanel;
    private JTextField inputField;
    private JTextArea outputArea;
    private JComboBox<String> testCombo;
    private TestRunner testRunner;
    
    /**
     * Constructor principal
     */
    public ABBSimulator() {
        setTitle("Simulador de Árbol Binario de Búsqueda (ABB)");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        bst = new ArbolBusquedaBinario();
        testRunner = new TestRunner(bst);
        
        add(createControlPanel(), BorderLayout.NORTH);
        
        treePanel = new ArbolPanel(bst);
        add(treePanel, BorderLayout.CENTER);
        
        add(createOutputPanel(), BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Crea el panel de controles
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Controles de Operaciones"));
        
        panel.add(new JLabel("Valor:"));
        inputField = new JTextField(8);
        panel.add(inputField);
        
        addButton(panel, "Insertar", e -> insertNode());
        addButton(panel, "Eliminar", e -> deleteNode());
        addButton(panel, "Buscar", e -> searchNode());
        addButton(panel, "Limpiar", e -> clearTree());
        
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        
        addButton(panel, "InOrden", e -> inOrder());
        addButton(panel, "PreOrden", e -> preOrder());
        addButton(panel, "PostOrden", e -> postOrder());
        
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        
        panel.add(new JLabel("Pruebas:"));
        testCombo = new JComboBox<>(new String[]{
            "P1.1: Árbol Balanceado",
            "P1.2: Degenerado Derecha",
            "P1.3: Degenerado Izquierda",
            "P1.4: Duplicados",
            "P4: Casos de Eliminación"
        });
        panel.add(testCombo);
        
        addButton(panel, "Ejecutar Prueba", e -> runTest());
        
        return panel;
    }
    
    /**
     * Crea el panel de salida
     */
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Resultados"));
        panel.setPreferredSize(new Dimension(0, 150));
        
        outputArea = new JTextArea(6, 0);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scroll = new JScrollPane(outputArea);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }
    
    private void insertNode() {
        try {
            int value = Integer.parseInt(inputField.getText().trim());
            boolean inserted = bst.insertar(value);
            
            if (inserted) {
                outputArea.append("✓ Nodo " + value + " insertado\n");
            } else {
                outputArea.append("✗ Nodo " + value + " ya existe (duplicado ignorado)\n");
            }
            
            treePanel.repaint();
            inputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteNode() {
        try {
            int value = Integer.parseInt(inputField.getText().trim());
            boolean deleted = bst.eliminar(value);
            
            if (deleted) {
                outputArea.append("✓ Nodo " + value + " eliminado\n");
            } else {
                outputArea.append("✗ Nodo " + value + " no encontrado\n");
            }
            
            treePanel.repaint();
            inputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchNode() {
        try {
            int value = Integer.parseInt(inputField.getText().trim());
            boolean found = bst.buscar(value);
            
            if (found) {
                outputArea.append("✓ Nodo " + value + " encontrado (resaltado en rojo)\n");
            } else {
                outputArea.append("✗ Nodo " + value + " no encontrado\n");
            }
            
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearTree() {
        bst.limpiar();
        outputArea.setText("");
        treePanel.repaint();
        outputArea.append("✓ Árbol limpiado\n");
    }
    
    private void inOrder() {
        outputArea.append("InOrden: " + bst.recorridoInOrden() + "\n");
    }
    
    private void preOrder() {
        outputArea.append("PreOrden: " + bst.recorridoPreOrden() + "\n");
    }
    
    private void postOrder() {
        outputArea.append("PostOrden: " + bst.recorridoPostOrden() + "\n");
    }
    
    private void runTest() {
        String result = "";
        
        switch (testCombo.getSelectedIndex()) {
            case 0: result = testRunner.runP11(); break;
            case 1: result = testRunner.runP12(); break;
            case 2: result = testRunner.runP13(); break;
            case 3: result = testRunner.runP14(); break;
            case 4: result = testRunner.runP4(); break;
        }
        
        outputArea.append(result + "\n");
        treePanel.repaint();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            ABBSimulator app = new ABBSimulator();
            app.setVisible(true);
        });
    }
}

