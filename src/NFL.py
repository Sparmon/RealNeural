import numpy as np 
import pandas as pd
import os


def getOutput(input,weightsInputToHidden, weightsHiddenToFinal):
    hiddenOne = np.dot(inputValues,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    return output
def __init__(self):
    return

def sigmoid_der(x):
    return sigmoid(x)*(1-sigmoid(x))
def sigmoid(x):
    x=np.array(x,dtype=np.float64)
    return 1/(1+np.exp(-x))
def train(inList,iteratations,learningRate, inputValues2, weightsInputToHidden, weightsHiddenToFinal,expectedOutput ):
    
    inputValues = inputValues2[5]
    ExpectedOutput = expectedOutput[5]
    hiddenOne = np.dot(inputValues,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    outputSig = sigmoid(output)
    print(ExpectedOutput)
    error = output - ExpectedOutput
    #error = sigmoid(error)
    print(output)
    print(error)
    
    for i in range(iteratations):
        for ii in range(inList):
            inputValues = inputValues2[ii]
            ExpectedOutput = expectedOutput[ii]
            hiddenOne = np.dot(inputValues,weightsInputToHidden)
            #print(hiddenOne)
            hiddenOneSig = sigmoid(hiddenOne)
            output = np.dot(hiddenOneSig,weightsHiddenToFinal)
            outputSig = sigmoid(output)
            #print(outputSig)
            error = output - ExpectedOutput
            #error = sigmoid(error)
            #print(error)

            #changing weights for hidden layer 
            der = sigmoid_der(output)
            changeForHiddenOne = np.dot(hiddenOneSig,(der*error[0]))
            #changing weights for input layer 
            # der =  dersig(g(f(x))* g'(f(X) * f'(x) 
            chain = error[0] * der # f'(x) 
            derSecond = np.dot(chain, weightsInputToHidden) #g'(f(x)) * f'(x)
            derFirst = sigmoid_der(hiddenOne)
            changeForHiddenTwo = np.dot(inputValues,(derFirst*derSecond))
            #changing
            weightsHiddenToFinal -= learningRate * changeForHiddenOne
            weightsInputToHidden -= learningRate * changeForHiddenTwo
    
    inputValues = inputValues2[5]
    ExpectedOutput = expectedOutput[5]
    hiddenOne = np.dot(inputValues,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    outputSig = sigmoid(output)
    error = output - ExpectedOutput
    #error = sigmoid(error)
    print(ExpectedOutput)
    print(output)
    print(error)
    
    
    pd.DataFrame(weightsInputToHidden).to_csv(os.getcwd() + '/weights/inputWeights2.csv', index = 0)
    pd.DataFrame(weightsHiddenToFinal).to_csv(os.getcwd() + '/weights/hiddenWeights2.csv', index = 0)
    


if __name__ == "__main__":
    """
    weightsInputToHidden = pd.read_csv(os.getcwd() + '/weights/inputWeights2.csv')
    weightsHiddenToFinal = pd.read_csv(os.getcwd() + '/weights/hiddenWeights2.csv')
    print("Height")
    Height = input()
    print("Weight")
    Weight = input()
    print("hand size")
    handSize = input()
    print("arm length")
    armLength = input()
    print("fourty time")
    fourtyYard = input()
    print("bench")
    bench = input()
    print("vert")
    vert = input()
    print("broad")
    broad = input()
    print("shuttle")
    shuttle = input()
    print("three cone")
    threeCone = input()
    inputValues = np.array([Height,Weight,handSize,armLength,fourtyYard,bench,vert,broad,shuttle,threeCone])
    print(getOutput(inputValues,weightsInputToHidden, weightsHiddenToFinal))
    """





    
    stats = pd.read_csv(os.getcwd() + '/stats/ActivePlayer_Receiving_Stats2018.csv')
    combine = pd.read_csv(os.getcwd() + '/stats/combine2018.csv')
    weightsInputToHidden = pd.read_csv(os.getcwd() + '/weights/inputWeights2.csv')
    weightsHiddenToFinal = pd.read_csv(os.getcwd() + '/weights/hiddenWeights2.csv')
    #weightsInputToHidden = np.random.rand(10,5)
    #weightsHiddenToFinal = np.random.rand(5,1)
    inputs = np.array([[0,1,2,2,3,4,5,6,7,8]])
    outputs = np.array([0])
    for year in range(2):
        stats = pd.read_csv(os.getcwd() + '/stats/ActivePlayer_Receiving_Stats2018.csv')
        combine = pd.read_csv(os.getcwd() + '/stats/combine2018.csv')
        if year == 0:
            stats = pd.read_csv(os.getcwd() + '/stats/ActivePlayer_Receiving_Stats2019.csv')
            combine = pd.read_csv(os.getcwd() + '/stats/combine2019.csv')
        try:
            combine = combine.drop(columns=['Wonderlic', '60Yd Shuttle'])
        except:
            None
        colums = combine.columns.values
        for i in range(3,13):
            combine[colums[i]].fillna(value=combine[colums[i]].mean(), inplace=True)
        
        for i in range(1,len(combine)-1):
            inputValues = combine.loc[[i]].values
            inputValues = inputValues.squeeze()
            name = inputValues[0]
            
            ExpectedOutput = stats.loc[stats['Player_Id'] == name]["Average"].values
            inputValues = inputValues[3:]
            inputValues=np.array(inputValues,dtype=np.float64)
            try:
                
                ExpectedOutput=np.array(ExpectedOutput,dtype=np.float64)
                if(ExpectedOutput.size>0):
                    inputs = np.vstack((inputs,inputValues))
                    outputs = np.vstack((outputs,ExpectedOutput))
            except:
                None
    #inputs = np.delete(inputs, (0), axis=0)
    #outputs = np.delete(outputs, (0), axis=0
            
    #print(inputs)      
    average = np.array([74.14,203,9.375,31.75,4.53,15,34.625,114,4.31,6.94])    
    inputs = inputs - average 
    inputs = inputs/10
    outputs = outputs - np.average(outputs)
    outputs = outputs/10

    weightsHiddenToFinal = np.squeeze(np.asarray(weightsHiddenToFinal))
    weightsInputToHidden = np.squeeze(np.asarray(weightsInputToHidden))
    train((len(inputs)-1),20000,0.25, inputs, weightsInputToHidden, weightsHiddenToFinal,outputs )
    

