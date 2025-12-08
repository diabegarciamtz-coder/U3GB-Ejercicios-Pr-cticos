package practicos.ABB;

import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado para dibujar el árbol visualmente.
 * Renderiza el Árbol Binario de Búsqueda (ABB) de forma gráfica con nodos y conexiones.
 * 
 * @author Diana Mabel Garcia
 *         diabegarciamtz@gmail.com  05/12/2025
 */
public class ArbolPanel extends JPanel {
    private ArbolBusquedaBinario abb;
    private final int RADIO_NODO = 25;
    private final int ALTURA_NIVEL = 80;

    /**
     * Constructor del panel de visualización.
     * @param abb Referencia al árbol binario de búsqueda.
     */
    public ArbolPanel(ArbolBusquedaBinario abb) {
        this.abb = abb;
        setBackground(Color.WHITE);
    }

    
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Comprobación correcta: si la raíz no es nula, dibujar el árbol
    if (abb != null && abb.getRaiz() != null) {
        int width = getWidth();
        dibujarNodo(g2d, abb.getRaiz(), width / 2, 50, width / 4);
    } else {
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.setColor(Color.GRAY);
        g2d.drawString("Árbol vacío - Inserte nodos para comenzar", 50, 100);
    }
}

    /**
     * Dibuja un nodo y sus hijos recursivamente.
     * @param g2d Graphics2D para dibujar.
     * @param nodo Nodo actual a dibujar.
     * @param x Posición X del nodo.
     * @param y Posición Y del nodo.
     * @param xOffset Desplazamiento horizontal para hijos.
     */
    private void dibujarNodo(Graphics2D g2d, Nodo nodo, int x, int y, int xOffset) {
        // Dibujar líneas a los hijos primero
        if (nodo.getIzquierdo() != null) {
            int childX = x - xOffset;
            int childY = y + ALTURA_NIVEL;
            g2d.setColor(new Color(100, 100, 100));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x, y, childX, childY);
            dibujarNodo(g2d, nodo.getIzquierdo(), childX, childY, xOffset / 2);
        }

        if (nodo.getDerecho() != null) {
            int childX = x + xOffset;
            int childY = y + ALTURA_NIVEL;
            g2d.setColor(new Color(100, 100, 100));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x, y, childX, childY);
            dibujarNodo(g2d, nodo.getDerecho(), childX, childY, xOffset / 2);
        }

        // Dibujar el nodo actual
        g2d.setColor(new Color(100, 181, 246)); // color por defecto
        g2d.fillOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);

        // Dibujar el valor del nodo
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        String valor = String.valueOf(nodo.getDato());
        int textX = x - fm.stringWidth(valor) / 2;
        int textY = y + fm.getAscent() / 2 - 2;

        g2d.setColor(Color.WHITE);
        g2d.drawString(valor, textX, textY);
    }
}
