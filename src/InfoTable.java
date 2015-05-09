import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class InfoTable extends JPanel {
    
    JTable jt; 
    
    public InfoTable(PriorityQueue before, PriorityQueue after) {
        
        JFrame tableFrame = new JFrame();
        tableFrame.setTitle("Customer Information");
        tableFrame.setBounds(50,50,857,257);
        //tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int bSize = before.size();
        int aSize = after.size();
        int numRows = bSize + aSize;
        
        String[] columns = {"Name","Type of Customer","Start Time","End Time",
                            "Querie","Answer","Priority","Remaining customers"};
        
        String[][] rows;
        
        rows = new String[numRows][8];
        
        for (int i=0; i<bSize; i++) {
            Customer c = (Customer) before.dequeueFinal();
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
        
        for (int i=0; i<aSize; i++) {
            Customer d = (Customer) after.dequeueFinal();
            
            rows[i+bSize][0] = d.getName();
            rows[i+bSize][1] = d.getType();
            rows[i+bSize][2] = d.getStartTimeF();
            rows[i+bSize][3] = d.getAnswerTimeF();
            rows[i+bSize][4] = d.getPhrase();
            rows[i+bSize][5] = d.getAnswer();
            rows[i+bSize][6] = d.getPriority();
            rows[i+bSize][7] = d.getLineLength();
        }
        for (int i=0; i<rows.length; i++) {
            System.out.println(rows[i][0]);
        }
        jt = new JTable(rows, columns) {
            public boolean isCellEditable(int rows, int columns) {
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
        
        
        
        
        //this.setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
        //this.setVisible(true);
        //this.setBounds(50,50,800,200);
        jt.setPreferredScrollableViewportSize(new Dimension(825,225));
        jt.setFillsViewportHeight(true);
        add(jt);
        JScrollPane scroll = new JScrollPane(jt);
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);
        tableFrame.add(this);
        tableFrame.setVisible(true);
    }
}
