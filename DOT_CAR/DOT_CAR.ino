#include <SoftwareSerial.h>

#define TX_PIN      3
#define RX_PIN      4
#define HC05        2

#define GND_M1      5
#define VCC_M1      6
#define GND_M2      9
#define VCC_M2      10

SoftwareSerial bluetooth(RX_PIN, TX_PIN);
int command = 0;
int Y_axis = 0;
int rotate = 50;

void setup() {
  Serial.begin(9600);
  while (!Serial) {}
  bluetooth.begin(9600);
  while (!bluetooth) {}
  Serial.println("OK!");
  pinMode(GND_M1, OUTPUT);
  pinMode(VCC_M1, OUTPUT);
  pinMode(GND_M2, OUTPUT);
  pinMode(VCC_M2, OUTPUT);
  pinMode(HC05, OUTPUT);

  digitalWrite(HC05, 1);
  analogWrite(GND_M1, 0);
  analogWrite(GND_M2, 0);
  analogWrite(VCC_M1, 0);
  analogWrite(VCC_M2, 0);
}

void loop() {
  if (bluetooth.available()) {
    command = bluetooth.read();
    if (command == 0) {
      Y_axis = 1;
      analogWrite(GND_M1, 0);
      analogWrite(GND_M2, 0);
      analogWrite(VCC_M1, 128);
      analogWrite(VCC_M2, 128);
    }

    if (command == 1) {
      Y_axis = -1;
      analogWrite(GND_M1, 128);
      analogWrite(GND_M2, 128);
      analogWrite(VCC_M1, 0);
      analogWrite(VCC_M2, 0);
    }

    if (command == 2) {
      analogWrite(GND_M1, 0);
      analogWrite(GND_M2, 0);
      analogWrite(VCC_M1, 0);
      analogWrite(VCC_M2, 0);
      Y_axis = 0;
    }

    if (command == 3) {
      if (Y_axis == 1) {
        analogWrite(GND_M1, 0);
        analogWrite(GND_M2, 0);
        analogWrite(VCC_M1, 128 + 2 * rotate);
        analogWrite(VCC_M2, 128 - rotate - 50);
      }
      if (Y_axis == -1) {
        analogWrite(GND_M1, 128 + 2 * rotate);
        analogWrite(GND_M2, 128 - rotate - 50);
        analogWrite(VCC_M1, 0);
        analogWrite(VCC_M2, 0);
      }
      if (Y_axis == 0) {
        analogWrite(GND_M1, 0);
        analogWrite(GND_M2, rotate);
        analogWrite(VCC_M1, rotate);
        analogWrite(VCC_M2, 0);
      }
    }

    if (command == 4) {
      if (Y_axis == 1) {
        analogWrite(GND_M1, 0);
        analogWrite(GND_M2, 0);
        analogWrite(VCC_M1, 128 - 2 * rotate);
        analogWrite(VCC_M2, 128 + rotate);
      }
      if (Y_axis == -1) {
        analogWrite(GND_M1, 128 - rotate - 50);
        analogWrite(GND_M2, 128 + 2 * rotate);
        analogWrite(VCC_M1, 0);
        analogWrite(VCC_M2, 0);
      }
      if (Y_axis == 0) {
        analogWrite(GND_M1, rotate);
        analogWrite(GND_M2, 0);
        analogWrite(VCC_M1, 0);
        analogWrite(VCC_M2, rotate);
      }
    }

    if (command == 5) {
      if (Y_axis == 1) {
        analogWrite(GND_M1, 0);
        analogWrite(GND_M2, 0);
        analogWrite(VCC_M1, 128);
        analogWrite(VCC_M2, 128);
      }
      if (Y_axis == -1) {
        analogWrite(GND_M1, 128);
        analogWrite(GND_M2, 128);
        analogWrite(VCC_M1, 0);
        analogWrite(VCC_M2, 0);
      }
      if (Y_axis == 0) {
        analogWrite(GND_M1, 0);
        analogWrite(GND_M2, 0);
        analogWrite(VCC_M1, 0);
        analogWrite(VCC_M2, 0);
      }
    }
    Serial.print(GND_M1);
    Serial.print(" ");
    Serial.print(VCC_M1);
    Serial.print(" ");
    Serial.print(GND_M2);
    Serial.print(" ");
    Serial.print(VCC_M1);
    Serial.print(" ");
    Serial.println("\n");
  }
  if (Serial.available()) {
    bluetooth.write(Serial.read());
  }
}
