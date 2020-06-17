package project.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Message {
    private String username;
    private Long millis;
    private String title;
    private String content;
    private int id;
    
    public Message(String username, Long millis, String title, String content, int id) {
        this.username = username;
        this.millis = millis;
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getMillis() {
        return millis;
    }

    public String getTitle() {
        return title;
    }
    
    public String getContent() {
    	return content;
    }
    
    public int getID() {
    	return id;
    }
    
    public LocalDateTime getLocalDateTime() {
        return Instant.ofEpochMilli(millis)
                      .atZone(ZoneId.of("Asia/Taipei"))
                      .toLocalDateTime();
    }
}

