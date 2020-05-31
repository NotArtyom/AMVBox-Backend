package ru.itis.demo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${spring.mail.username}")
    private String userName;

    @Override
    public void sendMail(String subject, String text, String email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(userName);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);

            Template t = freemarkerConfig.getTemplate("mail.ftlh");
            Map<String, Object> model = new HashMap<>();
            model.put("code", text);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            messageHelper.setText(html, true);
        };

        javaMailSender.send(messagePreparator);
    }

    @Override
    public void sendTextMail(String subject, String text, String email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(userName);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);

            messageHelper.setText(text, false);
        };

        javaMailSender.send(messagePreparator);
    }
}
