package br.com.tricoli.list.shopping.api;

import br.com.tricoli.shopping.list.model.Item;
import br.com.tricoli.shopping.list.model.ShoppingList;
import br.com.tricoli.shopping.list.model.ShoppingListRepository;
import br.com.tricoli.shopping.list.model.value.NameIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ShoppingListApi {

    @Inject
    private ShoppingListRepository repository;

    @GET
    @Path("/shopping-lists")
    public String get(){
        return "Works.....";
    }

    @GET
    @Path("/shopping-list/{name}")
    public ShoppingList get(@PathParam("name") String name){
        return repository.findByName(NameIdentifier.of(name).asPlainText());
    }

    @GET
    @Path("/shopping-lists/names")
    public List<String> getNames(){
        return repository.getListNames();
    }

    @POST
    @Path("/shopping-lists")
    public void newList(ShoppingList list){
        repository.save(list);
    }

    @DELETE
    @Path("/shopping-list/{name}")
    public void delete(@PathParam("name") String name){
        repository.delete(new ShoppingList(NameIdentifier.of(name)));
    }

    @POST
    @Path("/shopping-list/{name}/itens")
    public void addItem(ShoppingList list){
        ShoppingList dbList = repository.findByName(list.getName().asPlainText());

        if(dbList == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).
                    entity(String.format("The %s  Shopping List was no found", list)).type("text/plain").build());
        }

        dbList.addAll(list);
        repository.save(dbList);
    }

    @DELETE
    @Path("/shopping-list/{name}/iten/{item}")
    public void removeItem(@PathParam("name") String name,
                           @PathParam("item") String label,
                           @QueryParam("value") BigDecimal value){
        ShoppingList list = new ShoppingList(NameIdentifier.of(name));
        ShoppingList dbList = repository.findByName(list.getName().asPlainText());

        if(dbList == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).
                    entity(String.format("The %s Shopping List was no found", name)).type("text/plain").build());
        }

        dbList.remove(new Item(NameIdentifier.of(label), value));
        repository.save(dbList);
    }

    @PUT
    @Path("/shopping-list/{name}/iten/{item}")
    public void buyItem(ShoppingList list){
        ShoppingList dbList = repository.findByName(list.getName().asPlainText());

        if(dbList == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).
                    entity(String.format("The %s Shopping List was no found", list)).type("text/plain").build());
        }

        dbList.buyAll(list);
        repository.save(dbList);
    }

}
