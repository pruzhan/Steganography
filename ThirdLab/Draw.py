import matplotlib.pyplot as plt
f = open('Arrays.txt')
xlist = []
ylist = []
zlist = []
for line in f:
    xlist.append(int((line.rstrip()).split(',')[0]))
    ylist.append(float((line.rstrip()).split(',')[1]))
    zlist.append(float((line.rstrip()).split(',')[2]))
xlist.sort()
ylist.sort(reverse=True)
zlist.sort()
plt.plot(xlist, ylist)
plt.savefig('graphPSNR')
plt.show()
plt.plot(xlist, zlist)
plt.savefig('graphRMSE')
plt.show()