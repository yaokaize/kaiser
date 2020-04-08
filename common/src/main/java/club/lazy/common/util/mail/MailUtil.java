package club.lazy.common.util.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Map;

import static club.lazy.common.contant.CommonContants.*;

@Component
public class MailUtil {

    private final static String SEND_MAIL = "zz_ykz@163.com";

    private JavaMailSender mailSender;

    @Autowired
    public MailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发送普通邮件
     *
     * @param Addressee
     * @param subject
     * @param content
     * @return
     */
    public String sendSimpleMailMessage(String Addressee, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(SEND_MAIL);
        simpleMailMessage.setTo(Addressee);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            mailSender.send(simpleMailMessage);
            return SEND_SUCCESS;
        } catch (Exception e) {
            return SEND_FAIL;
        }
    }

    /**
     * 发送html邮件
     *
     * @param Addressee
     * @param subject
     * @param content
     * @return
     */
    public String sendMimeMessage(String Addressee, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(SEND_MAIL);
            mimeMessageHelper.setTo(Addressee);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            mailSender.send(mimeMessage);
            return SEND_SUCCESS;
        } catch (MessagingException e) {
            return SEND_FAIL;
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param Addressee
     * @param subject
     * @param content
     * @param filePath
     */
    public String sendMimeMessage(String Addressee, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(SEND_MAIL);
            mimeMessageHelper.setTo(Addressee);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = fileSystemResource.getFilename();
            if (StringUtils.isBlank(fileName)) {
                return NULL_FILENAME;
            }
            mimeMessageHelper.addAttachment(fileName, fileSystemResource);

            mailSender.send(mimeMessage);
            return SEND_SUCCESS;
        } catch (MessagingException e) {
            return SEND_FAIL;
        }
    }

    /**
     * 发送带静态文件的邮件
     *
     * @param Addressee
     * @param subject
     * @param content
     * @param rscIdMap
     * @return
     */
    public String sendMimeMessage(String Addressee, String subject, String content, Map<String, String> rscIdMap) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(SEND_MAIL);
            mimeMessageHelper.setTo(Addressee);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
                FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
                mimeMessageHelper.addInline(entry.getKey(), file);
            }
            mailSender.send(mimeMessage);

            return SEND_SUCCESS;
        } catch (MessagingException e) {
            return SEND_FAIL;
        }
    }
}
