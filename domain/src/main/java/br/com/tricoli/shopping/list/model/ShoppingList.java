package br.com.tricoli.shopping.list.model;

import br.com.tricoli.shopping.list.model.value.NameIdentifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection="ShoppingList")
public class ShoppingList implements Serializable {

    public static final int NOT_FOOUND = -1;
    @Id
    private String name;

    private List<Item> itens;

    private ShoppingList() {
        itens = new LinkedList<Item>();
    }

    public ShoppingList(NameIdentifier name) {
        this();
        this.name = name.asPlainText();
    }

    public NameIdentifier getName() {
        return NameIdentifier.of(name);
    }

    public boolean add(Item item){
        int index = itens.indexOf(item);
        if(index == NOT_FOOUND){
            return itens.add(item);
        }
        Item localItem = itens.remove(index);
        return itens.add(localItem.sum(item));
    }

    public void addAll(ShoppingList list) {
        for (Item item : list.itens) {
            this.add(item);
        }

    }
    
    public int size() {
        return itens.size();
    }

    public boolean isEmpty() {
        return itens.isEmpty();
    }
    
    public boolean remove(Item item) {
        return itens.remove(item);
    }

    public void removeAll(ShoppingList list) {
        for (Item item : list.itens) {
            itens.remove(item);
        }

    }

    public void buy(Item item) {
        int index = itens.indexOf(item);
        if(index != NOT_FOOUND){
            Item localItem = itens.remove(index);
            itens.add(localItem.purchased());
        }
    }

    public void buyAll(ShoppingList list) {
        for (Item item : list.itens) {
            this.buy(item);
        }
    }

    public void clear() {
        itens.clear();
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                ", itens=" + itens +
                '}';
    }
}
