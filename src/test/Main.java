package test;

import java.util.Map;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import components.Data;
import components.Domaine;
import components.Element;
import components.Relation;
import controller.QueryController;
import controller.QueryRequest;
import shared.Creator;
import shared.Function;
import shared.Operator;

public class Main{
    public static void main(String[] args)throws Exception{
        QueryRequest.prompt();
        // Relation r1 = Data.loadRelation("null", "A");  
        // Vector<Element> element1 = r1.getElements();
        // Relation r2 = Data.loadRelation("null", "B");
        // Vector<Element> element2 = r2.getElements();

        // int count = 0;
        // for(int i=0; i<element1.size(); i++){
        //     String[] str1 = element1.get(i).getValues(r1);
        //     for(int k=0; k<element2.size(); k++){
        //         String[] str2 = element2.get(k).getValues(r2);
        //         if(Function.isTheSame(str1, str2)){
        //             count += 1;
        //         }
        //     }
        // }
        // System.out.println(count);
        //Relation r2 = Operator.intersection(r, r1);
        //r1.printTable(r1.getAttributs());

        //r2.printTable(r2.getAttributs());
        // Relation r3 = r1.union(r2);
        // System.out.println(r3.getElements().size());
        // r3.printTable(r3.getAttributs());
        // Vector<Element> elem = r.getElements();
        // Vector<Domaine> dom = r.getAttributs();
        // Vector<String> e = r.getAllElement(dom.get(0));
        // Gson g  = new Gson();
        // for(int i=0; i<elem.size(); i++){
        //     System.out.println(e.get(i));
        // }
        // r.printTable(dom);
        // for(int i=0; i<res.length; i++){
        //     System.out.print(huhu[i]+"  ");
        //     System.out.println(huhu[i]);
        // }
        //System.out.println(Function.isTheSame(huhu,huhu));
    }
}