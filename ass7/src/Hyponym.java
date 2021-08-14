/**
 * @author Ori Angel
 * ID: 314617739
 * Hyponym class
 * Hyponym contain the data fo hyponym for the map.
 */
public class Hyponym {

    private String name;
    private int number;

    /**
     * Constructor.
     * build hyponym.
     *
     * @param name   of the hyponym.
     * @param number of the time that show.
     */
    public Hyponym(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * getNumber.
     *
     * @return number of the time that show.
     */
    public int getNumber() {
        return number;
    }

    /**
     * addCounter.
     * add one to the counter.
     */
    public void addCounter() {
        this.number++;
    }

    /**
     * getName.
     *
     * @return the name of the hyponym.
     */
    public String getName() {
        return name;
    }
}
