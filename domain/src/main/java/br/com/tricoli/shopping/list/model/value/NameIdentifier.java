package br.com.tricoli.shopping.list.model.value;

import org.apache.commons.lang.StringUtils;

/**
 *
 * A immutable value object that validate and encode to a {@code URI} format.
 * The encode is based on the fact that the use of words with the "-" character is low,
 * and almost always can be replace without trouble for the context.
 *
 * <p>URI encod sample: </br>
 *      "Foo Bar Boom" -> "Foo-Bar-Boom"</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class NameIdentifier {

    public static final String URI_SEPARATOR = "-";
    public static final String PLAIN_TEXT_SEPARATOR = " ";
    private final String plainText;
    private final String uri;

    /**
     * Constructs new a {@code NameIdentifier} from a {@link java.lang.String}.
     *
     * <p>If the {@code name} param is in plain text, without "-" character, it encodes the URI format.</p>
     *
     * <p>If the {@code name} param is in uri format, with "-" character, it encodes the plain text.</p>
     *
     * @throws IllegalArgumentException if the {@code name} param is null
     *
     * @param name the identifier name
     */
    public NameIdentifier(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("The name argument could not be null or empty");
        } else if(name.contains(URI_SEPARATOR)){
            this.plainText = name.replaceAll(URI_SEPARATOR, PLAIN_TEXT_SEPARATOR);
            this.uri = name;
        } else {
            this.plainText = name;
            this.uri = name.replaceAll(PLAIN_TEXT_SEPARATOR, URI_SEPARATOR);
        }
    }

    /**
     * Constructs new a {@code NameIdentifier} from a {@link java.lang.String}.
     *
     * <p>If the {@code name} param is in plain text, without "-" character, it encodes the URI format.</p>
     *
     * <p>If the {@code name} param is in uri format, with "-" character, it encodes the plain text.</p>
     *
     * @throws IllegalArgumentException if the {@code name} param is null
     *
     * @param name the identifier name
     */
    public static NameIdentifier of(String name){
        return new NameIdentifier(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameIdentifier that = (NameIdentifier) o;

        if (!plainText.equals(that.plainText)) return false;
        if (!uri.equals(that.uri)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = plainText.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return plainText;
    }

    /**
     *
     * @return the plain text, the name without "-" character.
     *
     */
    public String asPlainText() {
        return plainText;
    }

    /**
     *
     * @return the uri format, the name with "-" character.
     *
     */
    public String asUri() {
        return uri;
    }
}
