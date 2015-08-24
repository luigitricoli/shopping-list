package br.com.tricoli.shopping.list.model;


import org.springframework.data.mongodb.core.MongoOperations;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * The implementation for {@link ShoppingListRepositoryCustom}.
 *
 * @see <a href="http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/">Spring Data MongoDB</a>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ShoppingListRepositoryImpl implements ShoppingListRepositoryCustom{

    @Inject
    MongoOperations mongo;

    /**
     * Return the name of all {@link br.com.tricoli.shopping.list.model.ShoppingList} in the database.
     */
    @Override
    public List<String> getListNames() {
        List<ShoppingList> lists = mongo.findAll(ShoppingList.class);
        List<String> names = new LinkedList<String>();
        for (ShoppingList list : lists) {
            names.add(list.getName().asPlainText());
        }
        return names;
    }
}
