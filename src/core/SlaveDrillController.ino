#include <Wire.h>
#define ADDR 1
#define TPIN 6
#define MIN_UNIT 0
#define MED_UNIT 512
#define MAX_UNIT 1024

byte x = 0;

void setup() {
  Wire.begin(ADDR);
  Wire.onReceive(receiveEvent);
  Wire.onRequest(requestEvent);
  pinMode(TPIN, OUTPUT);
}

void loop() {} //TODO

void receiveEvent(int N) {
 while(1 < Wire.avalable()) {
    x = Wire.read();
    switch(x) {
      case 0: //Stopped
        analogWrite(TPIN, MIN_UNIT);
        break;
      case 1:
        analogWrite(TPIN, MED_UNIT);
        break;
      case 2:
        analogWrite(TPIN, MAX_UNIT); 
    }
 } 
  
}

void requestEvent(int N) {
 Wire.write(x);  
}

