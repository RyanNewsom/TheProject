import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * This class will facilitate running the program itself
 * @author Ryan Newsom
 * @version 1
 */
public class Office {
	static Image background;
	public static void main(String[]args){
		Office office = new Office();
	}
	public Office(){
		JFrame frame = new JFrame("Just another day at the office");
		JLabel title = new JLabel("Please enter the time for the office simulation in minutes");
		File file = new File("workaholics.jpg");
		JTextField titleInput = new JTextField(20);
		JButton go = new JButton("GO");
		Font largeFont = new Font("Verdana", 0, 30);
		//frame.setResizable(false);
		try {
			background = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Image load failed");
			// TODO Auto-generated catch block;
		}
		final JPanel j = new IPanel(background);
		frame.setBounds(350,350,1080,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(j);
		title.setFont(largeFont);
		title.setForeground(Color.BLUE);
		go.setFont(largeFont);
		j.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//RESERVED will initiate running the program for X minutes
			}	
		});
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
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(titleInput,gbc);
		//////////////Row 2//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		j.add(go,gbc);
		
		frame.repaint();
		frame.setVisible(true);
	}
}

