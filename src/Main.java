import java.util.*;
public class Main {
    static double[][] X= {
            {0,0},
            {1,0},
            {0,1},
            {1,1},
    };
    static double[][] Y= {
            {0},{1},{1},{0}
    };

    public static void main(String[] args) {

        NeuralNetwork nn = new NeuralNetwork(2,3,1);


        List<Double>output;

        nn.fit(X, Y, 1000000);

        for(double d[]:X)
        {
            output = nn.predict(d);
            System.out.println(output.toString());
        }

//        System.out.println("Weights_ih:");
//        nn.weights_ih.print();
//        System.out.println();
//        System.out.println("Weights_ho:");
//        nn.weights_ho.print();
//        System.out.println();
//        System.out.println("bias_h:");
//        nn.bias_h.print();
//        System.out.println();
//        System.out.println("bias_o:");
//        nn.bias_o.print();
//        System.out.println();

    }
}
