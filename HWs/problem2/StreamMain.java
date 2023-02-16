package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aBc");
        list.add("d");
        list.add("ef");
        list.add("123456");

        List<String> l = list.stream()
                .map(x -> x.toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(l);
    }
}
