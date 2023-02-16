package clientRMI;

import callback.CallBack;
import callback.CallBackImpl;
import server.GetFifteenInterface;

import java.rmi.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.rmi.registry.*;

public class GetFifteenClientRMI extends JApplet {
    // PlayerID is used to indicate which player is taking action
    private int playerID;

    // myTurn indicates whether the player can move now
    private boolean myTurn = false;

    // Each button represents a number from 1-9
    private JButton[][] board;

    // Custom colors for Player 1 (BLUE) and Player 2 (ORANGE)
    private Color customBlue = new Color(55, 155, 168);
    private Color customOrange = new Color(160, 80, 0);

    // GetFifteenServer is the game server for coordinating with the players
    private GetFifteenInterface getFifteenServer;

    // JLabel indicating status information for the player
    private JLabel jlblStatus = new JLabel("jLabel1");
    private JLabel jlblIdentification = new JLabel();

    // Compound border (made of another compound border) to acquire good-looking visual border
    private CompoundBorder borderAndPadding = BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                    new EmptyBorder(7, 7, 7, 7),
                    BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.DARK_GRAY)),
            new EmptyBorder(5, 5, 5, 5));


    boolean isStandalone = false;

    // Initialization of the applet
    public void init() {
        JPanel jPanel1 = new JPanel(new GridLayout(3, 3, 7, 7));
        jPanel1.setBorder(borderAndPadding);

        add(jlblStatus, BorderLayout.SOUTH);
        add(jPanel1, BorderLayout.CENTER);
        add(jlblIdentification, BorderLayout.NORTH);

        board = new JButton[3][3];
        // Create buttons with the method makeButton() and place them in the panel
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                board[i][j] = makeButton(i, j);
                jPanel1.add(board[i][j]);
            }
        try {
            initializeRMI();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // RMI initialization
    protected boolean initializeRMI() throws Exception {
        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            getFifteenServer = (GetFifteenInterface)registry.lookup("GetFifteenImpl");
            System.out.println("Server object " + getFifteenServer + " found");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        // Create callback for use by the server to control the client
        CallBackImpl callBackControl = new CallBackImpl(this);

        playerID = getFifteenServer.connect((CallBack)callBackControl);
        if (playerID != 3) {
            // Determine BLUE/ORANGE player
            System.out.println("Connected as " + playerID + " player.");
            jlblIdentification.setText(" You are player " + playerID +
                    ((playerID == 1) ? " (BLUE)" : " (ORANGE)") +
                    "               Collected numbers:");
            return true;
        }
        else {
            System.out.println("There are already two players connected");
            return false;
        }
    }

    //Method to set properties for regular button
    private JButton makeButton (int i, int j) {
        // (i * 3 + j + 1) is the number computed from the indexes i.j
        JButton button = new JButton(String.valueOf(i * 3 + j + 1));
        button.setBackground(Color.GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setFont(new Font("Arial", Font.PLAIN, 25));
        button.setEnabled(true);

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (myTurn && button.isEnabled()) {

                    // Update collected numbers within player information header
                    String playerInfo = jlblIdentification.getText();
                    jlblIdentification.setText(playerInfo + " " + button.getText());

                    // Disable button
                    // Set color of button according to player's color
                    button.setBackground(playerID == 1 ? customBlue : customOrange);
                    button.setEnabled(false);

                    // Notify the server of the move and update numbers at server side
                    try {
                        getFifteenServer.myMove(Integer.parseInt(button.getText()), playerID);
                    }
                    catch (RemoteException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });

        return button;
    }

    // Set variable myTurn to true or false
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    // Set message on the status label
    public void setMessage(String message) {
        jlblStatus.setText(message);
    }

    // Mark the cell with the specific number as taken
    public void mark(int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int current = Integer.parseInt(board[i][j].getText());
                if (current == number) {
                    board[i][j].setBackground(playerID == 2 ? customBlue : customOrange);
                    board[i][j].setEnabled(false);
                }
            }
        }
    }

    // Method that colors whole board with the corresponding color:
    // GREEN - WINNER, RED - LOSER
    public void highlightNums(Color color) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setBackground(color);
            }
        }
    }

    // method main
    public static void main(String[] args) {
        GetFifteenClientRMI applet = new GetFifteenClientRMI();
        applet.isStandalone = true;
        applet.init();
        applet.start();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GetFifteenClientRMI");
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(450, 350);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}