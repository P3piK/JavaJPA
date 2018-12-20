/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.piotr.gryt.view;

import java.util.List;
import java.util.Scanner;
import pl.polsl.piotr.gryt.controller.FactoryController;
import pl.polsl.piotr.gryt.controller.RiderController;
import pl.polsl.piotr.gryt.model.Factory;
import pl.polsl.piotr.gryt.model.Rider;

/**
 * Class performing CRUD operations for two tables
 * @author Piotr Gryt
 * @version 1.0
 */
public class MotoView 
{
/**
 * Main method calling entity controllers
 * @param args - array variable not used
 */
    public static void main(String[] args) 
    {
        FactoryController fc = new FactoryController();
        RiderController rc = new RiderController();
        
        while (true)
        {
            String name;
            Integer factoryId;
            Integer riderId;
            List<Rider> riders;
            List<Factory> factories;
            
            printMenu();
            Integer option = getId();
            
            switch(option)
            {
                case 1:
                    printEnterFactoryNameMessage();
                    name = getName();
                    fc.insertFactory(name);
                    printInsertedSuccessfullyMessage();
                    break;
                case 2:
                    fc.getAllFactories().forEach((f) -> System.out.println(f.toString())
                    );
                    break;
                case 3:
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    
                    try
                    {
                        System.out.println(fc.findFactory(factoryId).toString());
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 4:
                    printEnterFactoryNameMessage();
                    name = getName();
                    fc.findFactory(name).forEach((f) -> System.out.println(f.toString()));
                    break;
                case 5:
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    printEnterFactoryNameMessage();
                    name = getName();
                    try
                    {
                        fc.updateFactory(factoryId, name);
                        printUpdatedSuccessfullyMessage();
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 6:
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    try
                    {
                        fc.deleteFactory(factoryId);
                        printDeletedSuccessfullyMessage();
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;   
                case 7:
                    printEnterRiderNameMessage();
                    name = getName();
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    
                    try
                    {
                        rc.insertRider(name, factoryId);
                        printInsertedSuccessfullyMessage();
                    }
                    catch(Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 8:
                    riders = rc.getAllRiders();
                    
                    riders.forEach((r) -> System.out.println(r.toString()));
                    break;
                case 9:
                    printEnterRiderIdMessage();
                    riderId = getId();
                    
                    try
                    {
                        System.out.println(rc.findRider(riderId).toString());
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 10:
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    
                    try
                    {
                        riders = rc.findRider("", factoryId);
                        riders.forEach((r) -> System.out.println(r.toString()));
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 11:
                    printEnterRiderIdMessage();
                    riderId = getId();
                    printEnterRiderNameMessage();
                    name = getName();
                    printEnterFactoryIdMessage();
                    factoryId = getId();
                    
                    try
                    {
                        rc.updateRider(riderId, name, factoryId);
                        printUpdatedSuccessfullyMessage();
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 12:
                    
                    printEnterRiderIdMessage();
                    riderId = getId();
                    
                    try
                    {
                        rc.deleteRider(riderId);
                        printDeletedSuccessfullyMessage();
                    }
                    catch (Exception e)
                    {
                        printErrorMessage(e);
                    }
                    break;
                case 0:
                    return;
            }
            
        }
    }

    /**
     * Message for UI
     */
    private static final String DELETED_SUCCESSFULLY_MESSAGE = "Deleted successfully.";
    /**
     * Message for UI
     */
    private static final String UPDATED_SUCCESSFULLY_MESSAGE = "Updated successfully.";
    /**
     * Message for UI
     */
    private static final String INSERTED_SUCCESSFULLY_MESSAGE = "Inserted successfully.";
    /**
     * Message for UI
     */
    private static final String ENTER_RIDER_ID_MESSAGE = "Enter rider id:";
    /**
     * Message for UI
     */
    private static final String ENTER_FACTORY_ID_MESSAGE = "Enter factory id:";
    /**
     * Message for UI
     */
    private static final String ENTER_RIDER_NAME_MESSAGE = "Enter rider name:";
    /**
     * Message for UI
     */
    private static final String ENTER_FACTORY_NAME_MESSAGE = "Enter factory name:";

    /**
     * Prints "Enter rider id:"
     */
    private static void printEnterRiderIdMessage() {
        System.out.println(ENTER_RIDER_ID_MESSAGE);
    }

    /**
     * Prints "Inserted successfully."
     */
    private static void printInsertedSuccessfullyMessage() {
        System.out.println(INSERTED_SUCCESSFULLY_MESSAGE);
    }

    /**
     * Prints "Updated successfully."
     */
    private static void printUpdatedSuccessfullyMessage() {
        System.out.println(UPDATED_SUCCESSFULLY_MESSAGE);
    }

    /**
     * Prints "Enter rider name:"
     */
    private static void printEnterRiderNameMessage() {
        System.out.println(ENTER_RIDER_NAME_MESSAGE);
    }

    /**
     * Prints "Enter factory id:"
     */
    private static void printEnterFactoryIdMessage() {
        System.out.println(ENTER_FACTORY_ID_MESSAGE);
    }

    /**
     * Prints "Enter factory name:"
     */
    private static void printEnterFactoryNameMessage() {
        System.out.println(ENTER_FACTORY_NAME_MESSAGE);
    }

    /**
     * Prints "Deleted successfully."
     */
    private static void printDeletedSuccessfullyMessage() {
        System.out.println(DELETED_SUCCESSFULLY_MESSAGE);
    }
    
    /**
     * Prints error message
     * @param e - exception to be printed
     */
    private static void printErrorMessage(Exception e) {
        System.out.println("Error occured: " + e.getMessage());
    }

    /**
     * Prints menu options
     */
    private static void printMenu() 
    {
        System.out.println("Menu: \n"
                + "1: Insert Factory \n"
                + "2: Find all Factories \n"
                + "3: Find Factory by id \n"
                + "4: Find Factory by parameters \n"
                + "5: Update Factory \n"
                + "6: Delete Factory \n"
                + "7: Insert Rider \n"
                + "8: Find all Riders \n"
                + "9: Find Rider  by id \n"
                + "10: Find Rider by parameters \n"
                + "11: Update Rider \n"
                + "12: Delete Rider \n"
                + "0: Exit \n");
    }

    /**
     * Gets name from user
     * @return input String
     */
    private static String getName() 
    {
        Scanner reader = new Scanner(System.in);
        String ret = reader.next();
        
        while (ret.isEmpty())
        {
            System.out.println("Please enter name:");
            ret = reader.next();
        }
        
        return ret;
    }

    /**
     * Gets id from user
     * @return input Integer
     */
    private static Integer getId() 
    {
        Scanner reader = new Scanner(System.in);
        String input = reader.next();
        
        while (!tryParseInt(input) || Integer.parseInt(input) < 0)
        {
            System.out.println("Please enter correct id:");
            input = reader.next();
        }
        
        Integer ret = Integer.parseInt(input);

        return ret;
    }
    
    /**
     * Methods ensuring if correct number was input
     * @param value - value to be checked if it is integer
     * @return true if input correct integer; false if input String
     */
    private static boolean tryParseInt(String value) 
    {  
        try 
        {  
            Integer.parseInt(value);  
            return true;  
        } 
        catch (NumberFormatException e) 
        {  
            return false;  
        }  
    }   
    
}
