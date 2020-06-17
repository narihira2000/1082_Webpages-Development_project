package project.model;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class ReplyDAOJdbcImpl implements ReplyDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public ReplyDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Reply> ReplyUnderID(int id){
//		List rows = jdbcTemplate.queryForList("SELECT * FROM reply WHERE message_id = " + id);
//		Iterator it = rows.iterator();
//		List<Reply> replies = new ArrayList<>();
//		while(it.hasNext()) {
//			Map userMap = (Map)it.next();
//			
//			String name = userMap.get("username").toString();
//			Long time = Long.parseLong((userMap.get("time").toString()));
//			String content = userMap.get("content").toString();
//			int m_id = Integer.parseInt(userMap.get("message_id").toString());
//			replies.add(new Reply(name,time,content,m_id));
//		}
//		return replies;
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reply WHERE message_id = ?")){
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				List<Reply> replies = new ArrayList<>();
				while(rs.next()) {
					replies.add(new Reply(
							rs.getString(1),
							rs.getLong(3),
							rs.getString(4),
							rs.getInt(2))
					);
				}
				return replies;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void createReply(Reply reply) {
//		String name = reply.getUsername();
//		Long time = reply.getMillis();
//		String content = reply.getContent();
//		int m_id = reply.getID();
//		jdbcTemplate.update("INSERT INTO reply(username,time,content,message_id) VALUES("+name+","+time+","+content+","+m_id+")");
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO reply(username,message_id,time,content) VALUES(?,?,?,?)")){
			stmt.setString(1, reply.getUsername());
			stmt.setLong(3, reply.getMillis());
			stmt.setString(4, reply.getContent());
			stmt.setInt(2, reply.getID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void deleteReplyBy (String username, String millis, int id) {
		jdbcTemplate.update("DELETE FROM reply WHERE username = "+username+" AND time = " + millis + " AND message_id = " + id);

		/*try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM t_message WHERE name = ? AND time = ?")){
			stmt.setString (1, username); 
			stmt.setLong (2, Long.parseLong (millis)); 
			stmt.executeUpdate(); 
		} catch (SQLException e) { 
			throw new RuntimeException (e);
		}*/
	}
	
	@Override 
	public List<Reply> newestReply (int n, int id) { 
		List rows = jdbcTemplate.queryForList("SELECT * FROM reply WHERE message_id = " + id + " ORDER BY time DESC LIMIT " + n);
		Iterator it = rows.iterator();
		List<Reply> replies = new ArrayList<>();
		while(it.hasNext()) {
			Map userMap = (Map)it.next();
			
			String name = userMap.get("username").toString();
			Long time = Long.parseLong((userMap.get("time").toString()));
			String content = userMap.get("content").toString();
			int m_id = Integer.parseInt(userMap.get("message_id").toString());
			replies.add(new Reply(name,time,content,m_id));
		}
		return replies;
		/*try (Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM t_message ORDER BY time DESC LIMIT ?")){
			stmt.setInt(1, n); 
			ResultSet rs = stmt.executeQuery();
			
			List<Message> messages = new ArrayList<>(); 
			while(rs.next()) { 
				messages.add(new Message( 
					rs.getString(1), 
					rs.getLong(2), 
					rs.getString(3)) 
				);
			}
			return messages;
		} catch (SQLException e) { 
			throw new RuntimeException (e);
		}*/
	}

	
	
}
