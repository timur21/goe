package kg.goent.controllers;

import kg.goent.models.message.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/14/2017.
 */
@ManagedBean
@ViewScoped
public class MessageViewController {
    /**
     * 1 - info
     * 2 - warning
     * 3 - error
     */
    private List<Message> messageList = new ArrayList<Message>();

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    public void addInfoMessage(String message){
        messageList.add(new Message(message,1));
    }
    public void addWarnMessage(String message){
        messageList.add(new Message(message,2));
    }
    public void addErrorMessage(String message){
        messageList.add(new Message(message,3));
    }
}
