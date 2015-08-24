package br.com.tricoli.shopping.list.model;

import br.com.tricoli.shopping.list.infra.jaxb.adapter.BigDecimalAdapter;
import br.com.tricoli.shopping.list.infra.jaxb.adapter.ImageAdapter;
import br.com.tricoli.shopping.list.infra.jaxb.adapter.NameIdentifierAdapter;
import br.com.tricoli.shopping.list.model.value.Image;
import br.com.tricoli.shopping.list.model.value.NameIdentifier;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement
public class Item implements Serializable {

    @Id
    @XmlJavaTypeAdapter(NameIdentifierAdapter.class)
    private String label;

    private Integer quantity;

    @XmlJavaTypeAdapter(ImageAdapter.class)
    private Image image;

    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal value;

    private boolean purchased;

    private Item(){
        this.value = BigDecimal.ZERO;
        this.purchased = false;
    }

    private Item(Item i){
        this.label = i.label;
        this.value = i.value;
        this.quantity = i.quantity;
        this.image = image;
        this.purchased = purchased;
    }

    public Item(NameIdentifier label, BigDecimal value) {
        this(label, value, null, null, null);
    }

    public Item(NameIdentifier label, BigDecimal value, Integer quantity, Image image, Boolean purchased) {
        if(label == null){
            throw new IllegalArgumentException(String.format("The name param cannot be null."));
        }
        this.label = label.asPlainText();
        this.value = value == null ? BigDecimal.ZERO : value;
        this.quantity = quantity == null ? 0 : quantity;
        this.image = image == null ? Image.defaultImage() : image;
        this.purchased = purchased == null ? false : purchased;
    }

    public NameIdentifier getLabel() {
        return NameIdentifier.of(label);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Image getImage() {
        return image;
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public Item sum(Item item){
        if(!this.equals(item)){
            throw new IllegalArgumentException(String.format("The param item %s is not equals to this item %s", item, this));
        }
        Item result = new Item(this);
        result.quantity = this.getQuantity() + item.getQuantity();
        return result;
    }

    public Item purchased() {
        Item result = new Item(this);
        result.purchased = true;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!label.equals(item.label)) return false;
        if (value != null ? !value.equals(item.value) : item.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "label='" + label + '\'' +
                ", quantity=" + quantity +
                ", photo=" + image +
                ", value=" + value +
                ", purchased=" + purchased +
                '}';
    }
}
