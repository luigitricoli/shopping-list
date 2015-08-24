package br.com.tricoli.shopping.list.model.value;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NameIdentifierTest {

    @Test
    public void fromUriToPlainText() {
        assertThat(NameIdentifier.of("Foo Bar Bomm").asUri(), equalTo("Foo-Bar-Bomm"));
    }

    @Test
    public void fromPlainTextToUri() {
        assertThat(NameIdentifier.of("Foo-Bar-Bomm").asPlainText(), equalTo("Foo Bar Bomm"));
    }

}
