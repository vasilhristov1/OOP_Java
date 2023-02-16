package hw7.problem2;

public class Main {
    public static void main(String[] args) {
        Writer writer = new Writer();

        writer.createFile("numbers.txt");
        writer.writeStr("Text\n");
        writer.writeStr("To\n");
        writer.writeStr("Be\n");
        writer.writeStr("Added\n");
        writer.writeToFile("numbers.txt");
    }
}
