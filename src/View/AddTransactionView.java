package View;

import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.DataBaseController;
import Model.classes.SingletonManager;
import Model.classes.Transaction;

public class AddTransactionView extends JFrame {
    
    private JPanel mainFramePanel;
    private JPanel inputPanel;

    public JButton newButton(String label) {
        JButton b = new JButton(label);
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBackground(Color.BLUE);
        b.setForeground(Color.WHITE);
        return b;
    }

    public JLabel newLabel(String label) {
        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.PLAIN, 16));
        l.setForeground(Color.WHITE);
        return l;
    }

    public void newPair(JComponent a, JComponent b) {
        inputPanel.add(a);
        inputPanel.add(b);
    }

    public void newPair(String s, JComponent b) {
        newPair(newLabel(s), b);
    }

    private JComboBox<String> packageTypeComboBox;

    public AddTransactionView() {
        super("Add Transaction");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        
        mainFramePanel = new JPanel(new GridBagLayout());
        mainFramePanel.setBackground(Color.BLUE);

        inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setOpaque(false);
        
        JTextField nameField = new JTextField();
        newPair("Nama Penerima:", nameField);

        JTextField addressField = new JTextField();
        newPair("Alamat Penerima:", addressField);

        JTextField phoneField = new JTextField();
        newPair("Nomor Telepon:", phoneField);

        JTextField weightField = new JTextField();
        newPair("Berat Paket (kg):", weightField);

        packageTypeComboBox = new JComboBox<>(new DataBaseController().getDeliveryType());
        newPair("Tipe Paket:", packageTypeComboBox);

        JButton saveButton = newButton("Simpan");
        JButton backButton = newButton("Back");
        newPair(saveButton, backButton);

        ImageIcon logoIcon = new ImageIcon("src/View/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel header = new JLabel("SERIF DELIVERY");
        header.setFont(new Font("Arial", Font.PLAIN, 16));
        header.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        mainFramePanel.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        mainFramePanel.add(header, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        mainFramePanel.add(inputPanel, gbc);

        add(mainFramePanel);
        
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new MainMenu();
            }

        });

        // uda mager pindahin ke controller hehe :)
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty() || weightField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi");
                    return;
                }
                if (Double.parseDouble(weightField.getText()) == 0) {
                    JOptionPane.showMessageDialog(null, "Berat pengiriman tidak boleh 0");
                    return;
                }


                Transaction trans = new Transaction(
                    0,
                    SingletonManager.getInstance().getUser().getId(),
                    packageTypeComboBox.getSelectedItem().toString(),
                    Double.parseDouble(weightField.getText()),
                    0,
                    new Date(),
                    nameField.getText(),
                    addressField.getText(),
                    phoneField.getText()
                );
                if (Transaction.addTransaksi(trans)) {
                    JOptionPane.showMessageDialog(null, "Transaksi berhasil");
                    dispose();
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Transaksi gagal");
                }
            }
        });
    }
}
