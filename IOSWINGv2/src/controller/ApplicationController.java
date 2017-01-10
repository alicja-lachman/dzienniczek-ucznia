package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.ActionCommands;
import view.ApplicationView;
import view.dialog.AddGradeDialog;
import view.dialog.AddStudentDialog;

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
        switch (e.getActionCommand()) {
            case (ActionCommands.ADD_STUDENT):
                AddStudentDialog dialog = new AddStudentDialog(view);
                dialog.setVisible(true);
                break;
            case (ActionCommands.LIST_STUDENT):
                listAllStudents();
                break;
            case (ActionCommands.ADD_GRADE):
                AddGradeDialog gradeDialog = new AddGradeDialog(view);
                gradeDialog.setVisible(true);
                break;
        }
    }

    private void listAllStudents() {

    }
}
