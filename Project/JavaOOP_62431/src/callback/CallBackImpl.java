package callback;

import clientRMI.GetFifteenClientRMI;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {
    // The client will be called by the server through callback
    private GetFifteenClientRMI thisClient;

    // Constructor
    public CallBackImpl(Object client) throws RemoteException {
        thisClient = (GetFifteenClientRMI) client;
    }

    // Method which makes the server to notify the client to take a turn
    public void takeTurn(boolean turn) throws RemoteException {
        thisClient.setMyTurn(turn);
    }

    // Method which makes the server to send a message to be displayed by the client
    public void notify(String msg) throws RemoteException {
        thisClient.setMessage(msg);
    }

    // A client is notified by the server of the other player's turn
    public void mark(int num) throws RemoteException {
        thisClient.mark(num);
    }

    // The board of the player is highlighted by the server with the corresponding color:
    // green - player wins
    // red - player looses
    public void highlightWinnersNums(Color color) throws RemoteException {
        thisClient.highlightNums(color);
    }
}