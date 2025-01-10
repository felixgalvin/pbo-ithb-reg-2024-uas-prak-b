package Controller;

import View.*;

import javax.swing.JOptionPane;

import Model.classes.*;

public class Login {
    private LoginView loginView;

    public Login(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getLoginButton().addActionListener(e -> cekLogin());
    }

    public void cekLogin() {
        if (loginView.getNomorTelepon().trim().isEmpty() ||
            loginView.getPassword().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String noTelp = loginView.getNomorTelepon();
        String pass = loginView.getPassword();
        
        boolean isValid = false;

        Customer newCustomer = Customer.cekLogin(noTelp, pass);
        if (newCustomer != null) {
            isValid = true;
            SingletonManager.getInstance().setUser(newCustomer);
            loginView.showMessage("Login berhasil! Selamat datang, " + newCustomer.getName());
            loginView.dispose();
            new MainMenu();
        }
        
        if (!isValid) {
            loginView.showMessage("Login gagal. Silakan registrasi.");
            showRegisterForm();
        }
    }

    private void showRegisterForm() {
        loginView.dispose();  
        new Register(new RegisterView());  
    }
}
