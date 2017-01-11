/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import view.listing.ListTableFrame;

/**
 *
 * @author Wojciech Paczuła
 */
public class ListDialog extends JDialog {

    JComboBox tables;
    List<PersistentClass> classes;

    public ListDialog(Frame parent) {
        super(parent);
        initView();
    }

    private void initView() {
        classes = getTablesInfo();
        tables = new JComboBox(classes.stream()
                .map((classInfo) -> classInfo.getTable().getName())
                .toArray());
        setResizable(false);
        setTitle("Choose table");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Choose table to be listed");
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

        add(new JLabel("Tables: ", SwingConstants.RIGHT), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;

        add(tables, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.SOUTH;

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> dispose());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((ActionEvent e) -> {
            listTables(classes.get(tables.getSelectedIndex()));
            dispose();
        });

        JPanel buttons = new JPanel();
        buttons.add(cancelButton);
        buttons.add(okButton);

        add(buttons, constraints);
        pack();

        setModalityType(ModalityType.APPLICATION_MODAL);

        setTitle("Choose table");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(okButton);

    }

    private void listTables(PersistentClass classInfo) {
        ListTableFrame listing = new ListTableFrame(classInfo);
        listing.setVisible(true);
    }

    private List<PersistentClass> getTablesInfo() {
        ArrayList<PersistentClass> classList = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Iterator<PersistentClass> classMappings = configuration.getClassMappings();

        while (classMappings.hasNext()) {
            classList.add(classMappings.next());
        }
        return classList;
    }
}
