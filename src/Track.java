public class Track extends MediaFile {

    Track(String name, String path, Integer year) {
        super(name, path, year, "Track");
    }

    public String toString() {
        StringBuilder buff = new StringBuilder("");

        buff.append("\nTYPE: ");
        buff.append(this.TYPE);
        buff.append("\nNAME: ");
        buff.append(this.NAME);
        buff.append("\nLAUNCH YEAR: ");
        buff.append(this.YEAR);
        buff.append("\nPATH: ");
        buff.append(this.PATH);
        buff.append("\n");

        return new String(buff);
    }
}
