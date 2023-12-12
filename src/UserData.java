import java.util.*;

/**
 * The type User data.
 */
public class UserData {
    /**
     * The User name.
     */
    String userName;
    /**
     * The User id.
     */
    int userId;
    /**
     * The User difficulty.
     */
    int userDifficulty = 11;
    /**
     * The Climb history.
     */
    ArrayList <String> climbHistory =  new ArrayList<String>();

    /**
     * Instantiates a new User data.
     *
     * @param name the name
     * @param id   the id
     */
    public UserData(String name, int id){
        userName = name;
        userId = id;
    }

    /**
     * Instantiates a new User data.
     */
    public UserData(){
        userName = "default";
        userId = 1;
    }

    /**
     * Get user string.
     *
     * @return the string
     */
    public String getUser(){
        return userName;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
        return userId;
    }

    /**
     * Update history.
     *
     * @param c the c
     */
    public void updateHistory(String c){
        climbHistory.add(c);
    }

    /**
     * Get history array list.
     *
     * @return the array list
     */
    public ArrayList<String> getHistory(){
        return climbHistory;
    }

    /**
     * Set difficulty.
     *
     * @param d the d
     */
    public void setDifficulty(int d){
        userDifficulty = d;
    }

    /**
     * Get difficulty int.
     *
     * @return the int
     */
    public int getDifficulty(){
        return userDifficulty;
    }

    /**
     * Has history boolean.
     *
     * @return the boolean
     */
    public boolean hasHistory(){
        if(climbHistory.size()==0){
            return false;
        }else{
            return true;
        }
    }
}