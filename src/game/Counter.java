package game;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.Counter class
 * game.Counter count the objects in the game.
 */
public class Counter {
    private int value;

    /**
     * Constructor.
     * game.Counter count the objects in the game.
     *
     * @param number to add to counter.
     */
    public Counter(int number) {
        this.value = number;
    }

    /**
     * increase add number to current count.
     *
     * @param number to add to counter.
     */
    public void increase(int number) {

        this.value = value + number;
    }

    /**
     * decrease subtract number from current count.
     *
     * @param number to subtract from counter.
     */
    public void decrease(int number) {
        this.value = value - number;
    }

    /**
     * getValue return counter.
     *
     * @return counter value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * setValue set new val.
     *
     * @param value1 new val .
     */
    public void setValue(int value1) {
        this.value = value1;
    }
}