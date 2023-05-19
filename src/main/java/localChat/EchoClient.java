package localChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient  extends JFrame {

    private Socket socket;
    private  JTextArea chatArea;
    private  JTextField inputField;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initGUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(EchoConstants.HOST, EchoConstants.PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.AUTH_OK)) {
                        break;
                    }
                    chatArea.append(strFromServer);
                    chatArea.append("\n");
                }


                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(EchoConstants.STOP_WORD)) {
                        break;
                    } else if (strFromServer.startsWith(ChatConstants.CLIENTS_LIST)) {
                        chatArea.append("Now online " + strFromServer);
                    } else {
                        chatArea.append(strFromServer);
                    }
                    chatArea.append("\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public void initGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel jPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();

        jPanel.add(inputField, BorderLayout.CENTER);
        JButton sendButton = new JButton("Send");

        jPanel.add(sendButton, BorderLayout.EAST);


        add(jPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try{
                    outputStream.writeUTF(EchoConstants.STOP_WORD);
                    closeConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public void sendMessage() {
        if (!inputField.getText().trim().isEmpty()) {
            try {
                outputStream.writeUTF(inputField.getText());
                inputField.setText("");
                inputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Send error occur");
            }
        }
    }

    public void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new);
    }
}
