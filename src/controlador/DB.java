/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import java.util.Set;
import modelo.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author Josue
 */
public class DB {
    
    public static boolean agregar(Object o){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.save(o);
        tx.commit();
        
        sesion.close();
        return true;
        
    }
    public static boolean agregarOActualizar(Object o){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.saveOrUpdate(o);
        tx.commit();
        
        sesion.close();
        return true;
        
    }
    
    public static boolean borrar(Object o){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(o);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public static boolean update(Object o){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.update(o);
        tx.commit();
        sesion.close();
        
        return true;
    }
    
    public static Object get(Class clase,int key){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        
        Object objeto=sesion.get(clase,key);
        tx.commit();
        sesion.close();
        
        return objeto;
    }
    
    public static List getAll(Class clase){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session sesion = factory.openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query query=sesion.createQuery("select t from "+ clase.getSimpleName()+ " t ");
        List lista=query.list();
        tx.commit();
        sesion.close();
        
        return lista;
    }
    
}
