package com.github.jrry.productparser.io;

import com.github.jrry.productparser.dto.Products;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlOutput implements Output {

    private final Logger logger = LoggerFactory.getLogger(XmlOutput.class);

    private String filename;

    XmlOutput(String filename) {
        this.filename = filename;
    }

    @Override
    public void generateOutput(Products products) {
        try {
            JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[] {Products.class}, null);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(products, new File(filename));
        } catch (JAXBException e) {
            logger.error(e.getClass().getName() + " - " + e.getMessage());
        }
    }
}
