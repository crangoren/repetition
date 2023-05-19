package localChat;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService{

    private class Entry {
        private String nick;
        private String login;
        private String password;

        public Entry(String nick, String login, String password) {
            this.nick = nick;
            this.login = login;
            this.password = password;
        }
    }

    private List<Entry> entries;

//    public BaseAuthService() {
//        entries = new ArrayList<>();
//        entries.add (new Entry("nick1", "login1", "password1"));
//        entries.add (new Entry("nick2", "login2", "password2"));
//        entries.add (new Entry("nick3", "login3", "password3"));
//    }
    public BaseAuthService() {
        entries = List.of(new Entry("nick1", "login1", "password1"),
                new Entry("nick2", "login2", "password2"),
                new Entry("nick3", "login3", "password3"));
    }

    @Override
    public void start() {
        System.out.println(this.getClass().getName() + "Server Started");
    }

    @Override
    public void stop() {
        System.out.println(this.getClass().getName() + "Server Stopped");
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
//        return  entries.stream().
//                filter(entry -> entry.login.equals(login) && entry.password.equals(password))
//                .map(entry -> entry.nick)
//                .findFirst();
        for (Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(password)) {
                return entry.nick;
            }
        }
        return null;
    }
}
