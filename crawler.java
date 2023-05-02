import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class crawler {
    public static void main(String[] args) throws IOException {
        String familyNameNet="https://zhuanlan.zhihu.com/p/369213657";
        String boyNameNet="https://baike.pcbaby.com.cn/long/87557.html";
        String girlNameNet="https://jingyan.baidu.com/article/fea4511a40912af7bb912599.html";

        //创建储存数据的集合
        ArrayList<String> familyNameTempList = catchKeywords(webCrawler(familyNameNet,"UTF-8") ,"(.{4})(  )", 1);
        ArrayList<String> boyNameList = catchKeywords(webCrawler(boyNameNet,"GBK"), "(.{2})(、)", 1);
        ArrayList<String> girlNameList = catchKeywords(webCrawler(girlNameNet,"UTF-8"), "(.{2})(、)", 1);
        ArrayList<String> familyNameList=new ArrayList<>();

        familyNameTempList.remove(0);
        familyNameTempList.remove(0);

        familyNameTempList.forEach((str) ->{
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                familyNameList.add(aChar + "");
            }
        });

        //生成数据
        OutputNameData(createRandomName(familyNameList, boyNameList, girlNameList));

    }

    //将生成的数据输入到本地文件
    public static Boolean OutputNameData(ArrayList<String> name) throws FileNotFoundException {
        PrintStream printStream=new PrintStream("C:\\Users\\cx\\Desktop\\copy\\11\\22\\randomName.txt");
        int p=0;
        while (p++<name.size()-1)
        {
            printStream.println(name.get(p));
        }
        printStream.close();

        return false;
    }

    //生成数据 姓名-性别-年龄
    public  static ArrayList<String> createRandomName(ArrayList<String> familyNameList, ArrayList<String> boyNameList,ArrayList<String> girlNameList)
    {
        Random r=new Random();
        ArrayList<String> randomName=new ArrayList<>();
        int count=0;
        int nameNumber=150;

        while(count++<nameNumber)
        {
            StringBuilder sb=new StringBuilder();
            if (count%2==0)
                sb.append(familyNameList.get(r.nextInt(familyNameList.size())))
                        .append(girlNameList.get(r.nextInt(girlNameList.size()))).append("-女-").append(r.nextInt(20) + 20);
            else sb.append(familyNameList.get(r.nextInt(familyNameList.size())))
                    .append(boyNameList.get(r.nextInt(boyNameList.size()))).append("-男-").append(r.nextInt(20)+20);

            randomName.add(sb.toString());
        }
        return randomName;
    }

    //将网页中符合标准的数据爬取出来，注意编码类型
    public static ArrayList<String> catchKeywords(String str, String regex, int index)
    {
        ArrayList<String> familyNameList=new ArrayList<>();
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        while (matcher.find())
        {
            String group = matcher.group(index);
            familyNameList.add(group);
//            System.out.println(group);
        }

        return familyNameList;
    }
    //打开网页，将网页所有功能输入程序
    public static String webCrawler(String url,String charsetName) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        URL url1=new URL(url);

        URLConnection urlConnection = url1.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), Charset.forName(charsetName));
        int ch;
        while ((ch=inputStreamReader.read())!=-1)
        {
            stringBuilder.append((char) ch);
        }

        return stringBuilder.toString();
    }

}
