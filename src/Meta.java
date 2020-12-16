import java.util.*;
public class Meta {
    //make some way to collect data on which list is consistently the best and worst to remove it
    public Meta(double[][] x, double[] y, int hidden){
        inp = x;
        inpNum = x[0].length;
        out = y;
        outNum = 1;
        hid = hidden;
        n1 = new NeuralNetwork(inpNum, hid, outNum);
        n2 = new NeuralNetwork(inpNum, hid, outNum);
        n3 = new NeuralNetwork(inpNum, hid, outNum);
        n4 = new NeuralNetwork(inpNum, hid, outNum);
        n5 = new NeuralNetwork(inpNum, hid, outNum);
        n6 = new NeuralNetwork(inpNum, hid, outNum);
        n7 = new NeuralNetwork(inpNum, hid, outNum);
        n8 = new NeuralNetwork(inpNum, hid, outNum);
        n9 = new NeuralNetwork(inpNum, hid, outNum);
        n10 = new NeuralNetwork(inpNum, hid, outNum);
        //testing that standev works (it does)
        System.out.println("ex 1:");
        System.out.println(n1.predict(inp[0]));
        System.out.println("ex 2:");
        System.out.println(n1.predict(inp[1]));
        System.out.println("ex 3:");
        System.out.println(n1.predict(inp[2]));
        System.out.println("ex 4:");
        System.out.println(n1.predict(inp[3]));
        System.out.println("standev");
        System.out.println(standev(n1));

    }

    public NeuralNetwork calc(int time){
        //for loop
        //says how many times to go through and returns best at end
    }

    public double standev(NeuralNetwork poop){
        double sum = 0;
        for(int i = 0; i < inp.length; i++){
                double diff = (out[i] - poop.predict(inp[i]));
                double squared = diff * diff;
                sum+= squared;
        }
        double sqrt = Math.sqrt(sum);
        return (sum/inp.length);
    }

    public void setStanDevArray(){

    }

    public NeuralNetwork best(){

    }

    double[][] inp;
    int inpNum;
    double[] out;
    int outNum;
    int hid;
    private NeuralNetwork n1;
    private NeuralNetwork n2;
    private NeuralNetwork n3;
    private NeuralNetwork n4;
    private NeuralNetwork n5;
    private NeuralNetwork n6;
    private NeuralNetwork n7;
    private NeuralNetwork n8;
    private NeuralNetwork n9;
    private NeuralNetwork n10;
    private double[] standevs;
}
