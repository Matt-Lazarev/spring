package com.lazarev.hibernate.repository;

import com.lazarev.hibernate.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ProductRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAllProducts(){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            List<Product> products = session
                    .createQuery("from Product", Product.class)
                    .getResultList();

            session.getTransaction().commit();
            return products;
        }
        catch (Exception ex){
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Optional<Product> getProductById(Long id){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Product product = session.get(Product.class, id);

            session.getTransaction().commit();
            return Optional.ofNullable(product);
        }
        catch (Exception ex){
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void saveProduct(Product product){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            session.persist(product);

            session.getTransaction().commit();
        }
        catch (Exception ex){
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void updateProduct(Product product, Long id){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Product updatableProduct = session.get(Product.class, 1L);
            if(updatableProduct == null){
                throw new NoSuchElementException("No such product in table");
            }
            updatableProduct.setName(product.getName());
            updatableProduct.setPrice(product.getPrice());
            updatableProduct.setExpirationDate(product.getExpirationDate());
            session.persist(updatableProduct);

            session.getTransaction().commit();
        }
        catch (Exception ex){
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void deleteProduct(Long id){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Product deletableProduct = session.get(Product.class, id);
            if(deletableProduct == null){
                throw new NoSuchElementException("No such product in table");
            }
            session.remove(deletableProduct);

            session.getTransaction().commit();
        }
        catch (Exception ex){
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
}
