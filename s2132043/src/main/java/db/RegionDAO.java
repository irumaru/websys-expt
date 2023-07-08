package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
	public List<Region> findAll() {
		List<Region> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132043";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT KEN.県コード, 県名, 市コード, 市名 FROM KEN INNER JOIN SHI USING(県コード);";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				int kencode = rs.getInt("県コード");
				String kenname = rs.getString("県名");
				int shicode = rs.getInt("市コード");
				String shiname = rs.getString("市名");
				
				Region a = new Region(kencode, kenname, shicode, shiname);
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
	}
	
	public String getShicode(String kenName, String shiName) {
		String url = "jdbc:h2:tcp://localhost/./s2132043";
		Connection conn = null;
		String shicode = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT 市コード FROM SHI WHERE 県コード IN (SELECT 県コード FROM KEN WHERE 県名 = ?) AND 市名 = ?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, kenName);
			pre.setString(2, shiName);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				shicode = rs.getString("市コード");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return shicode;
	}
	
	public String getPokemonCode(String name) {
		String url = "jdbc:h2:tcp://localhost/./s2132043";
		Connection conn = null;
		String pokemonCode = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT 番号 FROM POKEMON WHERE 名前 = ?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				pokemonCode = rs.getString("番号");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return pokemonCode;
	}
}
