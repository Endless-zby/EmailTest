package club.zby.email.EmailTemplate;

import club.zby.email.Entity.MailBean;
import club.zby.email.Until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class HttpEmail implements HttpEmailFace {

    @Autowired
    private EmailTemplateFace emailTemplateFace;
    @Autowired
    private TemplateEngine templateEngine;

    public Result httpEmail(String id, String email){

        Context context = new Context();
        context.setVariable("id", id);
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(email);
        mailBean.setSubject("赵博雅,邮箱验证测试");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailBean.setContent(emailContent);
        return emailTemplateFace.sendHtmlMail(mailBean);

    }


}
