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
import pl.polsl.piotr.gryt.model.Rider;

/**
 * Controller for Rider entity
 * @author Piotr Gryt
 * @version 1.0
 */
public class RiderController 
{
    /**
     * EntityManagerFactory for RiderController
     */
    private EntityManagerFactory emf;
    /**
     * EntityManager for RiderController
     */
    private EntityManager em;
    
    /**
     * Main constructor initializing EntityManager and EntityManagerFactory variables
     */
    public RiderController() {
        this.emf = Persistence.createEntityManagerFactory("ReportJpaPU");
        this.em = emf.createEntityManager();
    }

    /**
     * Inserts new record to Rider table
     * @param name - name of the new Rider
     * @param factoryId - id of factory to be assigned to
     * @throws Exception if factory with given id was not found
     */
    public void insertRider(String name, Integer factoryId) throws Exception
    {
        if (!Helper.doesRecordExist(Factory.class, factoryId))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        Factory factory = em.find(Factory.class, factoryId);
        Rider rider = new Rider(name, factory);

        em.getTransaction().begin();
        em.persist(rider);
        em.getTransaction().commit();
    }

    /**
     * Finds riders with given names and factories
     * @param name - name to be found in database
     * @param factoryId - id of factory for which riders should be found
     * @return list of Riders matching provided parameters
     * @throws Exception if factory with given id was not found
     */
    public List<Rider> findRider(String name, Integer factoryId) throws Exception
    {
        if (!Helper.doesRecordExist(Factory.class, factoryId))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        
        Factory factory = em.find(Factory.class, factoryId);
        return em.createQuery(createCriteriaQuery(name, factory)).getResultList();
    }

    /**
     * Finds a Rider by id
     * @param id - id of Rider to be found
     * @return Rider object with given id
     * @throws Exception if no Rider with given id was found
     */
    public Rider findRider(Integer id) throws Exception
    {
        if (!Helper.doesRecordExist(Rider.class, id))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        
        return em.find(Rider.class, id);
    }

    /**
     * Gets all records from Rider table
     * @return list of all records from Rider table
     */
    public List<Rider> getAllRiders()
    {
        return em.createNamedQuery("getAllRiders").getResultList();
    }

    /**
     * Updates name of record with given id 
     * @param riderId - id of Rider whose name should be updated
     * @param name - new name to be set
     */
    public void updateRider(Integer riderId, String name)
    {
        Rider rider = em.find(Rider.class, riderId);
        rider.setName(name);
        
        em.getTransaction().begin();
        em.persist(rider);
        em.getTransaction().commit();
    }
    
    /**
     * Updates name and factory of record with given id
     * @param riderId - Rider to be updated
     * @param name - new name to be set
     * @param factoryId - new id of factory to be set
     * @throws Exception if rider or factory with given id was not found
     */
    public void updateRider(Integer riderId, String name, Integer factoryId) throws Exception
    {
        if (!Helper.doesRecordExist(Rider.class, riderId)
                || !Helper.doesRecordExist(Factory.class, factoryId))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE);
        }
        
        Factory factory = em.find(Factory.class, factoryId);
        Rider rider = em.find(Rider.class, riderId);
        rider.setName(name);
        rider.setFactory(factory);
        
        em.getTransaction().begin();
        em.persist(rider);
        em.getTransaction().commit();
    }

    /**
     * Deletes record with given id
     * @param id - id of record to be deleted
     * @throws Exception if record with given id was not found
     */
    public void deleteRider(Integer id) throws Exception
    {
        if (!Helper.doesRecordExist(Rider.class, id))
        {
            throw new Exception(Helper.RECORD_NOT_FOUND_MESSAGE); 
        }

        em.getTransaction().begin();
        em.remove(em.find(Rider.class, id));
        em.getTransaction().commit();
    }
    
    /**
     * Creates a combined query for Rider entity
     * @param name - records with name like to be found
     * @param factory - record with factory to be found
     * @return 
     */
    private CriteriaQuery<Rider> createCriteriaQuery(String name, Factory factory)
    {
        Expression expr;
        Root<Rider> root;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Rider> definition = builder.createQuery(Rider.class);
        List<Predicate> predicates = new ArrayList<>();
        
        root = definition.from(Rider.class);
        definition.select(root);
        
        if (name != null && !name.isEmpty())
        {
            expr = root.get("name");
            predicates.add(builder.like(expr, name));
        }
        
        if (factory != null)
        {
            expr = root.get("factory");
            predicates.add(builder.equal(expr, factory));
        }
        
        if (!predicates.isEmpty())
        {
            definition.where(builder
                    .or(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        return definition;
    }
}
