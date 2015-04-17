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
	LinkedList before;
	LinkedList atEndTime;
	
	public Log(LinkedList before, LinkedList atEndTime){
		this.before = before;
		this.atEndTime = atEndTime;
	}
	
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
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boolean empty = false;
		String contents = "";
		do{
			Object temp = before.remove();
			if(temp!=null){
				String tempS = temp.toString();
				contents += "\n" + temp.toString();
			}
			else empty = true;
		}while(empty);
		
		empty = false;
		
		do{
			Object temp = atEndTime.remove();
			if(temp!=null) {
				String tempS = temp.toString();
				contents += "\n" + temp.toString();
				}
			else empty = true;
		}while(empty);
		
		textArea.setText(contents);
	}
}
