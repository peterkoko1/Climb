import javax.swing.*;
public class Main {
    public static void main (String[] args){
        System.out.println("This will be the driver class");
        /*
        UserData d1 = new UserData("Me", 12);
        System.out.println(d1.getId() + " " + d1.getUser());
        d1.setDifficulty(6);
        System.out.println(d1.getDifficulty());
        d1.updateHistory("Climbb#3");
        System.out.println(d1.getHistory());
        SwingUtilities.invokeLater(() ->{
        Board board = new Board();
        board.setVisible(true);
        }); */
        SwingUtilities.invokeLater(() -> {
            ClimbingApp climbingApp = new ClimbingApp();
            climbingApp.showLoginScreen();
        });
    }

    /*
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClimbingApp climbingApp = new ClimbingApp();
            climbingApp.showLoginScreen();
        });
    }
     */
}