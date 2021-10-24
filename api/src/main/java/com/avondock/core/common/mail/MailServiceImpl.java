package com.avondock.core.common.mail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service("MailService")
public class MailServiceImpl implements MailService {

    private final JavaMailSender sender;

    private final MailProperties properties;

    private final FreeMarkerConfigurer freemarkerConfigurer;

    @Autowired
    public MailServiceImpl(JavaMailSender sender, MailProperties properties, FreeMarkerConfigurer freemarkerConfigurer) {
        this.sender = sender;
        this.properties = properties;
        this.freemarkerConfigurer = freemarkerConfigurer;
    }

    public void sendPlain(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getAccount());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        sender.send(message);
    }

    public void sendFtl(String to, String subject, Map<String, Object> model, String template) throws TemplateException, IOException {
        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate(template + ".ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, model);

        sendHtml(to, subject, htmlBody);
    }

    private void sendHtml(String to, String subject, String html) {
        try {
            MimeMessage       message = sender.createMimeMessage();
            MimeMessageHelper helper  = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(properties.getAccount());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
