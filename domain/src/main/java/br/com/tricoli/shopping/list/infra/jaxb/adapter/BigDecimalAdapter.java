package br.com.tricoli.shopping.list.infra.jaxb.adapter;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;


/**
 *
 * It is use to marshall and unmarshall a {@link java.math.BigDecimal} object to {@link java.lang.Double}
 * on the XML and JSON context.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class BigDecimalAdapter extends XmlAdapter<Double, BigDecimal> {

    @Override
    public BigDecimal unmarshal(Double v) throws Exception {
        return new BigDecimal(v);
    }

    @Override
    public Double marshal(BigDecimal v) throws Exception {
        return v.doubleValue();
    }
}
