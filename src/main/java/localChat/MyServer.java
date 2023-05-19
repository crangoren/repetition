package localChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyServer {
    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer() {
        try (ServerSocket serverSocket = new ServerSocket(ChatConstants.PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Server waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client is online");
                new ClientHandler(this, socket);

            }

        }catch ( IOException ex) {
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.stream().anyMatch(clientHandler -> clientHandler.getName().equals(nick));
//        for(ClientHandler client : clients) {
//            if (client.getName().equals(nick)) {
//                return true;
//            }
//        }
//        return false;
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClients();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClients();
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(c -> c.sendMsg(message));
//        for (ClientHandler client : clients) {
//            client.sendMsg(message);
//        }
    }


    public synchronized void privateMessage (String message, List<String> nicknames) {
        clients.stream().filter(clientHandler -> nicknames.contains(clientHandler.getName()))
                .forEach(clientHandler -> clientHandler.sendMsg(message));

//        for (ClientHandler client : clients) {
//            if(!nicknames.contains(client.getName())) {
//                continue;
//            }
//            client.sendMsg(message);
//        }
    }

    public synchronized void broadcastClients() {
        String clientsMessage = ChatConstants.CLIENTS_LIST +
                " " +
                clients.stream()
                        .map(ClientHandler::getName)
                        .collect(Collectors.joining(" "));
        clients.forEach(c -> c.sendMsg(clientsMessage));
    }
}
