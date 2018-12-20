/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.piotr.gryt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Factory entity class
 * @author Piotr Gryt
 * @version 1.0
 */
@Entity
@NamedQuery(name = "getAllFactories", query = "SELECT c FROM Factory c")
public class Factory implements Serializable 
{
    /**
     * Id of Factory, primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Name of the Factory
     */
    @Column(name = "name")
    private String name;

    /**
     * Empty constructor
     */
    public Factory() {
    }

    /**
     * Constructor setting name
     * @param name - name of Factory 
     */
    public Factory(String name) {
        this.name = name;
    }
    
    /**
     * Overrides toString method to print result
     * @return String like "Id: %id, Name: %name"
     */
    @Override
    public String toString() {
        return String.format("Factory: Id: %d, Name: %s", id, name);
    }

    /**
     * Id getter
     * @return Integer id variable
     */
    public int getId() {
        return id;
    }

    /**
     * Id setter
     * @param id - to set the id field 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Name getter
     * @return String name variable
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name - name to be set 
     */
    public void setName(String name) {
        this.name = name;
    }
}
