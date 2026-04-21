package ApartadoUno;

/**
 * Implements the person class
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class Person {
    private final String name;
    private final int age;
    private final int weight;
    private final int height;
    private final boolean male;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A person's constructor
     * @param name   the person's name
     * @param age    the person's age
     * @param weight the person's weight
     * @param height the person's height
     * @param male   whether the person is male (true) or not (false)
     */
    public Person(String name, int age, int weight, int height, boolean male) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.male = male;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Gets whether the person is male or not
     * @return true if they are male, false otherwise
     */
    public boolean isMale() {
        return male;
    }

    /**
     * Gets the person's age
     * @return the person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the person's height
     * @return the person's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the person's name
     * @return the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the person's weight
     * @return the person's weight
     */
    public int getWeight() {
        return weight;
    }
}