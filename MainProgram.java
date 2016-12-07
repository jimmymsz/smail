package smail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Steven on 12/3/2016.
 */


public class MainProgram extends JFrame implements ActionListener {
    JLabel label_email = new JLabel("Email");
    JLabel label_password = new JLabel("Password");
    JTextField textField_email = new JTextField();

    JPasswordField passwordField_pass = new JPasswordField();
    JButton button_login = new JButton("Login");
    JButton button_fpass = new JButton("Forgot Pass");
    JButton button_register = new JButton("Register");
    Vector<Object> data = new Vector<Object>();
    public MainProgram(){
        setTitle("Login");
        setSize(600,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel(new GridLayout(2,5,5,5));
        JPanel panel2 = new JPanel(new GridLayout(1,2,5,5));
        JPanel panel3 = new JPanel(new GridLayout(1,1,5,5));
        panel1.add(label_email);
        panel1.add(textField_email);
        panel1.add(label_password);
        panel1.add(passwordField_pass);
        panel3.add(button_register);
        panel2.add(button_login);
        panel2.add(button_fpass);
        textField_email.setSize(10,10);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);
        add(panel3);
        setVisible(true);
        panel1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel2.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));
        panel3.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        setVisible(true);
        button_login.addActionListener(this);
        button_fpass.addActionListener(this);
        button_register.addActionListener(this);
    }

    public static void main(String[] args){
        new MainProgram();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try{
            if(arg0.getSource()==button_login){
            	smail_func func = new smail_func();
            	String email = textField_email.getText();
            	String pass = new String(passwordField_pass.getPassword());
            	data = func.Login(email,pass);
                new Home(data.elementAt(1).toString(),data.elementAt(0).toString());
                this.dispose();
            }
            else if(arg0.getSource()==button_fpass){
                //new MainFrm();
                this.dispose();
            }
            else if(arg0.getSource()==button_register){
                new register();
                this.dispose();
            }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
        	JOptionPane.showMessageDialog(null, "Wrong email or password");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
