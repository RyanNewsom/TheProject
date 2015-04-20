import java.awt.LayoutManager;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.orbutil.graph.Node;

/**
 * This class will provide a GUI showing how the day at the office went and everything that happened.
 * @author Ryan Newsom
 * @version 1
 */
class Log {
	/** This linked list contains all the customers that had questions that were answered */
	LinkedList events; 
	
	/**
	 * Creates a log which will need to access information from two separate Linked Lists
	 * @param before - all the customers that had questions that were answered
	 * @param atEndTime - all the customers who are still in line after the office simulation ends 
	 */
	protected Log(LinkedList events){
		this.events = events;
	}
	
	/**
	 * This method creates the gui inside the JFrame and will desploy all the customers information for the linked lists.
	 */
	protected void createLog()
	{
		JFrame frame = new JFrame("Today at the office...");
		//JPanel panel = new IPanel();
		JTextArea textArea = new JTextArea("Results go here.");
		JScrollPane scrollPane = new JScrollPane(textArea);
		JTable table = new JTable();
		//table.setValueAt("asdasd", row, column);
		
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
			Object temp = events.peek();
			if(temp!=null){
				temp = events.remove();
				String tempS = temp.toString();
				contents += "\n" + temp.toString();
			}
			else empty = false;
		}while(empty);
		
		textArea.setText(contents);
		frame.setVisible(true);
	}
}
