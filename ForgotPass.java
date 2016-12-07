package smail;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ForgotPass extends JFrame implements ActionListener{
	JButton submit,cancel;
	JTextField mail,nama;
	JPasswordField pass,kon_pass,pin,kon_pin;
	ForgotPass(){
		setTitle("Forgot Password");
        setSize(520, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainpanel= new JPanel(new GridLayout(14,1,5,5));
        JPanel panel1= new JPanel(new GridLayout(1,2,5,5));
        JPanel panel2= new JPanel(new GridLayout(1,2,5,5));
        JLabel Email = new JLabel("Email");
        JLabel PIN = new JLabel("PIN");
        JLabel PIN2 = new JLabel("Confirm your PIN");
		mail = new JTextField();
		pin = new JPasswordField();
		kon_pin = new JPasswordField();
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		panel1.add(mail,BorderLayout.EAST);
		panel2.add(submit, BorderLayout.EAST);
		panel2.add(cancel, BorderLayout.WEST);
		
		mainpanel.add(Email);
		mainpanel.add(panel1);
		mainpanel.add(PIN);
		mainpanel.add(pin);
		mainpanel.add(PIN2);
		mainpanel.add(kon_pin);
		mainpanel.add(panel2);
		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//panel2.add(arg0);
		add(mainpanel);
		setVisible(true);
		submit.addActionListener(this);
		cancel.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			smail_func dbn = new smail_func();
			boolean trig=true;
			if(arg0.getSource()==submit){
					if(!mail.getText().equals("") &&Arrays.equals(pin.getPassword(),kon_pin.getPassword())&&pin.getPassword().length!=0){
						Vector<Object> data = new Vector<Object>();
					    String mailss= mail.getText();
						String a2 = new String(pin.getPassword());
						data = dbn.lupaPass(mailss, Integer.parseInt(a2));
						String passwords = new String((String) data.elementAt(0));
						JOptionPane.showMessageDialog(null,"your password is : "+passwords);
						new MainProgram();
						setVisible(false);
		        		dispose();
					}
					else{
						if(mail.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Email cannot be empty");
						}
						if(!Arrays.equals(pin.getPassword(),kon_pin.getPassword())){
							JOptionPane.showMessageDialog(null, "PIN and PIN confirmation must same");
						}
						if(pin.getPassword().length==0){
							JOptionPane.showMessageDialog(null, "PIN cannot be empty");				
						}
						if(kon_pin.getPassword().length==0){
							JOptionPane.showMessageDialog(null, "PIN confirmation cannot be empty");				
						}
					}				
			}
			else{
				//return home
				new MainProgram();
				dispose();
			}
		}
		catch (java.lang.ArrayIndexOutOfBoundsException e){
        	JOptionPane.showMessageDialog(null, "Wrong email or pin");
        }
		catch(Exception e){
			System.out.println(e);
		}
	}
}
