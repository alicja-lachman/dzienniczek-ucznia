package view.dialog;

import entity.Klasy;
import entity.Uczniowie;
import entity.Uzytkownicy;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.hibernate.Query;
import org.hibernate.Session;
import util.ClassDataParser;
import util.HibernateUtil;

/**
 *
 * @author alachman
 */
public class AddStudentDialog extends JDialog {

    JTextField name;
    JTextField surname;
    JTextField pesel;
    JTextField address;
    JComboBox className;

    public AddStudentDialog(Frame parent) {
        super(parent);
        initView();
    }

    private void initView() {

        name = new JTextField(15);
        surname = new JTextField(15);
        pesel = new JTextField(15);
        address = new JTextField(15);
        className = new JComboBox(getClassInfo().toArray());

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
        cancelButton.addActionListener((ActionEvent e) -> dispose());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((ActionEvent e) -> {
            if (allFieldsAreValid()) {
                createNewStudent();
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

        setTitle("Add new student");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(okButton);

    }

    private boolean allFieldsAreValid() {
        return !name.getText().isEmpty() && !surname.getText().isEmpty() && !pesel.getText().isEmpty()
                && !address.getText().isEmpty();
    }

    private List<String> getClassInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Klasy> classes = session.createCriteria(Klasy.class)
                .list();
        List<String> classInfo = new ArrayList<>();
        for (Klasy dbClass : classes) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(dbClass.getRocznik());
            classInfo.add(dbClass.getNazwa() + " " + year);
        }
        session.close();
        return classInfo;
    }

    private void createNewStudent() {
        Uzytkownicy dbUser;
        Klasy dbClass;
        String QUERY_CLASS_ID = "from Klasy k where k.nazwa='";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //create new user
        Uzytkownicy user = new Uzytkownicy(name.getText(), surname.getText(),
                pesel.getText(), address.getText());
        //add user to database
        session.save(user);

        //find given class in database
        try {
            Query q = session.createQuery(QUERY_CLASS_ID
                    + ClassDataParser.getClassName((String) className.getSelectedItem())
                    + "' and year(k.rocznik) = '"
                    + ClassDataParser.getClassYear((String) className.getSelectedItem())
                    + "'");
            List<Klasy> resultList = q.list();
            dbClass = resultList.get(0);
        } catch (IndexOutOfBoundsException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(getRootPane(),
                    "Wrong class id",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //find created user in database
        List<Uzytkownicy> uzytkownicy = session.createCriteria(Uzytkownicy.class)
                .list();

        //most recently created user
        dbUser = uzytkownicy.get(uzytkownicy.size() - 1);

        //create new student
        Uczniowie student = new Uczniowie(dbClass, dbUser);
        session.save(student);

        //Commit the transaction
        session.getTransaction().commit();
        session.close();
    }

}
