import java.awt.LayoutManager;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.orbutil.graph.Node;

/**
 * This class will provide a GUI showing how the day at the office went and everything that happened.
 * @author Ryan Newsom
 * @version 1
 */
public class Log {
	/** This linked list contains all the customers that had questions that were answered */
	LinkedList before; 
	/** This linked list contains all the customers that are still in line after the office simulation */
	LinkedList atEndTime;
	
	/**
	 * Creates a log which will need to access information from two separate Linked Lists
	 * @param before - all the customers that had questions that were answered
	 * @param atEndTime - all the customers who are still in line after the office simulation ends 
	 */
	public Log(LinkedList before, LinkedList atEndTime){
		this.before = before;
		this.atEndTime = atEndTime;
	}
	
	/**
	 * This method creates the gui inside the JFrame and will desploy all the customers information for the linked lists.
	 */
	public void createLog()
	{
		JFrame frame = new JFrame("Today at the office...");
		//JPanel panel = new IPanel();
		JTextArea textArea = new JTextArea("Results go here.");
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		textArea.setSize(500, 500);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setVisible(true);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		frame.add(scrollPane);
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boolean empty = true;
		String contents = "";
		do{
			Object temp = before.peek();
			if(temp!=null){
				temp = before.remove();
				String tempS = temp.toString();
				contents += "\n" + temp.toString();
			}
			else empty = false;
		}while(empty);
		
		empty = true; // reset the boolean
		
		do{
			Object temp = atEndTime.peek();
			if(temp!=null) {
				temp = atEndTime.remove();
				String tempS = temp.toString();
				contents += "\n" + temp.toString();
				}
			else empty = false;
		}while(empty);
		
		textArea.setText(contents);
		frame.setVisible(true);
	}
}
