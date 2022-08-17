package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Marshall {
    public static void main(String[] args) throws JAXBException, IOException {
        Crossover crossover = new Crossover(true, 900000, "Kia_Sportage",
                new Passport("W B A G B 3 3 0 4 0 2 1 8 2 6 1 6", "H099KK", "Boris_Pokidov"),
                new String[]{"conditioner", "ABS", "ESP"});

        JAXBContext context = JAXBContext.newInstance(Crossover.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(crossover, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        System.out.println("----------Unmarshall------------");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Crossover result = (Crossover) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
