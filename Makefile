#Final Variables
SRC = src
CORE = src/core
UI = src/core/ui
BIN = bin
JAVAC = javac
ANT = ant
LEGO_RULES = /etc/udev/rules.d/70-lego.rules
UDEVD = /etc/init.d/udev
LEJOS_BINS = /opt/leJOS_NXJ/bin


install_debian_dependencies:
	apt-get install openjdk-6-jdk libusb-dev 

install_leJOS:
	echo 'Preparing Driver'
	echo 'SUBSYSTEM=="usb", DRIVER=="usb", ATTRS{idVendor}=="0694", ATTRS{idProduct}=="0002", GROUP="lego", MODE="0660"' > $(LEGO_RULES)
	echo 'SUBSYSTEM=="usb", DRIVER=="usb", ATTRS{idVendor}=="03eb", ATTRS{idProduct}=="6124", GROUP="lego", MODE="0660"' >> $(LEGO_RULES)
	groupadd lego
	sudo gpasswd -a $USER lego
	echo 'Time to restart udev'
	$(UDEVD) restart
	wget http://sourceforge.net/projects/lejos/files/lejos-NXJ/0.9.1beta/leJOS_NXJ_0.9.1beta-3.tar.gz 
	echo 'Downloaded leJOS'
	tar xvf leJOS_NXJ_0.9.1beta-3.tar.gz /opt/ && cd /opt && mv leJOS_NXJ_0.9.1beta-3/ leJOS_NXJ/
	echo 'Moved leJOS and untarred it to /opt/'
	cd /opt/leJOS_NXJ/build
	$(ANT)
	echo 'Succesfully built leJOS'
	rm leJOS_NXJ_0.9.1beta-3.tar.gz
	echo 'Clean. Completed!'
	xdg-open "https://code.google.com/p/lego-mindstorms-3d-printing-machine/wiki/SetupLeJOS"
	
GUINavigator: $(UI)/GUINavigator.java
	$(LEJOS_BINS)/nxjpcc $(UI)/GUINavigator.java -d $(BIN)/
	
I2CSpeedController: $(UI)/I2CSpeedController.java
	$(LEJOS_BINS)/nxjpcc $(UI)/I2CSpeedController.java -d $(BIN)/
	
	
all: 
	cd $(SRC)
	$(LEJOS_BINS)/nxjpcc *.java -d $(BIN)

#help

help:
	@echo  'install_debian_dependencies - Installs dependencies on debian systems (as su)'
	@echo  'cleanbinaries               - Cleans ./bin directory'
	@echo  'all                         - Builds all java files'
	@echo  'install_leJOS               - Installs leJOS on debian systems and opens the leJOS installation guide (as su)' 
	


	
#Clean

cleanbinaries:
	rm $(BIN)/*
	

