package br.com.tricoli.shopping.list.infra.jaxb.adapter;


import br.com.tricoli.shopping.list.model.value.Image;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * It is use to marshall and unmarshall a {@link br.com.tricoli.shopping.list.model.value.Image} object to {@link java.lang.String}
 * on the XML and JSON context.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ImageAdapter extends XmlAdapter<String, Image> {

    @Override
    public Image unmarshal(String v) throws Exception {
        return Image.of(v);
    }

    @Override
    public String marshal(Image v) throws Exception {
        return v.toString();
    }
}
