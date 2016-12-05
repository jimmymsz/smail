import javax.swing.*;
import java.awt.*;

/**
 * Created by Steven on 12/4/2016.
 */
public class Home extends JFrame{
    JTabbedPane jtp = new JTabbedPane();
    JButton button_logout,button_ubahpin,button_ubahpass;


    Home(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel(new GridLayout(2,2,20,20));
        JPanel panel2 = new JPanel(new GridLayout(1,3,20,20));
        setTitle("Welcome");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        button_logout = new JButton("Logout");
        button_ubahpin = new JButton("Ubah PIN");
        button_ubahpass = new JButton("Ubah Password");
        jtp.addTab("Inbox" , new Panel1());
        jtp.addTab("OutBox" , new Panel2());
        jtp.addTab("Compose" , new Panel3());
        panel2.add(button_logout);
        panel2.add(button_ubahpass);
        panel2.add(button_ubahpin);

        add(jtp);

        panel1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //add(panel1);
        add(panel2);
        setVisible(true);
    }

    public static void main(String[] args){
        new Home();
    }
}

class Panel1 extends JPanel {
    public Panel1(){
        JButton button_reply = new JButton("Reply");
        String[] colHeads = { "Name", "Extension", "ID#" };
        Object[][] data = {
                {"Gail","456715","5921214"},
                {"DJai","452111","3489151"},
                {"Init","415166","1149272"}
        };
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);
        add(button_reply);
    }
}

class Panel2 extends JPanel {
    public Panel2(){
//        JPanel table_panel = new JPanel();
//        table_panel.setLayout(new BoxLayout(table_panel,BoxLayout.LINE_AXIS));
//        JPanel button_panel = new JPanel();
//        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.LINE_AXIS));
//        button_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton button_delete = new JButton("Delete");
        String[] colHeads = { "Name", "Extension", "ID#" };
        Object[][] data = {
                {"Gail","456715","5921214"},
                {"DJai","452111","3489151"},
                {"Init","415166","1149272"}
        };
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);
        add(button_delete);
//        button_panel.add(Box.createHorizontalGlue());
//        button_panel.add(button_delete);
//        button_panel.add(Box.createRigidArea(new Dimension(10,0)));

    }

}

class Panel3 extends JPanel {
    public Panel3(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel label_to = new JLabel("To :");
        JLabel label_subject = new JLabel("Subject ");
        JButton button_ok = new JButton("Send");
        JTextField textField_to = new JTextField();
        JTextArea textArea_subject = new JTextArea();
        JPanel msg_panel = new JPanel(new GridLayout(2,1,20,20));
        JPanel button_panel = new JPanel(new GridLayout(1,1,20,20));
        msg_panel.add(label_to);
        msg_panel.add(textField_to);
        msg_panel.add(label_subject);
        msg_panel.add(textArea_subject);
        add(msg_panel);
        button_panel.add(button_ok);
        add(button_panel);


    }
}


