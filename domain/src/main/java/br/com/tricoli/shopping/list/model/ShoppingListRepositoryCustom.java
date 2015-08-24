package br.com.tricoli.shopping.list.model;

import java.util.List;

/**
 *
 * A custom {@link br.com.tricoli.shopping.list.model.ShoppingList} repository.
 * It is based on (@code MongoRepository) of Spring Data and has method that are not automatically
 * generated for the framework.
 *
 * @see <a href="http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/">Spring Data MongoDB</a>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public interface ShoppingListRepositoryCustom {

    public List<String> getListNames();

}
