package org.FunctionSimplifier.GUI;

import javax.swing.*;
import java.awt.*;

public class syntaxTreePanel extends JPanel {
    ImageIcon image;
    Image pic;
    String fileName;

    public syntaxTreePanel(String fileName) {
        this.fileName = fileName;
        this.image = new ImageIcon(this.fileName);
        this.pic = image.getImage();
        this.setBackground(new Color(0, true));
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pic, 0, 0, 400, 400, this);
    }

    public void setPic(ImageIcon image) {
        this.pic = image.getImage();
        repaint();
    }
}
