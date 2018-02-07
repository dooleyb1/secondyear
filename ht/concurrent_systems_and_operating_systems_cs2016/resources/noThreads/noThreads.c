#include <pthread.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <math.h>
#include <curses.h>

#define NUM_THREADS 6 
#define N 1000

//Purpose of this program is to perform integration of the function f(x) = 4 / 1 + x^2 over the integral
//from limits 0 to 1
int main (int argc, const char * argv[]) { 

	doIntegration();
	return 0;

}


float f(float x)
{
    return(4/(1+pow(x,2)));
}


void doIntegration()
{
    int i,n;
    float x0,xn,h,y[20],so,se,ans,x[20];
	
	x0 = 0;
	xn = 1;
	h = 0.1;
    n=(xn-x0)/h;

    if(n%2==1)
    {
        n=n+1;
    }

    h=(xn-x0)/n;	
	
	//Do integration by parts
    for(i=0; i<=n; i++)
    {
		printf("i = %i\n",i);
		printf("h = %f\n",h);
		x[i]=x0+i*h;
		printf("x[i] = %f\n",x[i]);
		y[i]=f(x[i]);
		printf("f(x[i]) = %f\n\n",y[i]);
    }

    so=0;
    se=0;

    for(i=1; i<n; i++)
    {
        if(i%2==1)
        {
			//Sum of odds
            so=so+y[i];
        }
        else
        {	
			//Sum of evens
            se=se+y[i];
        }
    }

    ans=h/3*(y[0]+y[n]+4*so+2*se);
    printf("\nfinal integration is %f \n",ans);
    getch();
}
