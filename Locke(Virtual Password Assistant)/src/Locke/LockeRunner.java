package Locke;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class LockeRunner extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		PasswordTester test = new PasswordTester();
		PasswordManager test2 = new PasswordManager();
		try {
			
			primaryStage.setTitle("Crypto");
			
			GridPane rootNode = new GridPane();
	        rootNode.setPadding(new Insets(15));
	        rootNode.setHgap(5);
	        rootNode.setVgap(5);
	        rootNode.setAlignment(Pos.CENTER);
	        
	        Scene myScene = new Scene(rootNode, 600, 600);
	        rootNode.add(new Label("Website:"), 0, 0);
	        TextField textField = new TextField();
	        //textField.appendText(wSite + ":" + test2.displayPassword(wSite));
	        //textField.appendText(test2.selfCreatePassword());
			rootNode.add(textField, 1, 0);
			rootNode.add(new Label("Password:"), 0, 1);
			TextField textField2 = new TextField();
			rootNode.add(textField2, 1, 1);
			//textField2.appendText(test2.generatePassword());
			Button aButton = new Button("Self-Create Password");
			rootNode.add(aButton, 1, 3);
			Button bButton = new Button("Generate Password");
			rootNode.add(bButton, 2, 3);
			Button cButton = new Button("Password Updater");
			rootNode.add(cButton, 3, 3);
			Button dButton = new Button("Display Password");
			rootNode.add(dButton, 4, 3);
			Button eButton = new Button("Delete Password");
			rootNode.add(eButton, 5, 3);
	        GridPane.setHalignment(aButton, HPos.LEFT);
	        
	        rootNode.add(new Label("Encrypted:"), 0, 4);
	        TextField result = new TextField();
	        result.setEditable(false);
	        rootNode.add(new Label("Decrypted:"), 0, 5);
	        rootNode.add(result, 1, 4);
	        TextField result2 = new TextField();
	        result.setEditable(false);
	        rootNode.add(result2, 1, 5);
	        
	        aButton.setOnAction(e -> {
	        	String target = String.valueOf(textField.getText());
	        	String target2 = String.valueOf(textField2.getText());
	        	
	        	try {
					result.appendText(test.EncryptedSelfCreatePW(target, target2));
					result2.appendText(test.DisplayDecryptedPassword(target));
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        });
	        bButton.setOnAction(e -> {
	        	String target = String.valueOf(textField.getText());
	        	try {
	        		result.appendText(test.EncryptedGenerator(target, 12));
				} catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        });
	        cButton.setOnAction(e -> {
	        	String target = String.valueOf(textField.getText());
	        	String target2 = String.valueOf(textField2.getText());
	        	try {
	        		result.appendText(test.EncyptedPasswordUpdater(target, target2));
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


	        });
	        dButton.setOnAction(e -> {
	        	String target = String.valueOf(textField.getText());
	        	try {
	        		result2.appendText(test.DisplayDecryptedPassword(target));
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        });
	        eButton.setOnAction(e -> {
	        	String target = String.valueOf(textField.getText());
	        	
	        	try {
					test2.deletePassword(target);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	

	        });

			primaryStage.setScene(myScene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                VirtualAssistant mainGUI;
				try {
					mainGUI = new VirtualAssistant();
					mainGUI.loginDisplay();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
	//launch(args);
	}
}
