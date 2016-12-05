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
	public smail_func() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smail","root","");
	}
	public boolean isConnected(){
		return (conn!=null);
	}
	public void lupaPass(String id,int pin) throws SQLException{
		String query = "select Pass from users WHERE ID = ? and PIN = ? ";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, id);
		stmt.setInt(2, pin);
		stmt.execute();
		stmt.close();
	}
	public void GantiPass(String pass,String id) throws SQLException{
		String query = "UPDATE user SET pass = ? WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, pass);
		stmt.setString(2, id);
		stmt.execute();
		stmt.close();
	}
	public ResultSet Login(String email,String pass) throws SQLException{
		String query = "Select Nama SET pass = ? WHERE Email = ? and pass= ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, email);
		stmt.setString(2, pass);
		ResultSet ans = stmt.executeQuery(query);
		stmt.close();
		return ans;
	}
	public void GantiPin(int pin,String id) throws SQLException{
		String query = "UPDATE user SET PIN = ? WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, pin);
		stmt.setString(2, id);
		stmt.execute();
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
		String query = "SELECT * FROM mail WHERE ID_Pengirim = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, id);
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
	public Vector<Vector<Object>> searchEmailInbox(String id)throws SQLException{
		String query = "SELECT * FROM books WHERE "
				+ "(ID_Pengirim = ?)or(ID_Pengirim = ?)or(ID_Pengirim = ?)or(ID_Pengirim = ?)or"
				+ "(Subject = ?)or(Subject = ?)or(Subject = ?)or(Subject = ?)or"
				+ "(isi = ?)or(isi = ?)or(isi = ?)or(isi = ?)";
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
		String query = "SELECT * FROM books WHERE "
				+ "(ID_Penerima = ?)or(ID_Penerima = ?)or(ID_Penerima = ?)or(ID_Penerima = ?)or"
				+ "(Subject = ?)or(Subject = ?)or(Subject = ?)or(Subject = ?)or"
				+ "(isi = ?)or(isi = ?)or(isi = ?)or(isi = ?)";
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
		String query = "DELETE FROM mail Where ID_Pengirim = ? and ID_Penerima = ?, and Subjects = ?, and isi = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, ID_Pengirim);
		stmt.setString(2, ID_Penerima);
		stmt.setString(3, Subjects);
		stmt.setString(4, isi);
		stmt.execute();
	}
	public static void main(String[] args){
	}
}
