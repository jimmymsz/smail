package smail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

class smail_func {
	private Connection conn;
	Statement stmt = null;

	public smail_func() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smail","root","");
	}
	public boolean isConnected(){
		return (conn!=null);
	}
	public String lupaPass(String id,int pin) throws SQLException{
		stmt=conn.createStatement();
		String query = "select Pass from users WHERE Email = '"+id+"' and PIN = '"+pin+"'";
		String asd=stmt.executeQuery(query).toString();
		stmt.close();
		return asd;
	}
	public void GantiPass(String pass,String id) throws SQLException{
		stmt=conn.createStatement();
		String query = "UPDATE users SET pass = '"+pass+"' WHERE Email = '"+id+"'";
		stmt.execute(query);
		stmt.close();
	}
	public void GantiPin(int pin,String id) throws SQLException{
		stmt=conn.createStatement();
		String query = "UPDATE users SET PIN = '"+pin+"' WHERE Email = '"+id+"'";
		stmt.execute(query);
		stmt.close();
	}
	public void RegisUser(String mail,String nama, String pass, int pin) throws SQLException{
		String query="INSERT INTO users(Email,Nama,Pass,PIN) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, mail);
		stmt.setString(2, nama);
		stmt.setString(3, pass);
		stmt.setInt(4, pin);
		stmt.execute();
		stmt.close();
	}
	public void kirimEmail(String id_pengirim,String id_penerima,String Subject,String isi) throws SQLException{
		String query="INSERT INTO mail(ID_Pengirim,ID_Penerima,Subjects,isi,E_status,Waktu_terima) VALUES(?,?,?,?,?,CURRENT_TIMESTAMP)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, id_pengirim);
		stmt.setString(2, id_penerima);
		stmt.setString(3, Subject);
		stmt.setString(4, isi);
		stmt.setString(5, "unread");
		stmt.execute();
		stmt.close();
	}
	public Vector<Vector<Object>> selectEmailSent(String id)throws SQLException{
		stmt=conn.createStatement();
		String query = "SELECT * FROM mail WHERE ID_Pengirim = '"+id+"'";
		ResultSet rs=stmt.executeQuery(query);
		Vector<Vector<Object>> datass = new Vector<Vector<Object>>();
		while(rs.next()){
			Vector<Object> e = new Vector<Object>();
			e.add(rs.getString("ID_Penerima"));
			e.add(rs.getString("Subjects"));
			e.add(rs.getString("isi"));
			e.add(rs.getString("E_status"));
			e.add(rs.getString("Waktu_terima"));
			datass.add(e);
		}
		stmt.close();
		return datass;
	}
	public Vector<Vector<Object>> selectEmailInbox(String id)throws SQLException{
		stmt=conn.createStatement();
		String query = "SELECT * FROM mail WHERE ID_Penerima = '"+id+"'";
		ResultSet rs=stmt.executeQuery(query);
		Vector<Vector<Object>> datass = new Vector<Vector<Object>>();
		while(rs.next()){
			Vector<Object> e = new Vector<Object>();
			e.add(rs.getString("ID_Pengirim"));
			e.add(rs.getString("Subjects"));
			e.add(rs.getString("isi"));
			e.add(rs.getString("E_status"));
			e.add(rs.getString("Waktu_terima"));
			datass.add(e);
		}
		stmt.close();
		return datass;
	}
	public Vector<Object> Login(String email,String pass) throws SQLException{
		stmt=conn.createStatement();
		String query = "Select Nama, Email from users WHERE Email = '"+ email +"' And pass= '"+ pass +"'";
		ResultSet rs=stmt.executeQuery(query);
		Vector<Object> ans = new Vector<Object>();
		while(rs.next()){
			ans.add(rs.getString("Nama"));
			ans.add(rs.getString("Email"));
		}
		stmt.close();
		return ans;
	}
	public Vector<Vector<Object>> searchEmail(String id)throws SQLException{
		stmt = conn.createStatement();
		String search1 = "%"+id;
		String search2 = "%"+id+"%";
		String search3 = id+"%";
		String search4 = id;
		String query = "SELECT * FROM mail WHERE "
				+ "(ID_Pengirim LIKE '"+search1+"')or(ID_Pengirim LIKE '"+search2+"')or(ID_Pengirim LIKE '"+search3+"')or(ID_Pengirim LIKE '"+search4+"')or"
				+ "(ID_Penerima LIKE '"+search1+"')or(ID_Penerima LIKE '"+search2+"')or(ID_Penerima LIKE '"+search3+"')or(ID_Penerima LIKE '"+search4+"')or"
				+ "(Subjects LIKE '"+search1+"')or(Subjects LIKE '"+search2+"')or(Subjects LIKE '"+search3+"')or(Subjects LIKE '"+search4+"')or"
				+ "(isi LIKE '"+search1+"')or(isi LIKE '"+search2+"')or(isi LIKE '"+search3+"')or(isi LIKE '"+search4+"')";
		ResultSet rs=stmt.executeQuery(query);
		System.out.println("");
		Vector<Vector<Object>> datass = new Vector<Vector<Object>>();
		while(rs.next()){
			Vector<Object> e = new Vector<Object>();
			e.add(rs.getString("ID_Pengirim"));
			e.add(rs.getString("Subjects"));
			e.add(rs.getString("isi"));
			e.add(rs.getString("E_status"));
			e.add(rs.getString("Waktu_terima"));
			datass.add(e);
		}
		stmt.close();
		return datass;
	}
	public void hapus(String ID_Penerima,String Subjects,String isi,String ID_Pengirim) throws SQLException{
		stmt=conn.createStatement();
		String query = "DELETE FROM mail Where ID_Pengirim = '"+ID_Pengirim+"' and ID_Penerima = '"+ID_Penerima+"' and Subjects = '"+Subjects+"' and isi = '"+isi+"'";
		stmt.execute(query);
	}
	public void reads(String id_pene,String isi,String subj,String pengirim)throws SQLException{
		stmt=conn.createStatement();
		String query = "UPDATE mail SET E_Status = 'read' WHERE ID_Penerima = '"+id_pene+"' and ID_Pengirim = '"+pengirim+"' and isi = '"+isi+"' and Subjects = '"+subj+"'";
		stmt.execute(query);
		stmt.close();
	}
	//fungsi verifikasi email yang dituju
	public static void main(String[] args){
	}
}
