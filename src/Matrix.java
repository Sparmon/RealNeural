import java.util.*;

public class Matrix {
    public double[][] data;
    public int rows;
    public int cols;

    public Matrix(int r, int c){
        rows = r;
        cols = c;
        data = new double[rows][cols];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                data[i][j] = (Math.random() * 2) - 1;
            }
        }
    }

    /*scaler*/public void add(double poop){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                data[i][j]+= poop;
            }
        }
    }

    /*matrices*/public void add(Matrix poop){
        if(poop.rows != rows || poop.cols != cols){
            System.out.println("Shape mismatch");
            return;
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                data[i][j]+=poop.data[i][j];
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b){
        Matrix ret = new Matrix(a.rows, a.cols);
        for(int i = 0; i < a.rows; i++){
            for(int j = 0; j < a.cols; j++){
                ret.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return ret;
    }

    //instead of transpose,just make new matrix name and set it equal to old one

    /*dot*/public static Matrix multiply(Matrix a, Matrix b){
        Matrix poop = new Matrix(a.rows, a.cols);
        for(int i = 0; i < a.rows; i++){
            for(int j = 0; j < a.cols; j++){
                double sum = 0;
                for(int k = 0; k < a.cols; k++){
                    sum+= a.data[i][k] * b.data[k][j];
                }
                poop.data[i][j] = sum;
            }
        }
        return poop;
    }

    /*element-wise*/public void multiply(Matrix a){
        for(int i = 0; i <rows; i++){
            for(int j = 0; j < cols; j++){
                data[i][j]*= a.data[i][j];
            }
        }
    }

    /*scalar multiplication*/public void multiply(double a) {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                data[i][j]*=a;
            }
        }
    }

    public void sigmoid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0;j< cols; j++){
                data[i][j] = 1/(1+Math.exp(-1*data[i][j]));
            }
        }
    }

    public Matrix dsigmoid(){
        Matrix poop = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                poop.data[i][j] = data[i][j] * (1-data[i][j]);
            }
        }
        return poop;
    }

    public static Matrix fromArray(double[] inp){
        Matrix temp = new Matrix(inp.length, 1);
        for(int i = 0; i < inp.length; i++){
            temp.data[i][0] = inp[i];
        }
        return temp;
    }

    public List<Double> toArray(){
        //consider making this into array later for ease
        List<Double> ret = new ArrayList<Double>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                ret.add(data[i][j]);
            }
        }
        return ret;
    }

    public void print(){
        System.out.println("Matrix: ");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
