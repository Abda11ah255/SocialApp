package app.gui;


import app.model.User;
import app.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login / Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        JLabel message = new JLabel("");

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);
        frame.add(registerBtn);
        frame.add(message);

        AuthService authService = new AuthService();

        loginBtn.addActionListener(e -> {
            try {
                User u = authService.login(userField.getText(), new String(passField.getPassword()));
                if (u != null) {
                    frame.dispose();
                    new Dashboard(u);
                } else {
                    message.setText("❌ Login failed!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                message.setText("Error: " + ex.getMessage());
            }
        });

        registerBtn.addActionListener(e -> {
            try {
                boolean ok = authService.register(userField.getText(), "test@gmail.com", Arrays.toString(passField.getPassword()));
                if (ok) {
                    message.setText("✅ Register success!");
                } else {
                    message.setText("❌ User already exists.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                message.setText("Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}