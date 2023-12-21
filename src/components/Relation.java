package components;

import java.io.Serializable;
import java.util.Vector;
import controller.QueryController;

public class Relation implements Serializable{
    String baseName;
    String RELATION_NAME;
    Vector<Domaine> attributs;
    Vector<Element> elements; 
    
    /* Constructors */
    public Relation(String name, Vector<Domaine> listeDomaine)throws Exception{
        elements = new Vector<Element>();
        this.setNomRelation(name);
        this.setAttribut(listeDomaine);

    }

    /* Getters */
    public String getNomRelation(){
        return this.RELATION_NAME;
    }
    public Vector<Domaine> getAttributs(){
        return this.attributs;
    }
    public Vector<Element> getElements(){
        return this.elements;
    }
    public String getBaseName(){
        return this.baseName;
    }

    /* Setters */
    public void setNomRelation(String str)throws Exception{
        if(str == null || str.trim() == ""){
            Exception e = new Exception("Tsy azo atao foana na tsy misy ny anarana fifandraisana iray azafady");
            throw e;
        }else{
            QueryController.escapeCharacter(str);
            this.RELATION_NAME = str;
        }
    }
    public void setAttribut(Vector<Domaine> domaine)throws Exception{
        if(domaine == null){
            Exception e = new Exception("Ny fifandraisana iray dia tokony manana farafahakeliny attribut iray");
            throw e;
        }else{
            this.attributs = domaine;
        }
    }
    public void setElements(Vector<Element> e){
        this.elements = e;
    }
    public void setBaseName(String bn) throws Exception{
         if(bn == null || bn.trim() == ""){
            Exception e = new Exception("Tsy azo atao foana na tsy misy ny anarana databazina fifandraisana ");
            throw e;
        }
        QueryController.escapeCharacter(bn);
        this.baseName = bn;
    }

    /* Description */
    public void desc(){
        System.out.println("Mombamomba ny fifandraisana "+ this.getNomRelation());
        System.out.println("Ananrana :"+this.getNomRelation());
        for (int i = 0; i < attributs.size(); i++) {
            System.out.print(this.attributs.get(i).getNom());
            System.out.println(" -> "+this.attributs.get(i).getType());
        }
    }

/// DATA INSERTION 
    /* Inserting data in the relation */
    public void insert(String[] elements)throws Exception{
        Element e = new Element(this,elements);
        this.getElements().add(e);
        Data.saveRelation(this);
    }

/// DATA FETCH
    /* Getting a Domaine by his name*/
    public Domaine getDomaineByName(String domName)throws Exception{
        Domaine dResult = null;
        Vector<Domaine> lsDomaines = this.getAttributs();
        for(int i=0; i<lsDomaines.size(); i++){
            Domaine d = lsDomaines.get(i);
            if(d.getNom().equals(domName)){
                dResult = d;
                break; 
            }
        }
        if(dResult == null)throw new Exception("Tsy nahitana daomanina ny anarana "+domName+" izay nampidirina");
        return dResult;
    }

    /* allElement of a domain */
    public Vector<String> getAllElement(Domaine d){
        Vector<String> result = new Vector<String>();
        for(int i=0; i<this.getElements().size(); i++){
            Element e = this.getElements().get(i);
            result.add((String)e.getListeValeur().get(d.getNom()));
        }   
        return result;
    }

/// PRINT TABLE 
    /* getting the max length of charcter */
    public int getMaxCharacter(Vector<String> text){
        int count = -1;
        for(int i=0; i<text.size(); i++){
            String mot = text.get(i);
            if(count < mot.length()) count = mot.length();
        }
        return count+2;
    }
    /* getting limit of each columns */
    public String getLimit(int count){
        String result = "";
        for(int i=0; i<=count + 1; i++){
            if(i== count+1)result += "+";
            else if(i<count+1 )result += "~";
        }
        return result;
    }
    /* adjusting all elements in each columns */
    public String ajust(String mot, int max){
        int separator = Math.round((max-mot.length())/2) + 1;
        String left = "", right = "";
        double taille = separator  + mot.length() + separator;
        if(taille - max > 0){
            for(int i=0; i<separator; i++) left+=" ";
            for(int i=0; i<separator - (taille - max); i++) right+=" ";
        }else if(taille - max < 0 || taille - max == 0){
            for(int i=0; i<separator ;i++){left+=" "; right+=" ";}
        }
        String result = " "+left + mot+ right +"|";
        return result;
    }

    /* printing the relation  */
    public void printTable(Vector<Domaine> lsDomaine){
        Vector<String> content = new Vector<String>();
        String titre = "", limiteTitre = "";
        for(int i=0; i<lsDomaine.size(); i++){
            Domaine d = lsDomaine.get(i);
            Vector<String> contenu = getAllElement(d);
            Vector<String> all = getAllElement(d);
            all.add(d.getNom());
            String limite = this.getLimit(this.getMaxCharacter(all));
            limiteTitre += limite;
            titre += this.ajust(d.getNom(), this.getMaxCharacter(all));
            for(int j=0; j<contenu.size(); j++){
                if(i == 0)content.add(this.ajust(contenu.get(j), this.getMaxCharacter(all)));
                else{
                    String precedent = (String) content.get(j);
					String recent = this.ajust((String)contenu.get(j),this.getMaxCharacter(all));
					precedent += ""+recent;
					content.set(j,precedent);
                }
            }
        }
        System.out.println(limiteTitre+"\n"+titre.toUpperCase()+"\n"+limiteTitre);
        for(int i=0; i<content.size(); i++) System.out.println(content.get(i));
        System.out.println(limiteTitre);
    }
}
