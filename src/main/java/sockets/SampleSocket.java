package sockets;
import java.net.*;
import java.io.*;
import java.util.logging.Logger;

public class SampleSocket {
    static private ServerSocket serverSocket;
    static private Socket client;
    static private BufferedReader reader;
    static private PrintWriter print;
    static Logger LOGGER = Logger.getLogger(SampleSocket.class.getName());

    public static void main(String[] args) throws IOException {
        serverSocket=new ServerSocket(4545);
        LOGGER.info("server stated at 4545");

        client=serverSocket.accept();

        LOGGER.info("client call received "+client.getInetAddress());
        print=new PrintWriter(client.getOutputStream(),true);
        reader= new BufferedReader(new InputStreamReader(client.getInputStream()));
        String param;
        while((param=reader.readLine())!=null){
            if("close".equals(param)){
                print.println("Bye!");
                LOGGER.info("Connection going to close");
                break;
            }
            LOGGER.info("Got message from client "+param);
            print.println("I got you message!");
        }
    }

}

