package ba.unsa.etf.rs.tutorijal10;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public static void main(String[] args) {

        //        ArrayList<Grad> gradovi;
//        gradovi=ucitajGradove();
//        for(int i=0;i<gradovi.size();i++){
//            System.out.println(gradovi.get(i).getNaziv());x
//        }
//        ArrayList<Grad> gradovi = ucitajGradove();
//
//        for(Grad g : gradovi) {
//            System.out.println(g);
//        }
//
//        Drzava bih = new Drzava("Bosna i Hercegovina", 4000000, 52000, "km2", gradovi.get(0));
//        Drzava uk = new Drzava("Velika Britanija", 80000000, 100000, "km2", gradovi.get(1));
//
//        ArrayList<Drzava> drzave = new ArrayList<>();
//        drzave.add(bih);
//        drzave.add(uk);
//
//        UN un = new UN();
//        un.setDrzave(drzave);
//
//        zapisiXml(un);

        UN un = ucitajXml(null);
        System.out.println(un);
    }


    // Zadatak 1
    public static ArrayList<Grad> ucitajGradove(){
        ArrayList<Grad> gradovi = new ArrayList<>();
        Scanner tok = null;

        try {
            tok = new Scanner(new FileReader("mjerenja.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        double [] mjerenja = new double[1000];
        int brojac = 0;
        String str ="";
        String Name = "";
        while(tok.hasNext()){
            str = tok.nextLine();
            str = str.replace(',', '\n');
            Scanner Parser = new Scanner(str);
            Name = Parser.nextLine();
            for (int i = 0; Parser.hasNextLine() && i < 1000 ; i++ , brojac++){
                mjerenja[i] = Parser.nextDouble();

            }
            gradovi.add(new Grad(Name , mjerenja , brojac));
            brojac = 0;
        }
        return gradovi;
    }

    // Zadatak 2 - učitavanje podataka o državama iz datoteke drzave.xml​
    public static UN ucitajXml(ArrayList<Grad> gradovi){
        Document documentXML = null;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            documentXML = docReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            System.out.println("drzave.xml dokument nije validan!");
        }
        UN un = new UN();
        ArrayList<Drzava> drzave = new ArrayList<>();

        NodeList drzaveXml = documentXML.getElementsByTagName("drzava");

        for(int i = 0; i < drzaveXml.getLength(); i++) {
            Node drzavaNode = drzaveXml.item(i);

            if(drzavaNode instanceof Element) {
                Element drzavaElement = (Element)drzavaNode;

                int brojStanovnika = Integer.parseInt(drzavaElement.getAttribute("stanovnika"));
                String naziv = drzavaElement.getElementsByTagName("naziv").item(0).getTextContent();

                Element  glavniGradXML = (Element)drzavaElement.getElementsByTagName("glavnigrad").item(0);
                int glavniBrojStanovnika = Integer.parseInt( glavniGradXML.getAttribute("stanovnika"));
                String nazivGrada =  glavniGradXML.getTextContent().trim();

                Element povrsinaXml = (Element)drzavaElement.getElementsByTagName("povrsina").item(0);
                String jedinicaPovrsine = povrsinaXml.getAttribute("jedinica");
                double povrsina = Double.parseDouble(drzavaElement.getElementsByTagName("povrsina").item(0).getTextContent());

                Grad glavniGrad = new Grad(nazivGrada, glavniBrojStanovnika, null, 0);
                drzave.add(new Drzava(naziv, brojStanovnika, povrsina, jedinicaPovrsine, glavniGrad));
            }
        }

        un.setDrzave(drzave);
        return un;
    }

    //Zadatak 3 - serijalizacija klase UN u XML formatu u datoteku ​un.xml​
    public static void zapisiXml(UN un) {
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("un.xml")));
            encoder.writeObject(un);
            encoder.close();
        } catch(Exception exception) {
            System.out.println("Greška: " + exception.getMessage());
        }
    }

}

