package main.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrames extends AppFunctions {
    static JFrame MainFrame; // Main Frame
    static JFrame popoutFrame; // Main popout Frame

    static String title;
    static String author;
    static String year;
    static String price;
    static String bookID;
    static String Purchase_Date;

    // Main Panels
    static JPanel Top_Panel;
    static JPanel Left_Panel;
    static JPanel Center_Panel;
    static JPanel Right_Panel;
    static JPanel Bottom_Panel;

    // Main Header Message
    static JLabel Header_Message;

    // Main Menu Buttons
    static JButton[] MainMenu_Buttons = new JButton[4];

    // Directional Buttons

    static JLabel popLabel;
    static JPanel popCPanel;
    static JPanel popTPanel;
    static JPanel popBPanel;
    static JButton close;

    static JButton[] popButtons = new JButton[2];

    static protected int count, pgmax, max;

    void MainAppFrames() {

        // Main Buttons
        MainMenu_Buttons[0] = new JButton("Library");
        MainMenu_Buttons[1] = new JButton("Book Store");
        MainMenu_Buttons[2] = new JButton("Settings");
        MainMenu_Buttons[3] = new JButton("Log Out");

        // Main Application Frame
        MainFrame = new JFrame("E-Book Reader");
        MainFrame.setIconImage(EIcon.getImage());
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(1000, 600);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setLayout(new BorderLayout());
        MainFrame.setResizable(false);

        // Main Header Label
        Header_Message = new JLabel();
        Header_Message.setIcon(EIcon);
        Header_Message.setBounds(0, 0, 1000, 125);
        Header_Message.setText("Welcome " + username);
        Header_Message.setFont(new Font("Times New Roman", Font.ITALIC, 48));
        Header_Message.setForeground(Color.black);
        Header_Message.setHorizontalAlignment(JLabel.LEFT);
        Header_Message.setVerticalAlignment(JLabel.CENTER);
        Header_Message.setBackground(Color.white);
        Header_Message.setOpaque(true);

        // Main Top Panel
        Top_Panel = new JPanel();
        Top_Panel.add(Header_Message);
        Top_Panel.setBackground(Color.white);
        Top_Panel.setPreferredSize(new Dimension(100, 125));
        Top_Panel.setLayout(null);

        // Main Left Panel
        Left_Panel = new JPanel();
        Left_Panel.setBackground(Color.white);
        Left_Panel.setPreferredSize(new Dimension(150, 100));

        // Main Center Panel
        Center_Panel = new JPanel();
        Center_Panel.setBackground(Color.white);
        Center_Panel.setPreferredSize(new Dimension(50, 50));
        Center_Panel.setLayout(new GridLayout(2, 3, 10, 10));
        setStyle(MainMenu_Buttons);
        addButton(Center_Panel, MainMenu_Buttons);

        // Main Right Panel
        Right_Panel = new JPanel();
        Right_Panel.setBackground(Color.white);
        Right_Panel.setPreferredSize(new Dimension(150, 0));

        // Main Bottom Panel
        Bottom_Panel = new JPanel();
        Bottom_Panel.setBackground(Color.white);
        Bottom_Panel.setPreferredSize(new Dimension(150, 100));
        Bottom_Panel.setLayout(null);

        // Added all panels on to the Main Frame
        MainFrame.add(Top_Panel, BorderLayout.NORTH);
        MainFrame.add(Left_Panel, BorderLayout.WEST);
        MainFrame.add(Center_Panel, BorderLayout.CENTER);
        MainFrame.add(Right_Panel, BorderLayout.EAST);
        MainFrame.add(Bottom_Panel, BorderLayout.SOUTH);

        popoutFrame = new JFrame("E-Book Reader");
        popoutFrame.setIconImage(EIcon.getImage());
        popoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        popoutFrame.setSize(425, 300);
        popoutFrame.setLocationRelativeTo(null);
        popoutFrame.setResizable(false);

        /** Top Text & Text */
        JLabel Message = new JLabel();
        Message.setText("Confirm your Purchase:");
        Message.setFont(myFont);

        popTPanel = new JPanel();
        popTPanel.setBackground(Color.white);
        popTPanel.setPreferredSize(new Dimension(75, 50));
        popTPanel.add(Message);

        popLabel = new JLabel();
        popLabel.setFont(myFont);

        // Center Panel
        popCPanel = new JPanel();
        popCPanel.setBackground(Color.white);
        popCPanel.setPreferredSize(new Dimension(50, 50));
        popCPanel.setLayout(new GridBagLayout());// maybe change
        popCPanel.add(popLabel);

        popButtons[0] = new JButton("Not Now");
        popButtons[1] = new JButton("Confirm");
        // setStyle(popButtons);

        // Bottom Panel
        popBPanel = new JPanel();
        popBPanel.setBackground(Color.white);
        popBPanel.setPreferredSize(new Dimension(50, 60));
        popBPanel.setLayout(new GridLayout(1, 2, 10, 0));

        popoutFrame.add(popTPanel, BorderLayout.NORTH);
        popoutFrame.add(popCPanel, BorderLayout.CENTER);
        popoutFrame.add(popBPanel, BorderLayout.SOUTH);

        close = new JButton("Close");
        close.setFont(myFont);
        close.setFocusable(false);
        close.setBackground(Color.white);

        MainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MainMenu_Buttons[0]) {
            Center_Panel.removeAll();
            Library library = new Library();
            try {
                library.libButtons();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == MainMenu_Buttons[1]) {
            Center_Panel.removeAll();
            BookStore bookStore = new BookStore();
            try {
                bookStore.storeButtons();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == MainMenu_Buttons[2]) {
            Center_Panel.removeAll();
            Settings settings = new Settings();
            try {
                settings.setSettButtons();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == MainMenu_Buttons[3]) {
            MainFrame.dispose();
            try {
                Connect.c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}
