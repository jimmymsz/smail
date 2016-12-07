package smail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
        setSize(640,480);
        this.setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JSplitPane panel1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_email,textField_email);
        JSplitPane panel2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_password,passwordField_pass);
        JSplitPane panel3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,button_login,button_fpass);
        JPanel panel4 = new JPanel();
//        Mengatur Ukuran label dan text
        label_email.setMinimumSize(new Dimension (75,50));
        label_email.setMaximumSize(new Dimension (75,50));
        label_email.setPreferredSize(new Dimension (75,50));
        textField_email.setMinimumSize(new Dimension (70,50));
        textField_email.setMaximumSize(new Dimension (800,50));
        textField_email.setPreferredSize(new Dimension (200,50));
        panel1.setMinimumSize(new Dimension (300,50));
        panel1.setMaximumSize(new Dimension (800,50));
        panel1.setPreferredSize(new Dimension (600,50));
        label_password.setMinimumSize(new Dimension (75,50));
        label_password.setMaximumSize(new Dimension (75,50));
        label_password.setPreferredSize(new Dimension (75,50));
        passwordField_pass.setMinimumSize(new Dimension (30,50));
        passwordField_pass.setMaximumSize(new Dimension (800,50));
        passwordField_pass.setPreferredSize(new Dimension (50,50));
        panel2.setMinimumSize(new Dimension (800,50));
        panel2.setMaximumSize(new Dimension (800,50));
        panel2.setPreferredSize(new Dimension (800,50));
//        button_login
//        Mengatur Ukuran Button
        button_login.setMinimumSize(new Dimension (75,10));
        button_login.setMaximumSize(new Dimension (100,50));
        button_login.setPreferredSize(new Dimension (90,70));
        button_fpass.setMinimumSize(new Dimension (75,10));
        button_fpass.setMaximumSize(new Dimension (75,50));
        button_fpass.setPreferredSize(new Dimension (75,70));
        panel3.setMinimumSize(new Dimension (300,50));
        panel3.setMaximumSize(new Dimension (300,50));
        panel3.setPreferredSize(new Dimension (300,50));
        button_register.setMinimumSize(new Dimension (100,10));
        button_register.setMaximumSize(new Dimension (100,30));
        button_register.setPreferredSize(new Dimension (100,30));
//        add(button_register);
        panel4.add(button_register);
        panel4.setMinimumSize(new Dimension (450,50));
        panel4.setMaximumSize(new Dimension (450,50));
        panel4.setPreferredSize(new Dimension (450,50));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        setVisible(true);
        panel1.setBorder(BorderFactory.createEmptyBorder(10,40,10,85));
        panel2.setBorder(BorderFactory.createEmptyBorder(10,40,10,85));
        panel3.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
        panel4.setBorder(BorderFactory.createEmptyBorder(10,163,10,70));
        setVisible(true);
        button_login.addActionListener(this);
        button_fpass.addActionListener(this);
        button_register.addActionListener(this);
    }
    
//    public void keyPressed(KeyEvent ke){
//    	int key = ke.getKeyCode();
//    	switch(key){
//    	case KeyEvent.VK_ENTER:
//    		button_login.key
//    		
//    	}
//    }

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
                new ForgotPass();
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
