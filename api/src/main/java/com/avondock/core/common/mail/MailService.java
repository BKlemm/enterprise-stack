package com.avondock.core.common.mail;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface MailService {

    void sendPlain(String to, String subject, String text);

    void sendFtl(String to, String subject, Map<String, Object> model, String template) throws TemplateException, IOException;
}
