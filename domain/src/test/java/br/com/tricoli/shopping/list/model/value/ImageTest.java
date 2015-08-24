package br.com.tricoli.shopping.list.model.value;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ImageTest {

    @Test
    public void ofURI(){
        URI uri = URI.create("http://images.tricoli.com.br/any.jpg");
        assertThat(Image.of(uri).toString(), equalTo("http://images.tricoli.com.br/any.jpg"));
    }

    @Test
    public void ofString(){
        assertThat(Image.of("image/any.jpg").toString(), equalTo("image/any.jpg"));
    }

    @Test
    public void defaultImage(){
        assertThat(Image.defaultImage().toString(), equalTo("images/none.jpg"));
    }

}
