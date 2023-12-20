package controller;

import java.util.Scanner;

import shared.Creator;
import shared.Function;

public class QueryRequest {

    /* Reading the client's request */
    public static void prompt()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tongasoa _GamaSQL_");
        while (true) {
            System.out.print("_Gama >");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("hiala")) {
                System.out.println("Veloma ;)");
                break;
            }
            execute(userInput);
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
        }
    }
}
