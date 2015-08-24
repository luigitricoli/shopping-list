package br.com.tricoli.shopping.list.infra.jaxb.adapter;

import br.com.tricoli.shopping.list.model.value.NameIdentifier;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * It is use to marshall and unmarshall a {@link br.com.tricoli.shopping.list.model.value.NameIdentifier} object to {@link java.lang.String}
 * on the XML and JSON context.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class NameIdentifierAdapter extends XmlAdapter<String, NameIdentifier> {

    @Override
    public NameIdentifier unmarshal(String v) throws Exception {
        return NameIdentifier.of(v);
    }

    @Override
    public String marshal(NameIdentifier v) throws Exception {
        return v.toString();
    }
}