package Locke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import Locke.Logs;
public class PasswordManager {
	char[] upperCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ")).toCharArray();
	char[] lowerCharacters = (new String("abcdefghijklmnopqrstuvwxyz")).toCharArray();
	char[] numbers = (new String("0123456789")).toCharArray();
	char[] specialCharacters = (new String("~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
	char[] result;
	boolean inclUpper;
	boolean inclLower;
	boolean inclNum;
	boolean inclSpecial;
	String password;
	Properties prop = new Properties();
	//String propertiesFilePath = ("/Users/John Herrera/Desktop/Locke/files/config.properties");
	public PasswordManager(){
		Logs.log(Level.INFO, Logs.class.getName());
	}
	
	public String generatePassword(String propertiesFilePath, int pwLength, boolean inclUpper, boolean inclLower, boolean inclNum, boolean inclSpecial) throws IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		if(inclUpper == true) {
			result = ArrayUtils.addAll(result, upperCharacters);
		}
		if(inclLower == true) {
			result = ArrayUtils.addAll(result, lowerCharacters);
		}
		if(inclNum == true) {
			result = ArrayUtils.addAll(result, numbers);
		}
		if(inclSpecial == true) {
			result = ArrayUtils.addAll(result, specialCharacters);
		}
		String randomPW = RandomStringUtils.random( pwLength, 0, result.length-1, false, false, result, new SecureRandom() );
		Logs.log(Level.INFO, randomPW);
		return randomPW;
	}
	public String selfCreatePassword(String propertiesFilePath, String website, String pw) throws IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		//storeInfo(website, pw);
		Logs.log(Level.INFO, website + ":" + pw);
		return pw;
	}
	public void passwordUpdater(String propertiesFilePath, String website, String pw) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		prop.setProperty(website, pw);
		FileOutputStream fos = new FileOutputStream(propertiesFilePath);
        prop.store(fos,null);
        Logs.log(Level.INFO, "website + \" password updated to \"+ pw");
	}
	public String deletePassword(String propertiesFilePath, String website) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		String a = "\nRemoving "+ website + " and related password";
		String b = "\nWebsite does not exist!";
		if(prop.getProperty(website.toLowerCase()) != null) {
			prop.remove(website);
			FileOutputStream fos = new FileOutputStream(propertiesFilePath);
	        prop.store(fos,null);
	        Logs.log(Level.INFO, a);
			return a;
		}else {
			Logs.log(Level.INFO, b);
			return b;
		}
		
	}
	public void storeInfo(String propertiesFilePath, String website, String pw) throws IOException {
		File file = new File(propertiesFilePath);
		if(file.exists()) {
			file.setWritable(true);
			FileWriter fout = new FileWriter(propertiesFilePath, true);
			
			fout.write(website.toLowerCase() + ":" + pw + System.lineSeparator());
			fout.close();
		}
		else {
			Logs.log(Level.INFO, "File does not exist");
		}
	}
	public String displayPassword(String propertiesFilePath, String website) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(propertiesFilePath));
		String go = prop.getProperty(website);
		Logs.log(Level.INFO, go);
		return go;
		
	}
	

}
