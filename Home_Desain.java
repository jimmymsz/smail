package smail;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class Home extends JFrame implements ActionListener{
	
    JTabbedPane jtp = new JTabbedPane();
    JButton button_logout,button_ubahpin,button_ubahpass,searching;
    JTextField searchField;
    String emails;
    String name;
    Home(String mail,String nama){
    	emails=mail;
    	name=nama;
    	
        this.setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel(new GridLayout(1,3,5,5));
        JPanel panel3 = new JPanel(new GridLayout(1,2,5,5));
        setTitle("Welcome, "+name);
        setSize(800,800);
        button_logout = new JButton("Logout");
        button_ubahpin = new JButton("Ubah PIN");
        button_ubahpass = new JButton("Ubah Password");
        searching = new JButton("Search");
        jtp.addTab("Inbox" , new Panel1(emails));
        jtp.addTab("OutBox" , new Panel2(emails));
        jtp.addTab("Compose" , new Panel3(emails));
        searchField = new JTextField();
        panel2.add(searching);
        panel2.add(button_ubahpass);
        panel2.add(button_ubahpin);
        panel2.add(button_logout);
        panel3.add(searchField,BorderLayout.WEST);
        panel3.add(panel2,BorderLayout.EAST);
        add(panel3);
        add(jtp);
        panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setVisible(true);
        button_ubahpass.addActionListener(this);
        searching.addActionListener(this);
        button_ubahpin.addActionListener(this);
        button_logout.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
            if(arg0.getSource()==button_logout){
                new MainProgram();
                this.dispose();
            }
            else if(arg0.getSource()==button_ubahpass){
            	new Upass(emails,name);
                this.dispose();
            }
            else if(arg0.getSource()==button_ubahpin){
            	new UPIN(emails,name);
                this.dispose();
            }
            else if(arg0.getSource()==searching){
            	new Panelsearch(emails,searchField.getText());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
	}
}

class Panel1 extends JPanel implements ActionListener{
    JButton button_reply,button_delete,read;
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    String mail;
    String dumb1,dumb2,dumb3,dumb4,dumb5;
	public Panel1(String emails){
    	mail=emails;
		try{
			smail_func a = new smail_func();
			data = a.selectEmailInbox(mail);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    	JPanel panel11 = new JPanel(new GridLayout(2,1,5,5));
    	JPanel panel111 = new JPanel();

        button_reply = new JButton("Reply");
        button_delete = new JButton("Delete");
        read = new JButton("Read");
        Vector<Object> colHeads = new Vector<Object>();
        colHeads.add("Email Pengirim");
        colHeads.add("Subject");
        colHeads.add("Isi");
        colHeads.add("Status");
        colHeads.add("Waktu Kirim");
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                dumb1=((table.getValueAt(table.getSelectedRow(), 0).toString()));
                dumb2=((table.getValueAt(table.getSelectedRow(), 1).toString()));
                dumb3=((table.getValueAt(table.getSelectedRow(), 2).toString()));
                dumb4=((table.getValueAt(table.getSelectedRow(), 3).toString()));
                dumb5=((table.getValueAt(table.getSelectedRow(), 4).toString()));
            }
        });
        panel11.add(jsp);
        jsp.setMinimumSize(new Dimension (750,560));
        jsp.setMaximumSize(new Dimension (750,560));
        jsp.setPreferredSize(new Dimension (750,560));
        button_reply.setMinimumSize(new Dimension (100,100));
        button_reply.setMaximumSize(new Dimension (100,100));
        button_reply.setPreferredSize(new Dimension (100,100));
        button_delete.setMinimumSize(new Dimension (100,100));
        button_delete.setMaximumSize(new Dimension (100,100));
        button_delete.setPreferredSize(new Dimension (100,100));
        read.setMinimumSize(new Dimension (100,100));
        read.setMaximumSize(new Dimension (100,100));
        read.setPreferredSize(new Dimension (100,100));
        panel111.add(button_reply);
        panel111.add(button_delete);
        panel111.add(read);
        panel11.add(panel111);
        add(panel11);
        button_reply.addActionListener(this);
        button_delete.addActionListener(this);
        read.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			smail_func func = new smail_func();
			if(arg0.getSource()==button_reply){
				new Panel4(dumb2,dumb1,dumb3,mail);
			}
			else if(arg0.getSource()==button_delete){
				func.hapus(mail, dumb2, dumb3, dumb1);
				revalidate();
				repaint();
			}
			else if(arg0.getSource()==read){
				func.reads(mail,dumb3,dumb2,dumb1);
				new Panel5(dumb2,dumb1,dumb3,mail);
				revalidate();
				repaint();
	        }
		}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}
}

