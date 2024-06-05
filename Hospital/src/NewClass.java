import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class NewClass extends JFrame implements ActionListener{  
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
   public NewClass(){
         b1=new JButton("Doctor Sign up");
         b2=new JButton("Doctor Login");
         b3=new JButton("Patient Sign up");
         b4=new JButton("ٍShow All Doctors"); 
         
        JLabel l1=new JLabel("Doctors");
        JLabel l2=new JLabel("Patients");
        Font font = l1.getFont();
        l1.setFont(font.deriveFont(font.getSize() * 1.5f));
        l2.setFont(font.deriveFont(font.getSize() * 1.5f));
         
        this.setLayout(new FlowLayout());
        this.setTitle("CLINIC");
        this.setSize(450, 350);
        this.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setLocation(500, 200);
       this.setLayout(null);
       
        b1.setBounds(40, 75,150, 40);
        b2.setBounds(45, 160,140, 40);
         b4.setBounds(20, 250,200, 40);
        b3.setBounds(270, 75, 150,40);
       l1.setBounds(80, 10,150, 40);
       l2.setBounds(310, 10, 150,40);
       
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        
       this.add(b1);this.add(b2);this.add(b4);this.add(b3);this.add(l1);this.add(l2);
   }
   
     private void showAllDoctors() {
     
     String name,email ,phone ,query ;
                String sUSER ,sURL ,sPASS;
                sPASS="";
                sUSER="root";
                sURL="jdbc:MySQL://localhost:3306/clinic";
                query = "SELECT fullname FROM doctors";


            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection(sURL,sUSER,sPASS);
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery(query);

        StringBuilder doctorsList = new StringBuilder();
        while (result.next()) {
            String doctorName = result.getString("fullname");
            doctorsList.append(doctorName).append("\n");
        }

        if (doctorsList.length() > 0) {
            JOptionPane.showMessageDialog(null, "List of Doctors:\n" + doctorsList.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No doctors found.");
        }

        result.close();
        st.close();
        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching doctors. Please check your database connection.");
}

    }
   
   
   @Override
    public void paint(Graphics g) {
        super.paint(g);   
        g.setColor(Color.BLACK);
        g.drawLine(250, 0, 250, 350);
    }
    
   
   @Override
public void actionPerformed(ActionEvent e) {
    if(e.getSource() == b3) {
        Main d = new Main();
        d.setVisible(true);
          d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } else if (e.getSource() == b1) {
        DoctorForm2 o = new DoctorForm2();
        o.setVisible(true);
         o.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }else if (e.getSource() == b2) {
       LoginFrame1 k = new LoginFrame1();
        k.setVisible(true);
         k.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }else if(e.getSource() == b4){
        showAllDoctors();
    }
}
   
}
