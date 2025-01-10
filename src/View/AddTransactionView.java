package View;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.DataBaseController;

public class AddTransactionView {
    private JComboBox<String> packageTypeComboBox;

    public AddTransactionView() {
        insert();
    }

    public void insert() {
        JFrame frame = new JFrame("Add Transaksi Pengiriman");
        frame.setDefaultCloseOperation(JFrame.MAXIMIZED_HORIZ);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Nama Penerima:");
        nameLabel.setBounds(20, 20, 150, 20);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(180, 20, 150, 20);
        frame.add(nameField);

        JLabel addressLabel = new JLabel("Alamat Penerima:");
        addressLabel.setBounds(20, 60, 150, 20);
        frame.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(180, 60, 150, 20);
        frame.add(addressField);

        JLabel phoneLabel = new JLabel("Nomor Telepon:");
        phoneLabel.setBounds(20, 100, 150, 20);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(180, 100, 150, 20);
        frame.add(phoneField);

        JLabel weightLabel = new JLabel("Berat Paket (kg):");
        weightLabel.setBounds(20, 140, 150, 20);
        frame.add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setBounds(180, 140, 150, 20);
        frame.add(weightField);

        JLabel packageTypeLabel = new JLabel("Tipe Paket:");
        packageTypeLabel.setBounds(20, 180, 150, 20);
        frame.add(packageTypeLabel);

        packageTypeComboBox = new JComboBox<>(new DataBaseController().getDeliveryType());
        packageTypeComboBox.setBounds(180, 180, 150, 20);
        frame.add(packageTypeComboBox);

        JButton saveButton = new JButton("Simpan");
        saveButton.setBounds(50, 240, 100, 30);
        frame.add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 240, 100, 30);
        frame.add(backButton);

        
        saveButton.addActionListener(e -> {
            Date date = new Date();
            String nama = nameField.getText();
            String alamat = addressField.getText();
            String nomor = phoneField.getText();
            double berat = Double.parseDouble(weightField.getText());
            String type = (String) packageTypeComboBox.getSelectedItem();

            if (nama.isEmpty() || alamat.isEmpty() || nomor.isEmpty() || berat == 0 || weightField.getText() == null
                    || type.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Jangan ada yang kosong", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                new AddTransaction().AddTransactionUser(type, berat, date, nama, alamat, nomor);
                JOptionPane.showMessageDialog(null, "BERHASIL ADD TRANSACTION");
            }

            frame.dispose();
            new MainMenu();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        frame.setVisible(true);
    }

}
