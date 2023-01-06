package comitheima.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class LoginJFrame extends JFrame  {
    //添加静态账号密码
    static  ArrayList<User> userList=new ArrayList<>();
    static {
        User user1=new User("zhangsan","13121516");
        User user2=new User("woshilisi","13121516");
        userList.add(user1);
        userList.add(user2);
    }
    //设置相关按钮
    JButton login=new JButton("登录");
    JButton register=new JButton("注册");
    public LoginJFrame(){
        //初始化登录界面
        initLoginJf();
        //初始化图片和相关按钮，输入框
        initDetails();


        this.setVisible(true);
    }

    private void initDetails() {
        //创建两个输入框
        JTextField userName=new JFormattedTextField();
        JPasswordField password=new JPasswordField();

        //创建文字
        JLabel userNameText=new JLabel("用 户 名：");
        JLabel passwordText=new JLabel("密    码：");

        userNameText.setForeground(Color.white);
        passwordText.setForeground(Color.white);

        //设置文字位置

        userNameText.setBounds(93,120,60,25);
        passwordText.setBounds(93,220,60,25);

        this.getContentPane().add(userNameText);
        this.getContentPane().add(passwordText);

        login.setBounds(90,270,90,50);
        register.setBounds(290,270,90,50);

        //添加动作监听
        login.addActionListener(e -> {
            //检验是否为用户名和密码是否为空
            if (isEmpty(userName.getText())||isEmpty(String.valueOf(password.getPassword()))){
                JDialog passwordError=new JDialog();
                passwordError.setTitle("警告");

                //加入弹窗内容
                JLabel errorText=new JLabel("用户名和密码不能为空");
                errorText.setBounds(0,0,100,50);
                passwordError.getContentPane().add(errorText);

                passwordError.setSize(200,100);
                passwordError.setLocationRelativeTo(null);
                passwordError.setAlwaysOnTop(true);
                passwordError.setModal(true);

                passwordError.setVisible(true);
                return;
            }

            //校验验证码
            if (checkTest()) {
                //校验用户名
                if (userTest(userName.getText())) {
                    //校验密码
                    if (passwordTest(String.valueOf(password.getPassword()))) {
                        //进入游戏界面
                        new GameJFrame();
                    }
                }
            }
        });
        register.addActionListener(e -> {

        });

        this.getContentPane().add(login);
        this.getContentPane().add(register);

        //设置输入框的位置大小

        userName.setBounds(150,120,230,25);
        password.setBounds(155,220,230,25);


        this.getContentPane().add(userName);
        this.getContentPane().add(password);
        //添加背景图片
        JLabel loginBack=new JLabel(new ImageIcon("puzzleGame\\image\\background4.png"));
        loginBack.setBounds(0,0,468,431);
        this.getContentPane().add(loginBack);

    }

    private boolean isEmpty(String text) {
        if (text.equals("")){
            return true;
        }
        return false;
    }

    private boolean passwordTest(String passwordScan) {
        for (int i = 0; i < userList.size(); i++) {
            if (passwordScan.equalsIgnoreCase(userList.get(i).getPassword())){
                System.out.println("密码正确");
                return true;
            }else {
                System.out.println("密码错误");
                //创建提示弹窗
                JDialog passwordError=new JDialog();
                passwordError.setTitle("警告");

                //加入弹窗内容
                JLabel errorText=new JLabel("密码错误");
                errorText.setBounds(0,0,100,50);
                passwordError.getContentPane().add(errorText);

                passwordError.setSize(200,100);
                passwordError.setLocationRelativeTo(null);
                passwordError.setAlwaysOnTop(true);
                passwordError.setModal(true);

                passwordError.setVisible(true);
                return false;
            }
        }
        return false;
    }

    private void initLoginJf() {
        this.setSize(483,470);
        this.setTitle("登录入口");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    private boolean userTest(String userScan) {
        for (int i = 0; i < userList.size(); i++) {
            if (userScan.equals(userList.get(i).getUserName())){
                System.out.println("用户名正确");
                return true;
            }else {
                System.out.println("用户名错误");
                //创建提示弹窗
                JDialog userError=new JDialog();
                userError.setTitle("警告");

                //加入弹窗内容
                JLabel errorText=new JLabel("用户名错误");
                errorText.setBounds(0,0,100,50);
                userError.getContentPane().add(errorText);

                userError.setSize(200,100);
                userError.setLocationRelativeTo(null);
                userError.setAlwaysOnTop(true);
                userError.setModal(true);

                userError.setVisible(true);
                return false;
            }
        }
        return false;
    }

    private boolean checkTest() {

        //定义一个开关
        final boolean[] button = {false};

        String checkWord=createCheckPass();

        //添加验证码弹窗
        JDialog checkButton=new JDialog();
        checkButton.setTitle("请输入验证码");

        //添加内容
        JLabel checkText=new JLabel(checkWord);
        checkText.setBounds(20,30,60,25);
        checkButton.getContentPane().add(checkText);

        //添加验证码输入框
        JTextField checkPass=new JTextField();
        checkPass.setBounds(70,30,150,25);
        checkButton.getContentPane().add(checkPass);

        //添加确认按钮
        JButton toSure=new JButton("确认");
        toSure.setBounds(105,70,65,40);
        toSure.addActionListener(e -> {
            if (checkPass.getText().equalsIgnoreCase(checkWord)){
                System.out.println("验证成功");
                checkButton.dispose();
                button[0] =true;
            } else  {
                //验证码输入错误的提示弹窗
                JDialog checkError=new JDialog();
                checkError.setTitle("警告");
                checkError.setBounds(10,10,100,120);

                //写入提示
                JLabel checkTip=new JLabel("验证码输入错误");
                checkTip.setSize(100,50);
                checkError.getContentPane().add(checkTip);

                checkError.setLocationRelativeTo(null);
                checkError.setAlwaysOnTop(true);
                checkError.setModal(true);
                checkError.setVisible(true);
            }
        });
        checkButton.getContentPane().add(toSure);

        //添加背景图片
        JLabel checkBackground=new JLabel(new ImageIcon("puzzleGame\\image\\background5.png"));
        checkBackground.setBounds(0,0,390,132);
        checkButton.getContentPane().add(checkBackground);
        //页面初始化
        checkButton.setSize(300,150);
        checkButton.setLocationRelativeTo(null);
        checkButton.setAlwaysOnTop(true);
        checkButton.setModal(true);

        checkButton.setVisible(true);
        return button[0];
    }

    //生成验证码
    private String createCheckPass() {
        int index=0;
        char[] arr=new char[62];
        for (int i=48;i<58;i++)
            arr[index++]=(char) i;
        for (int i=65,j=97;i<=90;i++,j++){
            arr[index++]=(char) i;
            arr[index+25]=(char) j;
        }
        Random r=new Random();
        StringBuilder checkPass=new StringBuilder();
        for (int i=0;i<5;i++)
        {
            int temp=r.nextInt(61);
            checkPass.append(arr[temp]);
        }
        return checkPass.toString();
    }
}
