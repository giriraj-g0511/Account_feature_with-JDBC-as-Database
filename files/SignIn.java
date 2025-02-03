package newPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.sql.ResultSet;

public class SignIn extends JFrame {
	
	JTextField usernameField, mobileNumberField;
	JCheckBox checkBox;
	JButton signInButton;

	class setUpUI {
		setUpUI() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public SignIn() {
		setUpFrame();
		initializeComponents();
		addComponents();
		new setUpUI();
	}

	private void setUpFrame() {
		setSize(1366, 768);
		setTitle("SignIn into your LinkedIn Account");
		setVisible(true);
		setContentPane(createBackgroundImage());
	}

	private JPanel createBackgroundImage() {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				ImageIcon icon = new ImageIcon(("C:\\Users\\shree\\Downloads\\linkedinBckground.jpg"));
				Image image = icon.getImage();

				double panelWidth = getWidth();
				double panelHeight = getHeight();

				double imageHeight = image.getHeight(this);
				double imageWidth = image.getWidth(this);
				double scale = Math.max(panelWidth / imageWidth, panelHeight / imageHeight);

				int scaleWidth = (int) (imageWidth * scale);
				int scaleHeight = (int) (imageHeight * scale);

				int newWidth = (int) ((panelWidth - scaleWidth) / 2);
				int newHeight = (int) ((panelHeight - scaleHeight) / 2);

				g.drawImage(image, newWidth, newHeight, scaleWidth, scaleHeight, this);
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

	private void initializeComponents() {
		usernameField = createStyledTextField(20);

		mobileNumberField = createStyledTextField(20);

		checkBox = createStyledCheckbox("Terms and Conditions");

		signInButton = createStyledButton("Sign-In");
		signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateData()) {
					handleSignIn();
				}
			}
		});
		
	}

	private void addComponents() {
		JPanel formPanel = new JPanel();
		formPanel.add(Box.createVerticalStrut(200));
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBackground(new Color(0, 0, 0, 0));

		// Title
		JLabel title = new JLabel("User - Sign-In", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 21));
		title.setForeground(Color.WHITE);
		formPanel.add(title);
		formPanel.add(Box.createVerticalStrut(30)); // Add spacing after title

		// Add form rows
		addFormRow("User Name: ", usernameField, formPanel);
		addFormRow("Mobile Number: ", mobileNumberField, formPanel);

		// Add checkbox with spacing
		formPanel.add(Box.createVerticalStrut(20));
		formPanel.add(checkBox);

		// Add button with spacing
		formPanel.add(Box.createVerticalStrut(30));
		formPanel.add(signInButton);

		// Center the form panel on the frame
		formPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		add(formPanel);
	}

	private void addFormRow(String labelText, JComponent component, JPanel formPanel) {

		JPanel rowPanel = new JPanel();

		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel label = new JLabel(labelText);

		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.PLAIN, 14));

		rowPanel.add(label);
		rowPanel.add(Box.createHorizontalStrut(10));
		rowPanel.add(component);

		formPanel.add(rowPanel);
		formPanel.add(Box.createVerticalStrut(20));

	}

	private JTextField createStyledTextField(int widthCols) {
		JTextField textField = new JTextField(widthCols);
		textField.setBackground(new Color(255, 255, 255));
		textField.setForeground(new Color(33, 33, 33));
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		return textField;
	}

	private JCheckBox createStyledCheckbox(String text) {

		JCheckBox checkbox = new JCheckBox(text);
		checkbox.setFont(new Font("Arial", Font.BOLD, 20));
		checkbox.setForeground(Color.WHITE);
		checkbox.setBackground(Color.BLACK);
		checkbox.setFocusPainted(false);
//		checkbox.setOpaque(false);
		return checkbox;
	}

	private JButton createStyledButton(String text) {

		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
//		button.setOpaque(false);
		return button;
	}

	private void handleSignIn() {

		String name = usernameField.getText().trim();
		String no = mobileNumberField.getText().trim();
		long phoneNo = Long.parseLong(no);

		String query = "select * from linkedindata where username ='" + name + "'" + "and mobno='" + phoneNo + "';";

		ConnectionJDBC connection = new ConnectionJDBC();

		try {
			ResultSet rs = connection.statement.executeQuery(query);

			if (rs.next()) {
				JOptionPane.showConfirmDialog(null, "User is present", "Success", JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showConfirmDialog(null, "Invalid credentials", "Error", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validateData() {
		if (!checkBox.isSelected()) {
			showMessage("Accept terms and conditions.", "CheckBox");
			return false;
		}

		if (usernameField.getText() == null || mobileNumberField.getText() == null) {
			return false;
		}

		if (usernameField.getText().trim() != null) {
			if (usernameField.getText().isEmpty()) {
				showMessage("Name field can't be blank", "Name not Entered");
				return false;
			}
		}

		if (mobileNumberField.getText() != null) {
			if (mobileNumberField.getText().isEmpty()) {
				showMessage("Mobile Number field can't be blank", "Mobile Number not Entered");
				return false;
			}
			try {
				Long.parseLong(mobileNumberField.getText());
			} catch (NumberFormatException e) {
				showMessage("Mobile Number field can't contains characters it only contains numbers",
						"Mobile Number not Entered");
				return false;
			}
		}

		return true;
	}

	private void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {

		new SignIn();

	}

}