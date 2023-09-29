package main.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Library extends MainFrames {
	// OwnedBook Panels
	static JPanel OwnedCenter;
	static JPanel OwnedBottom;

	static protected JButton[] Purchased_Books = new JButton[10];
	static protected JButton[] PDirectional = new JButton[3];
	static protected String[] tempBooks = new String[10];

	static JLabel ownBookInfo;
	static JLabel none;

	static String link;

	/**
	 * need to get all the owned books from the database
	 */
	void LibraryFrame() {
		Header_Message.setText("Library");
		Center_Panel.setLayout(new GridLayout(2, 5, 10, 10));
		Bottom_Panel.setLayout(new GridLayout(1, 3, 20, 0));

		addButton(Center_Panel, Purchased_Books);
		addButton(Bottom_Panel, PDirectional);

		ownBookInfo = new JLabel();
		ownBookInfo.setFont(myFont);
		ownBookInfo.setPreferredSize(new Dimension(675, 350));
		ownBookInfo.setIconTextGap(30);
		ownBookInfo.setLayout(new FlowLayout());
		ownBookInfo.setBackground(Color.white);
		ownBookInfo.setOpaque(true);
		ownBookInfo.setHorizontalAlignment(JLabel.CENTER);

		if (Purchased_Books[0].getText() == " ") {
			Center_Panel.setLayout(new GridBagLayout());
			none = new JLabel();
			none.setFont(myFont);
			none.setText("No Books Purched");
			none.setPreferredSize(new Dimension(675, 350));
			none.setLayout(new FlowLayout());
			none.setBackground(Color.white);
			none.setOpaque(true);
			Center_Panel.removeAll();
			Center_Panel.add(none);
		}

	}

	void libButtons() throws SQLException {
		if (tempBooks[0] == null) {
			firstSet(PDirectional, Purchased_Books);
		}
		getLibCount();
	}

	void getLibCount() throws SQLException {
		String maxquery = "select COUNT(*) from Orders Where userID ='" + userID + "'";
		Connect.resultset = Connect.statement.executeQuery(maxquery);
		if (Connect.resultset.next()) {
			max = Connect.resultset.getInt(1);
		}
		getOwnedTitles(count = 0, pgmax = 10, Purchased_Books, tempBooks,bookID);
		isEmpty(Purchased_Books);
		LibraryFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PDirectional[0]) {
			if (count <= 0) {
				PDirectional[0].removeActionListener(this);
				PDirectional[0].addActionListener(this);

			} else {
				reset(Purchased_Books);
				try {
					getOwnedTitles(count -= 10, pgmax -= 10, Purchased_Books, tempBooks,bookID);
					isEmpty(Purchased_Books);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == PDirectional[1]) {
			reset(Purchased_Books);
			Center_Panel.setLayout(new GridLayout(2, 5, 10, 10));
			Header_Message.setText("Main Menu");
			Center_Panel.removeAll();
			removeButtons(Bottom_Panel, PDirectional);
			addButton(Center_Panel, MainMenu_Buttons);

		} else if (e.getSource() == PDirectional[2]) {
			if (pgmax >= max) {
				PDirectional[2].removeActionListener(this);
				PDirectional[2].addActionListener(this);
			} else {
				try {
					getOwnedTitles(count += 10, pgmax += 10, Purchased_Books, tempBooks,bookID);
					isEmpty(Purchased_Books);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		for (int i = 0; i < Purchased_Books.length; i++) {
			if (e.getSource() == Purchased_Books[i]) {
				removeButtons(Center_Panel, Purchased_Books);
				removeButtons(Bottom_Panel, PDirectional);
				try {
					Connect.query = "select * from Library where Title = ?";
					Connect.statement = Connect.c.prepareStatement(Connect.query);
					Connect.statement.setString(1, Purchased_Books[i].getText());
					Connect.resultset = Connect.statement.executeQuery();
					if (Connect.resultset.next()) {
						bookID = Connect.resultset.getString("BookID");
						title = Connect.resultset.getString("Title");
						author = Connect.resultset.getString("Author");
						year = Connect.resultset.getString("Published");
						link = Connect.resultset.getString("Link");
						String bookImg = "app/src/main/java/main/src/images/" + bookID + ".jpeg";
						book = new ImageIcon(bookImg);
						Image image = book.getImage();
						Image newImage = image.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
						book = new ImageIcon(newImage);
						ownBookInfo.setIcon(book);
					}
					ownBookInfo.setText(
							"<HTML>Title: " + title + "<BR>Author: " + author + " <BR>Published: " + year + "</HTML>");
					BookDets dets = new BookDets();
					dets.setInfoButtons();
					BookDets.DetailsButtons[2].setText("Read Now");
					Center_Panel.removeAll();
					Center_Panel.add(ownBookInfo);

				}

				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
