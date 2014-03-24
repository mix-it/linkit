package models.planning;

public enum Room {

    S1("Gosling"),
    S2("Eich"),
    S3("Nonaka"),
    S4("Dijkstra"),
    S5("Turing"),
    S6("Lovelace"),
    Amphi1("Grand Amphi"),
    Amphi2("Petit Amphi"),
    Mezzanine("Mezzanine");

    private String name;

    private Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
