import java.util.*;
public class Meta {
    //make some way to collect data on which list is consistently the best and worst to remove it
    public Meta(double[][] x, double[] y, int hidden){
        inp = x;
        inpNum = x[0].length;
        out = y;
        outNum = 1;
        hid = hidden;
        nn = new NeuralNetwork[10];
        for(int i = 0; i < nn.length; i++){
            nn[i] = new NeuralNetwork(inpNum, hid, 1);
        }
    }

    public NeuralNetwork calc(int time){
        for(int i = 0; i < time; i++){
            newMeta();
        }
        System.out.println(standev(best()));
        return best();
    }

    public void newMeta(){
        NeuralNetwork best = best();
        NeuralNetwork sec = secBest();
        NeuralNetwork worst = worst();
        nn[0] = best;
        nn[1] = sec;
        opp(worst.weights_ho);
        opp(worst.weights_ih);
        opp(worst.bias_h);
        opp(worst.bias_o);
        nn[2] = worst;
        //need functions for 3 to 7
        nn[3] = new NeuralNetwork(inpNum, hid, 1);
        nn[4] = new NeuralNetwork(inpNum, hid, 1);
        nn[5] = new NeuralNetwork(inpNum, hid, 1);
        nn[6] = new NeuralNetwork(inpNum, hid, 1);
        nn[7] = new NeuralNetwork(inpNum, hid, 1);
        //those 5 are temporary

        nn[8] = new NeuralNetwork(inpNum, hid, 1);
        nn[9] = new NeuralNetwork(inpNum, hid, 1);
    }

    public void opp(Matrix m){
        for(int i = 0; i < m.rows; i++){
            for(int j = 0; j < m.cols; j++){
                m.data[i][j] = -1 * m.data[i][j];
            }
        }
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

    public NeuralNetwork best(){
        double rec = standev(nn[0]);
        int ret = 0;
        for(int i = 1; i < nn.length; i++){
            if(standev(nn[i])<rec){
                rec = standev(nn[i]);
                ret = i;
            }
        }
        return nn[ret];
    }

    public NeuralNetwork secBest(){
        double best;
        double sec;
        int ret;
        int realRet;
        if(standev(nn[0])> standev(nn[1])){
            best = standev(nn[1]);
            sec = standev(nn[0]);
            ret = 1;
            realRet = 0;
        }else{
            best = standev(nn[0]);
            sec = standev(nn[1]);
            realRet = 1;
            ret = 0;
        }
        for(int i = 2; i < nn.length; i++){
            if(standev(nn[i])< best){
                sec = best;
                realRet = ret;
                best = standev(nn[i]);
                ret = i;
            }else if(standev(nn[i])<sec){
                realRet = i;
                sec = standev(nn[i]);
            }
        }
        return nn[realRet];
    }

    public NeuralNetwork worst(){
        double rec = standev(nn[0]);
        int ret = 0;
        for(int i = 1; i < nn.length; i++){
            if(standev(nn[i])>rec){
                rec = standev(nn[i]);
                ret = i;
            }
        }
        return nn[ret];
    }

    double[][] inp;
    int inpNum;
    double[] out;
    int outNum;
    int hid;
    private NeuralNetwork[] nn;
}
