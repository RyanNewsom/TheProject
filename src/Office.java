import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Bring the necessary class's together for the group assignment and run's the simulation andprints out results using 
 * the different gui's: Log, Table
 * @author Ryan Newsom
 * @version 2
 */
class Office {

	public static void main(String[]args){
		Office office = new Office();
	}
	/**
	 * Displays the GUI's and allows for the user to enter in a time they would like to run the office 
	 * simulation for.
	 */
	protected Office(){
		JFrame frame = new JFrame("Just another day at the office...");
		JLabel title = new JLabel("An Office Simulation");
		JLabel simTime = new JLabel("Time for office simulation: ");
		JLabel labelP = new JLabel("Average time for a new customer phone call: ");
		JLabel labelD = new JLabel("Average time for a new walk in customer: ");
		JLabel labelQ = new JLabel("Average time for secretary to answer a question: ");
		File file = new File("workaholics.jpg");
		JTextField titleInput = new JTextField(20);
		JTextField timeP = new JTextField(20);
		JTextField timeQ = new JTextField(20);
		JTextField timeD = new JTextField(20);
		JButton go = new JButton("GO");
		Font largeFont = new Font("SANS_SERIF", 1, 50);
		Font mediumFont = new Font("SANS_SERIF", 0, 30);
		Font smallFont = new Font("SANS_SERIF", 0, 20);
		Color grayl = new Color(0, 0, 0);
		Image background = null;
		
		try {
			background = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Image load failed");
		}
		final JPanel j = new IPanel(background);
		frame.setBounds(200,200,1080,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(j);
		
		j.setLayout(new GridBagLayout());
		
		title.setFont(largeFont);
		title.setForeground(grayl);
		simTime.setFont(mediumFont);
		simTime.setForeground(Color.BLUE);
		labelP.setFont(mediumFont);
		labelP.setForeground(Color.BLUE);
		labelQ.setFont(mediumFont);
		labelQ.setForeground(Color.BLUE);
		labelD.setFont(mediumFont);
		labelD.setForeground(Color.BLUE);
		
		titleInput.setFont(smallFont);
		timeP.setFont(smallFont);
		timeQ.setFont(smallFont);
		timeD.setFont(smallFont);
		
		go.setFont(largeFont);
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					runIt(Integer.parseInt(titleInput.getText()), Integer.parseInt(timeP.getText()), Integer.parseInt(timeQ.getText()), Integer.parseInt(timeD.getText()));
				}
				catch(NumberFormatException nfe){
					titleInput.setText("Error, you can only enter integers");
				}
			}	
		});
		GridBagConstraints gbc = new GridBagConstraints();
		//////////////Row 0//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.05;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(title,gbc);
		//////////////Row 1//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		j.add(simTime,gbc);
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		j.add(titleInput,gbc);
		//////////////Row 2//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		j.add(labelP,gbc);
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		j.add(timeP,gbc);
		///////////////Row 3///////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		j.add(labelD,gbc);
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		j.add(timeD,gbc);
		//////////////Row 4/////////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		j.add(labelQ,gbc);
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		j.add(timeQ,gbc);
		//////////////Row 5////////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(go,gbc);
		
		
		frame.setVisible(true);
	}
	/**
	 * Runs the program itself
	 * @param runTime - The time to run the office simulation
	 * @param pTime - Time it takes for a new phone customer to arrive
	 * @param qTime - Time it takes to answer a question
	 * @param dTime - Time it takes for a new door customer to arrive
	 */
	protected void runIt(int runTime, int pTime, int qTime, int dTime){
		PriorityQueue eventList = new PriorityQueue<>();
		
		Time runIt = new Time(runTime, pTime, qTime, dTime);
		eventList = runIt.getEventList();
		//Time to print out all the customer information per Gordons specs to the Log GUI.
		Log theLog = new Log(eventList);
		theLog.createLog();	
	}
}
