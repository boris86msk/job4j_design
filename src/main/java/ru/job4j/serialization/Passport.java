package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passport")
@XmlAccessorType(XmlAccessType.FIELD)
public class Passport {
    @XmlAttribute
    private String vin;
    @XmlAttribute
    private String num;
    @XmlAttribute
    private String owner;

    public Passport() {

    }

    public String getVin() {
        return vin;
    }
    public String getNum() {
        return num;
    }
    public String getOwner() {
        return owner;
    }

    public Passport(String vin, String num, String owner) {
        this.vin = vin;
        this.num = num;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Passport{"
                + "VIN='" + vin + '\''
                + ", num='" + num + '\''
                + ", owner='" + owner + '\''
                + '}';
    }
}
