package gui;

import java.awt.CardLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class ScreenHide extends JFrame{
	JPanel imagePane, blackPane, mainPane;
	JLabel img;
	CardLayout cl;
	Boolean isOn;
	GraphicsDevice gd;
	
	public ScreenHide(String url){
		setUndecorated(true);
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		mainPane = new JPanel();
		
		if(url == "black"){
			System.out.println("Url: " + url);
			blackPane = new JPanel();
			blackPane.setBackground(java.awt.Color.BLACK);
			mainPane.add(blackPane, "Card2");
		}else{
			imagePane = new JPanel();
			
			ImageIcon hideImg = new ImageIcon(url);		
			img = new JLabel();
			img.setIcon(hideImg);
			img.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
			
			imagePane.add(img);	
			mainPane.add(imagePane);
		}
	
		add(mainPane);
		
		setVisible(false);
    	}
	
	public void start(){
		while(true){
			isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if(isOn == true){
				setVisible(true);
				if (gd.isFullScreenSupported()) {
		    		try {
		    			gd.setFullScreenWindow(this);
		    		
		   
		    		}catch(Exception e){
		    			System.out.println(e.getMessage());
		    		}
				}
			}else {
				if (gd.isFullScreenSupported()) {
		    		try {
		    			gd.setFullScreenWindow(null);
		    		
		   
		    		}catch(Exception e){
		    			System.out.println(e.getMessage());
		    		}
				}
				
			}
		}
	}
	
	}




