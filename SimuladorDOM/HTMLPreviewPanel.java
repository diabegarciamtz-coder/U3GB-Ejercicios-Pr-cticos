package practicos.SimuladorDOM;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra la vista previa HTML renderizada
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
class HTMLPreviewPanel extends JPanel {
    private JEditorPane htmlPane;
    
    /**
     * Constructor del panel de vista previa
     */
    public HTMLPreviewPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Vista HTML"));
        
        initializeHTMLPane();
    }
    
    /**
     * Inicializa el componente de visualización HTML
     */
    private void initializeHTMLPane() {
        htmlPane = new JEditorPane();
        htmlPane.setContentType("text/html");
        htmlPane.setEditable(false);
        htmlPane.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JScrollPane htmlScroll = new JScrollPane(htmlPane);
        add(htmlScroll, BorderLayout.CENTER);
    }
    
    /**
     * Actualiza el contenido HTML mostrado
     * @param htmlContent Contenido HTML a mostrar
     */
    public void updateHTML(String htmlContent) {
        htmlPane.setText(htmlContent);
        htmlPane.setCaretPosition(0); // Volver al inicio
    }
}

