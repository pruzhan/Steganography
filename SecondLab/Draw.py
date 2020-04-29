import matplotlib.pyplot as plt
f = open('Arrays.txt')
xlist = []
ylist = []
for line in f:
    xlist.append(int((line.rstrip()).split(',')[0]))
    ylist.append(float((line.rstrip()).split(',')[1]))
xlist.sort()
ylist.sort(reverse=True)
print(xlist)
print(ylist)
plt.plot(xlist, ylist)
plt.savefig('graph')
plt.show()