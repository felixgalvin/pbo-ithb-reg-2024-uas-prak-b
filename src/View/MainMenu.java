package View;

import java.awt.*;

import javax.swing.*;

import Controller.Login;
import Controller.Register;
import Model.classes.SingletonManager;
import Model.classes.Customer;

public class MainMenu extends JFrame{
    private JButton regis, login, addTransaksi, viewHistory;
    private JLabel logoLabel;
    private JPanel panel;

    public MainMenu(){
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // show image icon
        ImageIcon logoIcon = new ImageIcon("src/View/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);
        
        // Header
        JLabel judulAtas = new JLabel("SERIF DELIVERY", SwingConstants.CENTER);
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 28));
        judulAtas.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulAtas, gbc);

        // Header 2
        Customer currentUser = SingletonManager.getInstance().getUser();
        JLabel judulBawah = new JLabel("Pilih opsi Anda", SwingConstants.CENTER);
        
        if (currentUser != null) {
            judulBawah = new JLabel(String.format("Welcome, %s Pilih opsi Anda", currentUser.getName()), SwingConstants.CENTER);
        }
        judulBawah.setFont(new Font("Arial", Font.PLAIN, 20));
        judulBawah.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5; 
        panel.add(judulBawah, gbc);

        login = new JButton("LOGIN");
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(login, gbc);

        regis= new JButton("REGISTRASI");
        regis.setFont(new Font("Arial", Font.PLAIN, 16));
        regis.setBackground(Color.BLUE);
        regis.setForeground(Color.WHITE);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(regis, gbc);

        addTransaksi = new JButton("TAMBAH TRANSAKSI");
        addTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        addTransaksi.setBackground(Color.BLUE);
        addTransaksi.setForeground(Color.WHITE);
        addTransaksi.addActionListener(e -> {
            if (SingletonManager.getInstance().getUser() == null) {
                JOptionPane.showMessageDialog(null, "Anda Belum LOGIN", "Error",
                JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new AddTransactionView();
            }

        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addTransaksi, gbc);

        JButton addDetailTransaksi =  new JButton("TAMBAH DETAIL TRANSAKSI");
        addDetailTransaksi.setFont(new Font("Arial", Font.PLAIN, 16));
        addDetailTransaksi.setBackground(Color.BLUE);
        addDetailTransaksi.setForeground(Color.WHITE);
        addDetailTransaksi.addActionListener(e -> {
            if (SingletonManager.getInstance().getUser() == null) {
                JOptionPane.showMessageDialog(null, "Anda Belum LOGIN", "Error",
                JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new AddDetailDeliveryView();
            }

        });

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addDetailTransaksi, gbc);     

        viewHistory = new JButton("LIHAT HISTORY");
        viewHistory.setFont(new Font("Arial", Font.PLAIN, 16));
        viewHistory.setBackground(Color.BLUE);
        viewHistory.setForeground(Color.WHITE);
        viewHistory.addActionListener(e -> {
            if (SingletonManager.getInstance().getUser() == null) {
                JOptionPane.showMessageDialog(null, "Anda Belum LOGIN", "Error",
                JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new ShowHistoryView();
            }

        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(viewHistory, gbc);  
        
        add(panel);
        
        setVisible(true);

        login.addActionListener(e -> {
            if (SingletonManager.getInstance().getUser() == null) {
                dispose();
                new Login(new LoginView());
            } else {
                JOptionPane.showMessageDialog(null, "Anda sudah LOGIN", "Error",
                JOptionPane.ERROR_MESSAGE);
            }

        });

        regis.addActionListener(e -> {
            if (SingletonManager.getInstance().getUser() == null) {
                dispose();
                new Register(new RegisterView());
            } else {
                JOptionPane.showMessageDialog(null, "Anda sudah LOGIN", "Error",
                JOptionPane.ERROR_MESSAGE);
            }

        });
    }
}
