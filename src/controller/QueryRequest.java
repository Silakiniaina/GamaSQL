package controller;

import java.util.Scanner;

import components.Data;
import components.Relation;
import shared.Creator;
import shared.Function;
import shared.Operator;

public class QueryRequest {

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
            String[] attributs = Function.getListData(request, 0);
            String[] domains = Function.getListData(request, 1);
            Creator.relation(QueryController.getNomCible(request), attributs, domains);
        }else if(actionType.equals("famoronana")){
            String nomBase = QueryController.getNomCible(request);
            Creator.database(nomBase);
        }else if(actionType.equals("fampidirana")){
            String relName = QueryController.splitRequest(request)[3];
            String[] data = QueryController.splitRequest(request)[1].substring(QueryController.splitRequest(request)[1].indexOf("[")+1, QueryController.splitRequest(request)[1].lastIndexOf("]")).split(",");
            Relation r = Data.loadRelation("null", relName);
            r.insert(data);
        }else if(actionType.equals("fangalana")){
            String data = QueryController.splitRequest(request)[1];
            String relName = QueryController.splitRequest(request)[3];
            if(data.equalsIgnoreCase("*")){
                Relation r = Data.loadRelation("null", relName);
                r.printTable(r.getAttributs());
            }else if(data.equalsIgnoreCase("fitambarana") || data.equalsIgnoreCase("fitoviana") || data.equalsIgnoreCase("fahasamihafana")){
                Relation rel1 = Data.loadRelation("null", QueryController.splitRequest(request)[2]), rel2 = Data.loadRelation("null", QueryController.splitRequest(request)[4]);
                Relation res = null;
                if(data.equalsIgnoreCase("fitambarana"))res = rel1.union(rel2);
                else if(data.equalsIgnoreCase("fitoviana")) res = rel1.intersection(rel2);
                else if(data.equalsIgnoreCase("fahasamihafana")) res = rel1.difference(rel2);
                res.printTable(res.getAttributs());
                Data.saveRelation(res);
            }
        }
    }
}
