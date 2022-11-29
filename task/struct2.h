#ifndef cd
#define cd
typedef struct _book
{
    int id;
    char name[50];
    char writer[50];
    float price;
    struct _book *next;
} Book;
typedef struct
{
    Book *head;
    Book *tail;
} SqList;
#endif