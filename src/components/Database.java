package components;

import java.util.Vector;

public class Database {
    String name;
    Vector<Relation> relations;

    /* Construcors */
    public Database(String nom)throws Exception{
        this.setName(nom);
        relations = new Vector<Relation>();
    }
    /*Getters */
    public String getName() {
        return name;
    }
    public Vector<Relation> getRelations() {
        return relations;
    }

    /*Setters */
    public void setName(String name) throws Exception{
        if(name == null || name.trim() == "")throw new Exception("Tsy mahazo atao foana ny anarana databazy iray");
        this.name = name;
    }
    public void setRelations(Vector<Relation> relations) {
        this.relations = relations;
    }

/// ADDING DATA
    /* ajout de relation */
    public void addRelation(Relation r){
        this.getRelations().add(r);
    }
    
}