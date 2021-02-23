import numpy as np
a = np.array([[500,100,200],[100,5,2]])
print(a)
b = np.array([100,50,2])
print(a-b)
colums = a.columns.values
for i in range(len(colums)):
   a[i] = a[i] - b[i]
print(a)