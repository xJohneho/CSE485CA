package Locke;
import Locke.PasswordTester;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class VirtualAssistant {
	String      appName     = "Locke Virtual Assistant";
    JFrame      newFrame    = new JFrame(appName);
    JButton     enterMessage;
    JTextField  messageBox;
    JTextArea   dialogueBox;
    JTextField  usernameChooser;
    JPasswordField  passwordEntry;
    JFrame      preFrame;
    JPanel p = new JPanel();
    JTextField t = new JTextField(10);
    String  username;
    String locke = "<Locke>: ";
	VirtualAssistant() {
		Logs.log(Level.INFO, Logs.class.getName());
	}
	
	public void loginDisplay() {
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        passwordEntry = new JPasswordField(20);
        JLabel chooseUsernameLabel = new JLabel("Enter Username:");
        JLabel passwordEntryLabel = new JLabel("Enter Password:");
        JButton login = new JButton("Enter Virtual Password Assistant");
        JButton createAccount = new JButton("Create an account");
        login.addActionListener(new loginButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());
        preFrame.getRootPane().setDefaultButton(login);
        

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;
        GridBagConstraints preMid = new GridBagConstraints();
        preMid.insets = new Insets(30, 10, 10, 0);
        preMid.anchor = GridBagConstraints.SOUTH;
        preMid.fill = GridBagConstraints.HORIZONTAL;
        preMid.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        prePanel.add(passwordEntryLabel, preLeft);
        prePanel.add(passwordEntry, preRight);
        prePanel.add(createAccount, preMid);
        preFrame.getContentPane().add(BorderLayout.CENTER, prePanel);
        preFrame.getContentPane().add(BorderLayout.SOUTH, login);
        preFrame.setSize(600, 600);
        preFrame.setLocationRelativeTo(null);
        preFrame.setVisible(true);

    }

    public void lockeDisplay() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        JLabel label = new JLabel("Enter Text  ");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        southPanel.setLayout(new GridBagLayout());
        
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        enterMessage = new JButton("Send Message");
        enterMessage.addActionListener(new enterMessageButtonListener());

        dialogueBox = new JTextArea();
        dialogueBox.setEditable(false);
        dialogueBox.setFont(new Font("Segoe UI", Font.PLAIN, 21));
        dialogueBox.setLineWrap(true);
        dialogueBox.setText(locke + "Hello " + username + "!" + " What would you like to do today?");

        mainPanel.add(new JScrollPane(dialogueBox), BorderLayout.CENTER);        
        
        southPanel.add(label);
        southPanel.add(messageBox);
        southPanel.add(enterMessage);

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        newFrame.getRootPane().setDefaultButton(enterMessage);

        newFrame.getContentPane().add(mainPanel);
        newFrame.getContentPane().add(BorderLayout.NORTH, mb);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(600, 600);
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
    }
    public enum Choices{
    	instructions("instructions"), display("display"), generate("generate"),
    	delete("delete"), create("create"), update("update");  
    	private String choice;

        private Choices(String choice) {
            this.choice = choice;
        }

        public String getType() {
            return choice;
        }
    }

    class enterMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	try {
        		PasswordTester a = new PasswordTester();
				PasswordManager b = new PasswordManager();
				/*
				String website;
				String password;
				Choices enumValue = Choices.valueOf(messageBox.getText());
				switch(enumValue) {
				case instructions:
					dialogueBox.append("\n" + locke + "\n1.Display Password" + "\n2.Create a new password Field" + "\n3.Generate a password" +
	                		"\n4.Update an existing passowrd" + "\n5.Delete a password");
	                messageBox.setText("");
				case display:
					website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append("\n" + locke + a.DisplayDecryptedPassword(website));
	                messageBox.setText("");
				case generate:
					website = JOptionPane.showInputDialog("Website");
	            	String pwLength = JOptionPane.showInputDialog("Password Length");
	            	int passWLength = Integer.parseInt(pwLength);
	                dialogueBox.append("\n" + locke +  a.EncryptedGenerator(website, passWLength) );
	                messageBox.setText("");
				case delete:
					website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append(b.deletePassword(website));
	                messageBox.setText("");
				case create:
					website = JOptionPane.showInputDialog("Website");
	            	password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke +  a.EncryptedSelfCreatePW(website, password));
	                messageBox.setText("");
				case update:
					website = JOptionPane.showInputDialog("Website");
	            	password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke + a.EncyptedPasswordUpdater(website, password));
	                messageBox.setText("");
				default:
					dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	                messageBox.setText("");
				}
				*/
				if (messageBox.getText().length() < 1) {
	                // Does nothing 
	            } else if (messageBox.getText().equalsIgnoreCase(".clear")) {
	                dialogueBox.setText("Cleared all messages\n");
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Instructions") || messageBox.getText().equalsIgnoreCase("Help")) {
	                dialogueBox.append("\n" + locke + "\n1.Display Password" + "\n2.Create a new password Field" + "\n3.Generate a password" +
	                		"\n4.Update an existing passowrd" + "\n5.Delete a password");
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Display")) {
	            	String website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append("\n" + locke + a.DisplayDecryptedPassword(website));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Generate")) {
	            	String website = JOptionPane.showInputDialog("Website");
	            	String pwLength = JOptionPane.showInputDialog("Password Length");
	            	int passWLength = Integer.parseInt(pwLength);
	                dialogueBox.append("\n" + locke +  a.EncryptedGenerator(website, passWLength) );
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Delete")) {
	            	String website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append(b.deletePassword(website));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Self-Create")) {
	            	String website = JOptionPane.showInputDialog("Website");
	            	String password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke +  a.EncryptedSelfCreatePW(website, password));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Update")) {
	            	String website = JOptionPane.showInputDialog("Website");
	            	String password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke + a.EncyptedPasswordUpdater(website, password));
	                messageBox.setText("");
	            }
	            else {
	                dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	                messageBox.setText("");
	            }
				
	            messageBox.requestFocusInWindow();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
    }

    

    class loginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	PasswordTester a;
        	username = usernameChooser.getText();
            String pw = passwordEntry.getText();
			try {
				a = new PasswordTester();
				if (a.loginChecker(username, pw) == false) {
	                System.out.println("Login does not exist!");
	            } else {
	                preFrame.setVisible(false);
	                lockeDisplay();
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
        }
    }
	
}

