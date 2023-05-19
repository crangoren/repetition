package localChat;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentification();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();

        }catch (IOException ex) {
            System.out.println("Creating client problem");
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageFromClient = dataInputStream.readUTF();
            System.out.println("from " + name + ": " + messageFromClient);
            if(messageFromClient.equals(ChatConstants.STOP_WORD)) {
                return;
            }else if (messageFromClient.startsWith(ChatConstants.PRIVATE_MESSAGE)){
                String[] prMsg = messageFromClient.split("\\s+");
                List<String> nickList = new ArrayList<>();
                for (int i = 1; i < prMsg.length -1; i++) {
                    nickList.add(prMsg[i]);
                }
            } else if (messageFromClient.startsWith(ChatConstants.CLIENTS_LIST)){
                server.broadcastClients();
            } else {
                server.broadcastMessage(name + ": " + messageFromClient);
            }

        }
    }

    private void authentification() throws IOException {
        while (true) {
            String message = dataInputStream.readUTF();
            if (message.startsWith(ChatConstants.AUTH_COMMAND)) {
                String[] parts = message.split("\\s+");
                String nick = server.getAuthService().getNickByLoginAndPassword(parts[1], parts[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMsg(ChatConstants.AUTH_OK + " " + nick);
                        name = nick;
                        server.subscribe(this);
                        server.broadcastMessage(name + " is online");
                        return;
                    } else {
                        sendMsg("Nick is already in use");
                    }
                } else {
                    sendMsg("An error in login/password");
                }
            }
        }
    }

    public void sendMsg(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " is offline");
        try {
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
