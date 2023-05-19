package localChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {


    public static void main(String[] args) {
        Socket socket = null;
        try(ServerSocket serverSocket = new ServerSocket(EchoConstants.PORT)) {
            System.out.println("Server started. Wait for connection...");
            socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String string = inputStream.readUTF();
                if (string.equals(EchoConstants.STOP_WORD)){
                    break;
                }
                outputStream.writeUTF("Echo: " + string);
//                writeLog(string);
            }
            System.out.println("Server shutting down.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeLog(String message) {
        File file = new File("D:\\log.txt");
        try(FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            out.print(message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
