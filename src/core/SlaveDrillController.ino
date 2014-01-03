#include <Wire.h>
#define ADDR 1
#define TPIN 6
#define MIN_UNIT 0
#define MED_UNIT 512
#define MAX_UNIT 1024
/**
Component		|		Symbol
--------------------------||-----------------------
Digital Pin 6(PWM)	|			D6~		(referred as TPIN)
Ground			|			GND
Motor			|			M
DCV Source		|			+5V
10kOhm Resistor	|		--/\/\/\/\/\--
Diode			|		--->|----
P2N2222AG			  	--C B E--
Transistor		|		      |	

Schematic:

	 /---->|------\
	|		  |
	|-----M-------|
	|		  |
+5V------------------C B E-----------GND
				|
				|
				\-------/\/\/\/\/\-----D6~

**/
byte x = 0;

void setup() {
  Wire.begin(ADDR);
  Wire.onReceive(receiveEvent);
  //Wire.onRequest(requestEvent);
  pinMode(TPIN, OUTPUT);
}

void loop() {
  delay(1);
} //TODO

void receiveEvent(int howMany) {
  while(1 < Wire.available()) {
    x = Wire.read();
      switch (x) {
    case 0:
      analogWrite(TPIN,MIN_UNIT);
      break;
    case 1:
      analogWrite(TPIN,MED_UNIT);
      break;
    case 2:
      analogWrite(TPIN, MAX_UNIT);  
      break;    
      }
}
}

void requestEvent() {
 Wire.write(x);  
}

