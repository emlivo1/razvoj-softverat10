package ba.unsa.etf.rs.tutorijal10;

import java.io.Serializable;
import java.util.ArrayList;

public class UN implements Serializable {

    private ArrayList<Drzava> drzave = new ArrayList<>();

    public UN() {
    }

    public UN(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    @Override
    public String toString() {
        return "UN{" +
                "drzave=" + drzave +
                '}';
    }
}