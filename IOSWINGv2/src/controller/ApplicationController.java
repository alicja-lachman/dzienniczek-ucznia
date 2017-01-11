package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import util.ActionCommands;
import view.ApplicationView;
import view.dialog.AddGradeDialog;
import view.dialog.AddStudentDialog;
import view.dialog.AddTeacherDialog;
import view.dialog.ListDialog;

/**
 *
 * @author alachman
 */
public class ApplicationController implements ActionListener {

    private ApplicationView view;

    public ApplicationController(ApplicationView view) {
        this.view = view;
        this.view.addListener(this);
        this.view.setup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog;
        switch (e.getActionCommand()) {
            case (ActionCommands.ADD_STUDENT):
                dialog = new AddStudentDialog(view);
                dialog.setVisible(true);
                break;
            case (ActionCommands.ADD_TEACHER):
                dialog = new AddTeacherDialog(view);
                dialog.setVisible(true);
                break;
            case (ActionCommands.LIST_DATA):
                dialog = new ListDialog(view);
                dialog.setVisible(true);
                break;
   			case (ActionCommands.ADD_GRADE):
                AddGradeDialog gradeDialog = new AddGradeDialog(view);
                gradeDialog.setVisible(true);
                break;

        }
    }
}
