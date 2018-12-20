/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.piotr.gryt.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.piotr.gryt.model.Factory;

/**
 * Controller for Factory entity
 * @author Piotr Gryt
 * @version 1.0
 */
public class FactoryController 
{
    /**
     * EntityManagerFactory for FactoryController
     */
    private EntityManagerFactory emf;
    /**
     * EntityManager for FactoryController
     */
    private EntityManager em;

    /**
     * Main constructor that initializes EntityManager 
     * and EntityManagerFactory variables.
     */
    public FactoryController() {
        this.emf = Persistence.createEntityManagerFactory("ReportJpaPU");
        this.em = emf.createEntityManager();
    }
    
    /**
     * Inserts new object to DB
     * @param factoryName - name of factory to be inserted
     */
    public void insertFactory(String factoryName)
    {
        Factory factory = new Factory(factoryName);
        
        em.getTransaction().begin();
        em.persist(factory);
        em.getTransaction().commit();
    }

    /**
     * Finds factories with names like given
     * @param name - a name to be sought
     * @return list of factories mathing given name
     */
    public List<Factory> findFactory(String name)
    {
        return em.createQuery(createCriteriaQuery(name)).getResultList();
    }
    
    /**
     * Finds factory with given id
     * @param id - id to be found
     * @return Factory entity with given id
     * @throws Exception if id was not found in database
     */
    public Factory findFactory(Integer id) throws Exception
    {
        if (!Helper.doesRecordExist(Factory.class, id))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
                
        return em.find(Factory.class, id);
    }
    
    /**
     * Gets all records from Factory table
     * @return complete list of factories from Factory table
     */
    public List<Factory> getAllFactories()
    {
        return em.createNamedQuery("getAllFactories").getResultList();
    }
    
    /**
     * Updates record with given id
     * @param id - record to be updated
     * @param newName - name to be set for the id
     * @throws Exception if id was not found
     */
    public void updateFactory(Integer id, String newName) throws Exception
    {
        if (!Helper.doesRecordExist(Factory.class, id))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        
        Factory factory = em.find(Factory.class, id);
        factory.setName(newName);
        
        em.getTransaction().begin();
        em.persist(factory);
        em.getTransaction().commit();
    }
    
    /**
     * Deletes record with given id
     * @param id - id of record to be deleted
     * @throws Exception if id was not found
     */
    public void deleteFactory(Integer id) throws Exception
    {
        if (!Helper.doesRecordExist(Factory.class, id))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        
        em.getTransaction().begin();
        em.remove(em.find(Factory.class, id));
        em.getTransaction().commit();
    }
    
    /**
     * Composes a query for given parameters
     * @param name - name to be found
     * @return CriteriaQuery for Factory table with composed parameters
     */
    private CriteriaQuery<Factory> createCriteriaQuery(String name)
    {
        Expression expr;
        Root<Factory> root;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Factory> definition = builder.createQuery(Factory.class);
        List<Predicate> predicates = new ArrayList<>();
        
        root = definition.from(Factory.class);
        definition.select(root);
        
        if (name != null)
        {
            expr = root.get("name");
            predicates.add(builder.like(expr, name));
        }
        
        if (!predicates.isEmpty())
        {
            definition.where(builder
                    .or(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        return definition;
    }
}
