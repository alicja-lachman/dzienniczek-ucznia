/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;


import entity.Nauczyciele;
import entity.Uzytkownicy;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Wojciech Paczuła
 */
public class AddTeacherDialog extends JDialog {

    JTextField name;
    JTextField surname;
    JTextField pesel;
    JTextField address;

    public AddTeacherDialog(Frame parent) {
        super(parent);
        initView();
    }

    private void initView() {
        name = new JTextField(15);
        surname = new JTextField(15);
        pesel = new JTextField(15);
        address = new JTextField(15);

        setResizable(false);
        setTitle("Add new teacher");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Add new teacher");
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

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.SOUTH;

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> dispose());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((ActionEvent e) -> {
            if (allFieldsAreValid()) {
                createNewTeacher();
                dispose();
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

        setTitle("Add new teacher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(okButton);

    }

    private boolean allFieldsAreValid() {
        return !name.getText().isEmpty() && !surname.getText().isEmpty() && !pesel.getText().isEmpty()
                && !address.getText().isEmpty();
    }

    private void createNewTeacher() {
        Uzytkownicy dbUser;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //create new user
        Uzytkownicy user = new Uzytkownicy(name.getText(), surname.getText(),
                pesel.getText(), address.getText());
        //add user to database
        session.save(user);

        //find created user in database
        List<Uzytkownicy> uzytkownicy = session.createCriteria(Uzytkownicy.class)
                .list();

        //most recently created user
        dbUser = uzytkownicy.get(uzytkownicy.size() - 1);

        //create new student
        Nauczyciele teacher = new Nauczyciele(dbUser);
        session.save(teacher);

        //Commit the transaction
        session.getTransaction().commit();
        session.close();
    }
}
