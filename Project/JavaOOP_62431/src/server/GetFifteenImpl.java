package server;

import callback.CallBack;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class GetFifteenImpl extends UnicastRemoteObject implements GetFifteenInterface {
    // Declare two players, used to call players back
    private CallBack firstPlayer = null;
    private CallBack secondPlayer = null;

    // Lists of selected numbers by each player
    private ArrayList<Integer> firstPlayerNumbers = new ArrayList<>();
    private ArrayList<Integer> secondPlayerNumbers = new ArrayList<>();

    // Colors used for the winner client view and the loser client view
    Color winHighlight = new Color(0, 255, 0, 255);
    Color loseHighlight = new Color(255, 0, 0, 255);

    // GetFifteenImpl object is constructed and exported on default port
    protected GetFifteenImpl() throws RemoteException {
        super();
    }

    // GetFifteenImpl object is constructed and exported on specified port
    public GetFifteenImpl(int port) throws RemoteException {
        super(port);
    }

    // Connect to the GetFifteen server and return the token.
    // The client is not connected to the server if the returned token is 3.
    public int connect(CallBack client) throws RemoteException {
        if (firstPlayer == null) {
            // first player registered
            firstPlayer = client;
            firstPlayer.notify(" Wait for a second player to join");
            return 1;
        }  else if (secondPlayer == null) {
            // second player registered
            secondPlayer = client;
            secondPlayer.notify(" Wait for the first player to move");
            secondPlayer.takeTurn(false);
            firstPlayer.notify(" It's my turn to select number");
            firstPlayer.takeTurn(true);
            return 2;
        } else {
            // Already two players
            client.notify(" Two players are already in the game");
            return 3;
        }
    }

    // This method is invoked by the client to notify the server of its move and update their numbers
    public void myMove(int num, int idOfPlayer) throws RemoteException {
        // Other player is notified of the move and the list of numbers is updated
        if (idOfPlayer == 1) {
            firstPlayerNumbers.add(num);
            secondPlayer.mark(num);
        }
        else if (idOfPlayer == 2) {
            secondPlayerNumbers.add(num);
            firstPlayer.mark(num);
        }

        // Check if the player with this ID wins
        if (isWinner(idOfPlayer)) {
            if (idOfPlayer == 1) {
                firstPlayer.notify("      You win!");
                firstPlayer.highlightWinnersNums(winHighlight);
                secondPlayer.notify("      You lose!");
                secondPlayer.highlightWinnersNums(loseHighlight);
                firstPlayer.takeTurn(false);
            }
            else if (idOfPlayer == 2) {
                firstPlayer.notify("      You lose!");
                firstPlayer.highlightWinnersNums(loseHighlight);
                secondPlayer.notify("      You win!");
                secondPlayer.highlightWinnersNums(winHighlight);
                secondPlayer.takeTurn(false);
            }
        }
        // Continue the game if the player doesn't win
        else if (idOfPlayer == 1) {
            firstPlayer.notify(" Wait for the second player to move");
            firstPlayer.takeTurn(false);
            secondPlayer.notify(" It's my turn");
            secondPlayer.takeTurn(true);
        } else if (idOfPlayer == 2) {
            secondPlayer.notify(" Wait for the first player to move");
            secondPlayer.takeTurn(false);
            firstPlayer.notify(" It's my turn");
            firstPlayer.takeTurn(true);
        }
    }

    // Method to check if the player with the given ID has any three numbers that sums up to exactly total of 15
    public boolean isWinner(int playerID) {
        // playerNumbers array size could be different. The array is chosen by the playerID.
        ArrayList<Integer> playerNumbers = (playerID == 1) ? firstPlayerNumbers : secondPlayerNumbers;
        int size = playerNumbers.size();

        // Three nested loops to calculate the sum of any three numbers and check if it totals 15
        for (int i = 0; i < size; i++) {
            int a = playerNumbers.get(i);

            for (int j = i + 1; j < size; j++) {
                int b = playerNumbers.get(j);

                for (int k = j + 1; k < size; k++) {
                    int c = playerNumbers.get(k);

                    if (a + b + c == 15) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            GetFifteenInterface obj = new GetFifteenImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GetFifteenImpl", obj);
            System.out.println("Server " + obj + " registered");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}