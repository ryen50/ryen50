import java.io.*;
import java.util.Arrays;

public class practice13 {
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\cx\\Desktop\\copy\\11\\22\\dtaft2.txt");
        StringBuilder stringBuilder=new StringBuilder();

        FileReader fr=new FileReader(file);
        int temp;
        while ((temp=fr.read())!=-1) stringBuilder.append((char) temp);
        fr.close();
//        String[] split = stringBuilder.toString().split("-");
//        for (String s : split) {
//            System.out.println(s);
//        }
        Integer[] integers = Arrays.stream(stringBuilder.toString().split("-"))
                        .map(Integer::parseInt)
                        .sorted()
                        .toArray(Integer[]::new);

        String s=Arrays.toString(integers).replace(", ","-");
        String s2=s.substring(1,s.length()-1);


        FileWriter fw=new FileWriter(file);
        fw.write(s2);
        fw.close();
        fr.close();
    }
}
