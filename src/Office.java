import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	public static void main(String[]args){
		JFrame frame = new JFrame("Just another day at the office");
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Please enter the time for the office simulation in minutes");
		JTextField titleInput = new JTextField(20);
		JButton go = new JButton("GO");
		Font largeFont = new Font(null, 0, 22);
		frame.setBounds(350,350,600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		title.setFont(largeFont);
		go.setFont(largeFont);
		GridBagConstraints gbc = new GridBagConstraints();
		//////////////Row 0//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.SOUTH;
		panel.add(title,gbc);
		//////////////Row 1//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(titleInput,gbc);
		//////////////Row 2//////////////////////
		gbc.weightx = 0.1;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		panel.add(go,gbc);
		
		frame.add(panel);
		frame.setVisible(true);
	}

}
