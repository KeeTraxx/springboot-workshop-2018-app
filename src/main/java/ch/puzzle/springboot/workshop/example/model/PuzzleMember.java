package ch.puzzle.springboot.workshop.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PuzzleMember {
    @Id
    @GeneratedValue
    long id;

    private String firstName;
    private String lastName;

    private int coffeeConsumption;

    public PuzzleMember() {
    }

    public PuzzleMember(String firstName, String lastName, int initialCoffeeConsumption) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.coffeeConsumption = initialCoffeeConsumption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCoffeeConsumption() {
        return coffeeConsumption;
    }

    public void setCoffeeConsumption(int coffeeConsumption) {
        this.coffeeConsumption = coffeeConsumption;
    }
}