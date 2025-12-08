package practicos.Conjunto;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Sistema de Gestión de Biblioteca utilizando Conjuntos (Set) de Java
 * Permite gestionar libros en diferentes categorías y realizar operaciones con conjuntos
 * 
 * @author Diana Mabel Garcia Martinez
 *         diabegarciamtz@gmail.com    04/12/2025
 */
public class SistemaBiblioteca extends JFrame {
    
    // Conjuntos para almacenar libros por categoría
    private Set<String> librosFiccion;
    private Set<String> librosCiencia;
    private Set<String> librosHistoria;
    
    // Componentes de la interfaz gráfica
    private JTextArea areaResultados;
    private JComboBox<String> comboCategoria;
    private JTextField campoLibro;
    private JButton btnAgregar, btnEliminar, btnUnion, btnInterseccion;
    private JButton btnDiferencia, btnDiferenciaSimetrica, btnSubconjunto, btnLimpiar;
    
    /**
     * Constructor principal que inicializa la aplicación
     */
    public SistemaBiblioteca() {
        // Inicializar los conjuntos de libros
        inicializarConjuntos();
        
        // Configurar la ventana principal
        configurarVentana();
        
        // Crear y organizar los componentes de la interfaz
        crearInterfaz();
        
        // Hacer visible la ventana
        setVisible(true);
    }
    
    /**
     * Inicializa los conjuntos de libros con datos de ejemplo
     * Cada conjunto representa una categoría de libros en la biblioteca
     */
    private void inicializarConjuntos() {
        // HashSet para libros de ficción
        librosFiccion = new HashSet<>();
        librosFiccion.add("El Quijote");
        librosFiccion.add("Cien Años de Soledad");
        librosFiccion.add("1984");
        librosFiccion.add("El Principito");
        librosFiccion.add("Crimen y Castigo");
        
        // TreeSet para libros de ciencia (ordenados alfabéticamente)
        librosCiencia = new TreeSet<>();
        librosCiencia.add("Breve Historia del Tiempo");
        librosCiencia.add("El Origen de las Especies");
        librosCiencia.add("Cosmos");
        librosCiencia.add("1984"); // Libro compartido con ficción
        librosCiencia.add("Sapiens");
        
        // LinkedHashSet para libros de historia (mantiene orden de inserción)
        librosHistoria = new LinkedHashSet<>();
        librosHistoria.add("Sapiens");
        librosHistoria.add("Guns, Germs and Steel");
        librosHistoria.add("Historia de México");
        librosHistoria.add("El Origen de las Especies"); // Compartido con ciencia
        librosHistoria.add("La Segunda Guerra Mundial");
    }
    
    /**
     * Configura las propiedades de la ventana principal
     */
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Biblioteca - Operaciones con Conjuntos");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }
    
    /**
     * Crea y organiza todos los componentes de la interfaz gráfica
     */
    private void crearInterfaz() {
        // Panel superior con título
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con entrada de datos
        JPanel panelEntrada = crearPanelEntrada();
        add(panelEntrada, BorderLayout.CENTER);
        
        // Panel izquierdo con operaciones básicas
        JPanel panelOperaciones = crearPanelOperaciones();
        add(panelOperaciones, BorderLayout.WEST);
        
        // Panel derecho con operaciones de conjuntos
        JPanel panelConjuntos = crearPanelOperacionesConjuntos();
        add(panelConjuntos, BorderLayout.EAST);
        
        // Panel inferior con resultados
        JPanel panelResultados = crearPanelResultados();
        add(panelResultados, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel del título de la aplicación
     * @return JPanel con el título formateado
     */
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(63, 81, 181));
        
        JLabel lblTitulo = new JLabel("📚 SISTEMA DE GESTIÓN DE BIBLIOTECA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        panel.add(lblTitulo);
        
        return panel;
    }
    
    /**
     * Crea el panel de entrada de datos con campos y botones básicos
     * @return JPanel con controles de entrada
     */
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            "Gestión de Libros",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Etiqueta y campo para nombre del libro
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblLibro = new JLabel("Nombre del Libro:");
        lblLibro.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblLibro, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        campoLibro = new JTextField(25);
        campoLibro.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(campoLibro, gbc);
        
        // Etiqueta y combo para categoría
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblCategoria, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        String[] categorias = {"Ficción", "Ciencia", "Historia"};
        comboCategoria = new JComboBox<>(categorias);
        comboCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(comboCategoria, gbc);
        
        // Botones de operaciones básicas
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        btnAgregar = crearBoton("Agregar Libro", new Color(76, 175, 80));
        btnAgregar.addActionListener(e -> agregarLibro());
        panel.add(btnAgregar, gbc);
        
        gbc.gridx = 1;
        btnEliminar = crearBoton("Eliminar Libro", new Color(244, 67, 54));
        btnEliminar.addActionListener(e -> eliminarLibro());
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 2;
        JButton btnMostrar = crearBoton("Mostrar Todos", new Color(33, 150, 243));
        btnMostrar.addActionListener(e -> mostrarTodosLosLibros());
        panel.add(btnMostrar, gbc);
        
        return panel;
    }
    
