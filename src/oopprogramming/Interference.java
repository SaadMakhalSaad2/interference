package oopprogramming;

import oopprogramming.models.Moire;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interference extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
    InputsPanel inputs = new InputsPanel(this);
    InterferencePanel drawingPanel = new InterferencePanel();

    public Interference() {
        setupFrame();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        inputs.setAlignmentY(Component.TOP_ALIGNMENT);
        inputs.setBorder(new EmptyBorder(10, 10, 10, 10));
        drawingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Timer timer = new Timer(50, drawingPanel);
        timer.start();

        mainPanel.add(inputs);
        mainPanel.add(drawingPanel);
        this.add(mainPanel);
    }

    private void setupFrame() {
        this.setBackground(Color.white);
        this.setSize(512, 512);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("Moire Drawer");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Interference();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().equals(JButton.class))
            if (e.getActionCommand().equals("remove") && selectedIndex > -1)
                removeMoire();
            else {
                drawButtonListener(e);
            }
        else if (e.getSource().getClass().equals(JComboBox.class))
            typeDropdownListener(e);
    }

    private void removeMoire() {
        int indexRemoved = selectedIndex;
        inputs.moiresDrawn.remove(selectedIndex);
        inputs.moires.remove(indexRemoved);
        drawingPanel.moireList.remove(indexRemoved);
        selectedIndex = -1;
        this.repaint();
        inputs.moiresJList.clearSelection();
        updating = false;
        inputs.remove.setVisible(false);
        inputs.draw.setText("draw");
        System.out.println(drawingPanel.moireList.size());
    }

    private void drawButtonListener(ActionEvent e) {
        Moire moire = new Moire(inputs.name.getText(), inputs.moireTypeDropdown.getSelectedItem() + "");

        moire.setLines(Integer.parseInt(inputs.linesCount.getText()));
        moire.setAngle(Double.parseDouble(inputs.angle.getText()));

        if (updating) {
            moire.setColor(inputs.moires.get(selectedIndex).getColor());
            inputs.moires.set(selectedIndex, moire);
            drawingPanel.moireList.set(selectedIndex, moire);
            inputs.moiresDrawn.set(selectedIndex, moire.getName() + "   " + moire.getType().toLowerCase().charAt(0));
        } else {
            moire.setColor(new Color((int) (Math.random() * 0x1000000)));
            inputs.moires.add(moire);
            drawingPanel.moireList.add(moire);
            inputs.moiresDrawn.addElement(moire.getName() + "   " + moire.getType().toLowerCase().charAt(0));
        }

        this.repaint();
        updating = false;
        inputs.remove.setVisible(false);
        inputs.moiresJList.clearSelection();
        inputs.draw.setText("draw");
        selectedIndex = -1;
    }

    private void typeDropdownListener(ActionEvent e) {
        String shape = ((JComboBox<?>) e.getSource()).getSelectedItem() + "";
        inputs.name.setText(shape + " x");

        if (shape.equals("Circles")) {
            inputs.showLineInputs(false);
        } else if (shape.equals("Lines")) {
            inputs.showLineInputs(true);
        }
    }

    int selectedIndex;
    boolean updating = false;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        selectedIndex = ((JList<?>) e.getSource()).getSelectedIndex();
        inputs.draw.setText("update");
        if (e.getSource().getClass().equals(JList.class) && selectedIndex != -1) {

            System.out.println("selected index: " + selectedIndex);
            inputs.updateInputs(selectedIndex);
            this.repaint();
            updating = true;
            inputs.remove.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        drawingPanel.speed = inputs.speed.getValue() * 1.0 / 100;
        drawingPanel.spacing = (int) (inputs.spacing.getValue() * 5.0 / 100);
        System.out.println("speed " + drawingPanel.speed);
        System.out.println("spacing " + drawingPanel.spacing);
    }
}