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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Ricardo Carvalho
 */
public class AppWindow {

    private JButton login, newAccount, register, back, menuOp1, menuOp2, menuOp3, menuOp4, menuOp5, play;
    private CardLayout cl;
    private JPanel content;

    public AppWindow() {
        AppUtilities.loadInfo();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame f = new JFrame("Euromilhoes Simulator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setResizable(false);

        cl = new CardLayout();
        content = new JPanel(cl);

        content.add(loginPanel(), "login");
        content.add(registerPanel(), "register");
        content.add(clientMenu(), "clientMenu");
        content.add(adminMenu(), "adminMenu");
        content.add(functPanel1(), "functPanel1");
        content.add(functPanel2(), "functPanel2");
        content.add(functPanel3(), "functPanel3");

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
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
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
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(textField.getText().equals("root") && passwordField.getText().equals("root")){
                    cl.show(content, "adminMenu");
                }
                else if(!textField.getText().contains("randomPlayer") && AppUtilities.userInDatabase(textField.getText(), passwordField.getText())){
                    cl.show(content, "clientMenu");
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Nome de utilizador ou password errada", "Login Inválido", JOptionPane.WARNING_MESSAGE);
                    passwordField.setText("");
                }
            }
        });
        newAccount = new JButton();
        newAccount.setText("Criar conta");
        newAccount.setPreferredSize(new Dimension(100, 30));
        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField.setText("");
                passwordField.setText("");
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
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
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
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(AppUtilities.nameInDatabase(textField.getText())){
                    textField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Nome de utilizador já utilizado", "Registo Inválido", JOptionPane.WARNING_MESSAGE);
                }
                else if(!AppUtilities.nameInDatabase(textField.getText()) && passwordField.getText().length()<8){
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Password inválida", "Registo Inválido", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    AppUtilities.addUserToDatabase(textField.getText(), passwordField.getText());
                    textField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Registo efectuado com sucesoo", "Registo Válido", JOptionPane.INFORMATION_MESSAGE);
                    cl.show(content, "login");
                }
            }
        });
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField.setText("");
                passwordField.setText("");
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

    private JPanel clientMenu() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new GridLayout(5, 1, 0, 5));
        p1.setBackground(Color.white);
        //Menu Buttons
        menuOp1 = new JButton();
        menuOp1.setText("Jogar no euromilhões");
        p1.add(menuOp1);

        menuOp2 = new JButton();
        menuOp2.setText("Mostrar prémios");
        p1.add(menuOp2);

        menuOp3 = new JButton();
        menuOp3.setText("Números mais frequentes");
        p1.add(menuOp3);

        menuOp4 = new JButton();
        menuOp4.setText("Chave semelhantes de outros utilizadores");
        p1.add(menuOp4);

        menuOp5 = new JButton();
        menuOp5.setText("Logout");
        p1.add(menuOp5);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        return p;
    }

    private JPanel adminMenu() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new GridLayout(5, 1, 0, 5));
        p1.setBackground(Color.white);
        //Menu Buttons
        menuOp1 = new JButton();
        menuOp1.setText("Remover Cliente");
        p1.add(menuOp1);

        menuOp2 = new JButton();
        menuOp2.setText("Adicionar Data");
        p1.add(menuOp2);

        menuOp3 = new JButton();
        menuOp3.setText("Remover Data");
        p1.add(menuOp3);

        menuOp4 = new JButton();
        menuOp4.setText("Simular sorteio");
        p1.add(menuOp4);

        menuOp5 = new JButton();
        menuOp5.setText("Logout");
        p1.add(menuOp5);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        return p;
    }

    private JPanel functPanel1() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new GridLayout(2, 1, 0, 2));
        p1.setBackground(Color.white);

        JPanel p11 = new JPanel(new FlowLayout());
        p11.setBackground(Color.white);
        JLabel lab1 = new JLabel();
        lab1.setText("Chave: ");
        p11.add(lab1);
        JComboBox[] nums = new JComboBox[7];
        for (int i = 0; i < 7; i++) {
            int max = (i < 5) ? 50 : 12;
            List<Integer> numsList = IntStream.rangeClosed(1, max).boxed().collect(Collectors.toList());
            nums[i] = new JComboBox(numsList.toArray(new Integer[0]));
            nums[i].setSelectedIndex(0);
            nums[i].setBackground((i < 5) ? Color.green : Color.yellow);
            p11.add(nums[i]);
        }
        p1.add(p11);

        JPanel p12 = new JPanel(new FlowLayout());
        p12.setBackground(Color.white);
        JLabel lab2 = new JLabel();
        lab2.setText("Data: ");
        p12.add(lab2);
        JComboBox[] dataFields = new JComboBox[7];
        for (int i = 0; i < 3; i++) {
            int min = 1;
            int max = 31;
            if (i == 1) {
                min = 1;
                max = 12;
            } else if (i == 2) {
                min = 2018;
                max = 2022;
            }
            List<Integer> dataList = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
            dataFields[i] = new JComboBox(dataList.toArray(new Integer[0]));
            dataFields[i].setSelectedIndex(0);
            p12.add(dataFields[i]);
        }
        p1.add(p12);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        play = new JButton();
        play.setText("Jogar Chave");
        play.setPreferredSize(new Dimension(100, 30));
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "clientMenu");
            }
        });

        p2.add(play);
        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }

    private JPanel functPanel2() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new GridLayout(1, 1));
        p1.setBackground(Color.white);
        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_CENTER);
        JTextPane textArea = new JTextPane();
        textArea.getStyledDocument().setParagraphAttributes(0, 600, sa, false);
        textArea.setText("1111111111111111111\n2\n3\n4\n5\n6\n7\n8\n9");
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        p1.add(textArea);
        JScrollPane sp = new JScrollPane(p1);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        play = new JButton();
        play.setText("Jogar Chave");
        play.setPreferredSize(new Dimension(100, 30));
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "clientMenu");
            }
        });

        p2.add(play);
        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(sp, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }

    private JPanel functPanel3() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new FlowLayout());
        p1.setBackground(Color.white);

        JPanel p11 = new JPanel();
        p11.setBackground(Color.white);
        JLabel l1 = new JLabel("Chave: ", JLabel.TRAILING);
        p1.add(l1);
        JTextField textField = new JTextField(20);
        l1.setLabelFor(textField);
        p1.add(textField);

        p1.add(p11);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        play = new JButton();
        play.setText("Jogar Chave");
        play.setPreferredSize(new Dimension(100, 30));
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "clientMenu");
            }
        });

        p2.add(play);
        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }
}
