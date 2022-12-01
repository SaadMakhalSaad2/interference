package oopprogramming;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interference extends JFrame implements ActionListener {
    InputsPanel inputs = new InputsPanel(this);
    InterferencePanel drawingPanel = new InterferencePanel();

    public Interference() {
        setupFrame();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        inputs.setAlignmentY(Component.TOP_ALIGNMENT);
        inputs.setBorder(new EmptyBorder(10, 10, 10, 10));
        drawingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(inputs);
        mainPanel.add(drawingPanel);
        this.add(mainPanel);

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
        if (e.getSource().getClass().equals(JButton.class)) {
            drawingPanel.setSlant(Double.parseDouble(inputs.angle.getText()));
            drawingPanel.setLinesCount(Integer.parseInt(inputs.linesCount.getText()));
            inputs.moiresDrawn.addElement(inputs.name.getText());
            this.repaint();
        } else if (e.getSource().getClass().equals(JComboBox.class)) {
            String shape = ((JComboBox<?>) e.getSource()).getSelectedItem() + "";
            drawingPanel.setShape(shape);
        }


    }
}