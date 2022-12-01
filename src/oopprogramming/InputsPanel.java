package oopprogramming;

import oopprogramming.models.Moire;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputsPanel extends JPanel {
    public InputsPanel(ActionListener listener, ListSelectionListener listener2) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addInputs(listener, listener2);
    }

    private void addInputs(ActionListener listener, ListSelectionListener listener2) {
        
        angle.setMaximumSize(angle.getPreferredSize());
        linesCount.setMaximumSize(angle.getPreferredSize());
        name.setMaximumSize(angle.getPreferredSize());
        moiresJList = new JList(moiresDrawn); //data has type Object[]
        moireListScroller = new JScrollPane(moiresJList);
        moireListScroller.setMaximumSize(new Dimension((int) angle.getPreferredSize().getWidth(), 200));
        moireTypeDropdown.setMaximumSize(angle.getPreferredSize());
        angle.setText("2.0");
        linesCount.setText("1");
        name.setText("Line 1");

        optionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        angleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        linesCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        draw.setAlignmentX(Component.CENTER_ALIGNMENT);

        draw.addActionListener(listener);
        moireTypeDropdown.addActionListener(listener);
        moiresJList.addListSelectionListener(listener2);

        moiresJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moiresJList.setLayoutOrientation(JList.VERTICAL);
        moiresJList.setVisibleRowCount(-1);


        this.add(optionsLabel);
        this.add(moireTypeDropdown);
        this.add(nameLabel);
        this.add(name);
        this.add(linesCountLabel);
        this.add(linesCount);
        this.add(angleLabel);
        this.add(angle);
        this.add(Box.createRigidArea(new Dimension(0, 8)));
        this.add(draw);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(moireListScroller);
    }

    String[] choices = {"Lines", "Circles", "CHOICE 3", "CHOICE 4", "CHOICE 5", "CHOICE 6"};
    DefaultListModel<String> moiresDrawn = new DefaultListModel<>();
    List<Moire> moires = new ArrayList<>();
    int selectedMoire = 0;

    final JComboBox<String> moireTypeDropdown = new JComboBox<>(choices);
    JButton draw = new JButton("draw");
    JLabel optionsLabel = new JLabel("Moire type");
    JLabel nameLabel = new JLabel("Name");
    JLabel angleLabel = new JLabel("Angle");
    JLabel linesCountLabel = new JLabel("# of lines");
    JTextField angle = new JTextField(16);
    JTextField linesCount = new JTextField(16);
    JTextField name = new JTextField(16);


    JList moiresJList;
    JScrollPane moireListScroller;

    public void updateInputs(int selectedMoireIndex) {
        Moire selectedMoire = moires.get(selectedMoireIndex);
        this.selectedMoire = selectedMoireIndex;
        this.name.setText(selectedMoire.getName());
        this.moireTypeDropdown.setSelectedIndex(new ArrayList<>(Arrays.asList(choices)).indexOf(selectedMoire.getType()));
        this.name.setText(String.valueOf(selectedMoire.getName()));

        if (selectedMoire.getType().equals("Circles")) {
            showLineInputs(false);
        } else {
            this.angle.setText(String.valueOf(selectedMoire.getAngle()));
            this.linesCount.setText(String.valueOf(selectedMoire.getLines()));
        }
    }

    public void showLineInputs(boolean b) {
        this.angle.setVisible(b);
        this.linesCount.setVisible(b);
        this.angleLabel.setVisible(b);
        this.linesCountLabel.setVisible(b);
    }


    public void clearInputs() {
        this.moireTypeDropdown.setSelectedIndex(new ArrayList<>(Arrays.asList(choices)).indexOf("Lines"));
        this.name.setText("Line x");
        this.draw.setText("draw");
        this.angle.setText("1.0");
    }
}
