package pyramid.models;

public class Artist {
    private int id;
    private String name;
    private int playCount;

    public Artist(int id, String name, int playcount) {
        this.id = id;
        this.name = name;
        this.playCount = playCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
