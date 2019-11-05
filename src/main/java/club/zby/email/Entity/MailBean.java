package club.zby.email.Entity;

import java.io.Serializable;

public class MailBean implements Serializable {

    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
