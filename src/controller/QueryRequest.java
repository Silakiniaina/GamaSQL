package controller;

import java.util.Scanner;
import java.util.Vector;

import components.Data;
import components.Relation;
import shared.Creator;
import shared.Function;

public class QueryRequest {
    static String[] operation = {"fitoviana","fitambarana","fahasamihafana"};
    /* Reading the client's request */
    public static void prompt()throws Exception{
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("_Gama >");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("hiala")) {
                    System.out.println("Veloma ;)");
                    break;
                }
                execute(userInput);
                Data.saveRequest(userInput);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /* executing a query */
    public static void execute(String request)throws Exception{
        String actionType = QueryController.getActionType(request);
        if(actionType.equals("fanamboarana")){
            Creator.relation(QueryController.getNomCible(request), Function.getListData(request, 0), Function.getListData(request, 1));
        }else if(actionType.equals("famoronana")){
            Creator.database(QueryController.getNomCible(request));
        }else if(actionType.equals("fampidirana")){
            String[] data = QueryController.splitRequest(request)[1].substring(QueryController.splitRequest(request)[1].indexOf("[")+1, QueryController.splitRequest(request)[1].lastIndexOf("]")).split(",");
            Relation r = Data.loadRelation("null", QueryController.splitRequest(request)[3]);
            r.insert(data);
        }else if(actionType.equals("fangalana")){
            String data = QueryController.splitRequest(request)[1];
            if(data.equalsIgnoreCase("*")){
                Relation r = Data.loadRelation("null",  QueryController.splitRequest(request)[3]);
                r.printTable();
            }
            else if(Function.contains(operation, data)){
                Relation rel1 = Data.loadRelation("null", QueryController.splitRequest(request)[2]), rel2 = Data.loadRelation("null",QueryController.splitRequest(request)[4]);
                Relation res = null;
                if(data.equalsIgnoreCase("fitambarana"))res = rel1.union(rel2);
                else if(data.equalsIgnoreCase("fitoviana")) res = rel1.intersection(rel2);
                else if(data.equalsIgnoreCase("fahasamihafana")) res = rel1.difference(rel2);
                res.printTable();
            }
            else if(!data.equalsIgnoreCase("*") && !Function.contains(operation, data)){
                Relation r = Data.loadRelation("null",  QueryController.splitRequest(request)[3]);    
                String[] colName = data.split(",");
                Relation result = r.projection(colName);
                result.printTable();
            }
        }
    }
}
