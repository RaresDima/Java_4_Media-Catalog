public class ExceptionHandler {
    ExceptionHandler() {}

    public static void illegalName() {
        System.out.println(" ------------------------------------------------------------------ ");
        System.out.println("| ERROR: Invalid name. There may already be a file with that name. |");
        System.out.println(" ------------------------------------------------------------------ ");
    }
    public static void illegalPath() {
        System.out.println(" ---------------------------------------------------------------------------------------- ");
        System.out.println("| ERROR: Invalid path. The path must be of the type \"d:\\<folder_name(s)>\\...\\file_name\". |");
        System.out.println(" ---------------------------------------------------------------------------------------- ");
    }
    public static void illegalYear() {
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println("| ERROR: Invalid year. The year must be a positive number. |");
        System.out.println(" ---------------------------------------------------------- ");
    }
    public static void IO() {
        System.out.println(" ---------------------------------------------------------------- ");
        System.out.println("| ERROR: Failed to open file. Possible bad file or invalid path. |");
        System.out.println(" ---------------------------------------------------------------- ");
    }
    public static void badFile() {
        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| ERROR: Bad file content. File either corrupted not a valid catalog file. |");
        System.out.println(" -------------------------------------------------------------------------- ");
    }
    public static void noSuchMedia() {
        System.out.println(" -------------------------------------------------------------- ");
        System.out.println("| ERROR: No media file with the specified name in the catalog. |");
        System.out.println(" -------------------------------------------------------------- ");
    }
    public static void illegalCommand() {
        System.out.println(" ----------------------------------------------------- ");
        System.out.println("| ERROR: Invalid command. The available commands are: |");
        System.out.println("| play / <name>                                       |");
        System.out.println("| add / <type> / <name> / <launch_year> / <path>      |");
        System.out.println("| list                                                |");
        System.out.println("| save / <file/path>                                  |");
        System.out.println("| load / <file/path>                                  |");
        System.out.println("| help                                                |");
        System.out.println("| quit                                                |");
        System.out.println(" ----------------------------------------------------- ");
    }
}
