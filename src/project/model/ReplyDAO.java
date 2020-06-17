package project.model;

import java.util.List;

public interface ReplyDAO {
    List<Reply> ReplyUnderID(int id);
    void createReply(Reply reply);
    void deleteReplyBy(String username, String millis, int id);
    List<Reply> newestReply(int n, int id);
}