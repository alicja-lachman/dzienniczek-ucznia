package view;

import controller.ApplicationController;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import util.ActionCommands;
import util.HibernateUtil;

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
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                HibernateUtil.getSessionFactory().close();
                System.exit(0);
            }
        });

        setupMenu();
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu addMenu = new JMenu("Add new user");
        JMenuItem addStudentItem = new JMenuItem("Add student");
        addStudentItem.setActionCommand(ActionCommands.ADD_STUDENT);
        addStudentItem.addActionListener(listener);
        addMenu.add(addStudentItem);

        JMenuItem addTeacherItem = new JMenuItem("Add teacher");
        addTeacherItem.setActionCommand(ActionCommands.ADD_TEACHER);
        addTeacherItem.addActionListener(listener);
        addMenu.add(addTeacherItem);

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
