package main.src;

import java.awt.Color;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AppFunctions extends LogPage {
    static ImageIcon book;

    /**
     * Sets the general style of a button by adding ActionListener, set Font,
     * setFocusable, and the background color.
     * 
     * @param Buttons
     */
    void setStyle(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
            buttons[i].setFont(myFont);
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.white);
        }
    }

    /**
     * Adds the Jbutton array into the a panel
     * 
     * @param panel
     * @param buttons
     */
    void addButton(JPanel panel, JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            panel.add(buttons[i]);
            panel.repaint();
        }
    }

    /**
     * Checks to see if JButton text is empty(" "), if it is, it will remove
     * ActionListener and make button "invsible". The button will not be visible in
     * the frame.
     * 
     * @param buttons
     */
    void isEmpty(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals(" ")) {
                buttons[i].removeActionListener(this);
                buttons[i].setOpaque(false);
                buttons[i].setContentAreaFilled(false);
                buttons[i].setBorderPainted(false);
                buttons[i].setIcon(null);;
            }
        }
    }

    /**
     * Resets empty buttons to original style.
     * 
     * @param buttons
     */
    void reset(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals(" ")) {
                buttons[i].addActionListener(this);
                buttons[i].setOpaque(true);
                buttons[i].setContentAreaFilled(true);
                buttons[i].setBorderPainted(true);
            }
        }
    }

    /**
     * 
     * Sets the Button with no text
     * 
     * @param button
     */
    void setButtons(JButton[] button) {
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton();
        }
    }

    /**
     * 
     * Removes JButtons from a given JPanel.
     * 
     * @param panel
     * @param Added_Buttons
     */
    void removeButtons(JPanel panel, JButton[] Added_Buttons) {
        for (int i = 0; i < Added_Buttons.length; i++) {
            panel.remove(Added_Buttons[i]);
            panel.repaint();
        }
    }

    /**
     * 
     * Gets and sets the buttons for the BookStore and the Library. Uses count and
     * pgmax to switch through each page
     * 
     * @param count
     * @param pgmax
     * @param buttons
     * @param tmpBooks
     * @throws SQLException
     */
    void getAvailableTitles(int count, int pgmax, JButton[] buttons, String[] tmpBooks, String bookID)
            throws SQLException {
        for (int i = 0; i < buttons.length; i++) {
            tmpBooks[i] = " ";
        }
        String LibQuery = "SELECT * FROM Library limit " + count + "," + pgmax + "";
        Connect.resultset = Connect.statement.executeQuery(LibQuery);
        for (int i = 0; i < buttons.length; i++, count++) {
            if (Connect.resultset.next()) {
                tmpBooks[i] = Connect.resultset.getString("Title");
                bookID = Connect.resultset.getString("BookID");
                String bookImg = "app/src/main/resources/images/" + bookID + ".jpeg";
                book = new ImageIcon(bookImg);
                Image image = book.getImage();
                Image newImage = image.getScaledInstance(150, 162, java.awt.Image.SCALE_SMOOTH);
                book = new ImageIcon(newImage);
                buttons[i].setIcon(book);
                buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
            }
            buttons[i].setText(tmpBooks[i]);
        }
    }

    void getOwnedTitles(int count, int pgmax, JButton[] buttons, String[] tmpBooks, String bookID) throws SQLException {
        for (int i = 0; i < buttons.length; i++) {
            tmpBooks[i] = " ";
        }
        String ownedQuery = "SELECT * FROM Library INNER JOIN Orders ON Orders.BookID = Library.BookID WHERE UserID = ? LIMIT ?,?";
        Connect.statement = Connect.c.prepareStatement(ownedQuery);
        Connect.statement.setString(1, userID);
        Connect.statement.setInt(2, count);
        Connect.statement.setInt(3, pgmax);
        Connect.resultset = Connect.statement.executeQuery();
        for (int i = 0; i < buttons.length; i++, count++) {
            if (Connect.resultset.next()) {
                tmpBooks[i] = Connect.resultset.getString("Title");
                bookID = Connect.resultset.getString("BookID");
                String bookImg = "app/src/main/resources/images/" + bookID + ".jpeg";
                book = new ImageIcon(bookImg);
                Image image = book.getImage();
                Image newImage = image.getScaledInstance(150, 162, java.awt.Image.SCALE_SMOOTH);
                book = new ImageIcon(newImage);
                buttons[i].setIcon(book);
            }
            buttons[i].setText(tmpBooks[i]);
        }
    }

    void getLink() {
        try {
            String ownedQuery = "SELECT  FROM Library INNER JOIN Orders ON Orders.BookID = Library.BookID WHERE UserID = ? LIMIT ?,?";
            Connect.statement = Connect.c.prepareStatement(ownedQuery);
            Connect.statement.setString(1, userID);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    void firstSet(JButton[] buttons1, JButton[] buttons2) {
        buttons1[0] = new JButton("Back");
        buttons1[1] = new JButton("Main Menu");
        buttons1[2] = new JButton("Next");
        setStyle(buttons1);
        setButtons(buttons2);
        setStyle(buttons2);
    }

    public String change(String change, String current, JLabel text, JLabel msg, String word) throws SQLException {
        try {
            Connect.query = "Select UserID from Users where binary " + word + "=?";
            Connect.statement = Connect.c.prepareStatement(Connect.query);
            Connect.statement.setString(1, change);
            Connect.resultset = Connect.statement.executeQuery();
            if (change.equals(current)) {
                msg.setText("New " + word + " cannot be the same");
                text.setText("Please try again:");
            } else if (change.isEmpty() || change.isBlank()) {
                msg.setText(word + " cannot be blank");
                text.setText("Please try again");
            } else if (Connect.resultset.next()) {
                if (word == "Username") {
                    msg.setText(word + " already exist");
                    text.setText("Please try again:");
                }
            } else {
                Connect.query = "UPDATE Users SET " + word + " = '" + change + "' WHERE " + word + "= '" + current
                        + "'";
                Connect.statement = Connect.c.prepareStatement(Connect.query);
                Connect.statement.executeUpdate();
                msg.setText(word + " Changed");
                text.setText("<HTML><CENTER>Username successfully changed to: <BR>" + change + "<CENTER><HTML>");
                current = change;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return current;
    }

    void removeAction(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].removeActionListener(this);
        }
    }

    void addAction(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        }
    }

}
