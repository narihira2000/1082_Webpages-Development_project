package project.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Reply {
    private String username;
    private Long millis;
    private String content;
    private int m_id;
    
    public Reply(String username, Long millis, String content, int m_id) {
        this.username = username;
        this.millis = millis;
        this.content = content;
        this.m_id = m_id;
    }

    public String getUsername() {
        return username;
    }

    public Long getMillis() {
        return millis;
    }
    
    public String getContent() {
    	return content;
    }
    
    public int getID() {
    	return m_id;
    }
    
    public LocalDateTime getLocalDateTime() {
        return Instant.ofEpochMilli(millis)
                      .atZone(ZoneId.of("Asia/Taipei"))
                      .toLocalDateTime();
    }
}


