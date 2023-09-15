//复杂度O(m*n^3)
#include<algorithm>
#include<cmath>
#include<cstdlib>
#include<cstdio>
#include<iostream> 
#include<cstring>
#include<vector>
#define size 200
int num; //输入几组数据 
int row; //行数
int column; //列数
int count; //交换次数
int min;
int a[size][size]; //目标矩阵
int b[size][size]; //初始矩阵
int c[size][size]; //临时存放矩阵
int found; //初始到最终是否有交换
void trans_row(int x) // 第x行取反 
{
	int i;
	for (i = 0;i<column; i++) 
		b[x][i] = b[x][i]^1; // 异或取反 
	count++;
}
void trans_column(int x, int y) // 交换x和y列 
{
	int i;
	int temp;
	for(i = 0; i < row; i++)
	{
		temp=b[i][x];
		b[i][x]=b[i][y];
		b[i][y]=temp;
	}
	if (x != y) 
		count++;
}
int is_same(int x, int y) //比较x和y列是否相同 
{
	int i;
	for(i = 0; i <row; i++)
		if (a[i][x] != b[i][y])
			return 0;
	return 1;
}
void copy(int a[size][size], int b[size][size]) // 拷贝数组 
{
	int i,j;
	for (i = 0; i <row; i++)
		for (j = 0; j < column; j++)
			a[i][j] = b[i][j];
}
int main()
{
	int i,j,k,p;
	int exchgmin[size];
	scanf("%d",&num);
	for(i=0;i<num;i++)
	{
		scanf("%d",&row);
		scanf("%d",&column);
		for(j=0;j<row;j++)
			for(k=0;k<column;k++)
				scanf("%d",&a[j][k]);
		for(j=0;j<row;j++)
			for(k=0;k<column;k++)
				scanf("%d",&b[j][k]);
		copy(c,b); //保护初始矩阵b 
		min=row+column+1;
		for(j=0;j<column;j++)
		{
			copy(b,c); //恢复初始数组b 
			count=0;  //交换次数清零 
			trans_column(0,j); //把每一列都假设成为第一列的目标状态，穷举这column中情况 
			for(k=0;k<row;k++)
			{ //如果行不同，则将行转换 
				if(a[k][0]!=b[k][0])
				trans_row(k);
			}
			int lo[200],flag=0,tot=0;
			for(k=1;k<column;k++)
			{//穷举其他所有列，如果相同则交换，说明目标状态统一，否则找不到与该列相同，说明不可行 
				found=0;//记录是否找到相同列 
				tot=0;flag=0;//flag记录是否有交换后都相同的列 
				for(p=k;p<column;p++)
				{
					if(is_same(k,p))
					{
						found=1;
						if(p==k)
							{break;}
						lo[tot++]=p;
						if(is_same(p,k))//此步实现优先选择列一致的 
							{flag=1;trans_column(k,p);break;}
					}
				}
				if(!found)
					break;
				else if(!flag && tot)//没有交换后两列都符合的，则与第一次发现的列交换 
					trans_column(k,lo[0]);
			}
			if(found&&count<min) //如果可行，找出最小值 
				min=count; 
		}
		if(min<row+column+1) //如果交换次数比初始值小，将其保存为当前组的最小交换次数，否则不可实现交换 
			exchgmin[i]=min;
		else exchgmin[i]=-1;
	}
	for(i=0;i<num;i++)
	printf("%d\n",exchgmin[i]);
	return 0;
}
/*
1
4 3
1 0 1
0 0 0
1 1 0
1 0 1
1 0 1
1 1 1
0 1 1
1 0 1
*/
