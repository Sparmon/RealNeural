import numpy as np 
import pandas as pd
import os
import cv2
import numpy as np
from PIL import Image
from scipy import misc
from numpy import asarray



def getOutput(input2,weightsInputToHidden, weightsHiddenToFinal):
    hiddenOne = np.dot(input2,weightsInputToHidden)
    #print(hiddenOne)
    hiddenOneSig = sigmoid(hiddenOne)
    output = np.dot(hiddenOneSig,weightsHiddenToFinal)
    print(output)
    max = 0
    number = 0
    for i in range(len(output)-1):
        if output[i] > max:
            max = output[i]
            number = i
    number = float( number)
    return (number/10)
def __init__(self):
    return

def sigmoid_der(x):
    return sigmoid(x)*(1-sigmoid(x))
def sigmoid(x):
    x=np.array(x,dtype=np.float128)
    return 1/(1+np.exp(-x))
def train(inList,iteratations,learningRate, inputValues, weightsInputToHidden, weightsHiddenToFinal,ExpectedOutput ):
   
    for i in range(iteratations):
        weightsHiddenToFinal2 = weightsHiddenToFinal
        weightsInputToHidden2 = weightsInputToHidden
        try:
            hiddenOne = np.dot(inputValues,weightsInputToHidden)
            #print(hiddenOne)
            hiddenOneSig = sigmoid(hiddenOne)
            output = np.dot(hiddenOneSig,weightsHiddenToFinal)
            outputSig = sigmoid(output)
            #print(outputSig)
            error = output - ExpectedOutput
            #print(error)
            #changing weights for hidden layer 
            der = sigmoid_der(output)
            der2 = sigmoid_der(hiddenOneSig)
            derOutput = error * der

            err = np.dot(weightsHiddenToFinal,derOutput)
            derHiddenLayer = err * der2
            changeForHiddenOne = np.dot(derOutput,np.array([hiddenOneSig]))
            changeForHiddenTwo = np.dot(inputValues.reshape(7500,1),derHiddenLayer.reshape(1,500))
            weightsHiddenToFinal -= 0.001*changeForHiddenOne.reshape(500,1)
            weightsInputToHidden -= 0.002*(changeForHiddenTwo)
        except:
            weightsHiddenToFinal = weightsHiddenToFinal2
            weightsInputToHidden = weightsInputToHidden2
    pd.DataFrame(weightsInputToHidden).to_csv(os.getcwd() + '/inputWeights2.csv', index = 0)
    pd.DataFrame(weightsHiddenToFinal).to_csv(os.getcwd() + '/hiddenWeights2.csv', index = 0)
    
    
def training(number = 0):
        outputs = (pd.read_csv(os.getcwd() + "/Emergency_Vehicles/train.csv"))["emergency_or_not"].values
        names = (pd.read_csv(os.getcwd() + "/Emergency_Vehicles/train.csv"))["image_names"].values
        if number < 0:
            number = names.size
        for i in range(number):
            name = "/Users/ethan/Desktop/Code with spencer /RealNeural/faceDetection/Emergency_Vehicles/train/"+str(names[i])
            image = Image.open(name)
            inputs = asarray(image)
            inputs = np.squeeze(inputs)/255
            weightsInputToHidden = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/inputWeights2.csv')))
            weightsHiddenToFinal= np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/hiddenWeights2.csv')))
            inputs = inputs.reshape(7500)
            if i == 0:
                weightsInputToHidden = np.full(shape =(7500,500), fill_value=0.00001)
                weightsHiddenToFinal = np.full(shape =(500,1), fill_value=0.00002)
            train(1,1000,0.001, inputs, weightsInputToHidden, weightsHiddenToFinal,outputs[i] )
            print("done"),
            print(i)
        weightsInputToHidden = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/inputWeights2.csv')))
        weightsHiddenToFinal = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/hiddenWeights2.csv')))

def test():
    outputs = (pd.read_csv(os.getcwd() + "/Emergency_Vehicles/train.csv"))["emergency_or_not"].values
    weightsInputToHidden = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/inputWeights2.csv')))
    weightsHiddenToFinal = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/hiddenWeights2.csv')))
    right =0 
    wrong =0
    tie =0 
    weightsInputToHidden = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/inputWeights2.csv')))
    weightsHiddenToFinal= np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/hiddenWeights2.csv')))
    
    names = (pd.read_csv(os.getcwd() + "/Emergency_Vehicles/train.csv"))["image_names"].values
    for i in range(10):#names.size):
        name = "/Users/ethan/Desktop/Code with spencer /RealNeural/faceDetection/Emergency_Vehicles/train/"+str(names[i])
        size = 50,50
        try:
            image = Image.open(name)
            inputs = asarray(image)
            inputs = np.squeeze(inputs)/255
            weightsInputToHidden = np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/inputWeights2.csv')))
            weightsHiddenToFinal= np.squeeze(np.asarray(pd.read_csv(os.getcwd() + '/hiddenWeights2.csv')))
            inputs = inputs.reshape(7500)
            hiddenOne = np.dot(inputs,weightsInputToHidden)
            hiddenOneSig = sigmoid(hiddenOne)
            output = abs(np.dot(hiddenOneSig,weightsHiddenToFinal))
            outputSig = sigmoid(output)
            print(output)
            print(outputSig)
            if outputSig > 0.5 and outputs[i] == 1:
                right += 1
            if outputSig < 0.5 and outputs[i] == 1:
                wrong += 1
            if outputSig > 0.5 and outputs[i] == 0:
                wrong += 1
            if outputSig < 0.5 and outputs[i] == 0:
                right += 1
            if outputSig == 0.5:
                tie += 1
        except:
            pass
    print(right)
    print(wrong)
    print(tie)

if __name__ == "__main__":
    test()
    training(1)
    test()
   
    
