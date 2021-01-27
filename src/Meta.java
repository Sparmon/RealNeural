//import java.util.*;
//public class Meta {
//    //make some way to collect data on which list is consistently the best and worst to remove it
//    //essentially this is luck of the draw
//    public Meta(double[][] x, double[] y, int hidden){
//        inp = x;
//        inpNum = x[0].length;
//        out = y;
//        outNum = 1;
//        hid = hidden;
//        nn = new NeuralNetwork[10];
//        for(int i = 0; i < nn.length; i++){
//            nn[i] = new NeuralNetwork(inpNum, hid, 1);
//        }
//    }
//
//    public NeuralNetwork calc(int time){
//        for(int i = 0; i < time; i++){
//            newMeta();
//        }
//        System.out.println(standev(best()));
//        return best();
//    }
//
//    public void newMeta(){
//        NeuralNetwork best = best();
//        NeuralNetwork sec = secBest();
//        NeuralNetwork worst = worst();
//        nn[0] = best;
//        nn[1] = sec;
//
//        //opposite of worst
//        opp(worst.weights_ho);
//        opp(worst.weights_ih);
//        opp(worst.bias_h);
//        opp(worst.bias_o);
//        nn[2] = worst;
//
//        //first half best, second half second best
//        nn[3].weights_ih = best.weights_ih;
//        nn[3].bias_h = best.bias_h;
//        nn[3].weights_ho = sec.weights_ho;
//        nn[3].bias_o = sec.bias_o;
//
//        //first half second best, second half best
//        nn[4].weights_ih = sec.weights_ih;
//        nn[4].bias_h = sec.bias_h;
//        nn[4].weights_ho = best.weights_ho;
//        nn[4].bias_o = best.bias_o;
//
//        //makes slight and larger changes (respectively) to the best neural network
//        nn[5] = semiRep(best, 0.05);
//        nn[6] = semiRep(best, 0.2);
//
//        int rand = (int)(Math.random() * inp.length);
//        best.train(inp[rand], toArr(out[rand]));
//        nn[7] = best;
//
//        nn[8] = new NeuralNetwork(inpNum, hid, 1);
//        nn[9] = new NeuralNetwork(inpNum, hid, 1);
//    }
//
//    public double[] toArr(double i){
//        double[] ret = {i};
//        return ret;
//    }
//
//    public NeuralNetwork semiRep(NeuralNetwork poop, double lRate){
//        for(int i = 0; i < poop.weights_ih.rows; i++){
//            for(int j = 0; j < poop.weights_ih.cols; j++){
//                double add = ((Math.random()-0.5)*lRate);
//                poop.weights_ih.data[i][j] = poop.weights_ih.data[i][j] + add;
//            }
//        }
//        poop.weights_ih.sigmoid();
//
//        for(int i = 0; i < poop.weights_ho.rows; i++){
//            for(int j = 0; j < poop.weights_ho.cols; j++){
//                double add = ((Math.random()-0.5)*lRate);
//                poop.weights_ho.data[i][j] = poop.weights_ho.data[i][j] + add;
//            }
//        }
//        poop.weights_ho.sigmoid();
//
//        for(int i = 0; i < poop.bias_h.rows; i++){
//            for(int j = 0; j < poop.bias_h.cols; j++){
//                double add = ((Math.random()-0.5)*lRate);
//                poop.bias_h.data[i][j] = poop.bias_h.data[i][j] + add;
//            }
//        }
//        poop.bias_h.sigmoid();
//
//        for(int i = 0; i < poop.bias_o.rows; i++){
//            for(int j = 0; j < poop.bias_o.cols; j++){
//                double add = ((Math.random()-0.5)*lRate);
//                poop.bias_o.data[i][j] = poop.bias_o.data[i][j] + add;
//            }
//        }
//        poop.bias_o.sigmoid();
//        return poop;
//    }
//
//    public void opp(Matrix m){
//        for(int i = 0; i < m.rows; i++){
//            for(int j = 0; j < m.cols; j++){
//                m.data[i][j] = -1 * m.data[i][j];
//            }
//        }
//    }
//
//    public double standev(NeuralNetwork poop){
//        double sum = 0;
//        for(int i = 0; i < inp.length; i++){
//                double diff = (out[i] - poop.predict(inp[i]));
//                double squared = diff * diff;
//                if(squared > 0.25){
//                    sum+=0.3; //makes it so those on opposite side of 0 have consequences
//                }
//                sum+= squared;
//        }
//        double sqrt = Math.sqrt(sum);
//        return (sum/inp.length);
//    }
//
//    public NeuralNetwork best(){
//        double rec = standev(nn[0]);
//        int ret = 0;
//        for(int i = 1; i < nn.length; i++){
//            if(standev(nn[i])<rec){
//                rec = standev(nn[i]);
//                ret = i;
//            }
//        }
//        return nn[ret];
//    }
//
//    public NeuralNetwork secBest(){
//        double best;
//        double sec;
//        int ret;
//        int realRet;
//        if(standev(nn[0])> standev(nn[1])){
//            best = standev(nn[1]);
//            sec = standev(nn[0]);
//            ret = 1;
//            realRet = 0;
//        }else{
//            best = standev(nn[0]);
//            sec = standev(nn[1]);
//            realRet = 1;
//            ret = 0;
//        }
//        for(int i = 2; i < nn.length; i++){
//            if(standev(nn[i])< best){
//                sec = best;
//                realRet = ret;
//                best = standev(nn[i]);
//                ret = i;
//            }else if(standev(nn[i])<sec){
//                realRet = i;
//                sec = standev(nn[i]);
//            }
//        }
//        return nn[realRet];
//    }
//
//    public NeuralNetwork worst(){
//        double rec = standev(nn[0]);
//        int ret = 0;
//        for(int i = 1; i < nn.length; i++){
//            if(standev(nn[i])>rec){
//                rec = standev(nn[i]);
//                ret = i;
//            }
//        }
//        return nn[ret];
//    }
//
//    double[][] inp;
//    int inpNum;
//    double[] out;
//    int outNum;
//    int hid;
//    private NeuralNetwork[] nn;
//}
