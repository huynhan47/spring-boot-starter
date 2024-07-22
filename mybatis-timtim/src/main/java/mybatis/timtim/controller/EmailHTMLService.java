/*
 *    Copyright 2015-2024 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mybatis.timtim.controller;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailHTMLService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendHtmlEmail(String to, String subject) throws MessagingException, jakarta.mail.MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    //
    // message.setFrom(new InternetAddress(to));
    // message.setRecipients(MimeMessage.RecipientType.TO, to);
    // message.setSubject(subject);
    // //message.addHeaderLine("myLogo", new ClassPathResource("img/mylogo.gif"));
    //
    // try {
    // message.setContent(body, "text/html; charset=utf-8");
    // } catch (MessagingException e) {
    // throw new RuntimeException(e);
    // }
    // Session session = Session.getInstance(props,
    // new javax.mail.Authenticator() {
    // protected PasswordAuthentication getPasswordAuthentication() {
    // return new PasswordAuthentication(mailSender.getClass().g, password);
    // }
    // });

    // Message message = new MimeMessage(session);
    //
    // // Set From: header field of the header.
    // message.setFrom(new InternetAddress(from));

    // // Set To: header field of the header.
    // message.setRecipients(Message.RecipientType.TO,
    // InternetAddress.parse(to));
    //
    // // Set Subject: header field
    message.setSubject("Testing Subject");

    message.setRecipients(MimeMessage.RecipientType.TO, to);
    message.setSubject(subject);

    // This mail has 2 part, the BODY and the embedded image
    MimeMultipart multipart = new MimeMultipart("related");

    // first part (the html)
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
    messageBodyPart.setContent(htmlText, "text/html");
    // add it
    multipart.addBodyPart(messageBodyPart);

    // second part (the image)
    messageBodyPart = new MimeBodyPart();
    DataSource fds = new FileDataSource("C:\\Test\\Vietnamese1.png");

    messageBodyPart.setDataHandler(new DataHandler(fds));
    messageBodyPart.setHeader("Content-ID", "<image>");

    // add image to the multipart
    multipart.addBodyPart(messageBodyPart);

    // put everything together
    message.setContent(multipart);

    mailSender.send(message);
  }
}
