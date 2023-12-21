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
}
