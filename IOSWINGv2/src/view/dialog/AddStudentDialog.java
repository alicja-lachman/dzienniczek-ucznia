package view.dialog;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author alachman
 */
public class AddStudentDialog extends JDialog {

    JTextField name;
    JTextField surname;
    JTextField pesel;
    JTextField address;
    JTextField className;

    public AddStudentDialog(Frame parent) {
        super(parent);
        initView();
    }

    private void initView() {

        name = new JTextField(15);
        surname = new JTextField(15);
        pesel = new JTextField(15);
        address = new JTextField(15);
        className = new JTextField(30);

        setResizable(false);
        setTitle("Add new student");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Add new student");
        nameLabel.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 10, 5, 10);
        add(nameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.anchor = GridBagConstraints.EAST;

        add(new JLabel("Name: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Surname: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Pesel: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Address: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Class id: ", SwingConstants.RIGHT), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;

        add(name, constraints);
        add(surname, constraints);
        add(pesel, constraints);
        add(address, constraints);
        add(className, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.SOUTH;

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e)-> dispose());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((ActionEvent e) -> {
            if (allFieldsAreValid()) {
                createNewStudent();
            } else {
                JOptionPane.showMessageDialog(getRootPane(),
                        "Fill all fields!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(cancelButton);
        buttons.add(okButton);

        add(buttons, constraints);
        pack();

        setModalityType(ModalityType.APPLICATION_MODAL);

        setTitle("Add new student");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(okButton);

    }

    private boolean allFieldsAreValid() {
        return !name.getText().isEmpty() && !surname.getText().isEmpty() && !pesel.getText().isEmpty()
                && !address.getText().isEmpty() && !className.getText().isEmpty();
    }

    private void createNewStudent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
