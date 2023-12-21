package controller;

import java.util.Scanner;

import components.Data;
import components.Relation;
import shared.Creator;
import shared.Function;

public class QueryRequest {

    /* Reading the client's request */
    public static void prompt()throws Exception{
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("_Gama >");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("hiala")) {
                    System.out.println("Veloma ;)");
                    break;
                }
                execute(userInput);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            prompt();
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
        }
    }
}
