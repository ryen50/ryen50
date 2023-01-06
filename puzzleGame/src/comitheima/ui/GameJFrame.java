package comitheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener
{
    //创建二维数组，管理数据便于打乱数字
    int [][]arr2=new int[3][3];
    int [][]win={
            {7,6,5},
            {4,3,2},
            {1,0,8}
    };
    int step=0;
    int blankX=0;
    int blankY=0;
    String path="puzzleGame\\image\\";
//    String imageName="";
    //创建选项界面的条目对象
    JMenu changeImage=new JMenu("更换图片");
    JMenuItem girl=new JMenuItem("美女");
    JMenuItem animals=new JMenuItem("动物");
    JMenuItem sports=new JMenuItem("运动");
    JMenuItem replayItem=new JMenuItem("重新游戏");
    JMenuItem reLoginItem=new JMenuItem("重新登录");
    JMenuItem closeItem=new JMenuItem("关闭游戏");
    JMenuItem accountItem=new JMenuItem("关于我们");
    public GameJFrame(){
        //设置界面的宽高
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数值
        initData();

        //初始化图片
        initImage();


        //使界面显示出来，写在最后面
        this.setVisible(true);
    }

    private boolean getWin() {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (win[i][j]!=arr2[i][j])
                {
                    return false;
                }
            }
        }
        return true;


    }

    private void initData() {
        int []arr = {0,1, 2, 3, 4, 5, 6, 7, 8};
        Random r=new Random();
        for (int i = 0; i < arr.length; i++) {
            int chance=r.nextInt(arr.length);
            int temp=arr[i];
            arr[i]=arr[chance];
            arr[chance]=temp;
        }


        for (int i = 0; i < arr.length; i++)
        {
            arr2[i/3][i%3]=arr[i];
            if (arr2[i/3][i%3]==8)
            {
                blankX=i/3;
                blankY=i%3;
            }
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();
        if (getWin()){
            JLabel end=new JLabel(new ImageIcon(path+"game over.png"));
            end.setLayout(null);
            end.setBounds(120,240,447,298);
            end.setBorder(new BevelBorder(BevelBorder.LOWERED));
            this.getContentPane().add(end);
            System.out.println("通关成功！");
        }
        JLabel countStep=new JLabel("步数:"+step);
        countStep.setBounds(33,12,120,30);
        this.getContentPane().add(countStep);
        //清除图片
        //创建一个图片imageICone的对象
        //创建一个JLabel
        for (int i=0;i<9;i++)
        {
            JLabel jLabel1=new JLabel(new ImageIcon(path+(arr2[i/3][i%3]+1)+".jpg"));
            jLabel1.setBounds(i%3*216+33,i/3*219+44,216,219);
            jLabel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
            this.getContentPane().add(jLabel1);
        }
//        JLabel background=new JLabel(new ImageIcon("puzzleGame\\image\\background2.png"));
//        background.setBounds(20,10,779,546);
//        this.getContentPane().add(background);
        //刷新界面
        this.getContentPane().repaint();
    }


    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar jMenuBar=new JMenuBar();

        //创建菜单上面的两个选项的对象(功能 关于我们）
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于我们");

        //添加包含项
        changeImage.add(girl);
        changeImage.add(animals);
        changeImage.add(sports);

        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //添加动作监听
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        girl.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);

        accountItem.addActionListener(this);
        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
        this.setLayout(null);
    }

    private void initJFrame() {
        this.setSize(733,800);
        //设置界面的标题
        this.setTitle("拼图单机版1.0");
        //设置界面置顶
//        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //添加键盘监听
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if (code==65){//显示原图
            this.getContentPane().removeAll();
            JLabel all=new JLabel(new ImageIcon(path+"ga.png"));
            all.setBounds(33,44,1000,1000);
            this.getContentPane().add(all);
            this.getContentPane().repaint();
            System.out.println("开始");
        } else if (code==82) {//重新游戏
            step=0;
            initData();
            initImage();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        if (getWin()){
            return;
        }
        int code=e.getKeyCode();
        System.out.println(code);
        if (code==37&&blankY!=2)
        {
            change(blankX,blankY,blankX,(blankY+1));
            step++;
            System.out.println("向左移动");
        }else if (code==38&&blankX!=2){
            change(blankX,blankY,(blankX+1),blankY);
            step++;
            System.out.println("向上移动");
        }else if (code==39&&blankY!=0) {
            change(blankX,blankY,blankX,(blankY-1));
            step++;
            System.out.println("向右移动");
        }else if (code==40&&blankX!=0) {
            change(blankX, blankY, (blankX - 1), blankY);
            step++;
            System.out.println("向下移动");
        } else if (code==65) {//查看原图
            System.out.println("还原");
        } else if (code==87) {//直接获胜
            arr2=new int[][]{{7,6,5}, {4,3,2}, {1,0,8}};
        }else{//校验
            System.out.println("错误指令");
            return;
        }
        initImage();
    }

    private void change(int x, int y,int actx,int acty) {
        int temp=arr2[x][y];
        arr2[x][y]=arr2[actx][acty];
        arr2[actx][acty]=temp;
        blankX=actx;
        blankY=acty;
    }//交换图片

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if (obj==replayItem){
            step=0;
            initData();
            initImage();
        }else if(obj==reLoginItem){
            this.setVisible(false);
            new LoginJFrame();
            System.out.println("重新登录");
        }else if (obj==closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        } else if (obj==accountItem) {
            System.out.println("关于我们");
            JDialog jDialog=new JDialog();
            jDialog.setTitle("这里是小小的操作攻略");
            JLabel text=new JLabel("呃呃呃，别瞅了");
            text.setBounds(100,30,100,200);
            jDialog.setSize(300,200);
            jDialog.getContentPane().add(text);
            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }


    }
}
