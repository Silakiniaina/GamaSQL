package shared;

import java.util.Vector;

import components.*;
import controller.QueryController;

public class Creator {
/// OPERATION TO CREATE IN GAMASQL

    /* RELATION */
    public static Relation relation(String nom,String[] attributNom, String [] attributType)throws Exception{
        if(attributNom.length != attributType.length){
            Exception e = new Exception("Tsy maintsy mitovy ny isa ny anarana attribut sy ny karazany tsirairay");
            throw e;
        }
        /* List of all domains */
        Vector<Domaine> listeDomaine = new Vector<Domaine>();
        for(int i=0; i<attributNom.length; i++){
            Domaine d = new Domaine(attributNom[i], attributType[i]);
            if(!QueryController.isAnExistantDomain(d)){
                Exception e = new Exception("Daomainina tsy misy ny karazany *"+attributType[i]+"* izay tafiditra");
                throw e;
            }else{
                listeDomaine.add(d);
            }
        }
        Relation result = new Relation(nom, listeDomaine);
        System.out.println("Fifandraisana "+nom+" voaforona");
        Data.saveRelation(result);
        return result;
    }

    /* DATABASES */
    public static void database(String nom)throws Exception{
        Database d = new Database(nom);
        System.out.println("Databazy voaforona :) ");
    }
}