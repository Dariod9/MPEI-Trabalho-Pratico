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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private JLabel userString;
    private Jogador currentPlayer;

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
        content.add(functPanel1(1), "functPanel1");
        content.add(functPanel1(2), "functPanel2");
        content.add(functPanel1(3), "functPanel3");
        content.add(functPanel2(4), "functPanel4");
        content.add(functPanel3(6), "functPanel6");
        content.add(functPanel3(7), "functPanel7");
        content.add(functPanel3(8), "functPanel8");
        content.add(functPanel1(9), "functPanel9");

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
        login.setPreferredSize(new Dimension(140, 30));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (textField.getText().equals("root") && passwordField.getText().equals("root")) {
                    textField.setText("");
                    passwordField.setText("");
                    currentPlayer = null;
                    cl.show(content, "adminMenu");
                } else if (!textField.getText().contains("randomPlayer") && AppUtilities.userInDatabase(textField.getText(), passwordField.getText())) {
                    currentPlayer = AppUtilities.getUser(textField.getText(), passwordField.getText());
                    textField.setText("");
                    passwordField.setText("");
                    userString.setText("Username: " + currentPlayer.getNome() + ",  Prémios esperados: " + currentPlayer.getPremiosEsperados() + ",  Total em prémios: " + currentPlayer.getTotalPremios() + "€");
                    cl.show(content, "clientMenu");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Nome de utilizador ou password errada", "Login Inválido", JOptionPane.WARNING_MESSAGE);
                    passwordField.setText("");
                }
            }
        });
        newAccount = new JButton();
        newAccount.setText("Criar conta");
        newAccount.setPreferredSize(new Dimension(140, 30));
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
        register.setPreferredSize(new Dimension(140, 30));
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (AppUtilities.nameInDatabase(textField.getText()) || textField.getText().contains("root") || textField.getText().contains("randomPlayer")) {
                    textField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Nome de utilizador já utilizado ou inválido", "Registo Inválido", JOptionPane.WARNING_MESSAGE);
                } else if (!AppUtilities.nameInDatabase(textField.getText()) && passwordField.getText().length() < 8) {
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Password inválida", "Registo Inválido", JOptionPane.WARNING_MESSAGE);
                } else {
                    AppUtilities.addUserToDatabase(textField.getText(), passwordField.getText());
                    textField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(new JFrame(), "Registo efectuado com sucesso", "Registo Válido", JOptionPane.INFORMATION_MESSAGE);
                    cl.show(content, "login");
                    AppUtilities.saveInfo();
                }
            }
        });
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(140, 30));
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

        JPanel user = new JPanel(new FlowLayout());
        user.setBackground(Color.white);
        userString = new JLabel();
        userString.setText("");
        user.add(userString);

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
        menuOp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel1");
            }
        });
        p1.add(menuOp1);

        menuOp2 = new JButton();
        menuOp2.setText("Verificar prémio");
        menuOp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel2");
            }
        });
        p1.add(menuOp2);

        menuOp3 = new JButton();
        menuOp3.setText("Números mais frequentes");
        menuOp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel3");
            }
        });
        p1.add(menuOp3);

        menuOp4 = new JButton();
        menuOp4.setText("Chave semelhantes de outros utilizadores");
        menuOp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel4");
            }
        });
        p1.add(menuOp4);

        menuOp5 = new JButton();
        menuOp5.setText("Logout");
        menuOp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AppUtilities.saveInfo();
                cl.show(content, "login");
            }
        });
        p1.add(menuOp5);

        p.add(user, BorderLayout.NORTH);
        p.add(image, BorderLayout.CENTER);
        p.add(p1, BorderLayout.SOUTH);
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
        menuOp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel6");
            }
        });
        p1.add(menuOp1);

        menuOp2 = new JButton();
        menuOp2.setText("Adicionar Data");
        menuOp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel7");
            }
        });
        p1.add(menuOp2);

        menuOp3 = new JButton();
        menuOp3.setText("Remover Data");
        menuOp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel8");
            }
        });
        p1.add(menuOp3);

        menuOp4 = new JButton();
        menuOp4.setText("Simular sorteio");
        menuOp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cl.show(content, "functPanel9");
            }
        });
        p1.add(menuOp4);

        menuOp5 = new JButton();
        menuOp5.setText("Logout");
        menuOp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AppUtilities.saveInfo();
                cl.show(content, "login");
            }
        });
        p1.add(menuOp5);

        p.add(image, BorderLayout.CENTER);
        p.add(p1, BorderLayout.SOUTH);
        return p;
    }

    private JPanel functPanel1(int id) {
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
            if (id != 1) {
                nums[i].setEnabled(false);
            }
            nums[i].setBackground((i < 5) ? Color.green : Color.yellow);
            p11.add(nums[i]);
        }
        p1.add(p11);

        if (id == 3) {
            int[] mostFrequent = AppUtilities.mostFrequentChave();
            for (int i = 0; i < 7; i++) {
                nums[i].setSelectedIndex(mostFrequent[i]);
            }
        }

        final JComboBox data = new JComboBox((id != 2) ? AppUtilities.datesList() : AppUtilities.getSorteiosDates());
        if (id != 3) {
            JPanel p12 = new JPanel(new FlowLayout());
            p12.setBackground(Color.white);
            JLabel lab2 = new JLabel();
            lab2.setText("Data: ");
            p12.add(lab2);
            p12.add(data);
            p1.add(p12);
        }

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        if (id != 3) {
            play = new JButton();
            play.setText((id == 1) ? "Jogar chave" : "Efectuar");
            play.setPreferredSize(new Dimension(140, 30));
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (id == 1) {
                        if (data.getSelectedItem() != null) {
                            List<Integer> numeros = new ArrayList<>();
                            List<Integer> estrelas = new ArrayList<>();
                            for (int i = 0; i < 7; i++) {
                                boolean b = (i < 5) ? numeros.add((Integer) nums[i].getSelectedItem()) : estrelas.add((Integer) nums[i].getSelectedItem());
                            }
                            Set<Integer> uniqueNumeros = new HashSet<>(numeros);
                            Set<Integer> uniqueEstrelas = new HashSet<>(estrelas);
                            if (numeros.size() == uniqueNumeros.size() && estrelas.size() == uniqueEstrelas.size()) {
                                if (!currentPlayer.getMapa().keySet().contains((Date) data.getSelectedItem()) && AppUtilities.dateInDatabase((Date) data.getSelectedItem())) {
                                    Collections.sort(numeros);
                                    Collections.sort(estrelas);
                                    AppUtilities.addJogadaToDatabase((Date) data.getSelectedItem(), currentPlayer, numeros, estrelas);
                                    AppUtilities.defaultUsersJogada((Date) data.getSelectedItem());
                                    currentPlayer.setEvento();
                                    JOptionPane.showMessageDialog(new JFrame(), "Jogada efectuada com sucesso", "Jogada Válida", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(new JFrame(), "Jogada já efectuada na data ou data não disponível", "Jogada Inválida", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Chave inválida", "Jogada Inválida", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Deve selecionar uma data", "Jogada Inválida", JOptionPane.WARNING_MESSAGE);
                        }
                    } else if (id == 2) {
                        if (data.getSelectedItem() != null) {
                            if (currentPlayer.getMapa().keySet().contains((Date) data.getSelectedItem())) {
                                for (int i = 0; i < 7; i++) {
                                    nums[i].setSelectedIndex(AppUtilities.chave((Date) data.getSelectedItem())[i] - 1);
                                }
                                int premio = AppUtilities.applyCountingBloomFilterToCheckAwards((Date) data.getSelectedItem(), currentPlayer);
                                if (premio > 0) {
                                    currentPlayer.setPremio(premio);
                                    JOptionPane.showMessageDialog(new JFrame(), "Prémio de " + premio + "€:\n" + AppUtilities.getChaveStringByUser((Date) data.getSelectedItem(), currentPlayer), "Operação Válida", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(new JFrame(), "Chave sem prémio", "Operação Válida", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Não foi efectuada nenhuma jogada nessa data", "Operação Inválida", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Deve selecionar uma data", "Operação Inválida", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        if (data.getSelectedItem() != null) {
                            Date d = (Date) data.getSelectedItem();
                            if (AppUtilities.dateInDatabase(d)) {
                                AppUtilities.addSorteioToDatabase((Date) data.getSelectedItem());
                                data.removeItem(d);
                                for (int i = 0; i < 7; i++) {
                                    nums[i].setSelectedIndex(AppUtilities.chave(d)[i] - 1);
                                }
                                JOptionPane.showMessageDialog(new JFrame(), "Sorteio efetuado com sucesso", "Sorteio Válido", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Data já não se encontra disponível", "Sorteio Inválido", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Deve selecionar uma data", "Sorteio Inválido", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            });
            p2.add(play);
        }
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(140, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (id < 5) {
                    userString.setText("Username: " + currentPlayer.getNome() + ",  Prémios esperados: " + currentPlayer.getPremiosEsperados() + ",  Total em prémios: " + currentPlayer.getTotalPremios() + "€");
                    cl.show(content, "clientMenu");
                } else {
                    cl.show(content, "adminMenu");
                }
            }
        });

        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }

    private JPanel functPanel2(int id) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JPanel image = new JPanel(new FlowLayout());
        image.setBackground(Color.white);
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("/euromilhoes/data/logo.png")));
        image.add(l);

        JPanel p1 = new JPanel(new GridLayout(2, 1));
        p1.setBackground(Color.white);
        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_CENTER);
        JTextPane textArea = new JTextPane();
        textArea.getStyledDocument().setParagraphAttributes(0, 600, sa, false);
        textArea.setText("");
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        p1.add(textArea);

        final JComboBox data = new JComboBox(AppUtilities.getSorteiosDates());
        JPanel p12 = new JPanel(new FlowLayout());
        p12.setBackground(Color.white);
        JLabel lab2 = new JLabel();
        lab2.setText("Data: ");
        p12.add(lab2);
        p12.add(data);
        p1.add(p12);
        
        JScrollPane sp = new JScrollPane(p1);
        
        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(140, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (id < 5) {
                    cl.show(content, "clientMenu");
                } else {
                    cl.show(content, "adminMenu");
                }
            }
        });

        p2.add(back);

        p.add(image, BorderLayout.NORTH);
        p.add(sp, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        return p;
    }

    private JPanel functPanel3(int id) {
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
        JLabel l1 = new JLabel("", JLabel.TRAILING);
        l1.setText((id == 6) ? "Cliente: " : "Data: ");
        p1.add(l1);
        JTextField textField = new JTextField(20);
        l1.setLabelFor(textField);
        p1.add(textField);

        p1.add(p11);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        play = new JButton();
        play.setText("Efetuar operação");
        play.setPreferredSize(new Dimension(140, 30));
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (id == 6) {
                    if (AppUtilities.removeUserFromDatabase(textField.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Utilizador removido com sucesso", "Utilizador Válido", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Utilizador não encontrado", "Utilizador Inválido", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    //Operações com datas
                    String[] dataFields = textField.getText().split("/");
                    if (dataFields.length == 3 && dataFields[0].length() == 2 && dataFields[1].length() == 2 && dataFields[2].length() == 4) {
                        try {
                            if (AppUtilities.validDate(Integer.parseInt(dataFields[0]), Integer.parseInt(dataFields[1]) - 1, Integer.parseInt(dataFields[2]))) {
                                if (id == 7) {
                                    if (!AppUtilities.dateInDatabase(Integer.parseInt(dataFields[0]), Integer.parseInt(dataFields[1]) - 1, Integer.parseInt(dataFields[2]))) {
                                        if (AppUtilities.addDateToDatabase(Integer.parseInt(dataFields[0]), Integer.parseInt(dataFields[1]) - 1, Integer.parseInt(dataFields[2]))) {
                                            textField.setText("");
                                            JOptionPane.showMessageDialog(new JFrame(), "Data adicionada com sucesso", "Data Válida", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(new JFrame(), "Dia da semana inválido", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Data já existente", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else {
                                    if (AppUtilities.removeDateFromDatabase(Integer.parseInt(dataFields[0]), Integer.parseInt(dataFields[1]) - 1, Integer.parseInt(dataFields[2]))) {
                                        textField.setText("");
                                        JOptionPane.showMessageDialog(new JFrame(), "Data removida com sucesso", "Data Válida", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Data não encontrada", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Formato de data inválido", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(new JFrame(), "Formato de data inválido", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Formato de data inválido", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        back = new JButton();
        back.setText("Voltar");
        back.setPreferredSize(new Dimension(140, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField.setText("");
                cl.show(content, "adminMenu");
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