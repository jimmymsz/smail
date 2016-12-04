import javax.swing.*;

/**
 * Created by Steven on 12/4/2016.
 */
public class Home extends JFrame{
    JTabbedPane jtp = new JTabbedPane();
    Home(){
        setTitle("Welcome");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jtp.addTab("Inbox" , new Panel1());
        jtp.addTab("OutBox" , new Panel2());
        jtp.addTab("Sent" , new Panel3());
        add(jtp);
        setVisible(true);
    }

    public static void main(String[] args){
        new Home();
    }
}

class Panel1 extends JPanel {
    public Panel1(){
        JComboBox<String> jcb = new JComboBox<String>();
        jcb.addItem("Vanilla");
        jcb.addItem("Chocolate");
        jcb.addItem("Strawbery");
        add(jcb);
    }
}

class Panel2 extends JPanel {
    public Panel2(){
        JButton button_compose = new JButton();
        String[] colHeads = { "Name", "Extension", "ID#" };
        Object[][] data = {
                {"Gail","456715","5921214"},
                {"DJai","452111","3489151"},
                {"Init","415166","1149272"}
        };
        JTable table = new JTable(data, colHeads);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);
    }

}

class Panel3 extends JPanel {
    public Panel3(){
        JButton merah = new JButton("Merah");
        JButton biru = new JButton("Biru");
        JButton hijau = new JButton("Hijau");
        add(merah);
        add(biru);
        add(hijau);

    }
}


