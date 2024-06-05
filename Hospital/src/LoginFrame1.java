import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame1 extends JFrame  implements ActionListener {
    JButton loginButton;
    JTextField userText;
    public LoginFrame1() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(); 
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        JLabel userLabel = new JLabel("Doctor Email:");
        panel.add(userLabel);

        userText = new JTextField(20);
        panel.add(userText);

         loginButton = new JButton("Login");
           loginButton.addActionListener(this);
        panel.add(loginButton);

    
    }
    
    private void takeAction(){
        draw f = new draw();
        f.setVisible(true);
         f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == loginButton ) {
        login();
       
}
    }
        public void login() {
        String email ="";
        String query;
        String sUSER = "root";
        String sURL = "jdbc:MySQL://localhost:3306/clinic";
        int notfound = 1;
        String sPASS = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(sURL, sUSER, sPASS);
            Statement s = con.createStatement();

            if (userText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Email is required ", "ERROR", JOptionPane.ERROR_MESSAGE);
               
            } else {
                email = userText.getText();
                query = "SELECT * FROM doctors WHERE email='" + email + "'";
                java.sql.ResultSet res = s.executeQuery(query);

                while (res.next()) {
                    // If a matching email is found, set notfound to 0
                    notfound = 0;
                }

                if (notfound == 1) {
                    
                    JOptionPane.showMessageDialog(new JFrame(), "Email not found", "ERROR", JOptionPane.ERROR_MESSAGE);
                    userText.setText("");
                }else{
                    takeAction();
                }
                                         
                }
            } catch (Exception ee) {
            // Handle exceptions properly
            ee.printStackTrace();
        }
}

}
    
    
    
    

