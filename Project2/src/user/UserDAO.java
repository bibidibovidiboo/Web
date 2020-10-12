package user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public UserDAO() {
		  try {
		   String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		   String dbID = "hr";
		   String dbPassword = "happy";
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  }
		 }
	
	// login
    public int login(String userID, String userPassword) {
    	String SQL = "SELECT userPassword FROM member WHERE userID = ?";     
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {	
					return 1; 
				} else
					return 0; 
			}
			return -1; 
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				
				if(pstmt!=null) {
					rs.close();
				}
				
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return -2; 
	}
    
    // join
    public int join(User user) {
		String SQL = "INSERT INTO member VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return -1;

	}

}
