/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab2;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author P3p!K-MSI
 */
@Remote
public interface StudentBean2Remote {

    Student findBest(List<Student> students);
    
}
