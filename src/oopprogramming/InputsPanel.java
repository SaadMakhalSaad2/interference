package oopprogramming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InputsPanel extends JPanel {
    public InputsPanel(ActionListener listener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addInputs(listener);
    }

    private void addInputs(ActionListener listener) {
        angle.setMaximumSize(angle.getPreferredSize());
        linesCount.setMaximumSize(angle.getPreferredSize());
        name.setMaximumSize(angle.getPreferredSize());
        moiresJList = new JList(moiresDrawn); //data has type Object[]
        moireListScroller = new JScrollPane(moiresJList);
        moireListScroller.setMaximumSize(new Dimension((int) angle.getPreferredSize().getWidth(), 200));
        options.setMaximumSize(angle.getPreferredSize());
        angle.setText("2.0");
        linesCount.setText("1");
        name.setText("Moire 1");

        JLabel optionsLabel = new JLabel("Moire type");
        JLabel nameLabel = new JLabel("Name");
        JLabel angleLabel = new JLabel("Angle");
        JLabel linesCountLabel = new JLabel("# of lines");
        optionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        angleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        linesCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        draw.setAlignmentX(Component.CENTER_ALIGNMENT);

        draw.addActionListener(listener);
        options.addActionListener(listener);

        moiresJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moiresJList.setLayoutOrientation(JList.VERTICAL);
        moiresJList.setVisibleRowCount(-1);


        this.add(optionsLabel);
        this.add(options);
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
    final JComboBox<String> options = new JComboBox<String>(choices);
    JButton draw = new JButton("draw");
    JTextField angle = new JTextField(16);
    JTextField linesCount = new JTextField(16);
    JTextField name = new JTextField(16);


    JList moiresJList;
    JScrollPane moireListScroller;




}