    /**
     * Crea el panel con operaciones básicas de conjuntos
     * @return JPanel con botones de operaciones
     */
    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones Básicas"));
        panel.setPreferredSize(new Dimension(200, 0));
        
        // OPERACIÓN 1: Unión de conjuntos
        btnUnion = crearBoton("Unión (Ficción ∪ Ciencia)", new Color(156, 39, 176));
        btnUnion.addActionListener(e -> realizarUnion());
        panel.add(btnUnion);
        
        // OPERACIÓN 2: Intersección de conjuntos
        btnInterseccion = crearBoton("Intersección (∩)", new Color(255, 152, 0));
        btnInterseccion.addActionListener(e -> realizarInterseccion());
        panel.add(btnInterseccion);
        
        // OPERACIÓN 3: Diferencia de conjuntos
        btnDiferencia = crearBoton("Diferencia (A - B)", new Color(0, 150, 136));
        btnDiferencia.addActionListener(e -> realizarDiferencia());
        panel.add(btnDiferencia);
        
        // OPERACIÓN 4: Verificar si es subconjunto
        btnSubconjunto = crearBoton("¿Es Subconjunto?", new Color(121, 85, 72));
        btnSubconjunto.addActionListener(e -> verificarSubconjunto());
        panel.add(btnSubconjunto);
        
