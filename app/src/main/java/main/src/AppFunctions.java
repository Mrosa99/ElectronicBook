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
     * Sets a consistent style for an array of JButton objects.
     * 
     * This method configures each JButton in the provided array with common
     * stylistic attributes
     * to ensure a cohesive appearance and behavior throughout the user interface.
     * 
     * @param buttons An array of JButton objects to be styled.
     * 
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
     * Adds an array of JButton components to a JPanel. This method iterates through
     * the provided array of buttons and adds each button to the specified JPanel.
     * After adding each button, the panel is repainted to reflect the changes.
     *
     * @param panel   The JPanel to which the buttons will be added.
     * @param buttons An array of JButton components to be added to the panel.
     */
    void addButton(JPanel panel, JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            panel.add(buttons[i]);
            panel.repaint();
        }
    }

    /**
     * Checks and processes an array of JButton components to identify and handle
     * buttons with an empty text value (" "). If a button is found to be empty,
     * it removes the ActionListener, sets its appearance to be transparent, and
     * removes any existing icon.
     *
     * @param buttons An array of JButton components to be checked for emptiness.
     */
    void isEmpty(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals(" ")) {
                buttons[i].removeActionListener(this);
                buttons[i].setOpaque(false);
                buttons[i].setContentAreaFilled(false);
                buttons[i].setBorderPainted(false);
                buttons[i].setIcon(null);
                ;
            }
        }
    }

    /**
     * Resets specific stylistic attributes for JButton objects with empty text.
     * 
     * This method selectively resets stylistic attributes, such as ActionListener
     * attachment,
     * background opacity, content area fill, and border painting, for JButton
     * objects in the
     * provided array that have an empty text.
     * 
     * @param buttons An array of JButton objects to be reset.
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
     * Initializes an array of JButton components with no text.
     * Each button in the array is created as a new JButton instance without any
     * text.
     *
     * @param buttons An array of JButton components to be initialized.
     */
    void setButtons(JButton[] button) {
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton();
        }
    }

    /**
     * Removes an array of JButton components from a specified JPanel.
     * Each button in the array is removed from the panel, and the panel is
     * repainted
     * to reflect the changes in the user interface.
     *
     * @param panel        The JPanel from which buttons will be removed.
     * @param addedButtons An array of JButton components to be removed from the
     *                     panel.
     */
    void removeButtons(JPanel panel, JButton[] Added_Buttons) {
        for (int i = 0; i < Added_Buttons.length; i++) {
            panel.remove(Added_Buttons[i]);
            panel.repaint();
        }
    }

    /**
     * Retrieves and sets information for buttons representing available book titles
     * in the BookStore or Library.
     * Utilizes the given count and pgmax parameters to navigate through pages of
     * results.
     *
     * @param count    The starting index for retrieving book titles from the
     *                 database.
     * @param pgmax    The maximum number of book titles to retrieve in a single
     *                 page.
     * @param buttons  An array of JButton components representing book titles.
     * @param tmpBooks An array to temporarily store book titles retrieved from the
     *                 database.
     * @param bookID   A variable to store the BookID of the currently processed
     *                 book.
     * @throws SQLException If a database access error occurs or the SQL execution
     *                      fails.
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
                String bookImg = "app/src/main/java/main/src/images/" + bookID + ".jpeg";
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

    /**
     * 
     * Gets the titles owned by the user in the database
     * 
     * @param counnt
     * @param pgmax
     * @param buttons
     * @param tmpBooks
     * @param bookID
     * @throws SQLException
     */
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
                String bookImg = "app/src/main/java/main/src/images/" + bookID + ".jpeg";
                book = new ImageIcon(bookImg);
                Image image = book.getImage();
                Image newImage = image.getScaledInstance(150, 162, java.awt.Image.SCALE_SMOOTH);
                book = new ImageIcon(newImage);
                buttons[i].setIcon(book);
            }
            buttons[i].setText(tmpBooks[i]);
        }
    }

    /**
     * Retrieves relevant data from the database based on the provided user ID.
     * The method constructs a parameterized SQL query involving an INNER JOIN
     * between the 'Library' and 'Orders' tables, filtering results by user ID
     * and utilizing a LIMIT clause for pagination.
     *
     * @throws SQLException if a database access error occurs or the SQL execution
     *                      fails.
     */
    void getLink() {
        try {
            String ownedQuery = "SELECT  FROM Library INNER JOIN Orders ON Orders.BookID = Library.BookID WHERE UserID = ? LIMIT ?,?";
            Connect.statement = Connect.c.prepareStatement(ownedQuery);
            Connect.statement.setString(1, userID);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 
     * Sets the main navigation buttons for the application.
     * 
     * @param buttons1
     * @param buttons2
     *
     */
    void firstSet(JButton[] buttons1, JButton[] buttons2) {
        buttons1[0] = new JButton("Back");
        buttons1[1] = new JButton("Main Menu");
        buttons1[2] = new JButton("Next");
        setStyle(buttons1);
        setButtons(buttons2);
        setStyle(buttons2);
    }

    /**
     * 
     * Method to change password/username while checking for same string, blank
     * inputs, and if username exists in database already
     * 
     * @param change
     * @param current
     * @param text
     * @param msg
     * @param word
     * @throws SQLException
     * 
     */
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

    /**
     *
     * Removes the action listener to a button in the application.
     * 
     * @param buttons
     */
    void removeAction(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].removeActionListener(this);
        }
    }

    /**
     * 
     * Adds the action listener to a button in the application
     * 
     * @param buttons
     *
     */
    void addAction(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        }
    }

}
