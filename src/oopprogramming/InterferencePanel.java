package oopprogramming;

import oopprogramming.models.Moire;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class InterferencePanel extends JPanel implements ActionListener {
    double slant2 = 0.0;
    Moire mMoire;

    public InterferencePanel() {
        mMoire = new Moire("Line x", "Lines");
        this.setBackground(Color.white);
    }

    Graphics2D graphics2D;
    double panelWidth, panelHeight;
    AffineTransform scale, transform, translate;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawingSettings(g);

        drawBackground();
        switch (this.mMoire.getType()) {
            case "Lines" -> drawLine();
            case "Circles" -> drawCircles();
        }
    }

    private void drawCircles() {

    }

    private void drawingSettings(Graphics g) {
        graphics2D = (Graphics2D) g;

        panelWidth = this.getWidth();
        panelHeight = this.getHeight();

        scale = new AffineTransform();
        scale.setToScale(panelWidth / 2, panelHeight / 2);

        translate = new AffineTransform();
        translate.setToTranslation(1.0, 1.0);

        transform = new AffineTransform();
        transform.concatenate(scale);
        transform.concatenate(translate);
    }

    private void drawBackground() {
        double y2 = -0.8;
        double dy2 = 0.1;

        Stroke stroke2 = new BasicStroke(1);
        graphics2D.setStroke(stroke2);
        graphics2D.setColor(Color.lightGray);
        while (y2 < 1.0) {
            double x0 = -0.8;
            double y0 = y2 - slant2;

            double x1 = 0.8;
            double y1 = y2 + slant2;

            Line2D line = new Line2D.Double(x0, y0, x1, y1);
            Shape shape = transform.createTransformedShape(line);
            graphics2D.draw(shape);
            y2 += dy2;
        }
    }

    private void drawLine() {
        double y = -0.8;
        double dy = 0.1;

        Stroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.lightGray);

        for (int i = 0; i < mMoire.getLines(); i++) {
            double x0 = -0.8;
            double y0 = y - mMoire.getAngle();

            double x1 = 0.8;
            double y1 = y + mMoire.getAngle();

            Line2D line = new Line2D.Double(x0, y0, x1, y1);
            Shape shape = transform.createTransformedShape(line);
            graphics2D.draw(shape);
            y += dy;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slant2 += 0.05;
        slant2 = slant2 >= 2.0 ? -slant2 : slant2;

        this.repaint();

        System.out.println("action" + e.getActionCommand());
    }
    public void setMMoire(Moire mMoire) {
        this.mMoire = mMoire;
    }

}
