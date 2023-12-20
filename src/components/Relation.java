package components;

import java.util.Vector;
import controller.QueryController;

public class Relation {
    String RELATION_NAME;
    Vector<Domaine> attributs;
    
    /* Constructors */
    public Relation(String name, Vector<Domaine> listeDomaine)throws Exception{
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

    /* Description */
    public void desc(){
        System.out.println("Mombamomba ny fifandraisana "+ this.getNomRelation());
        System.out.println("Ananrana :"+this.getNomRelation());
        for (int i = 0; i < attributs.size(); i++) {
            System.out.print(this.attributs.get(i).getNom());
            System.out.println(" -> "+this.attributs.get(i).getType());
        }
    }
}