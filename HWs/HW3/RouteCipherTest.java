package hw3;

public class RouteCipherTest {
    public static void main(String[] args) {
        RouteCipher rc1 = new RouteCipher(5);
        RouteCipher rc2 = new RouteCipher(-4);
        String s = rc1.encrypt("abort the mission, you have been spotted");
        System.out.println(s);
        String l = rc1.decrypt("atsyvntedxxteanitrobhsoespoehomeiub");
        System.out.println(l);
        String t = rc2.encrypt("THISISTHEPLAINTEXT");
        System.out.println(t);
        String c = rc2.decrypt("xEAHSIHTIEIXTxTLTSPN");
        System.out.println(c);
        System.out.println(rc1);
        System.out.println(rc2);
    }
}
