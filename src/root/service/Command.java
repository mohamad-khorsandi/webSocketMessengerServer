package root.service;

import java.util.Arrays;
import java.util.Optional;

public enum Command {
    CREATE_HOST("create-host"), REGISTER_CLIENT("register"),
    LOGIN_CLIENT("login"), CREATE_WS("create-workspace"),
    CONNECT_WS("connect-workspace");

    Command(String str) {
        this.str = str;
    }
    private String str;

    @Override
    public String toString() {
        return str;
    }

    static public Command type(String str){
        Optional<Command> command = Arrays.stream(Command.values()).filter(cmd -> cmd.str.equals(str)).findFirst();
        return command.orElseThrow();
    }
}
