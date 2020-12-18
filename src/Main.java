import java.util.*;
public class Main {
    static double[][] X= {
            {0,0},
            {1,0},
            {0,1},
            {1,1},
    };
    static double[] Y= {0,1,1,0};

    public static void main(String[] args) {
        Meta tester = new Meta(X, Y, 3);
        NeuralNetwork res = tester.calc(10000);
        for(int i = 0; i < X.length; i++){
            System.out.println(res.predict(X[i]));
        }







//        NeuralNetwork nn = new NeuralNetwork(2,3,1);
//
//
//        List<Double>output;
//
//        nn.fit(X, Y, 1000000);
//
//        for(double d[]:X)
//        {
//            output = nn.predict(d);
//            System.out.println(output.toString());
//        }

    }
}
