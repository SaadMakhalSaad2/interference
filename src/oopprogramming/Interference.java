package oopprogramming;

import oopprogramming.models.Moire;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interference extends JFrame implements ActionListener, ListSelectionListener {
    InputsPanel inputs = new InputsPanel(this, this);
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
            Moire moire = new Moire(inputs.name.getText(), inputs.moireTypeDropdown.getSelectedItem() + "");

            if (moire.getType().equals("Lines")) {
                moire.setLines(Integer.parseInt(inputs.linesCount.getText()));
                moire.setAngle(Double.parseDouble(inputs.angle.getText()));
            }
            if (updating) {
                inputs.moires.set(selectedIndex, moire);
                inputs.moiresDrawn.set(selectedIndex, moire.getName() + "   " + moire.getType().toLowerCase().charAt(0));
            } else {
                inputs.moires.add(moire);
                inputs.moiresDrawn.addElement(moire.getName() + "   " + moire.getType().toLowerCase().charAt(0));
            }

            drawingPanel.setMMoire(moire);
            this.repaint();
            updating = false;
            inputs.clearInputs();
        } else if (e.getSource().getClass().equals(JComboBox.class)) {
            String shape = ((JComboBox<?>) e.getSource()).getSelectedItem() + "";
            inputs.name.setText(shape + " x");

            if (shape.equals("Circles")) {
                inputs.showLineInputs(false);
            } else if (shape.equals("Lines")) {
                inputs.showLineInputs(true);
            }
        }
    }


    int selectedIndex;
    boolean updating = false;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        inputs.draw.setText("update");
        if (e.getSource().getClass().equals(JList.class)) {
            System.out.println("selected index: " + selectedIndex);
            selectedIndex = ((JList<?>) e.getSource()).getSelectedIndex();
            inputs.updateInputs(selectedIndex);
            drawingPanel.setMMoire(inputs.moires.get(selectedIndex));
            this.repaint();
            updating = true;
        }
    }
}