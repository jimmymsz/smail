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

public class register extends JFrame implements ActionListener{
	JButton submit,cancel;
	JTextField mail,nama;
	JPasswordField pass,kon_pass,pin,kon_pin;
	register(){
		setTitle("Register");
        setSize(520, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainpanel= new JPanel(new GridLayout(14,1));
        JPanel panel1= new JPanel(new GridLayout(1,2));
        JPanel panel2= new JPanel(new GridLayout(1,2));
        JLabel Email = new JLabel("Email");
        JLabel Nama = new JLabel("Name");
        JLabel Password = new JLabel("Password");
        JLabel Password2 = new JLabel("Confirm your password");
        JLabel PIN = new JLabel("PIN");
        JLabel PIN2 = new JLabel("Confirm your PIN");
        JLabel ext = new JLabel("@smail.com");
		mail = new JTextField();
		nama = new JTextField();
		pass = new JPasswordField();
		kon_pass = new JPasswordField();
		pin = new JPasswordField();
		kon_pin = new JPasswordField();
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		
		panel1.add(mail,BorderLayout.EAST);
		panel1.add(ext,BorderLayout.WEST);
		panel2.add(submit, BorderLayout.EAST);
		panel2.add(cancel, BorderLayout.WEST);
		
		mainpanel.add(Email);
		mainpanel.add(panel1);
		mainpanel.add(Nama);
		mainpanel.add(nama);
		mainpanel.add(Password);
		mainpanel.add(pass);
		mainpanel.add(Password2);
		mainpanel.add(kon_pass);
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
		boolean trig=true;
		if(arg0.getSource()==submit){
			int a=mail.getText().length();
			String mails=mail.getText();
			for(int i=0 ; i <a;i++){
				if(mails.charAt(i)=='@'){
					trig=false;
					break;
				}
			}
			if(!trig){
				JOptionPane.showMessageDialog(null, "You can't use two @ in one email");
			}
			else{
				if(!mail.getText().equals("") && !nama.getText().equals("")&&Arrays.equals(pass.getPassword(),kon_pass.getPassword())&&Arrays.equals(pin.getPassword(),kon_pin.getPassword())&&pass.getPassword().length!=0&&pin.getPassword().length!=0){
					//kirim data lewat networking
					//data yang dikirim berupa mail.getText()+"@smail.com",nama.getText(),dll;
					//return home
					JOptionPane.showMessageDialog(null, "Register success");
					setVisible(false);
	        		dispose();
				}
				else{
					if(mail.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Email cannot be null");
					}
					if(nama.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Name cannot be null");
					}
					if(!Arrays.equals(pass.getPassword(),kon_pass.getPassword())){
						JOptionPane.showMessageDialog(null, "Password and Password confirmation must same");
					}
					if(!Arrays.equals(pin.getPassword(),kon_pin.getPassword())){
						JOptionPane.showMessageDialog(null, "PIN and PIN confirmation must same");
					}
					if(pass.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "Password cannot be null");					
					}
					if(pin.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "PIN cannot be null");				
					}
					if(kon_pass.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "Password confirmation cannot be null");					
					}
					if(kon_pin.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "PIN confirmation cannot be null");				
					}
				}
			}
		}
		else{
			//return home
		}
	}
}
