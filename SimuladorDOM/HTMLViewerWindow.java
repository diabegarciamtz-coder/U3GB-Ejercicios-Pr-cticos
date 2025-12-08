package practicos.SimuladorDOM;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana independiente para visualizar el HTML renderizado
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class HTMLViewerWindow extends JFrame {
    private JEditorPane htmlPane;
    private JTextArea codeArea;
    private JTabbedPane tabbedPane;
    
    /**
     * Constructor de la ventana de visualización HTML
     * @param htmlContent Contenido HTML inicial a mostrar
     */
    public HTMLViewerWindow(String htmlContent) {
        setTitle("Visualizador HTML - Página Generada");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        initializeComponents();
        updateHTML(htmlContent);
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa los componentes de la ventana
     */
    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        
        // Panel de vista renderizada
        JPanel renderPanel = createRenderPanel();
        tabbedPane.addTab("Vista Renderizada", renderPanel);
        
        // Panel de código fuente
        JPanel codePanel = createCodePanel();
        tabbedPane.addTab("Código Fuente", codePanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel de vista renderizada
     * @return JPanel con el visor HTML
     */
    private JPanel createRenderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        htmlPane = new JEditorPane();
        htmlPane.setContentType("text/html");
        htmlPane.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(htmlPane);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Página HTML Renderizada"));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Crea el panel de código fuente
     * @return JPanel con el área de texto del código
     */
    private JPanel createCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        codeArea.setTabSize(2);
        
        JScrollPane scrollPane = new JScrollPane(codeArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Código HTML Generado"));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Crea el panel de botones
     * @return JPanel con los botones de acción
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton refreshButton = new JButton("Actualizar");
        refreshButton.setBackground(new Color(33, 150, 243));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "La vista se actualiza automáticamente al modificar el DOM",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton copyButton = new JButton("Copiar Código");
        copyButton.setBackground(new Color(76, 175, 80));
        copyButton.setForeground(Color.WHITE);
        copyButton.setFocusPainted(false);
        copyButton.addActionListener(e -> copyCodeToClipboard());
        
        JButton closeButton = new JButton("Cerrar");
        closeButton.setBackground(new Color(158, 158, 158));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());
        
        panel.add(refreshButton);
        panel.add(copyButton);
        panel.add(closeButton);
        
        return panel;
    }
    
    /**
     * Copia el código HTML al portapapeles
     */
    private void copyCodeToClipboard() {
        String code = codeArea.getText();
        java.awt.datatransfer.StringSelection selection = 
            new java.awt.datatransfer.StringSelection(code);
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
            .setContents(selection, selection);
        
        JOptionPane.showMessageDialog(this,
            "Código HTML copiado al portapapeles",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Actualiza el contenido HTML mostrado
     * @param htmlContent Nuevo contenido HTML
     */
    public void updateHTML(String htmlContent) {
        // Actualizar vista renderizada
        htmlPane.setText(htmlContent);
        htmlPane.setCaretPosition(0);
        
        // Actualizar código fuente formateado
        String formattedHTML = formatHTML(htmlContent);
        codeArea.setText(formattedHTML);
        codeArea.setCaretPosition(0);
    }
    
    /**
     * Formatea el HTML para mejor visualización
     * @param html HTML sin formatear
     * @return HTML formateado con indentación
     */
    private String formatHTML(String html) {
        StringBuilder formatted = new StringBuilder();
        int indent = 0;
        boolean inTag = false;
        
        for (int i = 0; i < html.length(); i++) {
            char c = html.charAt(i);
            
            if (c == '<') {
                // Nueva línea antes de abrir etiqueta (excepto primera)
                if (i > 0 && !inTag) {
                    formatted.append('\n');
                    addIndentation(formatted, indent);
                }
                
                inTag = true;
                
                // Verificar si es etiqueta de cierre
                if (i + 1 < html.length() && html.charAt(i + 1) == '/') {
                    indent--;
                    formatted.setLength(formatted.length() - 2); // Remover última indentación
                }
                
                formatted.append(c);
            } else if (c == '>') {
                formatted.append(c);
                inTag = false;
                
                // Aumentar indentación si no es etiqueta de cierre o auto-cerrada
                if (i > 0 && html.charAt(i - 1) != '/' && 
                    (i < 2 || html.charAt(i - 2) != '/')) {
                    String tag = extractTag(html, i);
                    if (!tag.startsWith("/")) {
                        indent++;
                    }
                }
            } else {
                formatted.append(c);
            }
        }
        
        return formatted.toString();
    }
    
    /**
     * Extrae el nombre de la etiqueta
     * @param html Cadena HTML
     * @param endPos Posición del cierre >
     * @return Nombre de la etiqueta
     */
    private String extractTag(String html, int endPos) {
        int startPos = html.lastIndexOf('<', endPos);
        if (startPos != -1) {
            return html.substring(startPos + 1, endPos);
        }
        return "";
    }
    
    /**
     * Agrega indentación al StringBuilder
     * @param sb StringBuilder donde agregar
     * @param level Nivel de indentación
     */
    private void addIndentation(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
    }
}