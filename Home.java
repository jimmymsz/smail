package smail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
    JTabbedPane jtp = new JTabbedPane();
    JButton button_logout,button_ubahpin,button_ubahpass,searching;
    JTextField searchField;
    String emails;
    String name;
    Home(String mail,String nama){
    	emails=mail;
    	name=nama;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel(new GridLayout(1,3));
        JPanel panel3 = new JPanel(new GridLayout(1,2));
        setTitle("Welcome, "+name);
        setSize(800,600);
        button_logout = new JButton("Logout");
        button_ubahpin = new JButton("Ubah PIN");
        button_ubahpass = new JButton("Ubah Password");
        searching = new JButton("Search");
        jtp.addTab("Inbox" , new Panel1());
        jtp.addTab("OutBox" , new Panel2());
        jtp.addTab("Compose" , new Panel3());
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
        //add(panel1);
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
        }
        catch (Exception e){
            System.out.println(e);
        }
	}
}

class Panel1 extends JPanel implements ActionListener{
    JButton button_reply,button_delete,read;
	public Panel1(){
    	JPanel panel11 = new JPanel(new GridLayout(2,1));
    	JPanel panel111 = new JPanel(new GridLayout(1,2));
        button_reply = new JButton("Reply");
        button_delete = new JButton("Delete");
        read = new JButton("Read");
        //String[] colHeads = {"Dari","Subject","Isi","Status"};
        String[] colHeads = { "Name", "Extension", "ID#" };
        Object[][] data = {
                {"Gail","456715","5921214"},
                {"DJai","452111","3489151"},
                {"Init","415166","1149272"}
        };
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        panel11.add(jsp);
        panel111.add(button_reply);
        panel111.add(button_delete);
        panel111.add(read);
        panel11.add(panel111);
        add(panel11);
        button_reply.addActionListener(this);
        button_delete.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

    	new Panel4();
	}
}

class Panel2 extends JPanel implements ActionListener{
    JButton button_delete;
	public Panel2(){
//        JPanel table_panel = new JPanel();
//        table_panel.setLayout(new BoxLayout(table_panel,BoxLayout.LINE_AXIS));
//        JPanel button_panel = new JPanel();
//        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.LINE_AXIS));
//        button_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    	JPanel panel12 = new JPanel(new GridLayout(2,1));
    	button_delete = new JButton("Delete");
        String[] colHeads = { "Name", "Extension", "ID#" };
        Object[][] data = {
                {"Gail","456715","5921214"},
                {"DJai","452111","3489151"},
                {"Init","415166","1149272"}
        };
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        panel12.add(jsp);
        panel12.add(button_delete);
        add(panel12);
//        button_panel.add(Box.createHorizontalGlue());
//        button_panel.add(button_delete);
//        button_panel.add(Box.createRigidArea(new Dimension(10,0)));

    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

class Panel3 extends JPanel implements ActionListener{
    JButton button_ok;
	JTextField textField_to,textField_subject;
	JTextArea textArea_isi;
    public Panel3(){
    	setSize(800,600);
    	JPanel panel13 = new JPanel(new GridLayout(3,10));
        JLabel label_to = new JLabel("To :");
        JLabel label_subject = new JLabel("Subject ");
        button_ok = new JButton("Send");
        textField_to = new JTextField();
        textField_subject = new JTextField();
        textArea_isi = new JTextArea();
        JPanel msg_panel = new JPanel(new GridLayout(2,1));
        msg_panel.add(label_to,BorderLayout.WEST);
        msg_panel.add(textField_to,BorderLayout.EAST);
        msg_panel.add(label_subject,BorderLayout.WEST);
        msg_panel.add(textField_subject,BorderLayout.EAST);
        panel13.add(msg_panel);
        panel13.add(textArea_isi);
        panel13.add(button_ok);
        add(panel13);
        button_ok.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
class Panel4 extends JFrame implements ActionListener{
        JButton button_ok;
    	JTextField textField_to,textField_subject;
    	JTextArea textArea_isi;
        public Panel4(){//String emails,String subject,String to){
        	setSize(800,600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        	JPanel panel14 = new JPanel(new GridLayout(3,10));
            JLabel label_to = new JLabel("To :");
            JLabel label_subject = new JLabel("Subject ");
            button_ok = new JButton("Send");
            textField_to = new JTextField();
            textField_subject = new JTextField();
            //textField_to.setText(to);
            //textField_subject.setText(subject);
            textArea_isi = new JTextArea();
            JPanel msg_panel = new JPanel(new GridLayout(2,1));
            msg_panel.add(label_to,BorderLayout.WEST);
            msg_panel.add(textField_to,BorderLayout.EAST);
            msg_panel.add(label_subject,BorderLayout.WEST);
            msg_panel.add(textField_subject,BorderLayout.EAST);
            panel14.add(msg_panel);
            panel14.add(textArea_isi);
            panel14.add(button_ok);
            add(panel14);
            setVisible(true);
        }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
class Panel5 extends JFrame implements ActionListener{
    JButton button_ok;
	JTextField textField_to,textField_subject;
	JTextArea textArea_isi;
    public Panel5(String subject,String to,String isi){
    	setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    	JPanel panel14 = new JPanel(new GridLayout(3,10));
        JLabel label_to = new JLabel("From :");
        JLabel label_subject = new JLabel("Subject ");
        button_ok = new JButton("Send");
        textField_to = new JTextField();
        textField_subject = new JTextField();
        textArea_isi = new JTextArea();
        textField_to.setText(to);
        textField_subject.setText(subject);
        textArea_isi.setText(isi);
        JPanel msg_panel = new JPanel(new GridLayout(2,1));
        msg_panel.add(label_to,BorderLayout.WEST);
        msg_panel.add(textField_to,BorderLayout.EAST);
        msg_panel.add(label_subject,BorderLayout.WEST);
        msg_panel.add(textField_subject,BorderLayout.EAST);
        panel14.add(msg_panel);
        panel14.add(textArea_isi);
        add(panel14);
        setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


