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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Rider entity class
 * @author Piotr Gryt
 * @version 1.0
 */
@Entity
@NamedQuery(name = "getAllRiders", query = "SELECT c FROM Rider c")
public class Rider implements Serializable 
{
    /**
     * Id field, primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /**
     * Name of the rider
     */
    @Column(name = "name")
    private String name;
    
    /**
     * Factory to which Rider is attached, foreign key
     */
    @JoinColumn(name = "factory")
    @ManyToOne
    private Factory factory;

    /**
     * Empty constructor
     */
    public Rider() {
    }
    
    /**
     * Constructor setting name and factory
     * @param name - name to be set
     * @param factory - factory object to be set
     */
    public Rider(String name, Factory factory) {
        this.name = name;
        this.factory = factory;
    }
    
    /**
     * Converts record into more readable form
     * @return String with format like "Id: %id, Name: %name, Factory: %factory"
     */
    @Override
    public String toString() {
        return String.format("Rider: Id: %d, Name: %s, Factory: %s", id, name, factory.getName());
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
     * @param id - id to be set 
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
     * Name setter
     * @param name - name to be set 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Factory getter
     * @return Factory factory entity
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * Factory setter
     * @param factory - factory to be set
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
