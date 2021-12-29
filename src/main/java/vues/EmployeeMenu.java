package vues;

import dao.EmployeeDao;
import models.Address;
import models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EmployeeMenu extends JFrame{
    private JPanel mainPanel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField fatherName;
    private JTextField birthday;
    private JTextField cinNumber;
    private JTextField phoneNumber;
    private JTextField postalCode;
    private JTextField roadName;
    private JTextField city;
    private JButton addEmployeeButton;
    private JButton updateEmployeeButton;
    private JTable table1;
    private JScrollPane scrollPanel;

    EmployeeDao dao = EmployeeDao.getInstance();
    JFrame frame = new JFrame();


    DefaultTableModel model;


    Object[] column = {"ID","First name","Last Name","birthday","Cin Number","Phone","Postal Code"," road Name","City"};
    Object[] row= new Object[0];

    private void fillTable() throws Exception{
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table1.setModel(model);

        List<Employee> employees = new LinkedList<>();
        employees = dao.findAll();
        for (int i=0;i<employees.size();i++)
        {
            Employee e = employees.get(i) ;
            Address a = new Address();
            a = e.getAddress();
            model.addRow(new Object[] { e.getId(),e.getFirstName(),e.getLastName(),e.getFatherName(),e.getBirthday(),e.getCinNumber(),e.getPhoneNumber(),
                    a.getPostalCode(),a.getRoadName(),a.getCity()});
        }

        table1.setModel(model);
    }










    public EmployeeMenu() throws Exception {
        setContentPane(mainPanel);
        mainPanel.setSize(600,600);
        setTitle("Home");
        setSize(1000, 600);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
        fillTable();








        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==addEmployeeButton){

                    Employee emp = new Employee();
                    emp.setFirstName(firstName.getText());
                    emp.setLastName(lastName.getText());
                    emp.setFatherName(fatherName.getText());
                    emp.setCinNumber(cinNumber.getText());
                    emp.setPhoneNumber(phoneNumber.getText());
                    emp.setAddress(new Address());
                    emp.getAddress().setRoadName(roadName.getText());
                    emp.getAddress().setPostalCode(Integer.parseInt(postalCode.getText()));
                    emp.getAddress().setCity(city.getText());
                    emp.setBirthday(LocalDate.now());
                    dao.save(emp);
                    model.addRow(new Object[] { emp.getId(),emp.getFirstName(),emp.getLastName(),emp.getFatherName(),emp.getBirthday(),emp.getCinNumber(),emp.getPhoneNumber(),
                            emp.getAddress().getPostalCode(),emp.getAddress().getRoadName(),emp.getAddress().getCity()});
                    JOptionPane.showMessageDialog(frame,"Employee added successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                   lastName.setText("");
                    fatherName.setText("");
                    cinNumber.setText("");
                    phoneNumber.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    city.setText("");
                }
            }
        });
        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==updateEmployeeButton){

                    Employee emp = new Employee();
                    emp.setFirstName(firstName.getText());
                    emp.setLastName(lastName.getText());
                    emp.setFatherName(fatherName.getText());
                    emp.setCinNumber(cinNumber.getText());
                    emp.setPhoneNumber(phoneNumber.getText());
                    emp.setAddress(new Address());
                    emp.getAddress().setRoadName(roadName.getText());
                    emp.getAddress().setPostalCode(Integer.parseInt(postalCode.getText()));
                    emp.getAddress().setCity(city.getText());
                    emp.setBirthday(LocalDate.now());
                    dao.save(emp);
                    model.addRow(new Object[] { emp.getId(),emp.getFirstName(),emp.getLastName(),emp.getFatherName(),emp.getBirthday(),emp.getCinNumber(),emp.getPhoneNumber(),
                            emp.getAddress().getPostalCode(),emp.getAddress().getRoadName(),emp.getAddress().getCity()});
                    JOptionPane.showMessageDialog(frame,"Employee added successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                    lastName.setText("");
                    fatherName.setText("");
                    cinNumber.setText("");
                    phoneNumber.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    city.setText("");
                }

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Employee emp = new Employee();
                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();
              //  firstName.setText(model.setValueAt(selectedRow,1););
            }
        });
    }

}
