package app.gui;

import javax.swing.*;
import java.awt.*;
import app.model.User;
public class Dashboard extends JFrame {
    private User loggedInUser;
    private JPanel contentPanel;

    public Dashboard(User user) {
        this.loggedInUser = user;

        // Window setup
        setTitle("Dashboard - " + user.getUsername());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Top bar with welcome text
        JLabel welcome = new JLabel("Welcome, " + user.getUsername() + "!", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcome, BorderLayout.NORTH);

        // 🔹 Left menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 5, 5));
        JButton profileBtn = new JButton("Profile");
        JButton friendsBtn = new JButton("Friends");
        JButton postsBtn = new JButton("Posts");
        JButton logoutBtn = new JButton("Logout");

        menuPanel.add(profileBtn);
        menuPanel.add(friendsBtn);
        menuPanel.add(postsBtn);
        menuPanel.add(logoutBtn);

        add(menuPanel, BorderLayout.WEST);

        // 🔹 Center content area
        contentPanel = new JPanel();
        contentPanel.add(new JLabel("Select an option from the left menu"));
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
