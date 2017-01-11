/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.listing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

    private void getColumnsInfo() throws SQLException {
        ArrayList<String> columnNames = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:6033/io", "root", null);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM "+classInfo.getTable().getName());
        ResultSetMetaData rsmt = rs.getMetaData();
        
        for(int i=0; i<rsmt.getColumnCount(); i++){
            columnNames.add(rsmt.getColumnName(i+1));
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

}
