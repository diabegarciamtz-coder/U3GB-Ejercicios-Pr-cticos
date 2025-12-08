package practicos.SimuladorDOM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel de controles para agregar y eliminar nodos
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class ControlPanel extends JPanel {
    private JTextField tagField;
    private JTextField textField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton viewHTMLButton;
    
    /**
     * Constructor del panel de controles
     * @param addListener Listener para el botón de agregar
     * @param deleteListener Listener para el botón de eliminar
     * @param viewHTMLListener Listener para el botón de visualizar HTML
     */
    public ControlPanel(ActionListener addListener, ActionListener deleteListener, 
                       ActionListener viewHTMLListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Controles"));
        
        initializeComponents(addListener, deleteListener, viewHTMLListener);
    }
    
    /**
     * Inicializa los componentes del panel
     */
    private void initializeComponents(ActionListener addListener, 
                                     ActionListener deleteListener,
                                     ActionListener viewHTMLListener) {
        // Campo para la etiqueta HTML
        add(new JLabel("Etiqueta:"));
        tagField = new JTextField(10);
        add(tagField);
        
        // Campo para el texto del nodo
        add(new JLabel("Texto:"));
        textField = new JTextField(15);
        add(textField);
        
        // Botón para agregar nodo
        addButton = new JButton("Agregar Nodo");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.GREEN);
        addButton.setFocusPainted(false);
        addButton.addActionListener(addListener);
        add(addButton);
        
        // Botón para eliminar nodo
        deleteButton = new JButton("Eliminar Nodo");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.RED);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(deleteListener);
        add(deleteButton);
        
        // Separador visual
        add(new JSeparator(SwingConstants.VERTICAL) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(2, 30);
            }
        });
        
        // Botón para ver HTML en ventana completa
        viewHTMLButton = new JButton("🌐 Ver Página HTML");
        viewHTMLButton.setBackground(new Color(33, 150, 243));
        viewHTMLButton.setForeground(Color.BLACK);
        viewHTMLButton.setFocusPainted(false);
        viewHTMLButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewHTMLButton.addActionListener(viewHTMLListener);
        add(viewHTMLButton);
    }
    
    /**
     * Obtiene el texto del campo de etiqueta
     * @return String con la etiqueta
     */
    public String getTag() {
        return tagField.getText().trim();
    }
    
    /**
     * Obtiene el texto del campo de texto
     * @return String con el texto
     */
    public String getText() {
        return textField.getText().trim();
    }
    
    /**
     * Limpia los campos de entrada
     */
    public void clearFields() {
        tagField.setText("");
        textField.setText("");
    }
}