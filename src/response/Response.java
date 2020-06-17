package response;

import graph.Database;

import java.util.ArrayList;

public class Response {
    public ArrayList<String> checkUser(String phoneNum){
        if(Database.checkUser(phoneNum).isEmpty())
        {
            ArrayList<String> result = new ArrayList<String>();
            result.add("");
            result.add("");
            result.add("-1");
            return result;
        }
        else {
            return Database.checkUser(phoneNum);
        }
    }

    public ArrayList<String> returnNumber(String phoneNum){
        return Database.addSentList(phoneNum);
//        return Database.getSentList(phoneNum);
    }
}