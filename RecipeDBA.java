package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class RecipeDBA {
	String url, user, password;
	public RecipeDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			password = "1234";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//추가
	public void cookInsert(Recipe recipe) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into recipe values(rec_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, recipe.getCookname());
			ps.setString(2, recipe.getCookkind());
			ps.setString(3, recipe.getCookstuff());
			ps.setString(4, recipe.getCookrecipe());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
	}
	
	//수정
	public void cookUpdate(Recipe r) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "update recipe set cookname=?, cookkind=?, cookstuff=?, cookrecipe=? where num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, r.getCookname());
			ps.setString(2, r.getCookkind());
			ps.setString(3, r.getCookstuff());
			ps.setString(4, r.getCookrecipe());
			ps.setInt(5, r.getNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
	}
	
	//삭제
	public void cookDelete(int num) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "delete from recipe where num="+num;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
	}
	
	//검색
	public ArrayList<Recipe> cookSearch(String kind, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Recipe> arr = new ArrayList<Recipe>();
		int morethanone = 0;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from recipe where "+kind+" like '%"+word+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setNum(rs.getInt("num"));
				r.setCookkind(rs.getString("cookkind"));
				r.setCookname(rs.getString("cookname"));
				r.setCookstuff(rs.getString("cookstuff"));
				r.setCookrecipe(rs.getString("cookrecipe"));
				arr.add(r);
				morethanone++;
			}
			if(arr.size()==0) {
				JOptionPane.showMessageDialog(null, "찾는 항목이 없습니다."+"\n"+"레시피를 추가해주세요.");
			}
			if(morethanone>1) {
				JOptionPane.showMessageDialog(null, "겹치는 항목이 있습니다."+" ("+morethanone+"개)"+"\n"+"다른 검색어를 입력해주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	
	//F5(전체보기)
	public ArrayList<Recipe> cookView() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Recipe> rec = new ArrayList<Recipe>();
		
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from recipe";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setNum(rs.getInt("num"));
				r.setCookname(rs.getString("cookname"));
				r.setCookkind(rs.getString("cookkind"));
				r.setCookstuff(rs.getString("cookstuff"));
				r.setCookrecipe(rs.getString("cookrecipe"));
				rec.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeCon(con, st, rs);
		}
		return rec;
	}
	
	

	private void closeCon(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeCon(Connection con, Statement st, ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
