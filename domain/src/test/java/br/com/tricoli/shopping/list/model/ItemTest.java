package br.com.tricoli.shopping.list.model;

import br.com.tricoli.shopping.list.model.value.Image;
import br.com.tricoli.shopping.list.model.value.NameIdentifier;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ItemTest {

    public static final NameIdentifier BEAR_LABEL = NameIdentifier.of("Heineken 300ml");
    public static final Image MOCK_IMAGE = Image.of("item.jpg");
    public static final boolean PURCHASED = true;
    public static final boolean NOT_PURCHASED = false;

    @Test
    public void gets(){
        Item bear = createBear();
        assertThat(bear.getLabel(), equalTo(BEAR_LABEL));
        assertThat(bear.getQuantity(), equalTo(1));
        assertThat(bear.getValue(), equalTo(BigDecimal.TEN));
        assertThat(bear.getImage(), equalTo(MOCK_IMAGE));
        assertThat(bear.isPurchased(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidName(){
        new Item(null, BigDecimal.TEN, 1, MOCK_IMAGE, PURCHASED);
    }

    @Test
    public void invalidQuatity(){
        Item bear = new Item(BEAR_LABEL, BigDecimal.TEN, null, MOCK_IMAGE, PURCHASED);
        assertThat(bear.getQuantity(), equalTo(0));
    }

    @Test
    public void sumQuantity(){
        Item foo = new Item(BEAR_LABEL, BigDecimal.TEN, 10, MOCK_IMAGE, false);
        Item bar = new Item(BEAR_LABEL, BigDecimal.TEN, 5, MOCK_IMAGE, false);
        assertThat(bar.sum(foo).getQuantity(), equalTo(15));
    }

    @Test
    public void purchased() {
        Item bear = new Item(BEAR_LABEL, BigDecimal.TEN, null, MOCK_IMAGE, false);
        assertThat(bear.purchased().isPurchased(), equalTo(true));
    }

    @Test
    public void itemEquals(){
        Item item1 = createBear();
        Item item2 = createBear();

        assertThat(item1, equalTo(item2));
    }

    @Test
    public void itemNotEquals(){
        Item item1 = createMeat();
        Item item2 = createBear();

        assertThat(item1, not(equalTo(item2)));
    }

    @Test
    public void itemEqualHashCode(){
        Item item1 = createMeat();
        Item item2 = createMeat();

        assertThat(item1.hashCode(), equalTo(item2.hashCode()));
    }

    @Test
    public void itemNoEqualHashCode(){
        Item item1 = createMeat();
        Item item2 = createBear();

        assertThat(item1.hashCode(), not(equalTo(item2.hashCode())));
    }

    private Item createBear() {
        return new Item(BEAR_LABEL, BigDecimal.TEN, 1, MOCK_IMAGE, PURCHASED);
    }

    private Item createMeat() {
        return new Item(NameIdentifier.of("Kg Fraldinha"), BigDecimal.TEN, 1, MOCK_IMAGE, NOT_PURCHASED);
    }

}
