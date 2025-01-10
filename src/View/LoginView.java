package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel frame;
    private JPanel input;
    private JLabel nomorTelepon, password, logoLabel, header;
    private JTextField nomorTeleponValue;
    private JPasswordField passwordValue;
    private JButton login, back;

    public LoginView() {
        super("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.BLUE);

        input = new JPanel(new GridLayout(3, 2, 10, 10));
        input.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("src/View/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        header = new JLabel("SERIF DELIVERY");
        header.setFont(new Font("Arial", Font.PLAIN, 16));
        header.setForeground(Color.WHITE);

        nomorTelepon = new JLabel("Nomor Telepon        :");
        nomorTelepon.setFont(new Font("Arial", Font.PLAIN, 16));
        nomorTelepon.setForeground(Color.WHITE);

        password = new JLabel("Password  :");
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.WHITE);

        nomorTeleponValue = new JTextField();
        nomorTeleponValue.setPreferredSize(new Dimension(300, 40));
        nomorTeleponValue.setFont(new Font("Arial", Font.PLAIN, 16));
        nomorTeleponValue.setBackground(Color.WHITE);
        nomorTeleponValue.setForeground(Color.BLUE);

        passwordValue = new JPasswordField();
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordValue.setBackground(Color.WHITE);
        passwordValue.setForeground(Color.BLUE);

        login = new JButton("LOGIN");
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);

        back = new JButton("BACK");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);

        input.add(nomorTelepon);
        input.add(nomorTeleponValue);
        input.add(password);
        input.add(passwordValue);
        input.add(login);
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
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(header, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;  
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(input, gbc);

        add(frame);

        setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new MainMenu();
            }

        });
    }

    public String getNomorTelepon() {
        return nomorTeleponValue.getText();
    }

    public String getPassword() {
        return new String(passwordValue.getPassword());
    }

    public JButton getBackButton() {
        return back;
    }

    public JButton getLoginButton() {
        return login;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