class Panel2 extends JPanel implements ActionListener{
    JButton button_delete,open;
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    String mail;
    String dumb1,dumb2,dumb3,dumb4,dumb5;
	public Panel2(String emails){
		mail=emails;
//        JPanel table_panel = new JPanel();
//        table_panel.setLayout(new BoxLayout(table_panel,BoxLayout.LINE_AXIS));
//        JPanel button_panel = new JPanel();
//        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.LINE_AXIS));
//        button_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		try{
			smail_func a = new smail_func();
			data = a.selectEmailSent(mail);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    	JPanel panel12 = new JPanel(new GridLayout(2,1,5,5));
    	JPanel panel123 = new JPanel();
    	button_delete = new JButton("Delete");
    	open = new JButton("Read");
        Vector<Object> colHeads = new Vector<Object>();
        colHeads.add("Email Penerima");
        colHeads.add("Subject");
        colHeads.add("Isi");
        colHeads.add("Status");
        colHeads.add("Waktu Kirim");
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                dumb1=((table.getValueAt(table.getSelectedRow(), 0).toString()));
                dumb2=((table.getValueAt(table.getSelectedRow(), 1).toString()));
                dumb3=((table.getValueAt(table.getSelectedRow(), 2).toString()));
                dumb4=((table.getValueAt(table.getSelectedRow(), 3).toString()));
                dumb5=((table.getValueAt(table.getSelectedRow(), 4).toString()));
            }
        });
        panel12.add(jsp);
        jsp.setMinimumSize(new Dimension (750,560));
        jsp.setMaximumSize(new Dimension (750,560));
        jsp.setPreferredSize(new Dimension (750,560));
        button_delete.setMinimumSize(new Dimension (100,100));
        button_delete.setMaximumSize(new Dimension (100,100));
        button_delete.setPreferredSize(new Dimension (100,100));
        open.setMinimumSize(new Dimension (100,100));
        open.setMaximumSize(new Dimension (100,100));
        open.setPreferredSize(new Dimension (100,100));
        panel123.add(button_delete);
        panel123.add(open);
        panel12.add(panel123);
        add(panel12);
//        button_panel.add(Box.createHorizontalGlue());
//        button_panel.add(button_delete);
//        button_panel.add(Box.createRigidArea(new Dimension(10,0)));
        button_delete.addActionListener(this);
        open.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			smail_func func = new smail_func();
			if(arg0.getSource()==button_delete){
				//public void hapus(String ID_Penerima,String Subjects,String isi,String ID_Pengirim) throws SQLException{
				//new Panel4();
				func.hapus(dumb1, dumb2, dumb3, mail);
				revalidate();
				repaint();
	        }
			else{
				new Panel5(dumb2,dumb1,dumb3,mail);
			}
		}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}
}

