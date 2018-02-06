package com.spider.song.spidercommon.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

//@Service
public class SendEmail {


    private static Logger logger = LoggerFactory.getLogger(SendEmail.class.getSimpleName());

    @Value("${myEmailStefanPassword}")
    private String myEmailStefanPassword;

    private static final String myEmailAccount = "stefan1102@163.com";//songzhengjie@gomeholdings.com
    private static final String myEmailPassword = "31385815916s";//授权码，切记不是密码，很重要！
    public static final String myEmailSMTPHost = "smtp.163.com";
    private static final String toEmailAccount = "songzhengjie@gomeholdings.com";


    public static  boolean send(String from, String toAddressList, String subject,String ccAddressList, String content) throws Exception {


        //  获取系统属性
        Properties properties = new Properties();//system.getProperties()
        // 使用的协议（JavaMail规范要求）
        properties.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.host", myEmailSMTPHost);
        //需要请求认证
        properties.setProperty("mail.smtp.auth", "true");

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        */
        final String smtpPort = "465";
        properties.setProperty("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // 获取默认session对象  根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(properties);
        session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
        Transport transport = null;

        try {


            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from==null?myEmailAccount:from,"我吃火锅你吃火锅底料","UTF-8"));
            // Set To: 头部头字段
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAccount));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddressList));

            //    Cc: 抄送（可选）
            message.setRecipients(MimeMessage.RecipientType.CC,InternetAddress.parse(ccAddressList));

            // Set Subject: 头部头字段
            message.setSubject(subject);
            // 设置消息体
            message.setContent(content,"text/html;charset=UTF-8");
            // 设置发送时间
            message.setSentDate(new Date());

            //不被当作垃圾邮件的关键代码--Begin ，如果不加这些代码，发送的邮件会自动进入对方的垃圾邮件列表
            message.addHeader("X-Priority", "3");
            message.addHeader("X-MSMail-Priority", "Normal");
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); //本文以outlook名义发送邮件，不会被当作垃圾邮件
            message.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
            message.addHeader("ReturnReceipt", "1");
            //不被当作垃圾邮件的关键代码--end

            // 7. 保存前面的设置
            message.saveChanges();

            // 发送消息
            // 4. 根据 Session 获取邮件传输对象
            transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。

            transport.connect(myEmailAccount,myEmailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            logger.info("send e-mail successfully……");
        } catch (Exception e) {
            logger.error("发送邮件出现错误:",e);
            throw new Exception("发送邮件出现错误:", e);
        }finally {
            transport.close();
        }

        return true;
    }


    public static void main(String[] args) throws Exception {
        boolean bool = send(myEmailAccount,null,"this is a beautiful project!",null,"hello,world!hello,honey! --from hotpot");
        System.out.println("发送结果回传:"+bool);
    }

    /**
     *  // 创建消息部分
     BodyPart messageBodyPart = new MimeBodyPart();

     // 消息
     messageBodyPart.setText("This is message body");

     // 创建多重消息
     Multipart multipart = new MimeMultipart();

     // 设置文本消息部分
     multipart.addBodyPart(messageBodyPart);

     // 附件部分
     messageBodyPart = new MimeBodyPart();
     String filename = "file.txt";
     DataSource source = new FileDataSource(filename);
     messageBodyPart.setDataHandler(new DataHandler(source));
     messageBodyPart.setFileName(filename);
     multipart.addBodyPart(messageBodyPart);

     // 发送完整消息
     message.setContent(multipart );
     *
     */


}
