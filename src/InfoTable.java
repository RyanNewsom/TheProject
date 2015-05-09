import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 * This class will create a nice table, in which we will display information on
 * the customers who have visited the office.
 * 
 * @author Aaron
 */
public class InfoTable extends JFrame {
    
    JTable table; 
    
    /**
     * 
     * @param served - Customers served during Office Simulation
     * @param leftAfterClose - Customers still in line at close
     */
    public InfoTable(PriorityQueue served, PriorityQueue leftAfterClose) {
        
        int bSize = served.size();
        int aSize = leftAfterClose.size();
        int numRows = bSize + aSize;
        
        String[] columnNames = {"Name","Type of Customer","Start Time","End Time",
                            "Querie","Answer","Priority","Remaining customers"};
        
        String[][] rows;
        
        rows = new String[numRows][8];
        
        // populating the top rows with customers who were served during the sim.
        for (int i=0; i<bSize; i++) {
            Customer c = (Customer) served.dequeueFinal();
            if (c != null) {
                rows[i][0] = c.getName();
                rows[i][1] = c.getType();
                rows[i][2] = c.getStartTimeF();
                rows[i][3] = c.getAnswerTimeF();
                rows[i][4] = c.getPhrase();
                rows[i][5] = c.getAnswer();
                rows[i][6] = c.getPriority();
                rows[i][7] = c.getLineLength();
            }
            
        }
        // populating the remaining rows with customers who got in line too late.
        for (int i=0; i<aSize; i++) {
            Customer d = (Customer) leftAfterClose.dequeueFinal();
            
            rows[i+bSize][0] = d.getName();
            rows[i+bSize][1] = d.getType();
            rows[i+bSize][2] = "Too Late!";
            rows[i+bSize][3] = "n/a";
            rows[i+bSize][4] = d.getPhrase();
            rows[i+bSize][5] = "Sorry, we're closed.";
            rows[i+bSize][6] = d.getPriority();
            rows[i+bSize][7] = "n/a";
        }
        // creating the table
        table = new JTable(rows, columnNames) {
            // disabling cell editing
            public boolean isCellEditable(int rows, int columns) {
                return false;
            }
            // coloring the rows of the table
            public Component prepareRenderer(TableCellRenderer r, int rows, int columns) {
                Component c = super.prepareRenderer(r, rows, columns);
                
                if (rows % 2 == 0) c.setBackground(Color.WHITE);
                else c.setBackground(Color.LIGHT_GRAY);
                
                if (isCellSelected(rows, columns)) c.setBackground(Color.CYAN);
                
                return c;
            }
            // setting the scroll size for the table in its window
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
    }
}
