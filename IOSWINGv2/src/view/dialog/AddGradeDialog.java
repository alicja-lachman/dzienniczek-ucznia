/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;

import entity.Klasy;
import entity.Nauczyciele;
import entity.Oceny;
import entity.Przedmioty;
import entity.Uczniowie;
import entity.Uzytkownicy;
import javax.swing.JDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import util.UserDataParser;

/**
 *
 * @author m_lig
 */
public class AddGradeDialog extends JDialog {

    JComboBox subject;
    JComboBox className;
    JComboBox student;
    JTextField grade;
    JTextField description;

    public AddGradeDialog(Frame parent) {
        super(parent);
        initView();
    }

    private void initView() {

        subject = new JComboBox(getSubjects().toArray());
        className = new JComboBox(getClassInfo().toArray());
        className.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                student.removeAllItems();
                for (String s : getStudentInfo()) {
                    student.addItem(s);
                }
            }
        });
        student = new JComboBox(getStudentInfo().toArray());
        grade = new JTextField(15);
        description = new JTextField(25);

        setResizable(false);
        setTitle("Add new student");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Add new grade");
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

        add(new JLabel("Subject: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Class: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Student: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Grade: ", SwingConstants.RIGHT), constraints);
        add(new JLabel("Description ", SwingConstants.RIGHT), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;

        add(subject, constraints);
        add(className, constraints);
        add(student, constraints);
        add(grade, constraints);
        add(description, constraints);

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
                addNewGrade();
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

        setTitle("Add new grade");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(okButton);

    }

    private boolean allFieldsAreValid() {
        return (!grade.getText().isEmpty() && !description.getText().isEmpty());
    }

    private List<String> getSubjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Przedmioty> subjects = session.createCriteria(Przedmioty.class)
                .list();
        List<String> subjectInfo = new ArrayList<>();
        for (Przedmioty dbSubject : subjects) {
            subjectInfo.add(dbSubject.getNazwa());
        }
        session.close();
        return subjectInfo;
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

    private List<String> getStudentInfo() {
        String QUERY_CLASS_ID = "from Klasy k where k.nazwa='";
        String QUERY_STUDENT = "from Uczniowie where Klasy_idk=";
        Klasy dbClass;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(QUERY_CLASS_ID
                + ClassDataParser.getClassName((String) className.getSelectedItem())
                + "' and year(k.rocznik) = '"
                + ClassDataParser.getClassYear((String) className.getSelectedItem())
                + "'");
        List<Klasy> resultList = q.list();
        dbClass = resultList.get(0); //get class

        Query studentQuery = session.createQuery(QUERY_STUDENT
                + dbClass.getIdk());
        List<Uczniowie> students = studentQuery.list();  //get students

        Query userQuery = session.createQuery("from Uzytkownicy where iduz in ("
                + getUserIds(students)
                + ")");
        List<Uzytkownicy> users = userQuery.list();  //get students

        List<String> studentInfo = new ArrayList<>();
        for (Uzytkownicy dbUser : users) {
            studentInfo.add(dbUser.getImie() + " " + dbUser.getNazwisko());
        }
        session.close();
        return studentInfo;
    }

    private void addNewGrade() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Przedmioty where nazwa like '"
                + (String) subject.getSelectedItem() + "'");

        Przedmioty dbSubject = (Przedmioty) q.list().get(0);
        //create new grade
        Query query = session.createQuery("from Nauczyciele where idn = 1 ");
        Nauczyciele dbTeacher = (Nauczyciele) query.list().get(0);

        Query queryUser = session.createQuery("from Uzytkownicy where imie like '"
                + UserDataParser.getName((String) student.getSelectedItem()) + "'"
                + " and nazwisko like '"
                + UserDataParser.getSurname((String) student.getSelectedItem()) + "'");
        Uzytkownicy dbUser = (Uzytkownicy) queryUser.list().get(0);

        Query queryStudent = session.createQuery("from Uczniowie where Uzytkownicy_iduz = "
                + dbUser.getIduz());
        List<Uczniowie> studentList = queryStudent.list();
        Uczniowie dbStudent = studentList.get(0);

        Oceny newGrade = new Oceny();
        newGrade.setNauczyciele(dbTeacher);
        newGrade.setUczniowie(dbStudent);
        newGrade.setPrzedmioty(dbSubject);
        newGrade.setData(new java.util.Date());
        newGrade.setOpis(description.getText());
        newGrade.setWartosc(Float.parseFloat(grade.getText()));
        //add user to database
        session.save(newGrade);

//        //Commit the transaction
        session.getTransaction().commit();
        session.close();

    }

    private String getUserIds(List<Uczniowie> students) {

        StringBuilder userIds = new StringBuilder();
        for (Uczniowie student : students) {
            userIds.append(student.getUzytkownicy().getIduz() + ",");
        }
        userIds.deleteCharAt(userIds.length() - 1);
        return userIds.toString();
    }

}
