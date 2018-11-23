/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Ricardo Carvalho
 */
public class AppWindow {

    private JButton login, newAccount, register, back;
    private CardLayout cl;
    private JPanel content;

    public AppWindow() {
        JFrame f = new JFrame("Euromilhoes Simulator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setResizable(false);

        cl = new CardLayout();
        content = new JPanel(cl);

        content.add(loginPanel(), "login");
        content.add(registerPanel(), "register");

        f.setContentPane(content);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private JPanel loginPanel() {

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon("imgs/logo.png"));
        image.add(l);

        JPanel p1 = new JPanel(new SpringLayout());
        p1.setBackground(Color.white);
        String[] labels = {"Nome de Utilizador: ", "Password: "};
        int numPairs = labels.length;

        //Username
        JLabel l1 = new JLabel(labels[0], JLabel.TRAILING);
        p1.add(l1);
        JTextField textField = new JTextField(10);
        l1.setLabelFor(textField);
        p1.add(textField);

        //Password
        JLabel l2 = new JLabel(labels[1], JLabel.TRAILING);
        p1.add(l2);
        JTextField passwordField = new JPasswordField(10);
        l2.setLabelFor(passwordField);
        p1.add(passwordField);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p1,
                numPairs, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Buttons
        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        login = new JButton();
        login.setText("Login");
        login.setPreferredSize(new Dimension(100, 30));
        newAccount = new JButton();
        newAccount.setText("Criar conta");
        newAccount.setPreferredSize(new Dimension(100, 30));
        newAccount.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "register");
            }
        });

        p2.add(login);
        p2.add(newAccount);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }

    private JPanel registerPanel() {

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon("imgs/logo.png"));
        image.add(l);

        JPanel p1 = new JPanel(new SpringLayout());
        p1.setBackground(Color.white);
        String[] labels = {"Nome de Utilizador: ", "Password: "};
        int numPairs = labels.length;

        //Username
        JLabel l1 = new JLabel(labels[0], JLabel.TRAILING);
        p1.add(l1);
        JTextField textField = new JTextField(10);
        l1.setLabelFor(textField);
        p1.add(textField);

        //Password
        JLabel l2 = new JLabel(labels[1], JLabel.TRAILING);
        p1.add(l2);
        JTextField passwordField = new JPasswordField(10);
        l2.setLabelFor(passwordField);
        p1.add(passwordField);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p1,
                numPairs, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Buttons
        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        register = new JButton();
        register.setText("Registar");
        register.setPreferredSize(new Dimension(100, 30));
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "login");
            }
        });

        p2.add(register);
        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }
}
