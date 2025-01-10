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

        if (Customer.cekLogin(noTelp, pass)) {
            isValid = true;
            Customer user = Customer.getData(noTelp); 
            SingletonManager.getInstance().setUser(user);
            loginView.showMessage("Login berhasil! Selamat datang, " + user.getName());
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
