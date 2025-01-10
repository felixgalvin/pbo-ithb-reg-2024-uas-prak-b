package View;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, nomorTelepon, nama, alamat, password;
    private JTextField nomorTeleponValue, namaValue, alamatValue;
    private JPasswordField passwordValue;
    private JButton back, registerButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);

    public RegisterView() {
        super("Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.BLUE);

        // untuk label dan input
        input = new JPanel(new GridLayout(5, 2, 15, 10));
        input.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("src/View/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        input.add(nomorTelepon = new JLabel("Nomor Telepon     :"));
        nomorTelepon.setFont(new Font("Arial", Font.PLAIN, 16));
        nomorTelepon.setForeground(Color.WHITE);

        nomorTeleponValue = new JTextField(10);
        nomorTeleponValue.setBorder(roundedBorder);
        nomorTeleponValue.setPreferredSize(new Dimension(300, 40));
        nomorTeleponValue.setFont(new Font("Arial", Font.PLAIN, 16));
        nomorTeleponValue.setBackground(Color.WHITE);
        nomorTeleponValue.setForeground(Color.BLUE);
        input.add(nomorTeleponValue);

        input.add(nama = new JLabel("Nama     :"));
        nama.setFont(new Font("Arial", Font.PLAIN, 16));
        nama.setForeground(Color.WHITE);

        namaValue = new JTextField(10);
        namaValue.setBorder(roundedBorder);
        namaValue.setPreferredSize(new Dimension(300, 40));
        namaValue.setFont(new Font("Arial", Font.PLAIN, 16));
        namaValue.setBackground(Color.WHITE);
        namaValue.setForeground(Color.BLUE);
        input.add(namaValue);

        input.add(alamat = new JLabel("Alamat                  :"));
        alamat.setFont(new Font("Arial", Font.PLAIN, 16));
        alamat.setForeground(Color.WHITE);

        alamatValue = new JTextField(10);
        alamatValue.setBorder(roundedBorder);
        alamatValue.setPreferredSize(new Dimension(300, 40));
        alamatValue.setFont(new Font("Arial", Font.PLAIN, 16));
        alamatValue.setBackground(Color.WHITE);
        alamatValue.setForeground(Color.BLUE);
        input.add(alamatValue);

        input.add(password = new JLabel("Password          :"));
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.WHITE);

        passwordValue = new JPasswordField(10);
        passwordValue.setBorder(roundedBorder);
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordValue.setBackground(Color.WHITE);
        passwordValue.setForeground(Color.BLUE);
        input.add(passwordValue);

        back = new JButton("BACK");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);

        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
        
        input.add(registerButton);
        input.add(back);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(input, gbc);
         
        add(frame);
 
        setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
              new MainMenu(); 
            }
        });
    }

    public String getAlamat() {
        return alamatValue.getText();
    }

    public String getPassword() {
        return new String(passwordValue.getPassword());
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getNomorTelepon() {
        return nomorTeleponValue.getText();
    }

    public String getNama() {
        return namaValue.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
