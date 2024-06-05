import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class Main extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField ageField;

    public Main() {
        createView();
        setTitle("Patient Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, constraints);

        constraints.gridy++;
        nameField = new JTextField(20);
        panel.add(nameField, constraints);

        constraints.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel, constraints);

        constraints.gridy++;
        emailField = new JTextField(20);
        panel.add(emailField, constraints);

        constraints.gridy++;
        JLabel ageLabel = new JLabel("Age:");
        panel.add(ageLabel, constraints);

        constraints.gridy++;
        ageField = new JTextField(20);
        panel.add(ageField, constraints);

        constraints.gridy++;
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
    }

    private void submitForm() {
                String patientName = nameField.getText();
                String patientEmail = emailField.getText();
                String patienAge = ageField.getText();
                String name,email ,age ,query ;
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
                }else if("".equals(ageField.getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"phone_number is required ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    name=nameField.getText();
                    email=emailField.getText();
                    age=ageField.getText();
                    query = "INSERT INTO patient(fullName, age, email) VALUES ('" + name + "', '" + age + "', '" + email + "')";                   
                    st.execute(query);
                    nameField.setText("");
                    ageField.setText("");
                    emailField.setText("");
                   
                }
                    st.close();
                    con.close();
            }catch(Exception ee){
                System.out.println("error"+ee.getMessage());
            }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
}
}