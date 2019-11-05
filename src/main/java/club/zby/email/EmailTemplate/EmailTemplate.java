package club.zby.email.EmailTemplate;

import club.zby.email.Entity.MailBean;
import club.zby.email.Until.Result;
import club.zby.email.Until.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;


@Component
public class EmailTemplate implements EmailTemplateFace {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value(value = "${lance.mail.sender}")
    private String MAIL_SENDER;
    @Autowired
    private final static Logger logger = LoggerFactory.getLogger(EmailTemplate.class);


    /**
     * 自定义内容
     * @param mailBean
     */
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            simpleMailMessage.setTo(mailBean.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mailBean.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送图片附件
     * @param mailBean
     */
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addAttachment("mail.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送html格式的邮件 --- emailTemplate.html
     * @param mailBean
     * @return
     */
    public Result sendHtmlMail(MailBean mailBean) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(MAIL_SENDER);
            helper.setTo(mailBean.getRecipient());
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getContent(),true);
            javaMailSender.send(message);
            return new Result(true, StatusCode.OK,"html格式邮件发送成功",mailBean.getRecipient());
        }catch (Exception e){
            System.out.println("html格式邮件发送失败");
            return new Result(false, StatusCode.ERROR,e.getMessage(),mailBean.getRecipient());
        }
    }


}
