package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.file.Files;

@XmlRootElement(name = "contact")
public class Contact implements Serializable {

    @XmlAttribute
    private String phone;

    public Contact() {

    }
    public Contact(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + ", phone='" + phone + '\''
                + '}';
    }

    /**
     * создаем объект для сериализации: contact
     * создаем временный файл: tempFile
     * запись объекта во временный файл: oos.writeObject(contact)
     * Чтение объекта из файла: (Contact) ois.readObject()
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact("+7 (111) 111-11-11");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
