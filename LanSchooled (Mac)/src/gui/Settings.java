package gui;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.io.FileFilter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings extends JFrame implements ActionListener, ItemListener{
		JPanel mainPane, radioPane1, infoPane, radioPane2;
		JRadioButton black, img;
		ButtonGroup group;
		JLabel imgFile;
		JTextField tf;
		JButton browse, launch;
		GridBagConstraints gbcRadio, gbcInfo;
		Boolean selected, fileChosen;
		JFileChooser fileChoose;
		FileNameExtensionFilter imgFilter;
		JOptionPane op;
	public Settings(){
		super("LanSchooled Settings");
		setSize(300, 170);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
		radioPane1 = new JPanel(new GridBagLayout());
		radioPane2 = new JPanel(new GridBagLayout());
		gbcRadio = new GridBagConstraints();
		group = new ButtonGroup();
		
		infoPane = new JPanel(new GridBagLayout());
		gbcInfo = new GridBagConstraints();
		
		img = new JRadioButton("Display a screenshot of your desktop.");
		group.add(img);
		img.addItemListener(this);
		gbcRadio.gridx = 0;
		gbcRadio.gridy = 0;
		gbcRadio.anchor = GridBagConstraints.WEST;
		radioPane1.add(img, gbcRadio);
		
		imgFile = new JLabel("Image:");
		imgFile.setEnabled(false);
		gbcInfo.gridx = 0;
		gbcInfo.gridy = 0;
		gbcInfo.anchor = GridBagConstraints.PAGE_START;
		gbcInfo.insets = new Insets(5, 0, 0, 0);
		infoPane.add(imgFile, gbcInfo);
		
		tf = new JTextField(12);
		tf.setEnabled(false);
		gbcInfo.gridx = 1;
		gbcInfo.gridy = 0;
		gbcInfo.anchor = GridBagConstraints.CENTER;
		gbcInfo.insets = new Insets(0, 0, 0, 0);
		infoPane.add(tf, gbcInfo);
		
		browse = new JButton("Browse");
		browse.addActionListener(this);
		browse.setEnabled(false);
		gbcInfo.gridx = 2;
		gbcInfo.gridy = 0;
		gbcInfo.anchor = GridBagConstraints.PAGE_END;
		infoPane.add(browse, gbcInfo);
		
		black = new JRadioButton("Display a blank window.");
		group.add(black);	
		black.setSelected(true);
		black.addItemListener(this);
		gbcRadio.gridx = 0;
		gbcRadio.gridy = 0;
		gbcRadio.anchor = GridBagConstraints.WEST;
		gbcRadio.insets = new Insets(0, 13, 0, 0);
		radioPane2.add(black, gbcRadio);
		
		launch = new JButton("Launch");
		launch.addActionListener(this);
		gbcRadio.gridx = 1;
		gbcRadio.gridy = 1;
		gbcRadio.anchor = GridBagConstraints.EAST;
		gbcRadio.insets = new Insets(0, 11, 0, 0);
		radioPane2.add(launch, gbcRadio);
		
		mainPane.add(radioPane1);
		mainPane.add(infoPane);
		mainPane.add(radioPane2);
		
		fileChoose = new JFileChooser();
		imgFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		fileChoose.setFileFilter(imgFilter);
		
		op = new JOptionPane();
		
		selected = false;
		fileChosen = false;
		
		add(mainPane);
		setVisible(false);
	}
	
	public static void main(String[] args) {
		Settings set = new Settings();
		String s = set.getChoice();
		System.out.println(s);
	}

	public String getChoice(){
		String ret;
		setVisible(true);
		do{
			try{
				Thread.sleep(200);
				
			}catch(Exception e){
				
			}
		}while(selected == false);
		
		if(img.isSelected()){
			ret = tf.getText();
			setVisible(false);
			return ret;
		}else {
			ret = "black";
			setVisible(false);
			return ret;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == browse){
			int i = fileChoose.showOpenDialog(this);
			if(i == JFileChooser.APPROVE_OPTION){
					tf.setText(fileChoose.getSelectedFile().getPath());
					fileChosen = true;
			
			}else{
				
			}
		}else if(src == launch){
			System.out.println("Launch was pressed.");
			if(black.isSelected() == true || img.isSelected() == true && fileChosen == true){
				selected = true;
			}else{
				op.showMessageDialog(this, "You haven't chosen an option or selected a file");
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		if(src == img){
			if(e.getStateChange() == ItemEvent.SELECTED){
				imgFile.setEnabled(true);
				tf.setEnabled(true);
				browse.setEnabled(true);
			}
		}if(src == black){
			if(e.getStateChange() == ItemEvent.SELECTED){
				imgFile.setEnabled(false);
				tf.setEnabled(false);
				browse.setEnabled(false);
			}
		}
	}

}
