package project.model;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class MessageDAOJdbcImpl implements MessageDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public MessageDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Message> messagesBy(String username){
//		List rows = jdbcTemplate.queryForList("SELECT * FROM message WHERE username = " + username);
//		Iterator it = rows.iterator();
//		List<Message> messages = new ArrayList<>();
//		while(it.hasNext()) {
//			Map userMap = (Map)it.next();
//			
//			String name = userMap.get("username").toString();
//			Long time = Long.parseLong((userMap.get("time").toString()));
//			String title = userMap.get("title").toString();
//			String content = userMap.get("content").toString();
//			int id = Integer.parseInt(userMap.get("id").toString());
//			messages.add(new Message(name,time,title,content,id));
//		}
//		return messages;
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM message WHERE username = ?")){
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				
				List<Message> messages = new ArrayList<>();
				while(rs.next()) {
					messages.add(new Message(
							rs.getString(1),
							rs.getLong(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5))
					);
				}
				return messages;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void createMessage(Message message) {
//		String name = message.getUsername();
//		Long time = message.getMillis();
//		String title = message.getTitle();
//		String content = message.getContent();
//		jdbcTemplate.update("INSERT INTO message(username,time,title,content) VALUES("+name+","+time+","+title+","+content+")");
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO message(username,time,title,content) VALUES(?,?,?,?)")){
			stmt.setString(1, message.getUsername());
			stmt.setLong(2, message.getMillis());
			stmt.setString(3, message.getTitle());
			stmt.setString(4, message.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void deleteMessageBy (String username, String millis) {
//		jdbcTemplate.update("DELETE FROM message WHERE username = "+username+" AND time = " + millis);

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM message WHERE username = ? AND time = ?")){
			stmt.setString (1, username); 
			stmt.setLong (2, Long.parseLong (millis)); 
			stmt.executeUpdate(); 
		} catch (SQLException e) { 
			throw new RuntimeException (e);
		}
	}
	
	@Override 
	public List<Message> newestMessages (int n) { 
//		List rows = jdbcTemplate.queryForList("SELECT * FROM message ORDER BY time DESC LIMIT " + n);
//		Iterator it = rows.iterator();
//		List<Message> messages = new ArrayList<>();
//		while(it.hasNext()) {
//			Map userMap = (Map)it.next();
//			
//			String name = userMap.get("username").toString();
//			Long time = Long.parseLong((userMap.get("time").toString()));
//			String title = userMap.get("title").toString();
//			String content = userMap.get("content").toString();
//			int id = Integer.parseInt(userMap.get("id").toString());
//			messages.add(new Message(name,time,title,content,id));
//		}
//		return messages;
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM message ORDER BY time DESC LIMIT ?")){
			stmt.setInt(1, n); 
			ResultSet rs = stmt.executeQuery();
			
			List<Message> messages = new ArrayList<>(); 
			while(rs.next()) { 
				messages.add(new Message( 
					rs.getString(1), 
					rs.getLong(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5)) 
				);
			}
			return messages;
		} catch (SQLException e) { 
			throw new RuntimeException (e);
		}
	}
	
	
	@Override
	public Message getMessageByID(int id) {
		System.out.println("<messageDAO>"+id);
		try(Connection conn = dataSource.getConnection();
				
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM message WHERE id = ?")){
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				Message message = null;
				
				
				while(rs.next()) {
					message = new Message(
							rs.getString(1),
							rs.getLong(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
					);
				}
				
				
				return message;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
