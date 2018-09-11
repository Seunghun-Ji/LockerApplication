import socket,serial


ser0 = serial.Serial('/dev/ttyACM0',9600,timeout =1)
ser1 = serial.Serial('/dev/ttyACM1',9600,timeout =1)


HOST = ""
PORT = 7777

##oc1 = 0
##oc2 = 0
#3oc3 = 0
#3oc4 = 0
##oc5 = 0
#3oc6 = 0


ocList = [0,0,0,0,0,0]

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Socket2 created')
s.bind((HOST,PORT))
print('Socket bind complete')
s.listen(1)
print('Socket now listening')

count =0
boxList = [['1key','1box','1ID'],['2key','2box','2ID'],['3key','3box','3ID']
        ,['4key','4box','4ID'],['5key','5box','5ID'],['6key','6box','6ID']]

def toArduino(stringData , conn):
    if (stringData == 11):
        print("open the No.1 door")
        ##if(oc1 == 0):
        ser1.write(b'11')
           ## oc1 = 1
    elif (stringData == 21):
        print("open the No.2 door")
        ser1.write(b'21')
        ##oc2 = 1
    elif (stringData == 31):
        ser1.write(b'31')
        print("open the No.3 door")
        ##oc3= 1
    elif (stringData == 41):
        ser0.write(b'41')
        print("open the No.4 door")
        ##oc4 = 1
    elif (stringData == 51):
        ser0.write(b'51')
        print("open the No.5 door")
        ##oc5 = 1
    elif (stringData == 61):
        ser0.write(b'61')
        print("open the No.6 door")
        ##oc6 = 1
    elif (stringData == 10):
        print("close the No.1 door")
        ##if(oc1 == 1):
        ser1.write(b'10')
           ## oc1 = 0
    elif (stringData == 20):
        print("close the No.2 door")
        ser1.write(b'20')
        ##oc2 = 0
    elif (stringData == 30):
        ser1.write(b'30')
        print("close the No.3 door")
        ##oc3 = 0
    elif (stringData == 40):
        ser0.write(b'40')
        print("close the No.4 door")
        ##oc4 = 0
    elif (stringData == 50):
        ser0.write(b'50')
        print("close the No.5 door")
        ##oc5 = 0
    elif (stringData == 60):
        ser0.write(b'60')
        print("close the No.6 door")
        ##oc6 = 0
    else:
        print("open close wrong")


def insurtboxdata(key , box , bid , signal):
    intbox = int(box)
    if(signal == "3"):##register
        boxList[intbox-1][0] = key
        boxList[intbox-1][1] = box
        boxList[intbox-1][2] = bid
        conn.send("register compelete. your box No."+box+" Your ID :" + bid)
    elif(signal =="4" and bid == boxList[intbox-1][2]): ## delete 
        boxList[intbox-1][0] = "NULL_key"
        boxList[intbox-1][1] = "NULL_box"
        boxList[intbox-1][2] = "NULL_bid"
        conn.send("Unregister your " + box + "box")
    elif(signal == "4" and bid != boxList[intbox-1][2]):
        print("box ID value is wrong. Don't delete")
        conn.send("You can only delete your ID")
    else:
        print("insurtdata error")
        conn.send("insurt data error")

while True:
    conn , addr = s.accept()
    print("Connected by " , addr)

    data = conn.recv(1024)
    data = data.decode("utf-8").strip()
    if not data: break
    print("Received : " + data)
    ##receiveValue = data
    receiveValue = data.split(',')
    if(len(receiveValue) == 4):
        print(data.split(','))
        key = data.split(',')[0]
        box = data.split(',')[1]
        bid = data.split(',')[2]
        signal = data.split(',')[3]
        intboxx = int(box)

        if(signal == "1"):##open
            print("Signal 1 is open the door please. key is : " + key)
            print("checking the door key")
            if(key == boxList[intboxx-1][0]):
                print("Find same key")
                if(ocList[intboxx-1] == 0):
                    toArduino((intboxx*10)+1,conn)
                    ocList[intboxx-1] = 1
                    conn.send("Block Chain key valid. OPEN the No."+box+"box")
                else:
                    print("already open")
                    conn.send("already open")
            else:
                print("invalid key value")
                conn.send("invalid KEY value")

        elif(signal == "2"):##close
            print("Signal 2 is close the door please. key is : " + key)
            print("checking the door key")
            if(key == boxList[intboxx-1][0]):
                print("Find same key")
                if(ocList[intboxx-1]==1):
                    toArduino((intboxx*10),conn)
                    ocList[intboxx-1] = 0
                    conn.send("Block chain key valid. CLOSE the No."+box+"box")
                else:
                    print("already close")
                    conn.send("already close")
            else:
                print("invalid key value")
                conn.send("invalid Block Chain KEY value")

        elif(signal == "3"): ##register
            print("register please")
            print("key : "+ key)
            print("box : "+ box)
            print("ID : "+ bid)
    
            insurtboxdata(key,box,bid,signal)
            print(boxList)
            

        elif(signal == "4"): ##unregister
            print("delete please")
    
            insurtboxdata(key,box,bid,signal)
            print(boxList)
        else:
            print("signal syntax error")
            conn.send("signal syntax error")


    else:
        print("The number of parameter is WRONG")
        print("you MUST send 4 parameter")
        conn.send("The number of parameter is WRONG\n you MUST send 4 parameter")



    conn.close()
s.close()

