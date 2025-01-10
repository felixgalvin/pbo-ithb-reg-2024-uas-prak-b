package View;

import java.util.Arrays;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.classes.DeliveryDetails;
import Model.classes.SingletonManager;
import Model.enums.Status;

public class AddDetailDeliveryView extends JFrame {
    
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

    public AddDetailDeliveryView() {
        super("Add Detail Delivery");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        
        mainFramePanel = new JPanel(new GridBagLayout());
        mainFramePanel.setBackground(Color.BLUE);

        inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setOpaque(false);
        
        JTextField transactionId = new JTextField();
        newPair("Transaction Id:", transactionId);

        JTextField currentPosition = new JTextField();
        newPair("Current Position:", currentPosition);
      
        JTextField evidence = new JTextField();
        newPair("Evidence:", evidence);
    
        JTextField updatedBy = new JTextField();  
        newPair("Updated By:", updatedBy);

        String[] valuesStatus = Arrays.stream(Status.values()).map(Status::name).toArray(String[]::new);
        JComboBox<String> statusComboBox = new JComboBox<String>(valuesStatus);
        newPair("Status:", statusComboBox);

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
                if(transactionId.getText().isEmpty() || currentPosition.getText().isEmpty() || evidence.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Maaf, semua field harus diisi");
                    return;
                }
                DeliveryDetails dd = new DeliveryDetails(
                    0,
                    Integer.parseInt(transactionId.getText()),
                    Status.valueOf(statusComboBox.getSelectedItem().toString()), 
                    currentPosition.getText(),
                    evidence.getText(),
                    new Date(),
                    SingletonManager.getInstance().getUser().getName()
                );
                if (DeliveryDetails.addDeliveryDetails(dd)) {
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
