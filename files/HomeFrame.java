package newPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import newPage.SignIn.setUpUI;

public class HomeFrame extends JFrame {

	private JButton signIn, signUp, adminSignIn;
	Color signInColor = Color.black;
	Color signUpColor = Color.black;
	Color adminSignInColor = Color.black;

	class setUpUI {
		setUpUI() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setUpFrame() {
		setTitle("Home Page-LinkedIn");
		setSize(1366, 768);
		setVisible(true);
//		setLayout(new FlowLayout(FlowLayout.CENTER));

		setContentPane(createBackgroundImage());
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

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

	public void addComponent() {

		JPanel contentPanel = new JPanel();
		setLayout(new BorderLayout()); // Use BorderLayout to center the panel
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		JLabel title = new JLabel("LinkedIn - Job Web Portal", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 21));
		title.setForeground(Color.white);
		title.setAlignmentX(CENTER_ALIGNMENT);
//		add(title);

		JPanel buttonPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(0, 0, 10000, 100);
			}

		};
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		buttonPanel.setOpaque(false);
		buttonPanel.add(signIn);
		buttonPanel.add(signUp);
		buttonPanel.add(adminSignIn);

		contentPanel.add(Box.createVerticalGlue());
		contentPanel.add(title);
		contentPanel.add(buttonPanel);

		add(contentPanel, BorderLayout.CENTER); // Add the content panel to the center
	}

	private void initializeButtonComponents() {
		this.signIn = createStyledButtons("Sign-In", signInColor);
		this.signUp = createStyledButtons("Sign Up", signUpColor);
		this.adminSignIn = createStyledButtons("Admin Sign-In", adminSignInColor);
	}

	private JButton createStyledButtons(String text, Color backColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 18));
		button.setBackground(backColor);
		button.setForeground(Color.white);
		button.setFocusPainted(false);

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.BLUE);
//				button.setForeground(Color.BLACK);

			}

			public void mouseExited(MouseEvent e) {
				button.setBackground(backColor);

			}
		});
		return button;
	}

	private void handleSignIn() {
		int option = JOptionPane.showConfirmDialog(this, "Existing User", "Confirmation Message",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			dispose();
			new SignIn();
		}
	}

	public void setUpListeners() {
		signIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleSignIn();
			}
		});
		
		signUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp();
			}
		});
	
	}

	public HomePage() {
		setUpFrame();
		createBackgroundImage();
		initializeButtonComponents();
		addComponent();
		setUpListeners();
		new setUpUI();

	}

	public static void main(String[] args) {
		new HomePage();
	}

}