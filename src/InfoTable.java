
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aaron
 */
public class InfoTable extends JFrame {
    
    JTable jt; 
    String[][] rows;
    
    public InfoTable(PriorityQueue before, PriorityQueue after) {
        
        String[] columns = {"Name","Type of Customer","Start Time","End Time",
                            "Querie","Answer","Priority","Remaining customers"};
        
        rows = new String[before.size() + after.size() + 1][8];
        
        for (int i=0; i<before.size(); i++) {
            String[] thisCustomer = new String[8];
            Customer c = (Customer) before.removeFirst();
            
            rows[i+1][0] = c.getName();
            rows[i+1][1] = c.getType();
            rows[i+1][2] = c.getStartTimeF();
            rows[i+1][3] = c.getAnswerTimeF();
            rows[i+1][4] = c.getPhrase();
            rows[i+1][5] = c.getAnswer();
            rows[i+1][6] = c.getPriority();
            rows[i+1][7] = c.getLineLength();
        }
        
        for (int i=0; i<after.size(); i++) {
            String[] thisCustomer = new String[8];
            Customer d = (Customer) after.removeFirst();
            
            rows[i+before.size()][0] = d.getName();
            rows[i+before.size()][1] = d.getType();
            rows[i+before.size()][2] = d.getStartTimeF();
            rows[i+before.size()][3] = d.getAnswerTimeF();
            rows[i+before.size()][4] = d.getPhrase();
            rows[i+before.size()][5] = d.getAnswer();
            rows[i+before.size()][6] = d.getPriority();
            rows[i+before.size()][7] = d.getLineLength();
        }
        
        jt = new JTable(rows,columns) {
            public boolean isCellSelected(int rows, int columns) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r, int rows, int columns) {
                Component c = super.prepareRenderer(r, rows, columns);
                
                if (rows % 2 == 0) c.setBackground(Color.WHITE);
                else c.setBackground(Color.LIGHT_GRAY);
                
                if (isCellSelected(rows, columns)) c.setBackground(Color.BLUE);
                
                return c;
            }
        };
        jt.setPreferredScrollableViewportSize(new Dimension(800,200));
        jt.setFillsViewportHeight(true);
        
        JScrollPane scroll = new JScrollPane();
        add(scroll);
    }
}
