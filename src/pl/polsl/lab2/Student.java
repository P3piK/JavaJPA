/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab2;

import java.io.Serializable;

/**
 *
 * @author P3p!K-MSI
 */

public class Student  implements Serializable {
    
    private String name;
    private double avarage;

    public Student() {
    }

    @Override
    public String toString() {
        return name + ": " + avarage; //To change body of generated methods, choose Tools | Templates.
    }

    public Student(String name, double avarage) {
        this.name = name;
        this.avarage = avarage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvarage() {
        return avarage;
    }

    public void setAvarage(double avarage) {
        this.avarage = avarage;
    }
}
