/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.listing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;

/**
 *
 * @author Wojciech Paczuła
 */
public class ListTableFrame extends JFrame {

    private PersistentClass classInfo;
    private String[] columns;
    private Object[][] data;

    public ListTableFrame(PersistentClass classInfo) {
        super();
        try {
            this.classInfo = classInfo;
            getColumnsInfo();
            getObjectsInfo();
            initView();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initView() {
        this.setTitle("Table " + classInfo.getTable().getName());

        final JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        this.pack();
    }

    private void getColumnsInfo() {
        ArrayList<String> columnNames = new ArrayList<>();
        Iterator iter = classInfo.getTable().getColumnIterator();
        while (iter.hasNext()) {
            Column column = (Column) iter.next();
            columnNames.add(column.getName());
        }
        this.columns = Arrays.copyOf(columnNames.toArray(), columnNames.size(), String[].class);
    }

    private void getObjectsInfo() throws SQLException {
        List<Object[]> objects = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:6033/io", "root", null);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + classInfo.getTable().getName());
        while (rs.next()) {
            List<Object> row = new ArrayList<>();
            for (int i = 0; i < columns.length; i++) {
                row.add(rs.getObject(i + 1));
            }
            objects.add(row.toArray());
        }
        this.data = objects.toArray(new Object[0][]);
    }
    /*public SimpleTableDemo() {
     super(new GridLayout(1,0));
 
     String[] columnNames = {"First Name",
     "Last Name",
     "Sport",
     "# of Years",
     "Vegetarian"};
 
     Object[][] data = {
     {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
     {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
     {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
     {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
     {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}
     };
 
     final JTable table = new JTable(data, columnNames);
     table.setPreferredScrollableViewportSize(new Dimension(500, 70));
     table.setFillsViewportHeight(true);
 
     if (DEBUG) {
     table.addMouseListener(new MouseAdapter() {
     public void mouseClicked(MouseEvent e) {
     printDebugData(table);
     }
     });
     }
 
     //Create the scroll pane and add the table to it.
     JScrollPane scrollPane = new JScrollPane(table);
 
     //Add the scroll pane to this panel.
     add(scrollPane);
     }*/
}
