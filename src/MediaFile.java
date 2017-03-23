public abstract class MediaFile {
    public final String  TYPE;
    public final String  PATH;
    public final String  NAME;
    public final Integer YEAR;

    MediaFile(String name, String path, Integer year, String type) {
        this.TYPE = new String(type);
        this.PATH = new String(path);
        this.NAME = new String(name);
        this.YEAR = new Integer(year);
    }

}