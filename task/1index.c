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
    strcpy(p1->name, "��Ĺ�ʼ�");
    strcpy(p1->writer, "��������");
    p1->price = 39.99;
    p1->next = NULL;
    s->head->next = p1;
    s->tail = p1;

    Book *p2 = (Book *)malloc(sizeof(Book));
    p2->id = number++;
    strcpy(p2->name, "������");
    strcpy(p2->writer, "�ƾ���");
    p2->price = 98;
    p2->next = NULL;
    s->tail->next = p2;
    s->tail = p2;

    Book *p3 = (Book *)malloc(sizeof(Book));
    p3->id = number++;
    strcpy(p3->name, "���");
    strcpy(p3->writer, "priest");
    p3->price = 49.8;
    p3->next = NULL;
    s->tail->next = p3;
    s->tail = p3;

    Book *p4 = (Book *)malloc(sizeof(Book));
    p4->id = number++;
    strcpy(p4->name, "�����ʼ�");
    strcpy(p4->writer, "�յ���Զ");
    p4->price = 31.7;
    p4->next = NULL;
    s->tail->next = p4;
    s->tail = p4;
}
void Add(Book *b, SqList *s) //���ͼ��
{
    int list = 1;
    char choose;

    while (1)
    {
        Book *p = (Book *)malloc(sizeof(Book));
        p->next = NULL;
        fflush(stdin);
        printf("�밴��˳���������������ߡ��۸�:\n");
        gets(p->name);
        if (!repeat(p, s))
        {
            printf("���鼮�Ѵ��ڣ���\n");
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
        printf("¼��ɹ�����\n");
        printf("------------------------------------------------------------------------------------------------------------\n");
        while (1)
        {
            fflush(stdin);
            printf("�Ƿ��������鼮(Y/N)��\n");
            scanf("%c", &choose);
            fflush(stdin);
            printf("------------------------------------------------------------------------------------------------------------\n");
            if (choose == 'Y')
                break;
            else if (choose == 'N')
                return;
            else
                printf("����������������룡��\n");
        }
    }
}
void del(Book *b, SqList *s) //ɾ��ͼ��
{
    while (1)
    {

        Book *p2, *p1;
        char t[50];
        p1 = s->head;
        p2 = p1->next;

        printf("��������Ҫɾ����ͼ������֣�\n");
        scanf("%s", t);
        while (1)
        {
            if (p2 == NULL)
            {
                printf("δ��ѯ����%s���������Ϣ\n", t);
                break;
            }
            else if (strcmp(t, s->head->name) == 0)
            {
                s->head = s->head->next;
                free(p1);
                printf("ɾ���ɹ�\n");
                printf("------------------------------------------------------------------------------------------------------------\n");
                break;
            }
            else if (strcmp(t, p2->name) == 0)
            {
                p1->next = p2->next;
                free(p2);
                printf("ɾ���ɹ�\n");
                printf("------------------------------------------------------------------------------------------------------------\n");
                break;
            }
            p1 = p1->next;
            p2 = p1->next;
        }
        fflush(stdin);
        printf("�Ƿ����ɾ��(Y/N):\n");
        char choose;
        scanf("%c", &choose);
        if (choose == 'Y')
            continue;
        else if (choose == 'N')
            break;
        else
            printf("����������������룡��\n");
    }
}
void sreach(Book *b, SqList *s) //��ѯͼ��
{
    while (1)
    {
        int botton = 0;
        Book *p;
        p = s->head;
        char ch[50];
        fflush(stdin);
        printf("�����������ѯ��ͼ������֣�\n");
        scanf("%s", ch);
        while (p)
        {
            if (strcmp(ch, p->name) == 0)
            {
                printf("*ID\t\t\t\t*����\t\t\t\t*����\t\t\t\t*�۸�\n");
                printf("*%d\t\t\t\t*��%s��\t\t\t*%s\t\t\t\t*%.2f��\n", p->id, p->name, p->writer, p->price);
                botton = 1;
                break;
            }
            p = p->next;
        }
        if (botton == 0)
            printf("û�в�ѯ����%s���������Ϣ\n", ch);
        p = NULL;
        fflush(stdin);
        printf("�Ƿ������ѯ(Y/N):\n");
        char choose;
        scanf("%c", &choose);
        if (choose == 'Y')
            continue;
        else if (choose == 'N')
            break;
        else
            printf("����������������룡��\n");
    }
}
void watch(Book *b, SqList *s) //��ʾ����ͼ��
{
    Book *p;
    p = s->head;
    printf("*ID\t\t\t\t*����\t\t\t\t*����\t\t\t\t*�۸�\n");
    do
    {
        printf("*%d\t\t\t\t*��%s��\t\t\t*%s\t\t\t\t*%.2f��\n", p->id, p->name, p->writer, p->price);
    } while (p = p->next);

    printf("------------------------------------------------------------------------------------------------------------\n");
}
int repeat(Book *p, SqList *s) //����Ƿ����ظ����鼮
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
    strcpy(b->name, "����");
    strcpy(b->writer, "ľ����");
    b->price = 38;
    b->next = NULL;
    s->head = s->tail = b;
    datas(b, s);
    int chance;

    printf("************************************************************************************************************\n");
    printf("*                                                                                                          *\n");
    printf("*                                           ��ӭ����ͼ�����ϵͳ��                                         *\n");
    printf("*                                                                                                          *\n");
    printf("************************************************************************************************************\n");
    while (1)
    {
        printf("��������Ҫ���в����ı�ţ�\n1.���ͼ��\t\t2.ɾ��ͼ��\t\t3.��ѯͼ��\t\t4.�鿴�����鼮\t\t5.�˳�\n");
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
            printf("�����˳�ͼ�����ϵͳ...\n��лʹ�ã���\n");
            printf("************************************************************************************************************\n");
            free(b);
            free(s);
            return;
        default:
            printf("�������ָ�����󣡣�\n���������룡��\n");
            break;
        }
    }
}
