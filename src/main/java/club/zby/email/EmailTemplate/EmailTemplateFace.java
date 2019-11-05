package club.zby.email.EmailTemplate;

import club.zby.email.Entity.MailBean;
import club.zby.email.Until.Result;
import org.springframework.stereotype.Component;

@Component
public interface EmailTemplateFace {

    void sendSimpleMail(MailBean mailBean);
    void sendAttachmentMail(MailBean mailBean);
    Result sendHtmlMail(MailBean mailBean);

}
