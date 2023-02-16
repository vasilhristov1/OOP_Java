package hw3;

public class RouteCipher {
    private int key;

    RouteCipher(int key) {
        setKey(key);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    // method to get the plain text in a grid
    private char[][] getTextGrid(String plainText) {
        char[] plainTextChars = plainText.toCharArray(); // the plain text represented by characters
        char[][] plainTextCharArr; // the plain text represented in grid
        int plainTextSize = 0; // the number of letters in the plain text
        double rows; // number of rows for the grid
        int cols = Math.abs(this.getKey()); // number of columns for the grid

        // count the number of letters in the plain text
        for (char plainTextChar : plainTextChars) {
            if ((plainTextChar >= 'A' && plainTextChar <= 'Z') || (plainTextChar >= 'a' && plainTextChar <= 'z')) {
                plainTextSize++;
            }
        }

        char[] plainTextChars_ = new char[plainTextSize]; // stores only the letter characters of the plain text

        int cnt = 0; // used to be checked not to get out of bounds

        // fill the plainTextChars_ with only the letters
        for (char plainTextChar : plainTextChars) {
            if ((plainTextChar >= 'A' && plainTextChar <= 'Z') || (plainTextChar >= 'a' && plainTextChar <= 'z')) {
                if (cnt > plainTextSize) {
                    plainTextChars_[cnt++] = 'x';
                } else {
                    plainTextChars_[cnt++] = plainTextChar;
                }
            }
        }

        // calculate how many rows should be there
        rows = plainTextSize / (double) cols;
        rows = Math.ceil(rows);

        plainTextCharArr = new char[(int) rows][cols]; // setting the size of the grid

        int itr = 0; // used to be checked not to get out of bounds

        // fills the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (itr < plainTextSize) {
                    plainTextCharArr[i][j] = plainTextChars_[itr];
                    itr++;
                } else {
                    plainTextCharArr[i][j] = 'x';
                }
            }
        }

        return plainTextCharArr;
    }

    // method to get the encrypted text
    private String getText(char[][] encryptedText) {
        String encrypted;

        if(encryptedText.length == 0|| encryptedText[0].length == 0){
            return null;
        }

        char[] encryptedStr = new char[encryptedText.length * encryptedText[0].length];
        int counter = encryptedText.length * encryptedText[0].length;

        int startRow;
        int endRow;
        int startCol;
        int endCol;
        boolean moveCol;
        boolean leftToRight;
        boolean upDown;
        int m = 0;

        if (this.getKey() > 0) {
            startRow = 0;
            endRow = encryptedText.length-1;
            startCol = 0;
            endCol = encryptedText[0].length-1;
            moveCol = false;
            leftToRight = true;
            upDown = true;

            createCipher(encryptedText, encryptedStr, counter, endRow, startRow, endCol, startCol, moveCol, leftToRight, upDown, m);
        } else if (this.getKey() < 0) {
            startRow = encryptedText.length-1;
            endRow = 0;
            startCol = encryptedText[0].length-1;
            endCol = 0;
            moveCol = false;
            leftToRight = false;
            upDown = false;

            createCipher(encryptedText, encryptedStr, counter, startRow, endRow, startCol, endCol, moveCol, leftToRight, upDown, m);
        } else {
            return null;
        }

        encrypted = new String(encryptedStr);

        return encrypted;
    }

    // help method for the getText method
    private void createCipher(char[][] encryptedText, char[] encryptedStr, int counter, int startRow, int endRow,
                              int startCol, int endCol, boolean moveCol, boolean leftToRight, boolean upDown, int m) {
        while(counter > 0) {

            if(moveCol){
                if(leftToRight){

                    /* printing entire row left to right */
                    for(int i = endCol; i <= startCol ; i++){
                        encryptedStr[m] = encryptedText[startRow][i];
                        counter--;
                        m++;
                    }
                    leftToRight = false;
                    moveCol = false;
                    startRow--;
                }
                else{

                    /* printing entire row right to left */
                    for(int i = startCol ; i >= endCol ; i--){
                        encryptedStr[m] = encryptedText[endRow][i];
                        counter--;
                        m++;
                    }
                    leftToRight = true;
                    moveCol = false;
                    endRow++;
                }
            }
            else
            {
                if(upDown){

                    /* printing column up down */
                    for(int i = endRow ; i <= startRow ; i++){
                        encryptedStr[m] = encryptedText[i][endCol];
                        counter--;
                        m++;
                    }
                    upDown = false;
                    moveCol = true;
                    endCol++;
                }
                else
                {

                    /* printing entire col down up */
                    for(int i = startRow ; i >= endRow ; i--){
                        encryptedStr[m] = encryptedText[i][startCol];
                        counter--;
                        m++;
                    }
                    upDown = true;
                    moveCol = true;
                    startCol--;
                }
            }
        }
    }

    public String encrypt(String plainText) {
        char[][] grid = getTextGrid(plainText);

        return getText(grid);
    }

    public String decrypt(String cipherText) {
        char[] cipherTextChars = cipherText.toCharArray();
        int cipherTextSize = cipherTextChars.length;
        int cols = Math.abs(this.getKey());
        int rows = cipherTextSize / cols;

        char[][] grid = new char[rows][cols];

        int count = 0;
        int startRow;
        int endRow;
        int startCol;
        int endCol;
        boolean moveCol;
        boolean leftToRight;
        boolean upDown;

        if (this.getKey() > 0) {
            startRow = 0;
            endRow = grid.length-1;
            startCol = 0;
            endCol = grid[0].length-1;
            moveCol = false;
            leftToRight = true;
            upDown = true;

            while (count < cipherTextSize) {
                if (moveCol) {
                    if (leftToRight) {
                        for(int i = startCol; i <= endCol ; i++){
                            grid[endRow][i] = cipherTextChars[count];
                            count++;
                        }

                        leftToRight = false;
                        moveCol = false;
                        endRow--;
                    } else {
                        for(int i = endCol ; i >= startCol ; i--){
                            grid[startRow][i] = cipherTextChars[count];
                            count++;
                        }

                        leftToRight = true;
                        moveCol = false;
                        startRow++;
                    }
                } else {
                    if (upDown) {
                        for (int j = startRow; j <= endRow; j++) {
                            grid[j][startCol] = cipherTextChars[count];
                            count++;
                        }

                        moveCol = true;
                        upDown = false;
                        startCol++;
                    } else {
                        for (int j = endRow; j >= startRow; j--) {
                            grid[j][endCol] = cipherTextChars[count];
                            count++;
                        }

                        moveCol = true;
                        upDown = true;
                        endCol--;
                    }
                }
            }
        } else if (this.getKey() < 0) {
            startRow = grid.length-1;
            endRow = 0;
            startCol = grid[0].length-1;
            endCol = 0;
            moveCol = false;
            leftToRight = false;
            upDown = false;

            while (count < cipherTextSize) {
                if (moveCol) {
                    if (leftToRight) {
                        for(int i = endCol; i <= startCol ; i++){
                            grid[startRow][i] = cipherTextChars[count];
                            count++;
                        }

                        leftToRight = false;
                        moveCol = false;
                        startRow--;
                    } else {
                        for(int i = startCol ; i >= endCol ; i--){
                            grid[endRow][i] = cipherTextChars[count];
                            count++;
                        }

                        leftToRight = true;
                        moveCol = false;
                        endRow++;
                    }
                } else {
                    if (upDown) {
                        for (int j = endRow; j <= startRow; j++) {
                            grid[j][endCol] = cipherTextChars[count];
                            count++;
                        }

                        moveCol = true;
                        upDown = false;
                        endCol++;
                    } else {
                        for (int j = startRow; j >= endRow; j--) {
                            grid[j][startCol] = cipherTextChars[count];
                            count++;
                        }

                        moveCol = true;
                        upDown = true;
                        startCol--;
                    }
                }
            }
        } else {
            return null;
        }

        char[] decrypted = new char[cipherTextSize];
        int cnt = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                decrypted[cnt] = grid[i][j];
                cnt++;
            }
        }

        return new String(decrypted);
    }

    @Override
    public String toString() {
        return String.format("The key for the cipher route is %d.", this.getKey());
    }
}
