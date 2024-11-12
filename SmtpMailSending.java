import java.io.*;
import java.time.LocalDateTime;
import javax.net.ssl.*;
import java.util.*;

class SmtpMailSending {

  private static DataOutputStream dos;
  public static BufferedReader br;

  public static void main(String argv[]) throws Exception {
    String user = "s2010776109@ru.ac.bd";
    String pass = "b00cf74ff7";
    
    String username = new String(Base64.getEncoder().encode(user.getBytes()));
    String password = new String(Base64.getEncoder().encode(pass.getBytes()));

    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket s = (SSLSocket) factory.createSocket("smtp.gmail.com", 465);
    s.startHandshake();
    dos = new DataOutputStream(s.getOutputStream());
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));

    receive();

    send("EHLO smtp.gmail.com\r\n");
    receive();

    send("AUTH LOGIN\r\n");
    receive(); 

    send(username + "\r\n");
    receive();

    send(password + "\r\n"); 
    receive();

    send("MAIL FROM:<s2010776109@ru.ac.bd>\r\n");
    receive();

    send("RCPT TO:<sbfrusho@gmail.com>\r\n"); 
    receive();

    send("DATA\r\n"); 
    receive(); 
    send("From: s2010776109@ru.ac.bd\r\n");
    send("To: sbfrusho@gmail.com\r\n");
    send("Subject: Email test " + LocalDateTime.now() + "\r\n");
    send("\r\n");
    send("THIS IS A TEST EMAIL. THANK YOU\r\n");
    send(".\r\n");

    receive();

    send("QUIT\r\n"); 
    receive(); 
    s.close();
  }

  private static void send(String s) throws Exception {
    dos.writeBytes(s);
    System.out.println("CLIENT: " + s);
  }

  private static void receive() throws Exception {
    String response;
    while ((response = br.readLine()) != null) {
      System.out.println("SERVER: " + response);
      if (response.startsWith("2") || response.startsWith("3")) {
        break;
      }
    }
  }
}
