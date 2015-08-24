package br.com.tricoli.shopping.list.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * A {@link br.com.tricoli.shopping.list.model.ShoppingList} repository.
 * It is based on (@code MongoRepository) of Spring Data.
 *
 * @see <a href="http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/">Spring Data MongoDB</a>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@Repository
public interface ShoppingListRepository extends MongoRepository<ShoppingList, String>, ShoppingListRepositoryCustom {

    /**
     * Find a {@link br.com.tricoli.shopping.list.model.ShoppingList} by its name;
     */
    public ShoppingList findByName(String name);

}
