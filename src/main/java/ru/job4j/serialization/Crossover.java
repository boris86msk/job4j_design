package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "crossover")
@XmlAccessorType(XmlAccessType.FIELD)
public class Crossover {
    @XmlAttribute
    private boolean awd;
    @XmlAttribute
    private int cost;
    @XmlAttribute
    private String model;

    private Passport passport;

    private String[] options;

    public Crossover() {

    }
    public Crossover(boolean awd, int cost, String model, Passport pass, String[] opt) {
        this.awd = awd;
        this.cost = cost;
        this.model = model;
        this.passport = pass;
        this.options = opt;
    }
    public static void main(String[] args) {
        Crossover crossover = new Crossover(true, 900000, "Kia_Sportage",
                new Passport("W B A G B 3 3 0 4 0 2 1 8 2 6 1 6", "H099KK", "Boris_Pokidov"),
                new String[]{"conditioner", "ABS", "ESP"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(crossover));

        final String crossoverJson =
                "{"
                        + "\"awd\":true,"
                        + "\"cost\":900000,"
                        + "\"model\":Kia_Sportage, "
                        + "\"passport\":"
                        + "{"
                        + "\"VIN\":\"W B A G B 3 3 0 4 0 2 1 8 2 6 1 6\", "
                        + "\"num\":\"H099KK\", "
                        + "\"owner\":\"H099KKBoris_Pokidov\""
                        + "},"
                        + "\"options\":"
                        + "[\"conditioner\",\"ABS\",\"ESP\"]"
                        + "}";

        final Crossover crossoverMod = gson.fromJson(crossoverJson, Crossover.class);
        System.out.println(crossoverMod);
    }

    @Override
    public String toString() {
        return "Crossover{"
                + "AWD=" + awd
                + ", cost=" + cost
                + ", model='" + model + '\''
                + ", passport=" + passport
                + ", options=" + Arrays.toString(options)
                + '}';
    }

}
