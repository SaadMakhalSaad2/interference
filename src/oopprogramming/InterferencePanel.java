package oopprogramming;

import oopprogramming.models.Moire;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class InterferencePanel extends JPanel implements ActionListener {
    double slant = 0.0;
    Moire mMoire;
    ArrayList<Moire> moireList = new ArrayList<>();


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

        for (Moire moire : moireList) {
            if (moire.getType().equals("Lines"))
                drawLine(moire);
            else
                drawCircles(moire);
        }

    }

    private void drawCircles(Moire moire) {
        int rad = 10;
        int dia = 20;

        drawConcentricCircles(moire, rad, dia, graphics2D);
    }

    public void drawConcentricCircles(Moire moire, int radius, int diameter, Graphics2D g2D) {
        g2D.setColor(Color.black);
        double dx = moire.getAngle() >= 0 ? dx0 : dx1;
        for (int i = 0; i < moire.getLines(); i++) {
            g2D.drawOval((int) (dx * 300) - (i * radius), 250 - (i * radius),
                    (i + 3) * diameter, (i + 3) * diameter);
        }
    }

    private void drawingSettings(Graphics g) {
        graphics2D = (Graphics2D) g;

        Stroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.black);

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
            double y0 = y2;

            double x1 = 0.8;
            double y1 = y2;

            Line2D line = new Line2D.Double(x0, y0, x1, y1);
            Shape shape = transform.createTransformedShape(line);
            graphics2D.draw(shape);
            y2 += dy2;
        }
    }

    private void drawLine(Moire moire) {
        double y = -0.8;
        double dy = 0.1;

        for (int i = 0; i < moire.getLines(); i++) {
            double x0 = -0.8 - slant;
            double y0 = y - moire.getAngle();

            double x1 = 0.8 + slant;
            double y1 = y + moire.getAngle();

            Line2D line = new Line2D.Double(x0, y0, x1, y1);
            Shape shape = transform.createTransformedShape(line);
            graphics2D.draw(shape);
            y += dy;
        }


    }

    private double dx0 = 2.0;
    private double dx1 = 2.0;
    public double speed = 0.2;

    @Override
    public void actionPerformed(ActionEvent e) {
        slant += 0.1 * speed;
        slant = slant >= 2.0 ? -slant : slant;

        this.dx0 += 0.1 * speed;
        if (this.dx0 > 2.5) {
            this.dx0 = 2.0;
        } // if
//
        this.dx1 -= 0.1 * speed;
        if (this.dx1 < 1.5) {
            this.dx1 = 2.0;
        }

        this.repaint();

    }

    public void setMMoire(Moire mMoire) {

        this.mMoire = mMoire;
    }

}
