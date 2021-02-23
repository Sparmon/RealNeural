import numpy as np 
import pandas as pd
import os


    
def __init__(self):
    return
def sigmoid_der(x):
    return sigmoid(x)*(1-sigmoid(x))
def sigmoid(x):
        return 1/(1+np.exp(-x))
def train(iteratations,learningRate, inputValues, weightsInputToHidden, weightsHiddenToFinal,ExpectedOutput ):
    for i in range(iteratations):
        hiddenOne = np.dot(inputValues,weightsInputToHidden)
        #print(hiddenOne)
        hiddenOneSig = sigmoid(hiddenOne)
        output = np.dot(hiddenOneSig,weightsHiddenToFinal)
        outputSig = sigmoid(output)
        #print(outputSig)
        error = outputSig - ExpectedOutput
        #print(error)

        #changing weights for hidden layer 
        der = sigmoid_der(output)
        print(hiddenOneSig)
        print(der*error)
        changeForHiddenOne = np.dot(hiddenOneSig,(der*error))
        #changing weights for input layer 
        # der =  dersig(g(f(x))* g'(f(X) * f'(x) 
        chain = error * der # f'(x) 
        derSecond = np.dot(chain, weightsInputToHidden) #g'(f(x)) * f'(x)
        derFirst = sigmoid_der(hiddenOne)
        changeForHiddenTwo = np.dot(inputValues,(derFirst*derSecond))
        #changing
        weightsHiddenToFinal -= learningRate * changeForHiddenOne
        weightsInputToHidden -= learningRate * changeForHiddenTwo

        


if __name__ == "__main__":
    inputValues = np.array([1,0,1,0])
    weightsInputToHidden = np.array([[0.5,0.2,0.3],[0.2,0.7,0.3],[0.3,0.2,0.6],[0.8,0.2,0.2]])
    weightsHiddenToFinal = np.array([[0.3,0.2,0.5]])
    #weightsInputToHidden = pd.read_csv(os.getcwd() + '/weights/inputWeights.csv')
    #weightsHiddenToFinal = pd.read_csv(os.getcwd() + '/weights/hiddenWeights.csv')
    weightsHiddenToFinal = np.squeeze(np.asarray(weightsHiddenToFinal))
    weightsInputToHidden = np.squeeze(np.asarray(weightsInputToHidden) )

    ExpectedOutput = 0.5
    hiddenOne = np.dot(inputValues,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    outputSig = sigmoid(output)
    #print(outputSig)
    error = outputSig - ExpectedOutput

    train(2000,0.25, inputValues, weightsInputToHidden, weightsHiddenToFinal,ExpectedOutput )
    pd.DataFrame(weightsInputToHidden).to_csv(os.getcwd() + '/src/inputWeights.csv', index = 0)
    pd.DataFrame(weightsHiddenToFinal).to_csv(os.getcwd() + '/src/hiddenWeights.csv', index = 0)
    hiddenOne = np.dot(inputValues,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    outputSig = sigmoid(output)
    #print(outputSig)
    error = outputSig - ExpectedOutput



