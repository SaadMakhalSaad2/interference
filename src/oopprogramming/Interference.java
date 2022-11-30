package oopprogramming;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Time;

public class Interference extends JFrame {
    public Interference(){
        setupFrame();

        Container container = this.getContentPane();
        InterferencePanel panel = new InterferencePanel();
        InputsPanel inputsPanel = new InputsPanel();
        Timer timer = new Timer(100, panel);
        timer.start();
        this.add(inputsPanel);
        //this.add(panel);
    }

    private void setupFrame() {
        this.setBackground(Color.white);
        this.setSize(512, 512);
        this.setTitle("Man-Month");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Interference interference = new Interference();

    }
}