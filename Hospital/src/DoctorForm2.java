import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorForm2 extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    public DoctorForm2() {
        setTitle("Doctor Information Form");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Doctor Name"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Doctor Email"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Doctor Phone Number"), gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        add(submitButton, gbc);

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = nameField.getText();
                String doctorEmail = emailField.getText();
                String doctorPhone = phoneField.getText();
                String name,email ,phone ,query ;
                String sUSER ,sURL ,sPASS;
                sPASS="";
                sUSER="root";
                sURL="jdbc:MySQL://localhost:3306/clinic";

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection(sURL,sUSER,sPASS);
                Statement st = con.createStatement();
                if("".equals(nameField.getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"nameField is required ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else if("".equals(emailField.getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"emailField is required ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else if("".equals(phoneField.getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"phone_number is required ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    name=nameField.getText();
                    email=emailField.getText();
                    phone=phoneField.getText();
                    query = "INSERT INTO doctors(fullname, email, `Doctor phone`) VALUES ('" + name + "', '" + email + "', '" + phone + "')";                   
                    st.execute(query);
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                   
                }
                    st.close();
                    con.close();

            }catch(Exception ee){
                System.out.println("error"+ee.getMessage());
            }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DoctorForm2());
}
}