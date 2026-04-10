package proyecto;
//@author Leonardo Estrada, Mariana Correa, Ana Laura Gervacio, Julia Ruiz, Lissandro Perez.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaPago extends JFrame {

    private JPanel panelEST;
    private JLabel totalP;
    private int total;

    // CAMPOS
    private JTextField campoTarjeta;
    private JTextField campoNombre;
    private JTextField campoFecha;
    private JTextField campoCVV;

    public VentanaPago(int total) {
        super("Ventana de Pago");

        // Fuentes
        Font fuente = null;

        try {
            fuente = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fuentes/Gameplay.ttf")
            ).deriveFont(Font.BOLD, 18f);
        } catch (Exception e) {
            e.printStackTrace();
            fuente = new Font("Arial", Font.BOLD, 14); // respaldo
        }

        Font fuenteTitulo = fuente.deriveFont(14f);
        Font fuenteTotal = fuente.deriveFont(14f);
        Font fuenteBoton = fuente.deriveFont(14f);

        this.total = total;

        setTitle("VENTANA DE PAGO");
        setSize(380, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        panelEST = new JPanel();
        panelEST.setLayout(new BoxLayout(panelEST, BoxLayout.Y_AXIS));
        panelEST.setBackground(new Color(20, 20, 20));
        panelEST.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // TÍTULO
        JLabel titulo = new JLabel("Realizar Pago");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(240,184,216));

        panelEST.add(titulo);
        panelEST.add(Box.createVerticalStrut(15));

        // TOTAL
        totalP = new JLabel("TOTAL: $" + total);
        totalP.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalP.setFont(fuenteTotal);
        totalP.setForeground(Color.WHITE);

        panelEST.add(totalP);
        panelEST.add(Box.createVerticalStrut(20));

        // Campos para tarjeta
        campoTarjeta = new JTextField();
        campoNombre = new JTextField();
        campoFecha = new JTextField();
        campoCVV = new JTextField();

        campoTarjeta.setMaximumSize(new Dimension(250, 30));
        campoNombre.setMaximumSize(new Dimension(250, 30));
        campoFecha.setMaximumSize(new Dimension(250, 30));
        campoCVV.setMaximumSize(new Dimension(250, 30));

        panelEST.add(crearCampo("Número de tarjeta (10 dígitos):", campoTarjeta, fuente));
        panelEST.add(Box.createVerticalStrut(10));

        panelEST.add(crearCampo("Nombre del titular:", campoNombre, fuente));
        panelEST.add(Box.createVerticalStrut(10));

        panelEST.add(crearCampo("Fecha (MM/AA):", campoFecha, fuente));
        panelEST.add(Box.createVerticalStrut(10));

        panelEST.add(crearCampo("CVV (3 dígitos):", campoCVV, fuente));
        panelEST.add(Box.createVerticalStrut(25));

        // Realizar el pago
        Action pagarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tarjeta = campoTarjeta.getText();
                String nombre = campoNombre.getText();
                String fecha = campoFecha.getText();
                String cvv = campoCVV.getText();

                // Verificaciones de los campos
                if (!tarjeta.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "La tarjeta debe tener 10 dígitos");
                    return;
                }

                if (nombre.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                    return;
                }

                if (!fecha.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido (MM/AA)");
                    return;
                }

                if (!cvv.matches("\\d{3}")) {
                    JOptionPane.showMessageDialog(null, "El CVV debe tener 3 dígitos");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Pago realizado correctamente");
                dispose();
            }
        };

        // Panel botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(20, 20, 20));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        String[] opciones = {"LIMPIAR", "PAGAR"};

        for (String op : opciones) {

            JButton btn = new JButton(op);
            btn.setPreferredSize(new Dimension(110, 35));
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setFont(fuenteBoton);

            if (op.equals("PAGAR")) {
                btn.setBackground(new Color(240,184,216));
                btn.addActionListener(pagarAction);
            } else {
                btn.setBackground(new Color(0,0,0));
                btn.addActionListener(e -> {
                    campoTarjeta.setText("");
                    campoNombre.setText("");
                    campoFecha.setText("");
                    campoCVV.setText("");
                });
            }

            panelBotones.add(btn);
        }

        panelEST.add(panelBotones);

        // ATAJO Ctrl + P
        KeyStroke ctrlP = KeyStroke.getKeyStroke("control P");

        panelEST.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(ctrlP, "pagar");

        panelEST.getActionMap().put("pagar", pagarAction);

        add(panelEST);
        setVisible(true);
    }

    // Metodo para crear los campos de tarjeta
    private JPanel crearCampo(String texto, JTextField campo, Font fuente) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20, 20, 20));

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(fuente);

        panel.add(label, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }
}