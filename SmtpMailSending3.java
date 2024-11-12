import java.io.*;
import java.net.UnknownHostException;
import java.util.Base64;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class SmtpMailSending3{
    public static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String[] args) {
        String user = "s2010776109@ru.ac.bd";
        String pass = "b00cf74ff7";

        String userName = new String(Base64.getEncoder().encode(user.getBytes()));
        String passWord = new String(Base64.getEncoder().encode(pass.getBytes()));

        try {
            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",465);
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            try {
                send("EHLO smtp.gmail.com\r\n");

                send("AUTH LOGIN\r\n");
                send(userName +"\r\n");
                send(passWord + "\r\n");
                send("MAIL FROM: <s2010776109@ru.ac.bd>\r\n");
                send("RCPT TO: <sbfrusho@gmail.com>\r\n");

                send("DATA\r\n");
                send("FROM: s2010776109@ru.ac.bd\r\n");
                send("TO: sbrusho@gmail.com\r\n");
                send("SUBJECT: ETAI LAST TIME KROTASI.\r\n");
                send("HOYLE HOILO NA HOILE NAI..LILE LAN LA LILE LA LAN\r\n");
                send(".\r\n");
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static void send(String s)throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: "+s);
    }
}