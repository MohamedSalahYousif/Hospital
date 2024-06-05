import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class draw extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JLabel nameLabel = new JLabel("Doctor name:");
    JButton nextPatientButton = new JButton("Show next patient");
    JButton allPatientsButton = new JButton("Show all patients");

    public draw() {
        setupUI();
        allPatientsButton.addActionListener(this); 
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allPatientsButton) {
            showAllPatients(); 
        }
    }
    
    private void showAllPatients() {
        String name, email, age, query;
        String sUSER, sURL, sPASS;
        sPASS="";
        sUSER="root";
        sURL="jdbc:MySQL://localhost:3306/clinic";
        query = "SELECT fullName FROM patient";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(sURL,sUSER,sPASS);
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);

            StringBuilder patientList = new StringBuilder();
            while (result.next()) {
                String patientName = result.getString("fullName");
                patientList.append(patientName).append("\n");
            }

            if (patientList.length() > 0) {
                JOptionPane.showMessageDialog(null, "List of patient:\n" + patientList.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No patient found.");
            }

            result.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching doctors. Please check your database connection.");
        }
    }
    
    private void setupUI() {
        // Set up the JFrame
        setTitle("Doctor Account");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use absolute positioning

        // Add components to the panel
        panel.setLayout(null); // Use absolute positioning within the panel
       
        panel.setBounds(0, 0, 600, 300); // Position and size of the panel

        nameLabel.setBounds(30, 30, 150, 20);
        nextPatientButton.setBounds(50, 250, 150, 30);
        allPatientsButton.setBounds(350, 250, 150, 30);

        panel.add(nameLabel);
        panel.add(nextPatientButton);
        panel.add(allPatientsButton);

        // Add the panel to the JFrame
        add(panel);

        // Make the JFrame visible
        setVisible(true);
    }
}
