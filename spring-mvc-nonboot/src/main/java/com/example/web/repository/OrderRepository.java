package com.example.web.repository;

import com.example.web.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public OrderRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Order> getAllOrders() { //READ
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Order> orders =  entityManager
                    .createQuery("from Order", Order.class)
                    .getResultList();

            entityManager.getTransaction().commit();
            return orders;
        }
        catch (Exception ex) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Optional<Order> getOrderById(Integer id) {  //READ
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Order order = entityManager.find(Order.class, id);

            entityManager.getTransaction().commit();
            return Optional.ofNullable(order);
        }
        catch (Exception ex) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void saveOrder(Order order) { //CREATE
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(order);

            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void updateOrder(Order order, Integer id) { //UPDATE
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Order updatableOrder = entityManager.find(Order.class, id);
            if(updatableOrder != null){
                updatableOrder.setProduct(order.getProduct());
                updatableOrder.setPrice(order.getPrice());
                updatableOrder.setAmount(order.getAmount());
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void deleteOrder(Integer id) { //DELETE
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            //delete from orders where id = :id;
            Order deletedOrder = entityManager.find(Order.class, id);
            if(deletedOrder != null){
                entityManager.remove(deletedOrder);
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