class Panel3 extends JPanel implements ActionListener{
    JButton button_ok;
	JTextField textField_to,textField_subject;
	JTextArea textArea_isi;
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    String mail;
    public Panel3(String emails){
    	mail=emails;
    	setSize(800,600);
    	JPanel panel15 = new JPanel();
        JLabel label_to = new JLabel("To :");
        JLabel label_subject = new JLabel("Subject ");
        button_ok = new JButton("Send");
        textField_to = new JTextField();
        textField_subject = new JTextField();
        textArea_isi = new JTextArea();
        JSplitPane panel13 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_to,textField_to);
        JSplitPane panel14 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_subject,textField_subject);
        JPanel msg_panel = new JPanel();
        label_to.setMinimumSize(new Dimension (75,50));
        label_to.setMaximumSize(new Dimension (75,50));
        label_to.setPreferredSize(new Dimension (75,50));
        textField_to.setMinimumSize(new Dimension (30,50));
        textField_to.setMaximumSize(new Dimension (800,50));
        textField_to.setPreferredSize(new Dimension (50,50));
        panel13.setMinimumSize(new Dimension (800,50));
        panel13.setMaximumSize(new Dimension (800,50));
        panel13.setPreferredSize(new Dimension (800,50));
        label_subject.setMinimumSize(new Dimension (75,50));
        label_subject.setMaximumSize(new Dimension (75,50));
        label_subject.setPreferredSize(new Dimension (75,50));
        textField_subject.setMinimumSize(new Dimension (30,50));
        textField_subject.setMaximumSize(new Dimension (800,50));
        textField_subject.setPreferredSize(new Dimension (50,50));
        panel14.setMinimumSize(new Dimension (800,50));
        panel14.setMaximumSize(new Dimension (800,50));
        panel14.setPreferredSize(new Dimension (800,50));
        panel13.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        panel14.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        textArea_isi.setMinimumSize(new Dimension (100,100));
        textArea_isi.setMaximumSize(new Dimension (600,200));
        textArea_isi.setPreferredSize(new Dimension (600,200));
        panel15.setMinimumSize(new Dimension (600,200));
        panel15.setMaximumSize(new Dimension (600,200));
        panel15.setPreferredSize(new Dimension (600,200));
        panel15.add(textArea_isi);
        panel15.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        msg_panel.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        button_ok.setMinimumSize(new Dimension (30,50));
        button_ok.setMaximumSize(new Dimension (200,50));
        button_ok.setPreferredSize(new Dimension (100,50));
        msg_panel.add(button_ok);
        add(panel13);
        add(panel14);
        add(panel15);
        
        add(msg_panel);
        button_ok.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			smail_func func = new smail_func();
			if(arg0.getSource()==button_ok){
				func.kirimEmail(mail, textField_to.getText(), textField_subject.getText(), textArea_isi.getText());
				JOptionPane.showMessageDialog(null, "Email sent successfully");
				revalidate();
				textField_to.setText("");
				textField_subject.setText("");
				textArea_isi.setText("");
	        }
		}
    	catch (Exception e){
    		e.printStackTrace();
    	}
		// TODO Auto-generated method stub
		
	}
}

//Untuk Reply

