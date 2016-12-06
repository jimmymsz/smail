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

public class UPIN extends JFrame implements ActionListener{
	JButton submit,cancel;
	JTextField mail,nama;
	JPasswordField pass,kon_pass,pin,kon_pin;
	String emails;
	String name;
	UPIN(String mailz,String namas){
		name=namas;
		emails=mailz;
		setTitle("Password Change");
        setSize(420, 195);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainpanel= new JPanel(new GridLayout(5,1));
        JPanel panel1= new JPanel(new GridLayout(1,2));
        JPanel panel2= new JPanel(new GridLayout(1,2));
        JLabel PIN = new JLabel("New PIN");
        JLabel PIN2 = new JLabel("Confirm your PIN");
		pin = new JPasswordField();
		kon_pin = new JPasswordField();
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		panel2.add(submit, BorderLayout.EAST);
		panel2.add(cancel, BorderLayout.WEST);
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
			boolean trig=true;
			if(arg0.getSource()==submit){
				if(Arrays.equals(pin.getPassword(),kon_pin.getPassword()) && pin.getPassword().length!=0){
					//kirim data lewat networking
					//data yang dikirim berupa pin
					//return home
					String pins = new String(pin.getPassword());
					smail_func func = new smail_func();
					func.GantiPin(Integer.parseInt(pins), emails);
					JOptionPane.showMessageDialog(null, "PIN Changed");
					new Home(emails,name);
					setVisible(false);
		        	dispose();
				}
				else{
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
				new Home(emails,name);
				setVisible(false);
				dispose();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