        return panel;
    }
    
    /**
     * Crea el panel con operaciones avanzadas de conjuntos
     * @return JPanel con botones de operaciones avanzadas
     */
    private JPanel crearPanelOperacionesConjuntos() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones Avanzadas"));
        panel.setPreferredSize(new Dimension(200, 0));
        
        // OPERACIÓN 5: Diferencia simétrica
        btnDiferenciaSimetrica = crearBoton("Dif. Simétrica (A Δ B)", new Color(233, 30, 99));
        btnDiferenciaSimetrica.addActionListener(e -> realizarDiferenciaSimetrica());
        panel.add(btnDiferenciaSimetrica);
        
        // OPERACIÓN 6: Verificar si conjuntos son disjuntos
        JButton btnDisjuntos = crearBoton("¿Son Disjuntos?", new Color(103, 58, 183));
        btnDisjuntos.addActionListener(e -> verificarDisjuntos());
        panel.add(btnDisjuntos);
        
        // Botón para limpiar resultados
        btnLimpiar = crearBoton("Limpiar Resultados", new Color(96, 125, 139));
        btnLimpiar.addActionListener(e -> areaResultados.setText(""));
        panel.add(btnLimpiar);
        
        // Botón para reiniciar todo
        JButton btnReiniciar = crearBoton("Reiniciar Biblioteca", new Color(255, 87, 34));
        btnReiniciar.addActionListener(e -> reiniciarBiblioteca());
        panel.add(btnReiniciar);
        
        return panel;
    }
    
    /**
     * Crea el panel de resultados donde se muestran las operaciones
     * @return JPanel con área de texto para resultados
     */
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Resultados de Operaciones"));
        
        areaResultados = new JTextArea(10, 60);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultados.setBackground(new Color(245, 245, 245));
        
        JScrollPane scrollPane = new JScrollPane(areaResultados);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea un botón personalizado con color de fondo
     * @param texto Texto del botón
     * @param color Color de fondo del botón
     * @return JButton configurado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
    
    /**
     * Agrega un libro al conjunto seleccionado
     * Valida que el campo no esté vacío antes de agregar
     */
    private void agregarLibro() {
        String libro = campoLibro.getText().trim();
        String categoria = (String) comboCategoria.getSelectedItem();
        
        if (libro.isEmpty()) {
            mostrarMensaje("Por favor ingrese el nombre del libro", "Advertencia");
            return;
        }
        
        Set<String> conjunto = obtenerConjunto(categoria);
        boolean agregado = conjunto.add(libro);
        
        if (agregado) {
            areaResultados.append("✓ Libro agregado: '" + libro + "' en categoría " + categoria + "\n");
            areaResultados.append("  Total de libros en " + categoria + ": " + conjunto.size() + "\n\n");
        } else {
            areaResultados.append("⚠ El libro '" + libro + "' ya existe en " + categoria + "\n\n");
        }
        
        campoLibro.setText("");
        campoLibro.requestFocus();
    }
    
    /**
     * Elimina un libro del conjunto seleccionado
     */
    private void eliminarLibro() {
        String libro = campoLibro.getText().trim();
        String categoria = (String) comboCategoria.getSelectedItem();
        
        if (libro.isEmpty()) {
            mostrarMensaje("Por favor ingrese el nombre del libro a eliminar", "Advertencia");
            return;
        }
        
        Set<String> conjunto = obtenerConjunto(categoria);
        boolean eliminado = conjunto.remove(libro);
        
        if (eliminado) {
            areaResultados.append("✓ Libro eliminado: '" + libro + "' de categoría " + categoria + "\n");
            areaResultados.append("  Total de libros en " + categoria + ": " + conjunto.size() + "\n\n");
        } else {
            areaResultados.append("⚠ El libro '" + libro + "' no existe en " + categoria + "\n\n");
        }
        
        campoLibro.setText("");
    }
    
    /**
     * OPERACIÓN 1: Unión de conjuntos
     * Combina todos los elementos de Ficción y Ciencia sin duplicados
     */
    private void realizarUnion() {
        Set<String> union = new HashSet<>(librosFiccion);
        union.addAll(librosCiencia);
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 1: UNIÓN (Ficción ∪ Ciencia)\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("Libros de Ficción (" + librosFiccion.size() + "): " + librosFiccion + "\n");
        areaResultados.append("Libros de Ciencia (" + librosCiencia.size() + "): " + librosCiencia + "\n\n");
        areaResultados.append("Resultado de la Unión (" + union.size() + " libros):\n");
        areaResultados.append(union + "\n\n");
    }
    
    /**
     * OPERACIÓN 2: Intersección de conjuntos
     * Encuentra libros que están en ambas categorías (Ciencia e Historia)
     */
    private void realizarInterseccion() {
        Set<String> interseccion = new HashSet<>(librosCiencia);
        interseccion.retainAll(librosHistoria);
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 2: INTERSECCIÓN (Ciencia ∩ Historia)\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("Libros de Ciencia: " + librosCiencia + "\n");
        areaResultados.append("Libros de Historia: " + librosHistoria + "\n\n");
        areaResultados.append("Libros en común (" + interseccion.size() + "):\n");
        if (interseccion.isEmpty()) {
            areaResultados.append("No hay libros en común entre estas categorías\n\n");
        } else {
            areaResultados.append(interseccion + "\n\n");
        }
    }
    
    /**
     * OPERACIÓN 3: Diferencia de conjuntos
     * Encuentra libros que están en Ficción pero no en Ciencia
     */
    private void realizarDiferencia() {
        Set<String> diferencia = new HashSet<>(librosFiccion);
        diferencia.removeAll(librosCiencia);
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 3: DIFERENCIA (Ficción - Ciencia)\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("Libros de Ficción: " + librosFiccion + "\n");
        areaResultados.append("Libros de Ciencia: " + librosCiencia + "\n\n");
        areaResultados.append("Libros solo en Ficción (" + diferencia.size() + "):\n");
        areaResultados.append(diferencia + "\n\n");
    }
    
    /**
     * OPERACIÓN 4: Verificar si un conjunto es subconjunto de otro
     * Verifica si Historia es subconjunto de la unión de Ficción y Ciencia
     */
    private void verificarSubconjunto() {
        Set<String> unionFC = new HashSet<>(librosFiccion);
        unionFC.addAll(librosCiencia);
        
        boolean esSubconjunto = unionFC.containsAll(librosHistoria);
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 4: VERIFICAR SUBCONJUNTO\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("¿Historia ⊆ (Ficción ∪ Ciencia)?\n\n");
        areaResultados.append("Historia: " + librosHistoria + "\n");
        areaResultados.append("Ficción ∪ Ciencia: " + unionFC + "\n\n");
        areaResultados.append("Resultado: " + (esSubconjunto ? "SÍ es subconjunto ✓" : "NO es subconjunto ✗") + "\n\n");
    }
    
    /**
     * OPERACIÓN 5: Diferencia simétrica
     * Encuentra libros que están en Ficción o Historia, pero no en ambos
     */
    private void realizarDiferenciaSimetrica() {
        Set<String> diferenciaSim = new HashSet<>(librosFiccion);
        Set<String> temp = new HashSet<>(librosHistoria);
        
        // (A - B) ∪ (B - A)
        Set<String> aMinusB = new HashSet<>(diferenciaSim);
        aMinusB.removeAll(temp);
        
        Set<String> bMinusA = new HashSet<>(temp);
        bMinusA.removeAll(librosFiccion);
        
        diferenciaSim.addAll(temp);
        Set<String> interseccion = new HashSet<>(librosFiccion);
        interseccion.retainAll(librosHistoria);
        diferenciaSim.removeAll(interseccion);
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 5: DIFERENCIA SIMÉTRICA (Ficción Δ Historia)\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("Ficción: " + librosFiccion + "\n");
        areaResultados.append("Historia: " + librosHistoria + "\n\n");
        areaResultados.append("Elementos solo en uno de los conjuntos (" + diferenciaSim.size() + "):\n");
        areaResultados.append(diferenciaSim + "\n\n");
    }
    
    /**
     * OPERACIÓN 6: Verificar si dos conjuntos son disjuntos
     * Verifica si Ficción e Historia no tienen elementos en común
     */
    private void verificarDisjuntos() {
        Set<String> interseccion = new HashSet<>(librosFiccion);
        interseccion.retainAll(librosHistoria);
        
        boolean sonDisjuntos = interseccion.isEmpty();
        
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("OPERACIÓN 6: VERIFICAR CONJUNTOS DISJUNTOS\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("Ficción: " + librosFiccion + "\n");
        areaResultados.append("Historia: " + librosHistoria + "\n\n");
        areaResultados.append("¿Son disjuntos? (¿Intersección vacía?)\n");
        areaResultados.append("Resultado: " + (sonDisjuntos ? "SÍ son disjuntos ✓" : "NO son disjuntos ✗") + "\n");
        if (!sonDisjuntos) {
            areaResultados.append("Elementos en común: " + interseccion + "\n");
        }
        areaResultados.append("\n");
    }
    
    /**
     * Muestra todos los libros de todas las categorías
     */
    private void mostrarTodosLosLibros() {
        areaResultados.append("═══════════════════════════════════════════════════════\n");
        areaResultados.append("LISTADO COMPLETO DE LIBROS EN LA BIBLIOTECA\n");
        areaResultados.append("═══════════════════════════════════════════════════════\n\n");
        
        areaResultados.append("📚 FICCIÓN (" + librosFiccion.size() + " libros):\n");
        for (String libro : librosFiccion) {
            areaResultados.append("   • " + libro + "\n");
        }
        
        areaResultados.append("\n🔬 CIENCIA (" + librosCiencia.size() + " libros):\n");
        for (String libro : librosCiencia) {
            areaResultados.append("   • " + libro + "\n");
        }
        
        areaResultados.append("\n📜 HISTORIA (" + librosHistoria.size() + " libros):\n");
        for (String libro : librosHistoria) {
            areaResultados.append("   • " + libro + "\n");
        }
        
        int total = librosFiccion.size() + librosCiencia.size() + librosHistoria.size();
        areaResultados.append("\n📊 TOTAL EN BIBLIOTECA: " + total + " libros\n\n");
    }
    
    /**
     * Reinicia la biblioteca a su estado inicial
     */
    private void reiniciarBiblioteca() {
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de reiniciar la biblioteca?\nSe perderán todos los cambios.",
            "Confirmar Reinicio",
            JOptionPane.YES_NO_OPTION
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            inicializarConjuntos();
            areaResultados.setText("");
            areaResultados.append("✓ Biblioteca reiniciada con datos por defecto\n\n");
            mostrarTodosLosLibros();
        }
    }
    
    /**
     * Obtiene el conjunto correspondiente a la categoría seleccionada
     * @param categoria Nombre de la categoría
     * @return Set de libros de la categoría
     */
    private Set<String> obtenerConjunto(String categoria) {
        switch (categoria) {
            case "Ficción":
                return librosFiccion;
            case "Ciencia":
                return librosCiencia;
            case "Historia":
                return librosHistoria;
            default:
                return librosFiccion;
        }
    }
    
    /**
     * Muestra un mensaje de diálogo al usuario
     * @param mensaje Texto del mensaje
     * @param titulo Título del diálogo
     */
    private void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Usar el Look and Feel del sistema para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new SistemaBiblioteca());
    }
}