package callback;

import java.awt.*;
import java.rmi.*;

public interface CallBack extends Remote {
    // Method which makes the server to notify the client to take a turn
    public void takeTurn(boolean turn) throws RemoteException;

    // Method which makes the server to send a message to be displayed by the client
    public void notify(String msg) throws RemoteException;

    // A client is notified by the server of the other player's turn
    public void mark(int num) throws RemoteException;

    // The server highlights the board of the player with the corresponding color:
    // green - player wins
    // red - player looses
    public void highlightWinnersNums(Color color) throws RemoteException;
}