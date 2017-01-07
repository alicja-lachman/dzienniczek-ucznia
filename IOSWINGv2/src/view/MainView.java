/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author alachman
 */
public class MainView extends JFrame{
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    public MainView(){
    setupGUI();
    }

    private void setupGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setupMenu();
    }

    private void setupMenu() {
       JMenuBar menuBar = new JMenuBar();
        JMenu addMenu = new JMenu("Add..");
        JMenuItem addStudentItem = new JMenuItem("Add student");
       addMenu.add(addStudentItem);
        menuBar.add(addMenu);
        setJMenuBar(menuBar);
    }
}
