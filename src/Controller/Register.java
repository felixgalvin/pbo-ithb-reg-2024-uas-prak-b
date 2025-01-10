package Controller;

import View.LoginView;
import View.RegisterView;

import javax.swing.JOptionPane;

import Model.classes.Customer;


public class Register {
    private RegisterView view;

    public Register(RegisterView view) {
        this.view = view;
        this.view.getRegisterButton().addActionListener(e -> register());
    }

    public void register() {
        if (view.getNomorTelepon().trim().isEmpty() ||
                view.getNama().trim().isEmpty() ||
                view.getAlamat().trim().isEmpty() ||
                view.getPassword().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String noTelp = view.getNomorTelepon();
        String nama = view.getNama();
        String alamat = view.getAlamat();
        String password = view.getPassword();

        Customer customer = new Customer(0, password, nama, alamat, noTelp);

        if (Customer.Register(customer)) {
            view.showMessage("Registrasi berhasil! Silakan login.");
            view.dispose();
            new Login(new LoginView());
        } else {
            view.showMessage("Registrasi gagal! Silakan coba lagi.");
        }
    }
}
