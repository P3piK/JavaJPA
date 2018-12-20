/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.piotr.gryt.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Helper class stores some functional method and variable
 * for further use in Controllers
 * @author Piotr Gryt
 * @version 1.0
 */
public class Helper 
{
    /**
     * Error message when record was not found in database
     */
    public static final String RECORD_NOT_FOUND_MESSAGE = "Record not found in DB.";
    
    /**
     * EntityManagerFactory for Helper class
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReportJpaPU");
    /**
     * EntityManager for Helper class
     */
    private static EntityManager em = emf.createEntityManager();
    
    /**
     * Method checks if record exists in database
     * @param entityClass - in which table should it look for
     * @param id - primary key, id to find in database
     * @return true if record was found in DB; false if not found
     */
    public static Boolean doesRecordExist(Class entityClass, Integer id)
    {
        Boolean ret = true;
        Object obj = em.find(entityClass, id);
        
        if (obj == null)
        {
            ret = false;
        }
        
        return ret;
    }
    
}
