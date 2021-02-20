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
            Matrix weights_hoGrad = weights_hoGradCalc(poop);
            Matrix bias_hGrad = bias_hGradCalc(poop);
            Matrix weights_ihGrad = weights_ihGradCalc(poop);

            poop.bias_o.add(bias_oGrad);
//            System.out.println("1");
//            bias_oGrad.print();
            poop.weights_ho.add(weights_hoGrad);
//            System.out.println("2");
//            weights_hoGrad.print();
            poop.bias_h.add(bias_hGrad);
//            System.out.println("3");
//            bias_hGrad.print();
            poop.weights_ih.add(weights_ihGrad);
//            System.out.println("4");
//            weights_ihGrad.print();
//            System.out.println("5");


        }
        print(poop);
    }

    public void print(NeuralNetwork poop){
        System.out.println();
        for(int i = 0; i < inp.length; i++){
            System.out.print(poop.predict(inp[i]) + " ");
        }
    }

    public Matrix weights_ihGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[4];
        for(int i = 0; i < 4; i++){
            Matrix in = Matrix.fromArray(inp[i]);
            Matrix inpu = Matrix.transpose(in);
            Matrix dsig = poop.zLm1(inp[i]).dsigmoid();
            Matrix temp = hiddenGradCalc(poop);
            temp.multiply(lrate*2);
            dsig.multiply(temp);
            Matrix ret = Matrix.multiply(dsig, inpu);
            listy[i] = ret;
        }
        return (avgMatrix(listy));
    }

    public Matrix bias_hGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[inp.length];
        for(int i = 0; i < inp.length; i++){
            Matrix dsig = poop.zLm1(inp[i]).dsigmoid();
            dsig.multiply(hiddenGradCalc(poop));
            dsig.multiply(2*lrate);
            listy[i] = dsig;
        }
        return (avgMatrix(listy));
    }

    public Matrix hiddenGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[inp.length];
        for(int i = 0; i <inp.length; i++) {
            Matrix weighty = Matrix.transpose(poop.weights_ho);
            Matrix ret = Matrix.multiply(weighty, poop.zL(inp[i]).dsigmoid());
            double temp = out[i] - poop.predict(inp[i]);
            ret.multiply(temp * 2 * lrate);
            listy[i] = ret;
        }
        return avgMatrix(listy);
    }

    public Matrix weights_hoGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[inp.length];
        for(int i = 0; i < inp.length; i++){
            Matrix alm1 = Matrix.transpose(poop.aLm1(inp[i]));
            double temp = out[i] - poop.predict(inp[i]);
            alm1.multiply(2*lrate*temp);
            Matrix dsig = poop.zL(inp[i]).dsigmoid();
            Matrix ret = Matrix.multiply(dsig, alm1);
            listy[i] = ret;
        }
        return (avgMatrix(listy));
    }

    public Matrix bias_oGradCalc(NeuralNetwork poop){
        Matrix[] listy = new Matrix[inp.length];
        for(int i = 0; i < inp.length; i++){
            Matrix dsig = poop.zL(inp[i]).dsigmoid();
            dsig.data[0][0] = ((out[i] - poop.predict(inp[i]))*lrate*2);
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
