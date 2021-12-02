package ma.octo.assignement.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Result<T> {
    private List<Message> errors;
    private List<Message> infos;
    private T data;

    public void addErrorMessage(String message) {
        addMessage(message, MessageType.ERROR);
    }

    public void addInfosMessage(String message) {
        addMessage(message, MessageType.INFO);
    }

    private void addMessage(String messageText, MessageType type) {
        Message msg = new Message(messageText);
        if (type == MessageType.ERROR) {
            if (getErrors() == null) errors = new ArrayList<>();
            getErrors().add(msg);
        } else if (type == MessageType.INFO) {
            if (getInfos() == null) infos = new ArrayList<>();
            getInfos().add(msg);
        }
    }
}

