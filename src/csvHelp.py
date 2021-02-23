"""
import pandas as pd
import os
csv = pd.read_csv(os.getcwd() + '/stats/ActivePlayer_Receiving_Stats.csv')
a = 0 
newDataFrame = pd.DataFrame(columns=csv.columns.values)

print(csv)
for row in range(4315):
    if csv["Year"][row]== "2018":
        newDataFrame = newDataFrame.append(csv.loc[[row]])
        a +=1
print(newDataFrame)
print(csv.columns.values)
print(newDataFrame[["Average"]].values)
pd.DataFrame(newDataFrame).to_csv(os.getcwd() + '/stats/ActivePlayer_Receiving_Stats2018.csv', index = 0)
#print(csv)
"""
import pandas as pd
import os
csv = pd.read_csv(os.getcwd() + '/stats/combine2018.csv')
a = 0 
newDataFrame = pd.DataFrame(columns=csv.columns.values)
print(csv)
for row in range(44):
    if csv["Position"][row] == "WR":
        newDataFrame = newDataFrame.append(csv.loc[[row]])
        a +=1
listOfNames  = newDataFrame["Name"].values 

for i in range(len(newDataFrame["Name"].values)):
    test = listOfNames[i]
    test  = test.replace(". ", "-")
    test = test.replace(" ", "-")
    test  = test.replace(".", "-")
    test = test.lower()
    listOfNames[i] = test
newDataFrame["Name"] = listOfNames
pd.DataFrame(newDataFrame).to_csv(os.getcwd() + '/stats/combine2018.csv', index = 0)
#print(csv)



