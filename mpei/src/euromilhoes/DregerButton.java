/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

// DregerButton.java
// Copyright Kyle Dreger 2012
// To make JButtons suck less
// https://gist.github.com/4646029
public class DregerButton extends JButton implements MouseListener {

    Font defaultFont = new Font("Gill Sans MT", Font.BOLD, 14);
    Color textColor = Color.decode("#ffffff");
    Color backgroundColor = Color.decode("#000000");
    Color hoverColor = Color.decode("#00aced");

    public DregerButton(String s) {
        s = s.toUpperCase();
        this.setFocusPainted(false);
        this.setText(s);
        this.setBorder(null);
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setFont(defaultFont);
        this.setOpaque(true);
        addMouseListener(this);
    }

    public DregerButton(String s, Color backgroundColor, Color hoverColor) {
        s = s.toUpperCase();
        this.setFocusPainted(false);
        this.setText(s);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(backgroundColor);
        this.backgroundColor= backgroundColor;
        this.hoverColor= hoverColor;
        this.setFont(defaultFont);
        this.setOpaque(true);
        addMouseListener(this);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public void setHoverColor(Color color) {
        hoverColor = color;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this) {
            this.setBackground(this.hoverColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this) {
            this.setBackground(this.backgroundColor);
        }
    }
}
