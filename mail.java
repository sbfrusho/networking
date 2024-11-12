import java.io.*;
import java.time.LocalDateTime;
import javax.net.ssl.*;
import java.util.*;

class mail{
    public static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String argv[]){

        String user = "s2010776109@ru.ac.bd";
        String pass = "b00cf76ff7";

        String username = new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));

        SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",405);
        dos = new DataOutputStream(s.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        send("EHLO smtp.gmal.com\r\n");
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());

        send("AUTH LOGIN\r\n");

        send(username +"\r\n");

        send(password + "\r\n");

        send("MAIL FROM: <s2010776109@ru.ac.bd>\r\n");

        send("RCPT TO: <sbfrusho@gmail.com>\r\n");

        send("DATA\r\n");
        System.out.println("server: " + br.readLine());

        send("FROM: s2010776109@ru.ac.bd\r\n");
        send("TO: sbfrusho@gmail.com\r\n");
        send("Subject: i am doing\r\n");
        send("cheking the codeing unga bunga\r\n");
        send(".\r\n");


    }

    private static void send(String s) throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("Clien: "+s);
    }
}