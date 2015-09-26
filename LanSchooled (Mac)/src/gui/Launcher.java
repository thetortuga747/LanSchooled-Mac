package gui;
import java.io.File;

import javax.swing.JOptionPane;

public class Launcher {
	ScreenHide sc;
	Settings set;
	
	public Launcher(){
		set = new Settings();
	}
	
	public static void main(String[] args) {
		Launcher launch = new Launcher();
		String url = launch.set.getChoice();
		launch.set = null;
		try{
			ScreenHide sh = new ScreenHide(url);
			sh.start();
		}catch(Exception e){
			System.out.println("An error occurred. " + e.getMessage() + ". " + url);
			System.out.println(e.getStackTrace());
			System.out.println(new File(url).exists());
			JOptionPane.showMessageDialog(null, "The file URL was invalid.\n Error: " + e.getMessage());
		}
			
	}

}
