public class SeriesSum
{
   public static void main(String[] args)
   {
   Scanner sc = new Scanner(System.in);
   float sum=0.0 ;
   int i,n , c=1;

  System.out.print("enter n value for series ");
  n=sc.nextInt();

  for(i=1;i<n;i++)
  { 
  sum = sum +  ( (float)(pow(c,2)) / (float)(pow(c,3)) );
 		c+=2;
   }

   cout<<" Sum of the series :"<<sum;
   }
