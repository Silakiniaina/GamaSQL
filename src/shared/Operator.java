package shared;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import components.*;

public class Operator {
    private static String[] CONNECTORS = {"ary","na"};

///OPERATIONS SQL

    /* UNION */
    // public static Relation union(Relation r1, Relation r2)throws Exception{
    //     if(r1.getAttributs().size() != r2.getAttributs().size()) throw new Exception("Tsy maintsy mitovy ny isan'ny domaine atao union amin'ny fifandraisana roa");
    //     Relation result = new Relation("union"+r1.getNomRelation()+"_"+r2.getNomRelation(), Function.generateDomains(r1.getAttributesName()));
    //     Vector<Element> e = r1.unionElements(r2);
    //     result.insertAll(e);
    //     result.extractDoublon();
    //     return result; 
    // }
}
