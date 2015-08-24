package br.com.tricoli.shopping.list.model;

import br.com.tricoli.shopping.list.model.value.NameIdentifier;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ShoppingListTest {

    public static final String LIST_NAME = "List A";
    private Item meat;
    private Item charcoal;
    private Item bear;
    private ShoppingList barbecue;

    @Before
    public void init() {
        barbecue = new ShoppingList(mock(NameIdentifier.class));

        bear = mock(Item.class);
        barbecue.add(bear);

        charcoal = mock(Item.class);
        barbecue.add(charcoal);

        meat = new Item(NameIdentifier.of("meat"), BigDecimal.TEN);
        barbecue.add(meat);

    }

    @Test
    public void size() {
        assertThat(barbecue.size(), is(3));
    }

    @Test
    public void addAll() {
        ShoppingList list = new ShoppingList(mock(NameIdentifier.class));
        list.addAll(barbecue);

        assertThat(list.size(), equalTo(3));
    }

    @Test
    public void remove() {
        int initialSize = barbecue.size();
        barbecue.remove(meat);

        assertThat(barbecue.size(), equalTo(initialSize-1));
    }

    @Test
    public void removeAll() {
        ShoppingList list = new ShoppingList(mock(NameIdentifier.class));
        list.add(new Item(meat.getLabel(), meat.getValue()));

        int initialSize = barbecue.size();
        barbecue.removeAll(list);
        assertThat(barbecue.size(), equalTo(initialSize - 1));
    }

    @Test
    public void clearAndisEmpty() {
        barbecue.clear();
        assertThat(barbecue.isEmpty(), is(true));
    }

}
