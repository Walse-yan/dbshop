package Utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
public class mailUtils
{
	public static boolean sendMail(String email, String emailMsg)
	{
		Properties props = new Properties();   //创建properties对象，设置邮件服务器的基本信息
		props.put("mail.smtp.host", "smtp.163.com");   //设置为QQ邮件服务器
        props.put("mail.smtp.auth", "true");    //设置服务器需要用户验证
        props.put("mail.smtp.ssl.enable", "true");

        props.put("mail.transport.protocol", "smtp");   //设置邮件协议为SMTP
        //props.put("mail.debug", "true");     //观察发送邮件的过程
        props.put("mail.smtp.timeout", "20000");    //限制发送邮件,等待20秒
        props.put("mail.smtp.port", "587");
        
        
		
		final String from = "15975526586@163.com";   //发件人的邮箱
		final String password = "HGFHHINVYYXBJETP";
		// 获取邮件会话session对象
	    Session session = Session.getDefaultInstance(props,new Authenticator(){
	    	@Override
	        public PasswordAuthentication getPasswordAuthentication()   //匿名类
	        {
	        	return new PasswordAuthentication(from, password); //发件人邮件用户名、授权码
	        }
	     });    
		
	    try
		{
	    	Message message = new MimeMessage(session);  //创建mimeMessage对象，相当于邮件内容
			message.setFrom(new InternetAddress(from));   //设置发送者
			
			message.setRecipient(RecipientType.TO, new InternetAddress(email));   //设置发送者和发送方式
			message.setSubject("全全商城");   //邮件主题
			message.setContent(emailMsg, "text/html;charset=utf-8");
			System.out.println("可以运行到send之前");
			Transport.send(message);
			System.out.println("发送邮件成功");
			return true;
		} catch (MessagingException e)
		{
			System.out.println("发送邮件失败");

			e.printStackTrace();
			return false;
		}    
		
	}
}
