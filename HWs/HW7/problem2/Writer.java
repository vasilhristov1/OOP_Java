package hw7.problem2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private String strToBeWritten;

    public Writer() {
        this.strToBeWritten = "";
    }

    public void createFile(String name) {
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String pathToFile) {
        try {
            FileWriter myWriter = new FileWriter(pathToFile);
            myWriter.write(strToBeWritten);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeStr(String entered) {
        this.strToBeWritten = this.strToBeWritten + entered;
    }
}
