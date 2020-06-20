package project.model;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



public class AccountDAOJdbcImpl implements AccountDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
//	@Autowired
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//	    this.jdbcTemplate = jdbcTemplate;
//	}
	
	@Override
	public void createAccount(Account acct) {
//		String username = acct.getName();
//		String email = acct.getEmail();
//		String password = acct.getPassword();
//		String salt = acct.getSalt();
//		System.out.print(username+"\n"+email+"\n"+password+"\n"+salt);
//		jdbcTemplate.update("INSERT INTO user(username,email,password,salt) VALUES("+username+","+email+","+password+","+salt+")");
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(username,email,password,salt) VALUES(?,?,?,?)");
				PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO user_role(username,role) VALUES(?,'unverified')")){
					stmt.setString(1, acct.getName());
					stmt.setString(2, acct.getEmail());
					stmt.setString(3, acct.getPassword());
					stmt.setString(4, acct.getSalt());
					stmt.executeUpdate();
					
					stmt2.setString(1, acct.getName());
					stmt2.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Optional<Account> accountBy(String name){
//		List rows = jdbcTemplate.queryForList("SELECT * FROM user WHERE username = " + name);
//		Iterator it = rows.iterator();
//		if(it.hasNext()) {
//			Map userMap = (Map) it.next();
//			String username = userMap.get("username").toString();
//			String email = userMap.get("email").toString();
//			String password = userMap.get("password").toString();
//			String salt = userMap.get("salt").toString();
//			return Optional.of(new Account(username, email, password, salt));
//		}
//		return Optional.empty();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")){
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return Optional.of(new Account(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
				));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<Account> accountByEmail(String email) {
		try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return Optional.of(new Account(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                ));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void activateAccount(Account acct) {
		try(Connection conn = dataSource.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE user_role SET role = ? WHERE username = ?")) {
	            stmt.setString(1, "member");
	            stmt.setString(2, acct.getName());
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	           throw new RuntimeException(e);
	        } 
	}

	@Override
	public void updatePasswordSalt(String email, String password, String salt) {
		try(Connection conn = dataSource.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET password = ?, salt = ? WHERE email = ?")) {
	            stmt.setString(1, password);
	            stmt.setString(2, salt);
	            stmt.setString(3, email);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	           throw new RuntimeException(e);
	        } 
		
	}
}

