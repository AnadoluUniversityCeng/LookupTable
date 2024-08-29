package edu.eskisehir;

import com.github.rvesse.airline.SingleCommand;

/**
 * Hello world!
 */
public class EOQ {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SingleCommand<CommandLine> parser = SingleCommand.singleCommand(CommandLine.class);
        CommandLine cmd = parser.parse(args);
        System.out.println(cmd.toString());
    }
}
