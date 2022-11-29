#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "struct2.h"
// #include "datas.h"
int number = 1;
void datas(Book *b, SqList *s)
{
    Book *p1 = (Book *)malloc(sizeof(Book));
    p1->id = number++;
    strcpy(p1->name, "盗墓笔记");
    strcpy(p1->writer, "南派三叔");
    p1->price = 39.99;
    p1->next = NULL;
    s->head->next = p1;
    s->tail = p1;

    Book *p2 = (Book *)malloc(sizeof(Book));
    p2->id = number++;
    strcpy(p2->name, "将进酒");
    strcpy(p2->writer, "唐酒卿");
    p2->price = 98;
    p2->next = NULL;
    s->tail->next = p2;
    s->tail = p2;

    Book *p3 = (Book *)malloc(sizeof(Book));
    p3->id = number++;
    strcpy(p3->name, "镇魂");
    strcpy(p3->writer, "priest");
    p3->price = 49.8;
    p3->next = NULL;
    s->tail->next = p3;
    s->tail = p3;

    Book *p4 = (Book *)malloc(sizeof(Book));
    p4->id = number++;
    strcpy(p4->name, "灰塔笔记");
    strcpy(p4->writer, "空灯流远");
    p4->price = 31.7;
    p4->next = NULL;
    s->tail->next = p4;
    s->tail = p4;
}
void Add(Book *b, SqList *s) //添加图书
{
    int list = 1;
    char choose;

    while (1)
    {
        Book *p = (Book *)malloc(sizeof(Book));
        p->next = NULL;
        fflush(stdin);
        printf("请按照顺序输入书名、作者、价格:\n");
        gets(p->name);
        if (!repeat(p, s))
        {
            printf("该书籍已存在！！\n");
            printf("------------------------------------------------------------------------------------------------------------\n");
            continue;
        }
        gets(p->writer);
        scanf("%f", &(p->price));
        p->id = number++;
        if (s->head)
        {
            s->tail->next = p;
            s->tail = p;
        }
        else
        {
            s->head = s->tail = p;
        }
        printf("录入成功！！\n");
        printf("------------------------------------------------------------------------------------------------------------\n");
        while (1)
        {
            fflush(stdin);
            printf("是否继续添加书籍(Y/N)：\n");
            scanf("%c", &choose);
            fflush(stdin);
            printf("------------------------------------------------------------------------------------------------------------\n");
            if (choose == 'Y')
                break;
            else if (choose == 'N')
                return;
            else
                printf("输入错误，请重新输入！！\n");
        }
    }
}
void del(Book *b, SqList *s) //删除图书
{
    while (1)
    {

        Book *p2, *p1;
        char t[50];
        p1 = s->head;
        p2 = p1->next;

        printf("请输入你要删除的图书的名字：\n");
        scanf("%s", t);
        while (1)
        {
            if (p2 == NULL)
            {
                printf("未查询到”%s“的相关信息\n", t);
                break;
            }
            else if (strcmp(t, s->head->name) == 0)
            {
                s->head = s->head->next;
                free(p1);
                printf("删除成功\n");
                printf("------------------------------------------------------------------------------------------------------------\n");
                break;
            }
            else if (strcmp(t, p2->name) == 0)
            {
                p1->next = p2->next;
                free(p2);
                printf("删除成功\n");
                printf("------------------------------------------------------------------------------------------------------------\n");
                break;
            }
            p1 = p1->next;
            p2 = p1->next;
        }
        fflush(stdin);
        printf("是否继续删除(Y/N):\n");
        char choose;
        scanf("%c", &choose);
        if (choose == 'Y')
            continue;
        else if (choose == 'N')
            break;
        else
            printf("输入错误，请重新输入！！\n");
    }
}
void sreach(Book *b, SqList *s) //查询图书
{
    while (1)
    {
        int botton = 0;
        Book *p;
        p = s->head;
        char ch[50];
        fflush(stdin);
        printf("请输入你想查询的图书的名字：\n");
        scanf("%s", ch);
        while (p)
        {
            if (strcmp(ch, p->name) == 0)
            {
                printf("*ID\t\t\t\t*书名\t\t\t\t*作者\t\t\t\t*价格\n");
                printf("*%d\t\t\t\t*《%s》\t\t\t*%s\t\t\t\t*%.2f￥\n", p->id, p->name, p->writer, p->price);
                botton = 1;
                break;
            }
            p = p->next;
        }
        if (botton == 0)
            printf("没有查询到“%s”的相关信息\n", ch);
        p = NULL;
        fflush(stdin);
        printf("是否继续查询(Y/N):\n");
        char choose;
        scanf("%c", &choose);
        if (choose == 'Y')
            continue;
        else if (choose == 'N')
            break;
        else
            printf("输入错误，请重新输入！！\n");
    }
}
void watch(Book *b, SqList *s) //显示所有图书
{
    Book *p;
    p = s->head;
    printf("*ID\t\t\t\t*书名\t\t\t\t*作者\t\t\t\t*价格\n");
    do
    {
        printf("*%d\t\t\t\t*《%s》\t\t\t*%s\t\t\t\t*%.2f￥\n", p->id, p->name, p->writer, p->price);
    } while (p = p->next);

    printf("------------------------------------------------------------------------------------------------------------\n");
}
int repeat(Book *p, SqList *s) //检查是否有重复的书籍
{
    Book *t;
    t = s->head;
    if (t)
    {
        while (t)
        {
            if (!strcmp(p->name, t->name))
            {
                free(t);
                return 0;
            }

            t = t->next;
        }
    }
    else
    {
        free(t);
        return 1;
    }
    free(t);
    return 1;
}
int main()
{
    Book *b = (Book *)malloc(sizeof(Book));
    SqList *s = (SqList *)malloc(sizeof(SqList));
    b->id = number++;
    strcpy(b->name, "黑天");
    strcpy(b->writer, "木苏里");
    b->price = 38;
    b->next = NULL;
    s->head = s->tail = b;
    datas(b, s);
    int chance;

    printf("************************************************************************************************************\n");
    printf("*                                                                                                          *\n");
    printf("*                                           欢迎来到图书管理系统！                                         *\n");
    printf("*                                                                                                          *\n");
    printf("************************************************************************************************************\n");
    while (1)
    {
        printf("请输入你要进行操作的编号：\n1.添加图书\t\t2.删除图书\t\t3.查询图书\t\t4.查看所有书籍\t\t5.退出\n");
        scanf("%d", &chance);
        switch (chance)
        {
        case 1:
            Add(b, s);
            break;
        case 2:
            del(b, s);
            break;
        case 3:
            sreach(b, s);
            break;
        case 4:
        {
            watch(b, s);
            break;
        }
        case 5:
            printf("正在退出图书管理系统...\n感谢使用！！\n");
            printf("************************************************************************************************************\n");
            free(b);
            free(s);
            return;
        default:
            printf("你输出的指令有误！！\n请重新输入！！\n");
            break;
        }
    }
}
