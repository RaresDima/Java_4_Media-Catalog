import java.io.*;
import java.util.Vector;

public class Catalog {
    private Vector<MediaFile> contents;

    Catalog() {
        contents = new Vector<>();
    }

    private void validateName(String name)  throws IllegalNameException     {
        for (MediaFile m : contents)
            if (m.NAME.equals(name))
                throw new IllegalNameException();
    }
    private void validateYear(Integer year) throws IllegalYearException     {
        if (year < 0)
            throw new IllegalArgumentException();
    }
    private void validatePath(String path)  throws IllegalPathException {
        if (!path.matches("d:\\\\([\\w ]+\\\\)*[\\w ]+\\.mp[34]"))
            throw new IllegalPathException();

    }

    public void add(MediaFile f)  {
        try {
            validateName(f.NAME);
            validateYear(f.YEAR);
            validatePath(f.PATH);
            contents.addElement(f);
        }
        catch (IllegalNameException e) { ExceptionHandler.illegalName(); e.printStackTrace(); }
        catch (IllegalYearException e) { ExceptionHandler.illegalYear(); e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }
    public void list()            {
        for (MediaFile f : contents) {
            System.out.println(f.toString());
        }
    }
    public void play(String name) {
        try {
            int i = -1;
            for (MediaFile f : this.contents) // Look for a file with the associated name
                if (f.NAME.equals(name))
                    i = this.contents.indexOf(f);
            if (i == -1)
                throw new NoSuchMediaException(); // Not found exception
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + this.contents.elementAt(i).PATH); }
        catch (IOException e)          { ExceptionHandler.IO(); e.printStackTrace(); }
        catch (NoSuchMediaException e) { ExceptionHandler.noSuchMedia(); e.printStackTrace(); }
    }
    public void save(String fileName) {
        try {
            BufferedWriter fd = new BufferedWriter(new FileWriter(new File(fileName)));

            fd.write("startFile\n");
            for (MediaFile media : this.contents) {
                fd.write("startObject\n");
                fd.write(media.TYPE + "\n");
                fd.write(media.NAME + "\n");
                fd.write(media.YEAR + "\n");
                fd.write(media.PATH + "\n");
            }
            fd.write("endFile");
            fd.close();
        }
        catch (IOException e)          { ExceptionHandler.IO();          e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }
    public void load(String path) {
        try {
            BufferedReader fd = new BufferedReader(new FileReader(new File(path)));
            String tokenBuff, nameBuff, pathBuff;
            Integer yearBuff;

            this.contents.clear();

            tokenBuff = fd.readLine();
            System.out.println("FOUND: " + tokenBuff);
            if (!tokenBuff.startsWith("startFile")) // Does the file have a valid start token?
                throw new BadFileException();



            while (true) {


                tokenBuff = fd.readLine();

                System.out.println("FOUND: " + tokenBuff);

                if (!tokenBuff.startsWith("startObject") && !tokenBuff.startsWith("endFile")) // The next token must either mark an object or a valid end of file
                    throw new BadFileException();

                if (tokenBuff.startsWith("endFile")) // End of file token is found, stop trying to read from the file
                    break;

                tokenBuff = fd.readLine();
                if (!tokenBuff.startsWith("Movie") && !tokenBuff.startsWith("Track")) //An invalid object type was found
                    throw new BadFileException();

                // Being here means a valid object representation is found

                // Read name, year and path of object
                nameBuff = fd.readLine();
                yearBuff = Integer.parseInt(fd.readLine());
                pathBuff = fd.readLine();

                // Validate dread data
                validateName(nameBuff);
                validateYear(yearBuff);
                validatePath(pathBuff);

                // Add to the current catalog
                if (tokenBuff.startsWith("Movie"))
                    contents.addElement(new Movie(nameBuff, pathBuff ,yearBuff));
                if (tokenBuff.startsWith("Track"))
                    contents.addElement(new Track(nameBuff, pathBuff ,yearBuff));
            }
            fd.close();
        }
        catch (IOException e)          { ExceptionHandler.IO();          e.printStackTrace(); }
        catch (BadFileException e)     { ExceptionHandler.badFile();     e.printStackTrace(); }
        catch (IllegalNameException e) { ExceptionHandler.illegalName(); e.printStackTrace(); }
        catch (IllegalYearException e) { ExceptionHandler.illegalYear(); e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }

}
