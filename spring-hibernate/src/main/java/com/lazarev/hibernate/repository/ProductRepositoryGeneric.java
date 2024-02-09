package com.lazarev.hibernate.repository;

import com.lazarev.hibernate.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class ProductRepositoryGeneric {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryGeneric(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //select * from products;
    public List<Product> findAllProducts(){
        Function<Session, List<Product>> getAllProducts = (session) -> {
            return session.createQuery("from Product", Product.class)
                    .getResultList();
        };

        return executeInTransaction(getAllProducts);
    }

    public void saveProduct(Product product){
        Consumer<Session> saveProduct = (session) -> {
            session.persist(product);
        };
        executeInTransaction(saveProduct);
    }

    public void updateProduct(Long id, Product product){
        Consumer<Session> updateProduct = (session) -> {
            Product retrievedProduct = session.get(Product.class, id);
            if(retrievedProduct == null){
                throw new RuntimeException("Product is not found");
            }

            retrievedProduct.setPrice(product.getPrice());
            retrievedProduct.setName(product.getName());
            retrievedProduct.setExpirationDate(product.getExpirationDate());
        };

        executeInTransaction(updateProduct);
    }

    public void deleteProduct(Long id){
        Consumer<Session> deleteProduct = (session) -> {
            int rowsAffected = session.createMutationQuery("delete from Product where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        };

        executeInTransaction(deleteProduct);
    }

    private void executeInTransaction(Consumer<Session> consumer){
        Function<Session, Void> func = (session -> {
            consumer.accept(session);
            return null;
        });
        executeInTransaction(func);
    }

    private <T> T executeInTransaction(Function<Session, T> func){
        Session session = null;
        try{
            session = sessionFactory.openSession();

            session.getTransaction().begin();

            T result = func.apply(session);

            session.getTransaction().commit();

            return result;
        }
        catch (Exception ex){
            if(session != null){
                session.getTransaction().rollback(); // 0/4 - success
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
