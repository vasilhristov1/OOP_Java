package server;

import callback.CallBack;

import java.rmi.*;

public interface GetFifteenInterface extends Remote {
    // Connect to the GetFifteen server and return the token.
    // The client is not connected to the server if the returned token is 3.
    public int connect(CallBack client) throws RemoteException;

    // This method is invoked by the client to notify the server of its move and update their numbers
    public void myMove(int num, int idOfPlayer) throws RemoteException;
}