package bcteamC.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import bcteamC.DTO.gameDTO;

public class gameDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "c##scott";
	String pw = "tiger";
	
	public gameDAO() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	// select
	
	public ArrayList<gameDTO> select(String id) {
		
		ArrayList<gameDTO> list = new ArrayList<gameDTO>();
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM game WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeQuery();
			
			
			while(res.next()) {
				int gameId = res.getInt("gameId");
				int musicId = res.getInt("musicId");
				String userId = res.getString("userId");
				int perfect = res.getInt("perfect");
				int good = res.getInt("good");
				int miss = res.getInt("miss");
				int musicScore = res.getInt("musicScore");
				
				gameDTO game = new gameDTO(gameId, userId, musicId, perfect, good, miss, musicScore);
				list.add(game);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(res != null) res.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
		
		//insert
		
		public ArrayList<gameDTO> insert(int gameId, String userId, int musicId, int perfect, int good, int miss, int musicScore) {
			
			java.sql.Connection con = null;
			java.sql.PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(url, id, pw);
				
				String sql = "INSERT INTO game VALUES (game_seq.nextval,?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setInt(2, musicId);
				pstmt.setInt(3, perfect);
				pstmt.setInt(4, good);
				pstmt.setInt(5, miss);
				pstmt.setInt(6, musicScore);
							
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println(userId + "님이 게임을 하셨습니다.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return null;
			
		}
	}

