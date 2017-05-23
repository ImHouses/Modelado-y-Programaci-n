import sys
sys.path.insert(0, "lib/")
from cliente import ClienteFTP

c = ClienteFTP()
c.start()