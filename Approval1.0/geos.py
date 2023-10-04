import os 
import os.path
import datetime
import tkinter as tk
from tkinter import filedialog


#Get the Paths
root = tk.Tk()
root.withdraw()
path1 = filedialog.askdirectory()
path2 = filedialog.askdirectory()

#The List of files 
dir_list = os.listdir(path1)
dir_list.sort()
dir_list = [f for f in dir_list if os.path.isfile(path1+'/'+f)]
#The List of Last Modified Date
date_path_list = [path1 +"/"+i for i in dir_list]
date_list = [os.path.getmtime(i) for i in date_path_list]
dt_m = [ datetime.datetime.fromtimestamp(i) for i in date_list]
final = [i.strftime("%m/%d/%Y %I:%M %p") for i in dt_m]
#Getting Format sizing
max_width = max(len(i) for i in dir_list)
#Print 
completeName = os.path.join(path2, "Approval.txt")
file1 = open(completeName, "w")
for (a,b) in zip(dir_list, final):
    file1.write("%-40s %20s\n" % (a, b))
file1.close()