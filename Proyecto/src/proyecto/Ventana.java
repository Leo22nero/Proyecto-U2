package proyecto;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import componentes.BotonAnimado;

public class Ventana extends JFrame {

    private JPanel panelNOR, panelEST, panelOES;
    private JTextArea listaCarrito;
    private JLabel lblTotal;

    private int total = 0;

    // Precios
    private int precio1 = 599;
    private int precio2 = 550;
    private int precio3 = 780;
    private int precio4 = 399;

    // FUENTES
    private Font fuenteTitulo;
    private Font fuentePrecio;
    private Font fuenteGeneral;

    public Ventana(){
        super("HOODIE STORE");
        setLayout(new BorderLayout(5,5));

        cargarFuentes();

        initCarrito();
        initNOR();
        initEST();
        initOES();
    }

    // ===================== FUENTES =====================
    private void cargarFuentes() {
        try {
            fuenteTitulo = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fuentes/Gameplay.ttf")
            ).deriveFont(Font.BOLD, 14f);

            fuentePrecio = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fuentes/beatmap.TTF")
            ).deriveFont(Font.PLAIN, 10f);

            fuenteGeneral = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fuentes/Moderniz.otf")
            ).deriveFont(Font.PLAIN, 16f);

        } catch (Exception e) {
            e.printStackTrace();

            fuenteTitulo = new Font("Arial", Font.BOLD, 14);
            fuentePrecio = new Font("Arial", Font.PLAIN, 10);
            fuenteGeneral = new Font("Arial", Font.PLAIN, 16);
        }
    }

    // ===================== CARRITO =====================
    private void initCarrito() {

        listaCarrito = new JTextArea();
        listaCarrito.setPreferredSize(new Dimension(350,400));
        listaCarrito.setEditable(false);
        listaCarrito.setFont(fuentePrecio);

        listaCarrito.setForeground(new Color(247,92,180));
        listaCarrito.setBackground(new Color(30,30,30));

        JPanel panelCarrito = new JPanel(new BorderLayout());
        panelCarrito.setBorder(BorderFactory.createTitledBorder(
                null,"CARRITO",TitledBorder.CENTER,TitledBorder.TOP));

        lblTotal = new JLabel("TOTAL: $0", JLabel.CENTER);
        lblTotal.setFont(fuenteTitulo);
        lblTotal.setForeground(new Color(240,184,216));

        panelCarrito.add(listaCarrito, BorderLayout.NORTH);
        panelCarrito.add(lblTotal, BorderLayout.CENTER);

        add(panelCarrito, BorderLayout.EAST);
    }

    // ===================== PANEL NORTE =====================
    private void initNOR() {
        panelNOR = new JPanel(new BorderLayout());

        ImageIcon icono = new ImageIcon(getClass().getResource("imagenes/titulo.png"));
        Image img = icono.getImage().getScaledInstance(320, 100, Image.SCALE_SMOOTH);

        JLabel titulo = new JLabel(new ImageIcon(img), SwingConstants.CENTER);
        titulo.setFont(fuenteTitulo);

        panelNOR.add(titulo, BorderLayout.CENTER);

        add(panelNOR, BorderLayout.NORTH);
    }

    // ===================== PANEL ESTE =====================
    private void initEST() {

        panelEST = new JPanel(new GridBagLayout());

        JButton btnFinalizar = new JButton("FINALIZAR");
        JButton btnLimpiar = new JButton("LIMPIAR");

        btnFinalizar.setFont(fuentePrecio);
        btnLimpiar.setFont(fuentePrecio);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEST.add(btnFinalizar, gbc);

        gbc.gridy = 1;
        panelEST.add(btnLimpiar, gbc);

        btnFinalizar.setBackground(new Color(198,251,172));
        btnLimpiar.setBackground(new Color(231,172,114));

        btnFinalizar.addActionListener(e -> {
            new proyecto.VentanaPago(total);
        });

        btnLimpiar.addActionListener(e -> {
            listaCarrito.setText("");
            total = 0;
            lblTotal.setText("TOTAL: $" + total);
        });

        add(panelEST, BorderLayout.CENTER);
    }

    // ===================== PANEL OESTE =====================
    private void initOES() {
        panelOES = new JPanel(new GridLayout(2,2,10,10));
        panelOES.setPreferredSize(new Dimension(600,700));

        panelOES.add(crearSudadera("Sudadera Negra", "imagenes/sudadera1.jpg", precio1));
        panelOES.add(crearSudadera("Sudadera Blanca", "imagenes/sudadera2.jpg", precio2));
        panelOES.add(crearSudadera("Sudadera Anime", "imagenes/sudadera3.jpg", precio3));
        panelOES.add(crearSudadera("Sudadera Beige", "imagenes/sudadera4.jpg", precio4));

        add(panelOES, BorderLayout.WEST);
    }


    private JPanel crearSudadera(String nombre, String rutaImagen, int precio) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel imagen = new JLabel(new ImageIcon(getClass().getResource(rutaImagen)));
        panel.add(imagen, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel(nombre, JLabel.CENTER);
        lblNombre.setFont(fuenteGeneral);
        panel.add(lblNombre, BorderLayout.NORTH);

        JPanel descripcion = new JPanel(new BorderLayout());

        JLabel lblPrecio = new JLabel("$" + precio, JLabel.CENTER);
        lblPrecio.setFont(fuenteTitulo);
        descripcion.add(lblPrecio, BorderLayout.NORTH);

        String tallas[] = {"S","M","L"};
        JComboBox<String> combo = new JComboBox<>(tallas);
        combo.setFont(fuentePrecio);

        JPanel contenedor = new JPanel();
        contenedor.add(combo);

        BotonAnimado btnAgregar = new BotonAnimado();
        contenedor.add(btnAgregar);

        descripcion.add(contenedor, BorderLayout.CENTER);
        panel.add(descripcion, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            String talla = (String) combo.getSelectedItem();
            listaCarrito.append(nombre + " - Talla: " + talla + "\n");
            total += precio;
            lblTotal.setText("TOTAL: $" + total);
        });

        return panel;
    }
}