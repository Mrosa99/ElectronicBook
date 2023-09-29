package main.src;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BookDets extends BookStore {
    static JLabel BookDisc = new JLabel();

    static protected JButton[] DetailsButtons = new JButton[3];

    void Books() {
        // General Frame settings
        Header_Message.setText(title);
        Center_Panel.setLayout(new FlowLayout());
        Bottom_Panel.setLayout(new GridLayout(1, 3, 20, 0));

        // Book Description
        BookDisc.setText(
                "<HTML>Title: " + title + "<BR>Author: " + author + " <BR>Published: " + year + " <BR>Price: $"
                        + price + "</HTML>");
        BookDisc.setFont(myFont);
        BookDisc.setPreferredSize(new Dimension(675, 350));
        BookDisc.setIconTextGap(30);
        BookDisc.setLayout(new GridBagLayout());
        BookDisc.setBackground(Color.white);
        BookDisc.setOpaque(true);
        BookDisc.setHorizontalAlignment(JLabel.CENTER);

        // Button Sets
        Center_Panel.add(BookDisc);
        addButton(Bottom_Panel, DetailsButtons);
        addButton(popBPanel, popButtons);

    }

    void setInfoButtons() throws SQLException {
        if (DetailsButtons[0] == null) {
            DetailsButtons[0] = new JButton("Back");
            DetailsButtons[1] = new JButton("Main Menu");
            DetailsButtons[2] = new JButton("Buy");
            setStyle(DetailsButtons);
            setStyle(popButtons);
            close.addActionListener(this);
        }
        BookDets.DetailsButtons[2].setText("Buy");
        Books();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = BookDets.DetailsButtons[2].getText();
        Library lib = new Library();
        if (e.getSource() == DetailsButtons[0]) {
            Center_Panel.removeAll();
            removeButtons(Bottom_Panel, DetailsButtons);
            if (name == "Read Now") {
                lib.LibraryFrame();
            } else {
                BookStore_MenuPanel();
            }
        } else if (e.getSource() == DetailsButtons[1]) {
            if (name == "Read Now") {
                reset(Library.Purchased_Books);
            } else {
                reset(Available_Books);
            }
            Header_Message.setText("Main Menu");
            Center_Panel.setLayout(new GridLayout(2, 3, 10, 10));

            Center_Panel.removeAll();
            removeButtons(Bottom_Panel, DetailsButtons);

            addButton(Center_Panel, MainMenu_Buttons);

        } else if (e.getSource() == DetailsButtons[2]) {
            if (name == "Read Now") {
                try {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI(Library.link));
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                removeAction(DetailsButtons);
                popLabel.setText(
                        "<HTML><Center>Title:" + title + "<BR>Author:  " + author + "<BR>Price  $" + price
                                + "</Center><HTML>");
                popoutFrame.setVisible(true);
            }
        } else if (e.getSource() == popButtons[0]) {
            addAction((DetailsButtons));
            popoutFrame.dispose();
        } else if (e.getSource() == popButtons[1]) {
            try {
                String checkPurchase = "Select * from Orders where UserID = '" + userID + "' and BookID = '" + bookID
                        + "'";
                Connect.statement = Connect.c.prepareStatement(checkPurchase);
                Connect.resultset = Connect.statement.executeQuery(checkPurchase);
                if (Connect.resultset.next()) {
                    Purchase_Date = Connect.resultset.getString("OrderDate");
                    popLabel.setText(
                            "<HTML><Center>Book Already purchased on: <BR>" + Purchase_Date + "</Center><HTML>");
                    removeButtons(popBPanel, popButtons);
                    popBPanel.add(close);
                } else {
                    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                    String purchase_query = "INSERT INTO Orders(BookID, UserID, OrderDate) VALUES (?, ?, ?)";
                    Connect.statement = Connect.c.prepareStatement(purchase_query);
                    Connect.statement.setString(1, bookID);
                    Connect.statement.setString(2, userID);
                    Connect.statement.setDate(3, sqlDate);
                    Connect.statement.executeUpdate();
                    popLabel.setText(
                            "<HTML><Center>Thank you for your purchase.<BR> Book has been added to your library.</Center><HTML>");
                    for (int i = 0; i < 2; i++) {
                        popBPanel.remove(popButtons[i]);
                        popBPanel.repaint();
                    }
                    popBPanel.add(close);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == close) {
            addAction(DetailsButtons);
            popBPanel.remove(close);
            popoutFrame.dispose();
        }
    }
}
