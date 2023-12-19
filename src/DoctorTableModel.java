import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class DoctorTableModel {
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    static final Color background = new Color(234, 250, 243);
    static final Color buttonBackground = new Color(189, 189, 189);
    static final Color buttonRedBackground = new Color(201, 73, 73);
    static final Color buttonActive = new Color(160, 160, 160);
    static final Color buttonRedActive = new Color(169, 67, 67);
    private ResultSet resultSet;
    private Statement statement;
    private JFrame frame;
    private JLabel tableLabel;
    private JLabel IDLabel;
    private JLabel surnameLabel;
    private JLabel nameLabel;
    private JLabel patronymicLabel;
    private JLabel birthdayLabel;
    private JLabel jobTitleLabel;
    private JLabel specializationLabel;

    private JTextField IDTextField;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JTextField patronymicTextField;
    private JTextField birthdayTextField;
    private JTextField jobTitleTextField;
    private JTextField specializationTextField;

    private JButton changeTableButton;
    private JButton addPatientButton;
    private JButton deletePatientButton;
    private JButton changePatientButton;
    private JButton saveAddButton;
    private JButton saveChangesButton;
    private JButton cancelChangesButton;
    private JButton nextButton;
    private JButton previousButton;

    public DoctorTableModel(ResultSet resultSett, Statement statementt) throws SQLException {
        resultSet = resultSett;
        statement = statementt;
        resultSet.next();

        frame = new JFrame();

        frame.getContentPane().setBackground(background);
        frame.setTitle("Doctors table");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(null);

        tableLabel = new JLabel("Doctors table model");
        tableLabel.setBounds(100,20,150,25);

        IDLabel = new JLabel("ID");
        IDLabel.setBounds(100,70,100,25);

        surnameLabel = new JLabel("Surname");
        surnameLabel.setBounds(100,105,100,25);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(100,140,100,25);

        patronymicLabel = new JLabel("Patronymic");
        patronymicLabel.setBounds(100,175,100,25);

        birthdayLabel = new JLabel("Birthday");
        birthdayLabel.setBounds(100,210,100,25);

        jobTitleLabel = new JLabel("JobTitle");
        jobTitleLabel.setBounds(100,245,100,25);

        specializationLabel = new JLabel("Specialization");
        specializationLabel.setBounds(100,280,100,25);


        IDTextField = new JTextField();
        IDTextField.setBounds(200,70,150,25);
        IDTextField.setText(resultSet.getString(1));
        IDTextField.setEditable(false);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(200,105,150,25);
        surnameTextField.setText(resultSet.getString(2));
        surnameTextField.setEditable(false);

        nameTextField = new JTextField();
        nameTextField.setBounds(200,140,150,25);
        nameTextField.setText(resultSet.getString(3));
        nameTextField.setEditable(false);

        patronymicTextField = new JTextField();
        patronymicTextField.setBounds(200,175,150,25);
        patronymicTextField.setText(resultSet.getString(4));
        patronymicTextField.setEditable(false);

        birthdayTextField = new JTextField();
        birthdayTextField.setBounds(200,210,150,25);
        birthdayTextField.setText(resultSet.getString(5));
        birthdayTextField.setEditable(false);

        jobTitleTextField = new JTextField();
        jobTitleTextField.setBounds(200,245,150,25);
        jobTitleTextField.setText(resultSet.getString(6));
        jobTitleTextField.setEditable(false);

        specializationTextField = new JTextField();
        specializationTextField.setBounds(200,280,150,25);
        specializationTextField.setText(resultSet.getString(6));
        specializationTextField.setEditable(false);


        changeTableButton = new JButton("Go to the patients table");
        changeTableButton.setBounds(350,20,200,25);
        changeTableButton.setBackground(buttonBackground);

        previousButton = new JButton("<<--");
        previousButton.setBounds(200,315,70,25);
        previousButton.setBackground(buttonBackground);

        nextButton = new JButton("-->>");
        nextButton.setBounds(280,315,70,25);
        nextButton.setBackground(buttonBackground);

        addPatientButton = new JButton("Add new doctor");
        addPatientButton.setBounds(200,350,150,25);
        addPatientButton.setBackground(buttonBackground);

        saveAddButton = new JButton("Save changes");
        saveAddButton.setBounds(200,455,150,25);
        saveAddButton.setBackground(buttonBackground);
        saveAddButton.setVisible(false);

        deletePatientButton = new JButton("Delete this doctor");
        deletePatientButton.setBounds(200,385,150,25);
        deletePatientButton.setBackground(buttonBackground);

        changePatientButton = new JButton("Change this doctor");
        changePatientButton.setBounds(200,420,150,25);
        changePatientButton.setBackground(buttonBackground);

        saveChangesButton = new JButton("Save changes");
        saveChangesButton.setBounds(200,455,150,25);
        saveChangesButton.setBackground(buttonBackground);
        saveChangesButton.setVisible(false);

        cancelChangesButton = new JButton("Cancel changes");
        cancelChangesButton.setBounds(200,490,150,25);
        cancelChangesButton.setBackground(buttonBackground);
        cancelChangesButton.setVisible(false);

        if(resultSet.isLast()){
            nextButton.setEnabled(false);
        }
        previousButton.setEnabled(false);

        frame.add(tableLabel);
        frame.add(IDLabel);
        frame.add(surnameLabel);
        frame.add(nameLabel);
        frame.add(patronymicLabel);
        frame.add(birthdayLabel);
        frame.add(jobTitleLabel);
        frame.add(specializationLabel);

        frame.add(IDTextField);
        frame.add(surnameTextField);
        frame.add(nameTextField);
        frame.add(patronymicTextField);
        frame.add(birthdayTextField);
        frame.add(jobTitleTextField);
        frame.add(specializationTextField);

        frame.add(changeTableButton);
        frame.add(previousButton);
        frame.add(nextButton);
        frame.add(addPatientButton);
        frame.add(saveAddButton);
        frame.add(deletePatientButton);
        frame.add(changePatientButton);
        frame.add(saveChangesButton);
        frame.add(cancelChangesButton);

        nextButton.addActionListener(new NextActionListener());
        previousButton.addActionListener(new PreviousActionListener());
        addPatientButton.addActionListener(new AddActionListener());
        changePatientButton.addActionListener((new ChangeActionListener()));
        cancelChangesButton.addActionListener(new CancelActionListener());
        saveAddButton.addActionListener(new SaveAddActionListener());
        saveChangesButton.addActionListener(new SaveChangesActionListener());
        deletePatientButton.addActionListener(new DeleteActionListener());

        changeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");
                    new PatientTableModel(resultSet, statement);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.setVisible(true);
    }

    public class NextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                resultSet.next();
                previousButton.setEnabled(true);
                if(resultSet.isLast()){
                    nextButton.setEnabled(false);
                }
                IDTextField.setText(resultSet.getString(1));
                surnameTextField.setText(resultSet.getString(2));
                nameTextField.setText(resultSet.getString(3));
                patronymicTextField.setText(resultSet.getString(4));
                birthdayTextField.setText(resultSet.getString(5));
                jobTitleTextField.setText(resultSet.getString(6));
                specializationTextField.setText(resultSet.getString(7));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class PreviousActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                resultSet.previous();
                nextButton.setEnabled(true);
                if(resultSet.isFirst()){
                    previousButton.setEnabled(false);
                }
                IDTextField.setText(resultSet.getString(1));
                surnameTextField.setText(resultSet.getString(2));
                nameTextField.setText(resultSet.getString(3));
                patronymicTextField.setText(resultSet.getString(4));
                birthdayTextField.setText(resultSet.getString(5));
                jobTitleTextField.setText(resultSet.getString(6));
                specializationTextField.setText(resultSet.getString(7));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            addPatientButton.setEnabled(false);
            deletePatientButton.setEnabled(false);
            changePatientButton.setEnabled(false);
            saveAddButton.setVisible(true);
            cancelChangesButton.setVisible(true);

            IDTextField.setText(null);
            surnameTextField.setText(null);
            surnameTextField.setEditable(true);
            nameTextField.setText(null);
            nameTextField.setEditable(true);
            patronymicTextField.setText(null);
            patronymicTextField.setEditable(true);
            birthdayTextField.setText(null);
            birthdayTextField.setEditable(true);
            jobTitleTextField.setText(null);
            jobTitleTextField.setEditable(true);
            specializationTextField.setText(null);
            specializationTextField.setEditable(true);

        }
    }
    public class ChangeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            addPatientButton.setEnabled(false);
            deletePatientButton.setEnabled(false);
            changePatientButton.setEnabled(false);
            saveChangesButton.setVisible(true);
            cancelChangesButton.setVisible(true);

            surnameTextField.setEditable(true);
            nameTextField.setEditable(true);
            patronymicTextField.setEditable(true);
            birthdayTextField.setEditable(true);
            jobTitleTextField.setEditable(true);
            specializationTextField.setEditable(true);
        }
    }
    public class SaveAddActionListener implements ActionListener {
        String surname;
        String namee;
        String patronymic;
        String birthday;
        String jobTitle;
        String specialization;
        @Override
        public void actionPerformed(ActionEvent e) {
            surname = surnameTextField.getText();
            namee = nameTextField.getText();
            patronymic = patronymicTextField.getText();
            birthday = birthdayTextField.getText();
            jobTitle = jobTitleTextField.getText();
            specialization = specializationTextField.getText();

            Pattern pattern = Pattern.compile(DATE_PATTERN);
            Matcher matcher = pattern.matcher(birthday);

            if(surname.isEmpty() || namee.isEmpty() || patronymic.isEmpty() || birthday.isEmpty() || jobTitle.isEmpty() || specialization.isEmpty()){
                new DialogWindow("Значения полей не могут быть пустыми!");
            } else if(!matcher.matches()) {
                new DialogWindow("Значение поля Birthday должно соответствовать шаблону ГГГГ-ММ-ДД");
            }else{
                try {
                    statement.execute("INSERT INTO doctor(Surname, Namee, Patronymic, Birthday, JobTitle, Specialization) VALUES ('"+surname+"', '"+namee+"', '"+patronymic+"', '"+birthday+"', '"+jobTitle+"', '"+specialization+"')");
                    resultSet = statement.executeQuery("SELECT * FROM doctor");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                nextButton.setEnabled(false);
                previousButton.setEnabled(true);
                addPatientButton.setEnabled(true);
                deletePatientButton.setEnabled(true);
                changePatientButton.setEnabled(true);
                saveAddButton.setVisible(false);
                cancelChangesButton.setVisible(false);

                try {
                    resultSet.last();
                    IDTextField.setText(resultSet.getString(1));
                    surnameTextField.setText(resultSet.getString(2));
                    surnameTextField.setEditable(false);
                    nameTextField.setText(resultSet.getString(3));
                    nameTextField.setEditable(false);
                    patronymicTextField.setText(resultSet.getString(4));
                    patronymicTextField.setEditable(false);
                    birthdayTextField.setText(resultSet.getString(5));
                    birthdayTextField.setEditable(false);
                    jobTitleTextField.setText(resultSet.getString(6));
                    jobTitleTextField.setEditable(false);
                    specializationTextField.setText(resultSet.getString(7));
                    specializationTextField.setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public class SaveChangesActionListener implements ActionListener {
        String ID;
        String surname;
        String namee;
        String patronymic;
        String birthday;
        String jobTitle;
        String specialization;

        @Override
        public void actionPerformed(ActionEvent e) {
            surname = surnameTextField.getText();
            namee = nameTextField.getText();
            patronymic = patronymicTextField.getText();
            birthday = birthdayTextField.getText();
            jobTitle = jobTitleTextField.getText();
            specialization = specializationTextField.getText();

            Pattern pattern = Pattern.compile(DATE_PATTERN);
            Matcher matcher = pattern.matcher(birthday);

            if(surname.isEmpty() || namee.isEmpty() || patronymic.isEmpty() || birthday.isEmpty() || jobTitle.isEmpty() || specialization.isEmpty()){
                new DialogWindow("Значения полей не могут быть пустыми!");
            } else if(!matcher.matches()) {
                new DialogWindow("Значение поля Birthday должно соответствовать шаблону ГГГГ-ММ-ДД");
            }else{
                try {
                    statement.execute("INSERT INTO doctor(Surname, Namee, Patronymic, Birthday, JobTitle, Specialization) VALUES ('"+surname+"', '"+namee+"', '"+patronymic+"', '"+birthday+"', '"+jobTitle+"', '"+specialization+"')");
                    resultSet = statement.executeQuery("SELECT * FROM doctor");

                    while(resultSet.next()){
                        if(Objects.equals(resultSet.getString(1), ID))
                            break;
                    }
                    if(!resultSet.isFirst()){
                        previousButton.setEnabled(true);
                    }
                    if(!resultSet.isLast()){
                        nextButton.setEnabled(true);
                    }
                    IDTextField.setText(resultSet.getString(1));
                    surnameTextField.setText(resultSet.getString(2));
                    surnameTextField.setEditable(false);
                    nameTextField.setText(resultSet.getString(3));
                    nameTextField.setEditable(false);
                    patronymicTextField.setText(resultSet.getString(4));
                    patronymicTextField.setEditable(false);
                    birthdayTextField.setText(resultSet.getString(5));
                    birthdayTextField.setEditable(false);
                    jobTitleTextField.setText(resultSet.getString(6));
                    jobTitleTextField.setEditable(false);
                    specializationTextField.setText(resultSet.getString(7));
                    specializationTextField.setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                addPatientButton.setEnabled(true);
                deletePatientButton.setEnabled(true);
                changePatientButton.setEnabled(true);
                saveChangesButton.setVisible(false);
                cancelChangesButton.setVisible(false);
            }
        }
    }

    public class DeleteActionListener implements ActionListener {
        String ID;
        @Override
        public void actionPerformed(ActionEvent e) {
            ID = IDTextField.getText();

            try {
                statement.execute("DELETE FROM doctor WHERE DoctorID = '"+ID+"'");
                resultSet = statement.executeQuery("SELECT * FROM doctor");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                resultSet.first();
                IDTextField.setText(resultSet.getString(1));
                surnameTextField.setText(resultSet.getString(2));
                nameTextField.setText(resultSet.getString(3));
                patronymicTextField.setText(resultSet.getString(4));
                birthdayTextField.setText(resultSet.getString(5));
                jobTitleTextField.setText(resultSet.getString(6));
                specializationTextField.setText(resultSet.getString(7));
                previousButton.setEnabled(false);
                nextButton.setEnabled(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class CancelActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(true);
            previousButton.setEnabled(false);
            addPatientButton.setEnabled(true);
            deletePatientButton.setEnabled(true);
            changePatientButton.setEnabled(true);
            saveChangesButton.setVisible(false);
            cancelChangesButton.setVisible(false);

            try {
                IDTextField.setText(resultSet.getString(1));
                surnameTextField.setText(resultSet.getString(2));
                surnameTextField.setEditable(false);
                nameTextField.setText(resultSet.getString(3));
                nameTextField.setEditable(false);
                patronymicTextField.setText(resultSet.getString(4));
                patronymicTextField.setEditable(false);
                birthdayTextField.setText(resultSet.getString(5));
                birthdayTextField.setEditable(false);
                jobTitleTextField.setText(resultSet.getString(6));
                jobTitleTextField.setEditable(false);
                specializationTextField.setText(resultSet.getString(7));
                specializationTextField.setEditable(false);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class DialogWindow {
        JDialog dialog;
        JLabel label;
        JButton button;
        public DialogWindow(String errorMessage){

            dialog = new JDialog(frame,"Error",true);
            dialog.setSize(500, 150);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
            dialog.setLayout(null);

            label = new JLabel(errorMessage);
            label.setBounds(20,20,450,25);

            button = new JButton("OK");
            button.setBounds(200,60,100,25);
            button.addActionListener(e -> dialog.setVisible(false));
            button.setBackground(buttonBackground);

            dialog.add(label);
            dialog.add(button);

            dialog.setVisible(true);
        }
    }
}
