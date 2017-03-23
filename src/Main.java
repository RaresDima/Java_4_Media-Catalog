import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Catalog populate() {
        Catalog instance = new Catalog();

        instance.add (new Track("The Foundation", "d:\\tracks\\asimov\\foundation.mp3",            1951));
        instance.add (new Track("The Writer",     "d:\\tracks\\Breakdown\\TheWriter.mp3",          2001));
        instance.add (new Track("Machine",        "d:\\tracks\\Osiris\\Machine.mp3",               2009));

        instance.add (new Movie("Blade Runner",   "d:\\movies\\blade_runner.mp4",                  1982));
        instance.add (new Movie("Titanic",        "d:\\movies\\titanic.mp4",                       1952));
        instance.add (new Movie("Harry Potter",   "d:\\movies\\harryPotter.mp4",                   2002));

        return instance;
    }
    public static void start(Catalog instance) {
        Scanner user = new Scanner(System.in);
        String commandBuffer;
        String[] parsedCommand;
        Boolean exit = false;

        while (!exit) {
            System.out.print("command> ");
            commandBuffer = user.nextLine();
            parsedCommand = commandBuffer.split(" ?/ ?");

            try {
                switch (parsedCommand[0]) {
                    case "play":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        instance.play(parsedCommand[1]);
                        break;
                    case "add":
                        if (parsedCommand.length < 5)
                            throw new IllegalCommandException();
                        if (parsedCommand[1].startsWith("Track")) {
                            instance.add(new Track(parsedCommand[2], parsedCommand[4], Integer.parseInt(parsedCommand[3])));
                            break;
                        }
                        if (parsedCommand[1].startsWith("Movie")) {
                            instance.add(new Movie(parsedCommand[2], parsedCommand[4], Integer.parseInt(parsedCommand[3])));
                            break;
                        }
                        throw new IllegalCommandException();
                    case "list":
                        instance.list();
                        break;
                    case "save":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        instance.save(parsedCommand[1]);
                        break;
                    case "load":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        instance.load(parsedCommand[1]);
                        break;
                    case "help":
                        System.out.println("The available commands are:");
                        System.out.println("play / <name>");
                        System.out.println("add / <type> / <name> / <launch_year> / <path>");
                        System.out.println("list");
                        System.out.println("save / <file/path>");
                        System.out.println("load / <file/path>");
                        System.out.println("help");
                        System.out.println("quit");
                        break;
                    case "quit":
                        exit = true;
                        break;
                    default:
                        throw new IllegalCommandException();
                }
            } catch (IllegalCommandException e) {
                ExceptionHandler.illegalCommand();
                e.printStackTrace();

            }
        }
    }
    
    public static void main(String[] args) {
        Catalog instance = new Catalog();
        start(instance);
    }
}
