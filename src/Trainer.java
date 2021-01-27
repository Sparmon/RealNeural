public class Trainer {
    //generalize after for more nodes of input and hidden
    public double[][] inp;
    public double[] out;
    public int time;
    public double lrate;

    public Trainer(double[][] X, double[] Y, int cycs, double learn){
        inp = X;
        out = Y;
        time = cycs;
        lrate = learn;
    }

    public void train(NeuralNetwork poop){
        for(int i = 0; i < time; i++) {
            Matrix bias_oGrad = bias_oGradCalc(poop);
            bias_oGrad.multiply(lrate);
            bias_oGrad.print();

            Matrix weights_hoGrad = weights_hoGradCalc(poop);
            weights_hoGrad.multiply(lrate);
            weights_hoGrad.print();

            //calc gradient for input-hiden layer (bias and weights)
            //add changes at end
        }
    }

    public Matrix weights_hoGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[4];
        for(int i = 0; i < 4; i++){
            Matrix alm1 = Matrix.transpose(poop.aLm1(inp[i]));
            Matrix dsig = poop.zL(inp[i]).dsigmoid();
            Matrix ret = Matrix.multiply(dsig, alm1);
            double temp = out[i] - poop.predict(inp[i]);
            for(int j = 0; j < ret.rows; j++){
                for(int k = 0; k < ret.cols; k++){
                    ret.data[j][k] = temp/ret.data[j][k];
                }
            }
            listy[i] = ret;
        }
        return (avgMatrix(listy));
    }

    public Matrix bias_oGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[4];
        for(int i = 0; i < 4; i++){
            Matrix dsig = poop.zL(inp[i]).dsigmoid();
            dsig.data[0][0] = (out[i] - poop.predict(inp[i]))/dsig.data[0][0];
            listy[i] = dsig;
        }
        return (avgMatrix(listy));
    }

    public static Matrix avgMatrix(Matrix[] poo){
        Matrix ret = new Matrix(poo[0].rows, poo[0].cols, 0);
        for(int i = 0; i < poo[0].rows; i++){
            for(int j=0; j < poo[0].cols; j++){
                for(int k = 0; k < poo.length; k++){
                    ret.data[i][j] += poo[k].data[i][j];
                }
                    ret.data[i][j] /= poo.length;
            }
        }
        return ret;
    }

}
