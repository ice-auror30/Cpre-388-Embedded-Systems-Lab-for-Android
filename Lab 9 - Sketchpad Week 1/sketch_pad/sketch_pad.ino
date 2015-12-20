/*
 * Modified version of https://github.com/adafruit/RGB-matrix-Panel project.
 * This Project has been modified to take the commands from an Android tablet 
 * It also utilizes this library https://github.com/adafruit/Adafruit-GFX-Library
 *
 */


//////////////////////
// Library Includes //
//////////////////////
#include <Adafruit_GFX.h>   // Core graphics library
#include <RGBmatrixPanel.h> // Hardware-specific library
#include <Usb.h> //library to use the USB port
#include <AndroidAccessory.h> // Android library
#include "bitmap.h"

/////////////////////
// Hardware Hookup //
/////////////////////
// R0, G0, B0, R1, G1, B1 should be connected to pins 
// 2, 3, 4, 5, 6, and 7 respectively. Their pins aren't defined,
// because they're controlled directly in the library. These pins
// can be moved (somewhat):
#define OE  9
#define LAT 10
#define A   A0
#define B   A1
#define C   A2
#define D   A3 // Comment this line out if you're using a 32x16
// CLK can be moved but must remain on PORTB(8, 9, 10, 11, 12, 13)
#define CLK 11  // MUST be on PORTB!

//Connection to Tablet
AndroidAccessory acc("cpre388",
		"sketchpad",
		"Description",
		"1.0",
		"cpre388.iastate.edu",
                "0000000012345679");

// For 32x32 LED panels:
RGBmatrixPanel matrix(A, B, C, D, CLK, LAT, OE, false); // 32x32

#define MSG_SIZE 6

int8_t cursorX = 0;  // Cursor x position, initialize left
int8_t cursorY = 0;  // Cursor y position, initialize top
int16_t color = 0; // Keep track of color under cursor
int8_t red = 1;   // Red paint value 
int8_t blue = 1;  // Blue paint value
int8_t green = 1; // Green paint value

// The setup() function initializes the rgb matrix, and clears the 
// screen. It also starts the Serial connection.
void setup()
{
  matrix.begin();       // Initialize the matrix.
  blankEasel();         // Blank screen
  Serial.begin(9600);   // Start serial
  acc.powerOn();        // Start up android app
}

void loop()
{

  byte msg[MSG_SIZE];
  if(acc.isConnected())
  {
    int len = acc.read(msg, sizeof(msg), 1);
    if (len > 0) {
      // TODO: Based on the message received, complete the following tasks:
      //
      // Set values for cursorX, cursorY, red, green, and blue variables.
      // Call loadBitmap() if signaled
      // Erase LED board if signaled
      // Otherwise draw the new color to LED board at (cursorX, cursorY)
      //
      // It it up to you to determine the format of this message.
      int8_t type = msg[MSG_SIZE - 1];
      switch (type) {
        case 0:
          // erase board
          blankEasel();
          break;
        case 1:
          // display bitmap
          loadBitmap();
          break;
        case 2:
          // set color of pixel
          cursorX = msg[MSG_SIZE - 2] % 32;
          cursorY = msg[MSG_SIZE - 3] % 32;
          red = msg[MSG_SIZE - 4] % 8;
          green = msg[MSG_SIZE - 5] % 8;
          blue = msg[MSG_SIZE - 6] % 8;
          drawDot();
          break;
      }
    }
  }
  
}

// Draws active color at (cursorX, cursorY).
void drawDot()
{
  color = matrix.Color333(red, green, blue);
  matrix.drawPixel(cursorX, cursorY, color);
}

// loadBitmap loads up a stored bitmap into the matrix's data buffer.
void loadBitmap()
{
  uint8_t *ptr = matrix.backBuffer(); // Get address of matrix data
  memcpy_P(ptr, bmp, sizeof(bmp));
}

// Reset the screen. Set cursors back to top-left. Reset oldColor
// and blank the screen.
void blankEasel()
{
  cursorX = 0;
  cursorY = 0;
  color = 0;
  matrix.fillScreen(0); // Blank screen
}