class Panel4 extends JFrame implements ActionListener{
        JButton button_ok,cancel;
    	JTextField textField_to,textField_subject;
    	JTextArea textArea_isi;
    	String mail;
        public Panel4(String subject,String to,String isi,String emails){//String emails,String subject,String to){
        	mail=emails;
        	setSize(800,450);
            this.setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        	JPanel panel141 = new JPanel();
            JLabel label_to = new JLabel("To :");
            JLabel label_subject = new JLabel("Subject ");
            button_ok = new JButton("Send");
            cancel = new JButton("Cancel");
            textField_to = new JTextField();
            textField_subject = new JTextField();
            textArea_isi = new JTextArea();
            textField_to.setText(to);
            textField_subject.setText(subject);
            textArea_isi.setText(isi);
            JSplitPane panel13 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_to,textField_to);
            JSplitPane panel14 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_subject,textField_subject);
            JPanel panel15 = new JPanel();
            JPanel msg_panel = new JPanel();
            label_to.setMinimumSize(new Dimension (75,50));
            label_to.setMaximumSize(new Dimension (75,50));
            label_to.setPreferredSize(new Dimension (75,50));
            textField_to.setMinimumSize(new Dimension (30,50));
            textField_to.setMaximumSize(new Dimension (400,50));
            textField_to.setPreferredSize(new Dimension (200,50));
            panel13.setMinimumSize(new Dimension (200,50));
            panel13.setMaximumSize(new Dimension (500,50));
            panel13.setPreferredSize(new Dimension (350,50));
            label_subject.setMinimumSize(new Dimension (75,50));
            label_subject.setMaximumSize(new Dimension (75,50));
            label_subject.setPreferredSize(new Dimension (75,50));
            textField_subject.setMinimumSize(new Dimension (30,50));
            textField_subject.setMaximumSize(new Dimension (400,50));
            textField_subject.setPreferredSize(new Dimension (200,50));
            panel14.setMinimumSize(new Dimension (200,50));
            panel14.setMaximumSize(new Dimension (500,50));
            panel14.setPreferredSize(new Dimension (350,50));
            panel13.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
            panel14.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
            textArea_isi.setMinimumSize(new Dimension (100,100));
            textArea_isi.setMaximumSize(new Dimension (700,200));
            textArea_isi.setPreferredSize(new Dimension (670,200));
            panel15.setMinimumSize(new Dimension (200,200));
            panel15.setMaximumSize(new Dimension (800,200));
            panel15.setPreferredSize(new Dimension (700,200));
            panel15.add(textArea_isi);
            panel15.setBorder(BorderFactory.createEmptyBorder(10,600,10,0));
            msg_panel.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
            button_ok.setMinimumSize(new Dimension (30,50));
            button_ok.setMaximumSize(new Dimension (200,50));
            button_ok.setPreferredSize(new Dimension (100,50));
            cancel.setMinimumSize(new Dimension (30,50));
            cancel.setMaximumSize(new Dimension (200,50));
            cancel.setPreferredSize(new Dimension (100,50));
            add(panel13);
            add(panel14);
            add(panel15);
            msg_panel.add(button_ok);
            panel141.add(button_ok,BorderLayout.EAST);
            panel141.add(cancel,BorderLayout.WEST);
            
            add(panel141);
            setVisible(true);
            cancel.addActionListener(this);
            button_ok.addActionListener(this);
        }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				smail_func func = new smail_func();
				if(arg0.getSource()==button_ok){
					func.kirimEmail(mail, textField_to.getText(), textField_subject.getText(), textArea_isi.getText());
					JOptionPane.showMessageDialog(null, "Email sent successfully");
					revalidate();
					textField_to.setText("");
					textField_subject.setText("");
					textArea_isi.setText("");
					repaint();
		        }
				else{
					dispose();
					setVisible(false);
				}
			}
	    	catch (Exception e){
	    		e.printStackTrace();
	    	}
			// TODO Auto-generated method stub
			
		}
}

