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
		String query="INSERT INTO mail(ID_Pengirim,ID_Penerima,Subject,Isi,E_status,Waktu_terima) VALUES(?,?,?,?,unread,date('Y-m-d H:i:s'))";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, id_pengirim);
		stmt.setString(2, id_penerima);
		stmt.setString(3, Subject);
		stmt.setString(4, isi);
		stmt.execute();
		stmt.close();
	}
	public Vector<Vector<Object>> selectEmail(String id)throws SQLException{
		String query = "SELECT * FROM mail WHERE ID_Pengirim = '"+id+"'";
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
	public Vector<Vector<Object>> searchEmailInbox(String id)throws SQLException{
		String query = "SELECT * FROM mail WHERE "
				+ "(ID_Pengirim LIKE ?)or(ID_Pengirim LIKE ?)or(ID_Pengirim LIKE ?)or(ID_Pengirim LIKE ?)or"
				+ "(Subject LIKE ?)or(Subject LIKE ?)or(Subject LIKE ?)or(Subject LIKE ?)or"
				+ "(isi LIKE ?)or(isi LIKE ?)or(isi LIKE ?)or(isi LIKE ?)";
		String search1 = "%"+id;
		String search2 = "%"+id+"%";
		String search3 = id+"%";
		String search4 = id;
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, search1);
		stmt.setString(2, search2);
		stmt.setString(3, search3);
		stmt.setString(4, search4);
		stmt.setString(5, search1);
		stmt.setString(6, search2);
		stmt.setString(7, search3);
		stmt.setString(8, search4);
		stmt.setString(9, search1);
		stmt.setString(10, search2);
		stmt.setString(11, search3);
		stmt.setString(12, search4);
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
	public Vector<Vector<Object>> searchEmailSent(String id)throws SQLException{
		String query = "SELECT * FROM mail WHERE "
				+ "(ID_Penerima LIKE ?)or(ID_Penerima LIKE ?)or(ID_Penerima LIKE ?)or(ID_Penerima LIKE ?)or"
				+ "(Subject LIKE ?)or(Subject LIKE ?)or(Subject LIKE ?)or(Subject LIKE ?)or"
				+ "(isi LIKE ?)or(isi LIKE ?)or(isi LIKE ?)or(isi LIKE ?)";
		String search1 = "%"+id;
		String search2 = "%"+id+"%";
		String search3 = id+"%";
		String search4 = id;
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, search1);
		stmt.setString(2, search2);
		stmt.setString(3, search3);
		stmt.setString(4, search4);
		stmt.setString(5, search1);
		stmt.setString(6, search2);
		stmt.setString(7, search3);
		stmt.setString(8, search4);
		stmt.setString(9, search1);
		stmt.setString(10, search2);
		stmt.setString(11, search3);
		stmt.setString(12, search4);
		ResultSet rs=stmt.executeQuery(query);
		System.out.println("");
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
	public void hapus(String ID_Penerima,String Subjects,String isi,String ID_Pengirim) throws SQLException{
		stmt=conn.createStatement();
		String query = "DELETE FROM mail Where ID_Pengirim = '"+ID_Pengirim+"' and ID_Penerima = '"+ID_Penerima+"' and Subjects = '"+Subjects+"' and isi = '"+isi+"'";
		stmt.execute(query);
	}
	public static void main(String[] args){
	}
}
