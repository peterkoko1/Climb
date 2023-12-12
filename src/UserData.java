import java.util.*;
public class UserData {
    String userName;
    int userId;
    int userDifficulty = 11;
    ArrayList <String> climbHistory =  new ArrayList<String>();

    public UserData(String name, int id){
        userName = name;
        userId = id;
    }

    public UserData(){
        userName = "default";
        userId = 1;
    }

    public String getUser(){
        return userName;
    }

    public int getId(){
        return userId;
    }

    public void updateHistory(String c){
        climbHistory.add(c);
    }

    public ArrayList<String> getHistory(){
        return climbHistory;
    }

    public void setDifficulty(int d){
        userDifficulty = d;
    }

    public int getDifficulty(){
        return userDifficulty;
    }

    public boolean hasHistory(){
        if(climbHistory.size()==0){
            return false;
        }else{
            return true;
        }
    }
}