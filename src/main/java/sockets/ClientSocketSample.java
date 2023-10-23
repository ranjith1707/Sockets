package sockets;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class ClientSocketSample {
    static private Socket client;
    static private PrintWriter print;
    static private BufferedReader reader;
    static Logger LOGGER = Logger.getLogger(ClientSocketSample.class.getName());

    public static void main(String[] args){
        try{
            Scanner scanner=new Scanner(System.in);
            client= new Socket("127.0.0.1",4545);
            print=new PrintWriter(client.getOutputStream(),true);
            reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String message;
            while(true){
                System.out.print(">>");
                if((message=scanner.nextLine())==null){
                    LOGGER.info("Going to close the connection with server...");
                    print.println("close");
                    break;
                }
                print.println(message);

                String response=reader.readLine();
                LOGGER.info("Response from server: "+response);
                Thread.sleep(100);
            }
        }catch(Exception e) {
            System.out.print(e);
        }

    }
}
