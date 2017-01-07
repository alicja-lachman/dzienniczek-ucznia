package view;

import controller.ApplicationController;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import util.ActionCommands;

/**
 *
 * @author alachman
 */
public class ApplicationView extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private ActionListener listener;

    public void setup() {
        setupGUI();
    }

    private void setupGUI() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setupMenu();
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu addMenu = new JMenu("Add new user");
        JMenuItem addStudentItem = new JMenuItem("Add student");
        addStudentItem.setActionCommand(ActionCommands.ADD_STUDENT);
        addStudentItem.addActionListener(listener);
        addMenu.add(addStudentItem);

        JMenu listMenu = new JMenu("List all...");
        JMenuItem listAllStudents = new JMenuItem("List all students");
        listAllStudents.setActionCommand(ActionCommands.LIST_STUDENT);
        listAllStudents.addActionListener(listener);

        listMenu.add(listAllStudents);

        menuBar.add(addMenu);
        menuBar.add(listMenu);
        setJMenuBar(menuBar);
    }

    public void addListener(ActionListener listener) {
        this.listener = listener;
    }
}