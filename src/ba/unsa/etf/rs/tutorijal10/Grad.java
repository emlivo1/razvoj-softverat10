package ba.unsa.etf.rs.tutorijal10;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Grad implements Serializable {
    private String naziv;
    private Integer broj_stanovnika;
    private double []temperature = new double[1000];

    private int brMjerenja = 0;

    public Grad() { }

    public Grad(String nazivGrada, int glavniBrojStanovnika, double[] mjerenja, int br) {
        naziv = nazivGrada;
        broj_stanovnika = glavniBrojStanovnika;
        temperature = mjerenja;
        brMjerenja = br;
    }

    public Grad(String name, double[] mjerenja, int brojac) {
        naziv = name;
        broj_stanovnika = 0;
        temperature = mjerenja;
        brMjerenja = brojac;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika = broj_stanovnika;
    }

    public double[] getTemperature() {
        return temperature.clone();
    }

    public void setTemperature(double[] temperature, int brojMjerenja) {
        if(temperature!=null)
            System.arraycopy(temperature, 0, this.temperature, 0, brojMjerenja);
        /*this.temperature = temperature;*/
    }

    public int getBrMjerenja() {
        return brMjerenja;
    }

    public void setBrMjerenja(int brMjerenja) {
        this.brMjerenja = brMjerenja;
    }

    @Override
    public String toString() {
        return "Grad{" +
                "naziv='" + naziv + '\'' +
                ", broj_stanovnika=" + broj_stanovnika +
                ", temperature=" + Arrays.toString(temperature) +
                ", brMjerenja=" + brMjerenja +
                '}';
    }

}