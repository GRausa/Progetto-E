/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * Represents an insurance. Insurances are supplements of the ticket.
 *
 * @author cl418377
 */
public class Insurance { //ASSICURAZIONE

    private String code;
    private String name;
    private double price;
    private String description;

    /**
     * Contructs a new insurance.
     *
     * @param code Code of the insurance.
     * @param name Name of the insurance.
     * @param price Price of the insurance.
     */
    public Insurance(String code, String name, double price, String description) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Contructs a new insurance with only the code.
     *
     * @param code Code of the insurance.
     */
    public Insurance(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return code + " " + name + " " + price + " €";
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
