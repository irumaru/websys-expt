package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShiDAO {
	public List<Shi> findAll() {
		List<Shi> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132043";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT * FROM SHI";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("県コード");
				String shi = rs.getString("市名");
				Shi a = new Shi(id, shi);
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
}
