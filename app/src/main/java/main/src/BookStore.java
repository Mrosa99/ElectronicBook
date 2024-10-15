package main.src;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BookStore extends MainFrames {
    // Displayed Books
    static protected JButton[] Available_Books = new JButton[10];

    static protected String[] tmpBooks = new String[10];
    static JButton[] Directional_Buttons = new JButton[3];

    ImageIcon book;

    void BookStore_MenuPanel() {
        // General Frame settings
        Header_Message.setText("Book Store");
        Center_Panel.setLayout(new GridLayout(2, 5, 10, 10));
        Bottom_Panel.setLayout(new GridLayout(1, 3, 20, 0));

        // Button Sets
        addButton(Center_Panel, Available_Books);
        addButton(Bottom_Panel, Directional_Buttons);

    }

    void storeButtons() throws SQLException {
        if (tmpBooks[0] == null) {
            firstSet(Directional_Buttons, Available_Books);
        }
        getStoreCount();
    }

    void getStoreCount() throws SQLException {

        String maxquery = "SELECT COUNT(*) from library";
        Connect.resultset = Connect.statement.executeQuery(maxquery);
        if (Connect.resultset.next()) {
            max = Connect.resultset.getInt(1);
        }
        getAvailableTitles(count = 0, pgmax = 10, Available_Books, tmpBooks, bookID);
        BookStore_MenuPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Directional_Buttons[0]) {
            if (count <= 0) {
                Directional_Buttons[0].removeActionListener(this);
                Directional_Buttons[0].addActionListener(this);

            } else {
                reset(Available_Books);
                try {
                    getAvailableTitles(count -= 10, pgmax -= 10, Available_Books, tmpBooks, bookID);
                    isEmpty(Available_Books);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource() == Directional_Buttons[1]) {
            reset(Available_Books);
            Header_Message.setText("Main Menu");
            removeButtons(Center_Panel, Available_Books);
            removeButtons(Bottom_Panel, Directional_Buttons);
            addButton(Center_Panel, MainMenu_Buttons);

        } else if (e.getSource() == Directional_Buttons[2]) {
            if (pgmax >= max) {
                Directional_Buttons[2].removeActionListener(this);
                Directional_Buttons[2].addActionListener(this);
            } else {
                try {
                    getAvailableTitles(count += 10, pgmax += 10, Available_Books, tmpBooks, bookID);
                    isEmpty(Available_Books);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        for (int i = 0; i < Available_Books.length; i++) {
            if (e.getSource() == Available_Books[i]) {
                removeButtons(Center_Panel, Available_Books);
                removeButtons(Bottom_Panel, Directional_Buttons);
                try {
                    Connect.query = "SELECT * FROM library where title = ?";
                    Connect.statement = Connect.c.prepareStatement(Connect.query);
                    Connect.statement.setString(1, Available_Books[i].getText());
                    Connect.resultset = Connect.statement.executeQuery();
                    if (Connect.resultset.next()) {
                        bookID = Connect.resultset.getString("bookID");
                        title = Connect.resultset.getString("title");
                        author = Connect.resultset.getString("author");
                        year = Connect.resultset.getString("published");
                        price = Connect.resultset.getString("price");
                        String bookImg = "app/src/main/java/main/src/images/" + bookID + ".jpeg";
                        book = new ImageIcon(bookImg);
                        Image image = book.getImage();
                        Image newImage = image.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
                        book = new ImageIcon(newImage);
                        BookDets.BookDisc.setIcon(book);
                    }
                    BookDets dets = new BookDets();
                    dets.setInfoButtons();
                    Center_Panel.add(BookDets.BookDisc);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
