package com.sfzjh.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送工具类
 * @Author  孙飞
 * @Date  2021年03月10日 09:25
 * @PackageName  com.sfzjh.util
 * @Name  MailUtils
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public final class MailUtils {
    /**
     * 发件人称号，同邮箱地址
     */
    private static final String USER = "sunfei@sfzjh.com";
    /**
     * 如果是qq邮箱可以使客户端授权码，或者登录密码
     */
    private static final String PASSWORD = "lIIl0.*.o0o0lIIl";
    private static final String HOST = "smtp.exmail.qq.com";
    private static final String PORT = "465";
    private static final String PROTOCOL = "imap";


    /**
     * 邮件初始化
     * @author  孙飞
     * @date  2021年03月08日 16:22
     * @return  javax.mail.Session
     */
    public static Session initProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", PROTOCOL);
        properties.setProperty("mail.smtp.host", HOST);
        properties.setProperty("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory socketFactory = null;
        try {
            socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException exception) {
            exception.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", socketFactory);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", PORT);
       return Session.getDefaultInstance(properties, new Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(USER, PASSWORD);
           }
       });
    }
    /**
     * 发送邮件
     * @author  孙飞
     * @date  2021年03月08日 16:20
     * @param receiver 接收人，多个接收者用","隔开
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return  返回是否发送成功
     */
    public static void sendMail(String receiver, String subject, String content){
        try {
            Session session = initProperties();
            MimeMessage mimeMessage = new MimeMessage(session);
            //发件人
            mimeMessage.setFrom(new InternetAddress(USER, "码农博客"));
            //收件人，多人接收
            InternetAddress[] internetAddressesTo = InternetAddress.parse(receiver, true);
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddressesTo);
            //主题
            mimeMessage.setSubject(subject);
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类 附件
            MimeMultipart mimeMultipart = new MimeMultipart();
            //可以包装文本，图片，附件
            MimeBodyPart bodyPart = new MimeBodyPart();
            //设置内容
            bodyPart.setContent(content, "text/html;charset=UTF-8");
            mimeMultipart.addBodyPart(bodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException exception) {
            exception.printStackTrace();
        }
    }
    /**
     * 发送验证信息的邮件
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     */
    public static boolean send(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 测试邮件发送
     * @author  孙飞
     * @date  2021年03月10日 09:30
     * @param args 测试
     * @return  void
     */
    public static void main(String[] args) throws Exception { // 做测试用
        MailUtils.sendMail("939763776@qq.com","老婆，妇女节快乐","老婆，老公爱你噢！");
        System.out.println("发送成功");
    }



}
