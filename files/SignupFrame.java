package newPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SignupFrame extends JFrame {

	class setUpUI{
		setUpUI(){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private JTextField usernameField, mobilenumberField;
	private JButton submitButton;
	private JCheckBox checkBox;
	private JComboBox<String> designation;

	public SignUp() {
		setUpFrame();
		initializeComponents();
		addComponents();
		new setUpUI();
	}

	private void setUpFrame() {
		setSize(1366, 768);
		setVisible(true);
		setTitle("SignUp / Create New LinkedIn Account");
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

	private void addComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(new Color(0, 0, 0, 0));
//		mainPanel.add(Box.createVerticalStrut(200)) ;
		JLabel title = new JLabel("User Sign-Up", SwingConstants.CENTER);

		title.setFont(new Font("Arial", Font.BOLD, 21));

		title.setForeground(new Color(25, 194, 246));

		mainPanel.add(title);

		mainPanel.add(Box.createVerticalStrut(300));

		addFormRow("User Name :", usernameField, mainPanel);

		mainPanel.add(Box.createVerticalStrut(30));

		addFormRow("Mobile Number", mobilenumberField, mainPanel);

		mainPanel.add(Box.createVerticalStrut(30));
		
		mainPanel.add(designation);
		
		mainPanel.add(Box.createVerticalStrut(30));

		mainPanel.add(checkBox);

		mainPanel.add(Box.createVerticalStrut(30));

		mainPanel.add(submitButton);

		add(mainPanel);

	}

	private JTextField createStyledTextField(int width) {
		JTextField textField = new JTextField(width);
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.setForeground(Color.white);
		textField.setCaretColor(Color.white);
		textField.setBackground(new Color(30, 30, 30));
		return textField;
	}

	private JButton createStyledButton(String text, Color backColor) {
		JButton button = new JButton(text);

		button.setFont(new Font("Araial", Font.BOLD, 14));
		button.setForeground(Color.white);
		button.setBackground(new Color(25, 100, 246));
		
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(25, 100, 246));
			}
		});		
		
//		button.setOpaque(false);
		button.setFocusPainted(false);

		return button;
	}

	private void initializeComponents() {
		usernameField = createStyledTextField(10);
		mobilenumberField = createStyledTextField(10);
		checkBox = createCheckBox("Accept Terms & Conditions.");
		submitButton = createStyledButton("Sign-Up", Color.MAGENTA);
		submitButton.addActionListener((e) -> {
			if (validateData()) {
				ConnectionJDBC database = new ConnectionJDBC();
				
				try {
					String name = usernameField.getText().trim();
					Long mobNo = Long.parseLong(mobilenumberField.getText());
					String designation = (String)this.designation.getSelectedItem();
					String query = "Insert into linkedindata values('"+name+"',"+mobNo+",'"+designation+"')";
					database.statement.executeUpdate(query);
				} catch(SQLException sqle) {
					sqle.printStackTrace();
					showMessage("Data Not Entered", "Error");
				}
				JOptionPane.showConfirmDialog(this, "SignUp successful","Success",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		String[] designations= {"Select Designation","SDE","QA-Eng","HR","BA","TLead"};
		designation = new JComboBox<String>(designations);
		createStyledCoboBox(designation);
		
	}

	private boolean validateData() {
		if (!checkBox.isSelected()) {
			showMessage("Accept terms and conditions.", "CheckBox");
			return false;
		}

		if (usernameField.getText() == null || mobilenumberField.getText() == null) {
			return false;
		}

		if (usernameField.getText().trim() != null) {
			if (usernameField.getText().isEmpty()) {
				showMessage("Name field can't be blank", "Name not Entered");
				return false;
			}
		}

		if (mobilenumberField.getText() != null) {
			if (mobilenumberField.getText().isEmpty()) {
				showMessage("Mobile Number field can't be blank", "Mobile Number not Entered");
				return false;
			}
			try {
				Long.parseLong(mobilenumberField.getText());
			} catch(NumberFormatException e) {
					showMessage("Mobile Number field can't contains characters it only contains numbers", "Mobile Number not Entered");
					return false;
			}			
		}
		
		if (((String)designation.getSelectedItem()).equals("Select Designation")) {
			showMessage("Please select the correct designation", "Designation not Selected");
			return false;
		}
		
		return true;
	}

	private void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	private JCheckBox createCheckBox(String message) {
		JCheckBox box = new JCheckBox(message);
		box.setFont(new Font("Arial", Font.BOLD, 20));
		box.setForeground(Color.white);
		box.setBackground(Color.black);
//		box.setBackground(new Color(30,30,30));
//		box.setOpaque(false);

		return box;
	}

	private void addFormRow(String title, JComponent theComponent, JPanel formPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));

		JLabel text = new JLabel(title);
		text.setFont(new Font("Arial", Font.BOLD, 19));
		text.setForeground(Color.white);

		panel.add(text);
		panel.add(Box.createHorizontalStrut(30));
		panel.add(theComponent);

		formPanel.add(panel);

		add(formPanel);

	}
	
	
	private void createStyledCoboBox(JComboBox<String> designation) {
		designation.setFont(new Font("Arial",Font.PLAIN,20));
		designation.setForeground(Color.white);
		designation.setBackground(Color.black);
	}

	public static void main(String[] args) {

		new SignUp();

	}

}
