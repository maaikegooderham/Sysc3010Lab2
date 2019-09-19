# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
num_messages = sys.argv[3]

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)

for i in range(int(num_messages)):
    # send message 
    data = "Message" + str(i)
    print( "Sending "  + data)
    s.sendto(data.encode('utf-8'), server_address)
    # wait for acknowledgment 
    buf, address = s.recvfrom(port)
    if not len(buf):
        break
    print( "Received acknowledgment: " + buf.decode('utf-8'))

s.shutdown(1)

