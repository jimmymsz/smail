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

public class Upass extends JFrame implements ActionListener{
	JButton submit,cancel;
	JTextField mail,nama;
	JPasswordField pass,kon_pass,pin,kon_pin;
	Upass(){
		setTitle("Password Change");
        setSize(420, 195);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainpanel= new JPanel(new GridLayout(5,1));
        JPanel panel1= new JPanel(new GridLayout(1,2));
        JPanel panel2= new JPanel(new GridLayout(1,2));
        JLabel Password = new JLabel("New Password");
        JLabel Password2 = new JLabel("Confirm your password");
		pass = new JPasswordField();
		kon_pass = new JPasswordField();
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		panel2.add(submit, BorderLayout.EAST);
		panel2.add(cancel, BorderLayout.WEST);
		mainpanel.add(Password);
		mainpanel.add(pass);
		mainpanel.add(Password2);
		mainpanel.add(kon_pass);
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
			boolean trig=true;
			if(arg0.getSource()==submit){
				if(Arrays.equals(pass.getPassword(),kon_pass.getPassword())&&pass.getPassword().length!=0){
					//kirim data lewat networking
					//data yang dikirim berupa password 
					//return home
					JOptionPane.showMessageDialog(null, "Password Changed");
					setVisible(false);
		        	dispose();
				}
				else{
					if(!Arrays.equals(pass.getPassword(),kon_pass.getPassword())){
						JOptionPane.showMessageDialog(null, "Password and Password confirmation must same");
					}
					if(pass.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "Password cannot be empty");					
					}
					if(kon_pass.getPassword().length==0){
						JOptionPane.showMessageDialog(null, "Password confirmation cannot be empty");					
					}
				}
			}
			else{
				new Home();
				setVisible(false);
				dispose();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
