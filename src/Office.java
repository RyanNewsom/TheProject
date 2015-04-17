import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * This class will facilitate running the program itself and bring everything together. The flow will be as such
 * 1) Kris will keep track of the times and 
 * @author Ryan Newsom
 * @version 1
 */
public class Office {

	public static void main(String[]args){
		Office office = new Office();
	}
	
	public Office(){
		JFrame frame = new JFrame("Just another day at the office...");
		JLabel title = new JLabel("Please enter the time for the office simulation in minutes");
		File file = new File("workaholics.jpg");
		JTextField titleInput = new JTextField(20);
		JButton go = new JButton("GO");
		Font largeFont = new Font("SANS_SERIF", 1, 34);
		Font mediumFont = new Font("SANS_SERIF", 0, 20);
		Color teal = new Color(46, 177, 157);
		Image background = null;
		
		try {
			background = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Image load failed");
			// Some Error Handling in the future
		}
		final JPanel j = new IPanel(background);
		//frame.setResizable(false);
		frame.setBounds(200,200,1080,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(j);
		
		j.setLayout(new GridBagLayout());
		
		title.setFont(largeFont);
		title.setForeground(teal);
		titleInput.setFont(mediumFont);
		
		go.setFont(largeFont);
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				runIt(Integer.parseInt(titleInput.getText()));
				
				//
			}	
		});
		GridBagConstraints gbc = new GridBagConstraints();
		//////////////Row 0//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(title,gbc);
		//////////////Row 1//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.SOUTH;
		j.add(titleInput,gbc);
		//////////////Row 2//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(go,gbc);
		frame.setVisible(true);
	}
	
	public void runIt(int runTime){
		LinkedList list1 = new LinkedList();
		LinkedList listAfterRun = new LinkedList();
		/*
		Time runIt = new Time(runTime);
		list1 = runIt.getLinkedList2()
		list2 = runIt.getLinkedList1();
		//Time to print out all the customer information per Gordons specs to the Log GUI.
		Log theLog = new Log(list1, list2);
		theLog.createLog();
		*/
	}
}
