package oopprogramming;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputsPanel extends JPanel implements ActionListener {
    public InputsPanel(){
        draw.addActionListener(this);
        this.add(new JLabel("Angle"));
        this.add(textField);
        this.add(draw);
    }

    JButton draw = new JButton("draw");
    JTextField textField = new JTextField(16);

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
       // System.out.println(s);

        if (s.equals("draw")) {
            System.out.println(textField.getText());

        }
    }
}
