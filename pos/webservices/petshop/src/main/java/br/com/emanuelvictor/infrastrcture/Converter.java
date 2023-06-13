package br.com.emanuelvictor.infrastrcture;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.lang.reflect.ParameterizedType;

public abstract class Converter<T> {

    public void convertToXML(final T object, final File file) {
        try {
            final JAXBContext context = JAXBContext.newInstance(object.getClass());
            final Marshaller m = context.createMarshaller();
            m.marshal(object, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T convertToObject(final File filexml, final File fileSchema) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(getClazz());
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            final Schema schema = sf.newSchema(fileSchema);
            jaxbUnmarshaller.setSchema(schema);
            return (T) jaxbUnmarshaller.unmarshal(filexml);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> getClazz() {
        return ((Class<?>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
