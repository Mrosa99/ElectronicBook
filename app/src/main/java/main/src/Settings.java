package main.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends MainFrames {

    static JFrame confirmFrame;
    static JLabel settingsText;
    static JLabel settings_msg;
    static JPanel settingsCenter;
    static JPanel settingsBottom;
    protected JTextField tmpNewUser;
    protected JTextField tmpNewPass;
    protected static String newUsername = null;
    protected static String newPassword = null;

    static protected JButton[] Settings_Buttons = new JButton[3];
    static protected JButton[] main = new JButton[1];

    static private JButton[] confirmButtons = new JButton[2];

    void SettingsFrame() {
        Header_Message.setText("Settings");
        Center_Panel.setLayout(new GridBagLayout());
        Bottom_Panel.setLayout(new GridLayout(1, 3, 20, 0));

        addButton(Center_Panel, Settings_Buttons);
        addButton(Bottom_Panel, main);

    }

    void setSettButtons() throws SQLException {
        if (Settings_Buttons[0] == null) {
            Settings_Buttons[0] = new JButton("Change Username");
            Settings_Buttons[1] = new JButton("Change Password");
            Settings_Buttons[2] = new JButton("Delete Account");
            main[0] = new JButton("Main Menu");
            setStyle(main);
            setStyle(Settings_Buttons);
        }
        SettingsFrame();
    }

    void confirm_frame() {
        confirmFrame = new JFrame("E-Book Reader Settings");
        confirmFrame.setIconImage(EIcon.getImage());
        confirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmFrame.setSize(425, 300);
        confirmFrame.setLocationRelativeTo(null);
        confirmFrame.setResizable(false);

        settings_msg = new JLabel();
        settings_msg.setText("Username Change");
        settings_msg.setFont(myFont);

        JPanel settingsTop = new JPanel();
        settingsTop.setBackground(Color.white);
        settingsTop.setPreferredSize(new Dimension(75, 50));
        settingsTop.add(settings_msg);

        settingsText = new JLabel();
        settingsText.setText("Confirm to change username.");
        settingsText.setFont(myFont);
        settingsText.setPreferredSize(new Dimension(675, 350));
        settingsText.setLayout(new FlowLayout());
        settingsText.setBackground(Color.white);
        settingsText.setOpaque(true);

        settingsCenter = new JPanel();
        settingsCenter.setBackground(Color.white);
        settingsCenter.setPreferredSize(new Dimension(50, 50));
        settingsCenter.setLayout(new GridBagLayout());// maybe change
        settingsCenter.add(settingsText);

        settingsBottom = new JPanel();
        settingsBottom.setBackground(Color.white);
        settingsBottom.setPreferredSize(new Dimension(50, 60));
        settingsBottom.setLayout(new GridLayout(1, 2, 10, 0));
        addButton(settingsBottom, confirmButtons);

        confirmFrame.add(settingsTop, BorderLayout.NORTH);
        confirmFrame.add(settingsCenter, BorderLayout.CENTER);
        confirmFrame.add(settingsBottom, BorderLayout.SOUTH);

        confirmFrame.setVisible(true);
    }

    void confirmButtons() {
        if (confirmButtons[0] == null) {
            confirmButtons[0] = new JButton("Not Now");
            confirmButtons[1] = new JButton("Confirm");
            setStyle(confirmButtons);
        }
        confirm_frame();
    }

    void new_name() {
        settingsCenter.setLayout(new GridLayout(0, 1));
        settingsText.setText("Please enter your new username:");

        settingsText.setHorizontalAlignment(JLabel.CENTER);
        tmpNewUser = new JTextField();
        tmpNewUser.setPreferredSize(new Dimension(250, 40));
        settingsCenter.add(tmpNewUser);

    }

    void userChange() throws SQLException {
        newUsername = tmpNewUser.getText();
        username = change(newUsername, username, settingsText, settings_msg, "Username");
        String finish = settings_msg.getText();
        if (finish.equals("Username Changed")) {
            settingsCenter.remove(tmpNewUser);
            settingsBottom.remove(confirmButtons[1]);
            confirmButtons[0].setText("Close Window");
        }
    }

    void new_password() {
        settingsCenter.setLayout(new GridLayout(0, 1));
        settingsText.setText("Please enter your new password:");

        settingsText.setHorizontalAlignment(JLabel.CENTER);
        tmpNewPass = new JTextField();
        tmpNewPass.setPreferredSize(new Dimension(250, 40));
        settingsCenter.add(tmpNewPass);

    }

    void passChange() throws SQLException {
        newPassword = tmpNewPass.getText();
        password = change(newPassword, password, settingsText, settings_msg, "Password");
        String finish = settings_msg.getText();
        if (finish.equals("Password Changed")) {
            settingsCenter.remove(tmpNewPass);
            settingsBottom.remove(confirmButtons[1]);
            confirmButtons[0].setText("Close Window");
        }
    }

    void deleteUser() throws SQLException {
        settingsText.setText("Account Deleted");
        Connect.query = "DELETE FROM users WHERE users.userID = ?";
        Connect.statement = Connect.c.prepareStatement(Connect.query);
        Connect.statement.setString(1, userID);
        Connect.statement.executeUpdate();
        settingsBottom.remove(confirmButtons[1]);
        confirmButtons[0].setText("Close App");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == main[0]) {
            Header_Message.setText("Main Menu");
            removeButtons(Center_Panel, Settings_Buttons);
            removeButtons(Bottom_Panel, main);
            Center_Panel.setLayout(new GridLayout(2, 3, 10, 10));
            addButton(Center_Panel, MainMenu_Buttons);

        } else if (e.getSource() == Settings_Buttons[0]) {
            removeAction(main);
            removeAction(Settings_Buttons);
            confirmButtons();
            confirmFrame.setTitle("Username Change");
        } else if (e.getSource() == Settings_Buttons[1]) {
            removeAction(main);
            removeAction(Settings_Buttons);
            confirmButtons();
            confirmFrame.setTitle("Password Change");
            settingsText.setText("Confirm to change password.");
            settings_msg.setText("Password Change");

        } else if (e.getSource() == Settings_Buttons[2]) {
            removeAction(main);
            removeAction(Settings_Buttons);
            confirmButtons();
            confirmFrame.setTitle("Delete Account");
            settingsText.setText("Confirm to delete account.");
            settings_msg.setText("Delete Account");
        } else if (e.getSource() == confirmButtons[0]) {
            String tmp = confirmButtons[0].getText();
            if (tmp == "Close App") {
                confirmFrame.dispose();
                MainFrame.dispose();
                try {
                    Connect.c.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                addAction(Settings_Buttons);
                addAction(main);
                confirmButtons[0].setText("Not Now");
                confirmButtons[1].setText("Confirm");
                confirmFrame.dispose();
            }
        } else if (e.getSource() == confirmButtons[1]) {
            String Sname = confirmButtons[1].getText();
            String msg = confirmFrame.getTitle();
            if (msg == "Username Change") {
                if (Sname == "Confirm") {
                    new_name();
                    confirmButtons[1].setText("Next");
                } else if (Sname == "Next") {
                    try {
                        userChange();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if (msg == "Password Change") {
                if (Sname == "Confirm") {
                    new_password();
                    confirmButtons[1].setText("Next");
                } else if (Sname == "Next") {
                    try {
                        passChange();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if (msg == "Delete Account") {
                if (Sname == "Confirm") {
                    try {
                        deleteUser();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

    }
}
