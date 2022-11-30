package oopprogramming;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.dnd.DragGestureEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Interference extends JFrame implements ActionListener {
    InputsPanel inputs = new InputsPanel();
    InterferencePanel drawingPanel = new InterferencePanel();

    public Interference() {
        setupFrame();

        setupInputListeners();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        inputs.setAlignmentY(Component.TOP_ALIGNMENT);
        inputs.setBorder(new EmptyBorder(10, 10, 10, 10));
        drawingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(inputs);
        mainPanel.add(drawingPanel);
        this.add(mainPanel);

    }

    private void setupInputListeners() {
        inputs.draw.addActionListener(e -> {
            drawingPanel.setSlant(Double.parseDouble(inputs.textField.getText()));
            this.repaint();
        });
    }

    private void setupFrame() {
        this.setBackground(Color.white);
        this.setSize(512, 512);
        this.setTitle("Moire Drawer");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Interference();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action happened");
    }
}