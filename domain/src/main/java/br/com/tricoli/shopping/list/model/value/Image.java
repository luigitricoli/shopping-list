package br.com.tricoli.shopping.list.model.value;

import java.io.Serializable;
import java.net.URI;

/**
 *
 * A immutable value object for {@link br.com.tricoli.shopping.list.model.Item} image.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class Image implements Serializable{

    private final String path;

    private Image(String path) {
        this.path = path;
    }

    /**
     * Constructs new a {@code Image} from a {@link java.net.URI}.
     * <b>It does not validate if the resource exist or not</b>
     *
     * @param uri  the source of the image.
     */
    public static Image of(URI uri){
        return new Image(uri.toString());
    }

    /**
     * Constructs new a {@code Image} from a {@link java.lang.String}.
     * <p>Needs to refer to a image path resource on the server application.
     * <b>It does not validate if the resource exist or not</b></p>
     *
     * @param path the image path
     */
    public static Image of(String path){
        return new Image(path);
    }

    /**
     *
     * Constructs new a {@code Image} to a default image.
     *
     */
    public static Image defaultImage(){
        return new Image("images/none.jpg");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (path != null ? !path.equals(image.path) : image.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }

    @Override
    public String toString() {
        return path;
    }
}
