import java.util.Scanner;

public class matrix {
    public static void main(String[] args) {
        int row,col;

        Scanner sc=new Scanner(System.in);


        System.out.print("Input number of rows: ");
        row=sc.nextInt();
        System.out.print("Input number of columns: ");
        col=sc.nextInt();


        int a[][]=new int[row][col];
        int b[][]=new int[row][col];
        int c[][]=new int[row][col];


        System.out.println("Enter elements of matrix a:");
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {

                a[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter elements of matrix b:");
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                
                b[i][j]=sc.nextInt();
            }
        }


        System.out.println("Matrix a:");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(a[i][j] + "\t");
            }
            System.out.print("\n");
        }


        System.out.println("Matrix b:");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(b[i][j] + "\t");
            }
            System.out.print("\n");
        }


        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                c[i][j]=a[i][j] + b[i][j];
            }
        }


        System.out.println(":: Final Matrix:");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(c[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
