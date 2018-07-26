package projetali;


import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendMail 
{

    public static void send(String to, String sub,String msg, final String user, final String pass) 
    {
        Properties props = new Properties();

        
props.put("mail.smtp.host", "smtp.gmail.com"); 
props.put("mail.smtp.port", "25"); 
props.put("mail.debug", "true"); 
props.put("mail.smtp.auth", "true"); 
props.put("mail.smtp.starttls.enable","true"); 
props.put("mail.smtp.EnableSSL.enable","true");

props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
props.setProperty("mail.smtp.socketFactory.fallback", "false");   
props.setProperty("mail.smtp.port", "465");   
props.setProperty("mail.smtp.socketFactory.port", "465"); 
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            
            
        } catch (MessagingException e) 
        {
            JOptionPane.showMessageDialog(null,"Something happened!");
            
            throw new RuntimeException(e);
        }
        
    }
}