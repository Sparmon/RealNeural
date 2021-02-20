import java.util.*;
public class Main {
    static double[][] X= {
            {0,0},
            {1,0},
            {0,1},
            {1,1},
    };
    static double[] Y= {0,0,1,1};

    public static void main(String[] args) {
        Trainer test = new Trainer(X,Y,100000,1);
        NeuralNetwork poop = new NeuralNetwork(2,4,1);
        test.train(poop);
//        double[] guess = {1,1,1};
//        System.out.println();
//        System.out.println(poop.predict(guess));







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
