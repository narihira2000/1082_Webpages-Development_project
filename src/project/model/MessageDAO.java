package project.model;

import java.util.List;

public interface MessageDAO {
    List<Message> messagesBy(String username);
    Message getMessageByID(int id);
    void createMessage(Message message);
    void deleteMessageBy(String username, String millis, int ID);
    List<Message> newestMessages(int n);
}
