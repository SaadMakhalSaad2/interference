package oopprogramming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputsPanel extends JPanel implements ActionListener {
    public InputsPanel() {
        textField.setMaximumSize(textField.getPreferredSize() );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Angle"));
        this.add(textField);
        this.add(draw);
        textField.setText("2.0");

    }

    JButton draw = new JButton("draw");
    JTextField textField = new JTextField(16);


    @Override
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        if (s.equals("draw")) {
            System.out.println(textField.getText());


        }
    }
}
