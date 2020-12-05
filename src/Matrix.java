import java.util.ArrayList;
import java.util.List;

class Matrix {
    double [][]data;
    int rows,cols;

    public Matrix(int rows,int cols) {
        data= new double[rows][cols];
        this.rows=rows;
        this.cols=cols;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                data[i][j]=Math.random()*2-1;
            }
        }
    }

    public void print()
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                System.out.print(this.data[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void add(int scaler)
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]+=scaler;
            }

        }
    }

    public void add(Matrix m)
    {
        if(cols!=m.cols || rows!=m.rows) {
            System.out.println("Shape Mismatch");
            return;
        }

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]+=m.data[i][j];
            }
        }
    }

    public static Matrix fromArray(double[]x)
    {
        Matrix poop = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++)
            poop.data[i][0]=x[i];
        return poop;

    }

    public List<Double> toArray() {
        List<Double> poop= new ArrayList<Double>()  ;

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                poop.add(data[i][j]);
            }
        }
        return poop;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix poop=new Matrix(a.rows,a.cols);
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                poop.data[i][j]=a.data[i][j]-b.data[i][j];
            }
        }
        return poop;
    }

    public static Matrix transpose(Matrix a) {
        Matrix poop=new Matrix(a.cols,a.rows);
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                poop.data[j][i]=a.data[i][j];
            }
        }
        return poop;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix poop=new Matrix(a.rows,b.cols);
        for(int i=0;i<poop.rows;i++)
        {
            for(int j=0;j<poop.cols;j++)
            {
                double sum=0;
                for(int k=0;k<a.cols;k++)
                {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                poop.data[i][j]=sum;
            }
        }
        return poop;
    }

    public void multiply(Matrix a) {
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                this.data[i][j]*=a.data[i][j];
            }
        }

    }

    public void multiply(double a) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]*=a;
            }
        }

    }

    public void sigmoid() {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
        }

    }

    public Matrix dsigmoid() {
        Matrix poop=new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                poop.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        }
        return poop;

    }
}