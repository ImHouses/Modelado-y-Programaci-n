import sys
sys.path.insert(0, "lib/")
from servidor import ServidorFTP

s = ServidorFTP()
s.start()

