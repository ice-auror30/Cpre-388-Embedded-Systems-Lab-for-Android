#include <Usb.h>
#include <AndroidAccessory.h>
#define  LED_PIN  13

#define SHORT 0
#define LONG 1
#define LETTER 2
#define WORD 3
#define STOP 4

#define UNIT 250

AndroidAccessory acc("cpre388",
		"morse_code",
		"Description",
		"1.0",
		"cpre388.iastate.edu",
                "0000000012345678");
void setup()
{
  // set communiation speed
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  acc.powerOn();
}
 
void loop()
{
  byte msg[125];
  if (acc.isConnected()) {
    int len = acc.read(msg, sizeof(msg), 1); // read data into msg variable
    if (len > 0) { // Only do something if a message has been received.
        displayMorseCode(msg, len);

    }
  } 
  else
    digitalWrite(LED_PIN , LOW); // turn off light
}

// Function to display the morse code message
void displayMorseCode(byte* msg, int len) {
  
}  