//Untuk read
class Panel5 extends JFrame implements ActionListener{
    JButton button_ok;
	JTextField textField_to,textField_subject;
	JTextArea textArea_isi;
	String mail;
    public Panel5(String subject,String to,String isi,String from){
    	mail=from;
    	setSize(800,450);
        this.setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel label_to = new JLabel("From :");
        JLabel label_subject = new JLabel("Subject ");
        button_ok = new JButton("Close");
        textField_to = new JTextField();
        textField_subject = new JTextField();
        textArea_isi = new JTextArea();
        textField_to.setText(to);
        textField_subject.setText(subject);
        textArea_isi.setText(isi);
        textField_to.setEnabled(false);
        textField_subject.setEnabled(false);
        textArea_isi.setEnabled(false);
        JSplitPane panel13 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_to,textField_to);
        JSplitPane panel14 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,label_subject,textField_subject);
        JPanel panel15 = new JPanel();
        JPanel msg_panel = new JPanel();
        label_to.setMinimumSize(new Dimension (75,50));
        label_to.setMaximumSize(new Dimension (75,50));
        label_to.setPreferredSize(new Dimension (75,50));
        textField_to.setMinimumSize(new Dimension (30,50));
        textField_to.setMaximumSize(new Dimension (400,50));
        textField_to.setPreferredSize(new Dimension (200,50));
        panel13.setMinimumSize(new Dimension (200,50));
        panel13.setMaximumSize(new Dimension (500,50));
        panel13.setPreferredSize(new Dimension (350,50));
        label_subject.setMinimumSize(new Dimension (75,50));
        label_subject.setMaximumSize(new Dimension (75,50));
        label_subject.setPreferredSize(new Dimension (75,50));
        textField_subject.setMinimumSize(new Dimension (30,50));
        textField_subject.setMaximumSize(new Dimension (400,50));
        textField_subject.setPreferredSize(new Dimension (200,50));
        panel14.setMinimumSize(new Dimension (200,50));
        panel14.setMaximumSize(new Dimension (500,50));
        panel14.setPreferredSize(new Dimension (350,50));
        panel13.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        panel14.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        textArea_isi.setMinimumSize(new Dimension (100,100));
        textArea_isi.setMaximumSize(new Dimension (700,200));
        textArea_isi.setPreferredSize(new Dimension (670,200));
        panel15.setMinimumSize(new Dimension (200,200));
        panel15.setMaximumSize(new Dimension (800,200));
        panel15.setPreferredSize(new Dimension (700,200));
        panel15.add(textArea_isi);
        panel15.setBorder(BorderFactory.createEmptyBorder(10,600,10,0));
        msg_panel.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        button_ok.setMinimumSize(new Dimension (30,50));
        button_ok.setMaximumSize(new Dimension (200,50));
        button_ok.setPreferredSize(new Dimension (100,50));
        msg_panel.add(button_ok);
        add(panel13);
        add(panel14);
        add(panel15);
        add(msg_panel);
        setVisible(true);
        button_ok.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			smail_func func = new smail_func();
			if(arg0.getSource()==button_ok){
				revalidate();
				repaint();
				dispose();
			}
		}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}
}

//Untuk Search
class Panelsearch extends JFrame implements ActionListener{
    JButton button_delete,open;
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    String mail;
    String dumb1,dumb2,dumb3,dumb4,dumb5;
	public Panelsearch(String emails,String searchess){
		mail=emails;
//        JPanel table_panel = new JPanel();
//        table_panel.setLayout(new BoxLayout(table_panel,BoxLayout.LINE_AXIS));
//        JPanel button_panel = new JPanel();
//        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.LINE_AXIS));
//        button_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		try{
			smail_func a = new smail_func();
			data = a.searchEmail(searchess,mail);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		setSize(800,600);
        JPanel panel12 = new JPanel(new GridLayout(2,1,5,5));
    	JPanel panel123 = new JPanel();
    	button_delete = new JButton("Delete");
    	open = new JButton("Read");
        Vector<Object> colHeads = new Vector<Object>();
        colHeads.add("Email Penerima");
        colHeads.add("Email Pengirim");
        colHeads.add("Subject");
        colHeads.add("Isi");
        colHeads.add("Status");
        colHeads.add("Waktu Kirim");
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                dumb1=((table.getValueAt(table.getSelectedRow(), 0).toString()));
                dumb2=((table.getValueAt(table.getSelectedRow(), 1).toString()));
                dumb3=((table.getValueAt(table.getSelectedRow(), 2).toString()));
                dumb4=((table.getValueAt(table.getSelectedRow(), 3).toString()));
                dumb5=((table.getValueAt(table.getSelectedRow(), 4).toString()));
            }
        });
        panel12.add(jsp);
        panel123.add(open);
        panel12.add(panel123);
        add(panel12);
        setVisible(true);
        open.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			new Panel5(dumb2,dumb1,dumb3,mail);
		}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}
}

