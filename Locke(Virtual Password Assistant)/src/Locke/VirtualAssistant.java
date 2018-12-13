package Locke;
import Locke.PasswordTester;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
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
    JTextArea   passwordBox;
    JTextArea   helpBox;
    JTextField  usernameChooser;
    JPasswordField  passwordEntry;
    JTextField  usernameSignUp;
    JPasswordField  passwordSignUp;
    JFrame      preFrame;
    JFrame      popupFrame = new JFrame("Passwords");
    JFrame      helpFrame = new JFrame("Help");
    JFrame      signupFrame = new JFrame("Sign-Up");
    JTextField t = new JTextField(10);
    String  username;
    String locke = "<Locke>: ";
	VirtualAssistant() {
		Logs.log(Level.INFO, Logs.class.getName());
	}
	
	public void loginDisplay() {
		//signupPanel.setLayout(new BorderLayout());
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        passwordEntry = new JPasswordField(20);
        usernameSignUp = new JTextField(15);
        passwordSignUp = new JPasswordField(20);
        JLabel chooseUsernameLabel = new JLabel("Enter Username:");
        JLabel passwordEntryLabel = new JLabel("Enter Password:");
        JLabel signupUsernameLabel = new JLabel("Enter Username:");
        JLabel signupPasswordLabel = new JLabel("Enter Password:");
        JButton login = new JButton("Enter Virtual Password Assistant"); 
        JButton finalizeAccount = new JButton("Sign-Up");
        JButton createAccount = new JButton("Create an account");
        login.addActionListener(new loginButtonListener());
        createAccount.addActionListener(new createAccountButtonListener());
        finalizeAccount.addActionListener(new finalizeAccountButtonListener());
       
        JPanel signupPanel = new JPanel(new GridBagLayout());
		signupFrame.getRootPane().setDefaultButton(finalizeAccount);
        JPanel prePanel = new JPanel(new GridBagLayout());
        preFrame.getRootPane().setDefaultButton(login);
        

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;
        GridBagConstraints preMid = new GridBagConstraints();
        preMid.insets = new Insets(30, 10, 10, 0);
        preMid.anchor = GridBagConstraints.SOUTH;
        preMid.fill = GridBagConstraints.HORIZONTAL;
        preMid.gridwidth = GridBagConstraints.REMAINDER;

        signupPanel.add(signupUsernameLabel, preLeft);
        signupPanel.add(usernameSignUp, preRight);
        signupPanel.add(signupPasswordLabel, preLeft);
        signupPanel.add(passwordSignUp, preRight);
        signupFrame.getContentPane().add(BorderLayout.CENTER,signupPanel);
        signupFrame.getContentPane().add(BorderLayout.SOUTH,finalizeAccount);
        signupFrame.setSize(600, 600);
		signupFrame.setResizable(false);
        signupFrame.setLocationRelativeTo(null);
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
        popupFrame.setSize(600, 600);
        popupFrame.setLocationRelativeTo(null);
        helpFrame.setSize(600, 600);
        helpFrame.setLocationRelativeTo(null);
        mainPanel.setLayout(new BorderLayout());
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BorderLayout());
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        JLabel label = new JLabel("Enter Text  ");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        southPanel.setLayout(new GridBagLayout());
        
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Password");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Display All Passwords");
        m1.add(m11);
        JMenuItem m21 = new JMenuItem("Display Commands");
        m2.add(m21);
        
        m11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//popupFrame.setSize(300, 300);
        		try {
					displayAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		popupFrame.setVisible(true);
        	}
        
        });
        m21.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		helpFrame.setVisible(true);
        	}
        
        });
        

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        enterMessage = new JButton("Send Message");
        enterMessage.addActionListener(new enterMessageButtonListener());

        dialogueBox = new JTextArea();
        dialogueBox.setEditable(false);
        dialogueBox.setFont(new Font("Segoe UI", Font.PLAIN, 21));
        dialogueBox.setLineWrap(true);
        dialogueBox.setText(locke + "Hello " + username + "!" + " What would you like to do today?");
        
        passwordBox = new JTextArea();
        passwordBox.setEditable(false);
        passwordBox.setFont(new Font("Segoe UI", Font.PLAIN, 21));
        passwordBox.setLineWrap(true);
        passwordBox.setText(locke + "Hello " + username + "!" + " These are all the passwords saved within the system!");
        passwordPanel.add(new JScrollPane(passwordBox), BorderLayout.CENTER); 
        popupFrame.getContentPane().add(passwordPanel);
        
        helpBox = new JTextArea();
        helpBox.setEditable(false);
        helpBox.setFont(new Font("Segoe UI", Font.PLAIN, 21));
        helpBox.setLineWrap(true);
        helpBox.setText(locke + "Hello " + username + "!" + " These are all the commands used to communicate with me! The commands are not case sensitive!");
        helpBox.append("\n1.Display Password: Type in 'Display'" + "\n2.Create a new password Field: Type in 'Self-Create'"
        + "\n3.Generate a password: Type in 'Generate'" + "\n4.Update an existing passowrd: Type in 'Update'" +
        "\n5.Delete a password: Type in 'Delete'"+
        "\n6.Clear chatbox: Type in 'Clear'");
        helpPanel.add(new JScrollPane(helpBox), BorderLayout.CENTER); 
        helpFrame.getContentPane().add(helpPanel);

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
    public void displayAll() throws Exception {
    	Properties prop = new Properties();
    	PasswordTester a = new PasswordTester();
    	String propertiesFilePath = ("/Users/John/Desktop/Locke/files/" + username.toLowerCase() + "config.properties");
    	prop.load(new FileInputStream(propertiesFilePath));
    	Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			passwordBox.append("\n"+ a.DisplayDecryptedPassword(propertiesFilePath, key));
		}
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
				String propertiesFilePath = ("/Users/John/Desktop/Locke/files/" + username.toLowerCase() + "config.properties");
				if (messageBox.getText().length() < 1) {
	                // Does nothing 
	            } else if (messageBox.getText().equalsIgnoreCase(".clear")) {
	                dialogueBox.setText("Cleared all messages\n");
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Display")) {
	            	dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	            	String website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append("\n" + locke + a.DisplayDecryptedPassword(propertiesFilePath, website));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Generate")) {
	            	dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	            	boolean upper = false;
	            	boolean lower = false;
	            	boolean numbers = false;
	            	boolean special= false;
	            	String website = JOptionPane.showInputDialog("Website");
	            	String pwLength = JOptionPane.showInputDialog("Password Length");
	            	int includeUpper = JOptionPane.showConfirmDialog(null, "Include uppercase letters?", "Uppercase", JOptionPane.YES_NO_OPTION);
	            	if(includeUpper == 0) {
	            		upper = true;
	            	}else if(includeUpper == 1){
	            		upper = false;
	            	}
	            	int includeLower = JOptionPane.showConfirmDialog(null, "Include lowercase letters?", "Lowercase", JOptionPane.YES_NO_OPTION);
	            	if(includeLower == 0) {
	            		lower = true;
	            	}else if(includeLower == 1){
	            		lower = false;
	            	}
	            	int includeNum = JOptionPane.showConfirmDialog(null, "Include numbers?", "Numbers", JOptionPane.YES_NO_OPTION);
	            	if(includeNum == 0) {
	            		numbers = true;
	            	}else if(includeNum == 1){
	            		numbers = false;
	            	}
	            	int includeSpecial = JOptionPane.showConfirmDialog(null, "Include special characters?", "Special", JOptionPane.YES_NO_OPTION);
	            	if(includeSpecial == 0) {
	            		special = true;
	            	}else if(includeSpecial == 1) {
	            		special = false;
	            	}
	            	
	            	int passWLength = Integer.parseInt(pwLength);
	                dialogueBox.append("\n" + locke +  a.EncryptedGenerator(propertiesFilePath, website, passWLength, upper, lower, numbers, special) );
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Delete")) {
	            	dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	            	String website = JOptionPane.showInputDialog("Website");
	                dialogueBox.append(b.deletePassword(propertiesFilePath, website));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Self-Create")) {
	            	dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	            	String website = JOptionPane.showInputDialog("Website");
	            	String password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke +  a.EncryptedSelfCreatePW(propertiesFilePath, website, password));
	                messageBox.setText("");
	            }else if (messageBox.getText().equalsIgnoreCase("Update")) {
	            	dialogueBox.append("\n<" + username + ">:  " + messageBox.getText());
	            	String website = JOptionPane.showInputDialog("Website");
	            	String password = JOptionPane.showInputDialog("Password");
	                dialogueBox.append("\n" + locke + a.EncyptedPasswordUpdater(propertiesFilePath, website, password));
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
            String propertiesFilePath = ("/Users/John/Desktop/Locke/files/" + username.toLowerCase() + "config.properties");
			try {
				a = new PasswordTester();
				if (a.loginChecker(propertiesFilePath, username.toLowerCase()+ "java", pw) == false) {
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
    
    class createAccountButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	signupFrame.setVisible(true);
        	
        	
        	        
        }
    }
    class finalizeAccountButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	try {
				Cryption test = new Cryption();
				String createdUserName = usernameSignUp.getText();
	            String createdPassword = passwordSignUp.getText();
	            String propertiesFilePath = ("/Users/John/Desktop/Locke/files/" + createdUserName.toLowerCase() + "config.properties");
	            System.out.println(propertiesFilePath);
	            File file = new File(propertiesFilePath);
	            if(file.createNewFile()) {
	    			file.setWritable(true);
	    			FileWriter fout = new FileWriter(propertiesFilePath, true);
	    			
	    			fout.write(createdUserName.toLowerCase() + "java" + ":" + test.encrypt(createdPassword) + System.lineSeparator());
	    			fout.close();
	    			signupFrame.setVisible(false);
	    		}
	    		else {
	    			Logs.log(Level.INFO, "File does not exist");
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    }
}

