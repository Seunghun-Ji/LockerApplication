#include <Servo.h>

Servo servo1;
Servo servo2;
Servo servo3;

int servopin1 = 6;
int angle1=0;

int servopin2 = 7;
int angle2 = 0;

int servopin3 = 8;
int angle3 = 0;


int button1 = 0;
int button2 = 0;
int button3 = 0;

unsigned long current_time1 ;
unsigned long current_time2 ;
unsigned long current_time3 ;

String thisChar = "";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servo1.attach(servopin1);
  servo2.attach(servopin2);
  servo3.attach(servopin3);
}


void close1(unsigned long prev_time)
{
  unsigned long current_time11 = millis();

   if(current_time11 - prev_time > 30000)
   {
    button1 = 0;
    for (angle1 = 175 ; angle1 > 90 ; angle1--)
      {
        servo1.write(angle1);
        delay(10);
      }
    }  
}

void close2(unsigned long prev_time)
{
  unsigned long current_time22 = millis();
  if(current_time22 - prev_time > 30000)
  {
    button2 = 0;
    for (angle2 = 175 ; angle2 > 90 ; angle2--)
      {
        servo2.write(angle2);
        delay(10);
      }
    }  
}
void close3(unsigned long prev_time)
{
  unsigned long current_time33 = millis();
   if(current_time33 - prev_time > 30000)
   {
    button3 = 0;
    for (angle3 = 175 ; angle3 > 90 ; angle3--)
      {
        servo3.write(angle3);
        delay(10);
      }
   }
}


void loop()
{
  if(button1 ==1 )
  {
    close1(current_time1);
  }
   if(button2 ==1 )
  {
    close2(current_time2);
  }
   if(button3 ==1 )
  {
    close3(current_time3);
  }
  if (Serial.available() > 0)
  {
    thisChar += (char)Serial.read();
    //Serial.write(thisChar);
     if(thisChar == "11")
    {
      for (angle1 = 90 ; angle1 < 180 ; angle1++)
      {
        servo1.write(angle1);
        delay(10);
      }
      current_time1 = millis();
      button1 = 1;
      thisChar ="";
    }
    if(thisChar == "10")
    {
      for (angle1 = 175 ; angle1 > 90 ; angle1--)
      {
        servo1.write(angle1);
        delay(10);
      }
      button1 = 0;
      thisChar ="";
    }

    if(thisChar == "21")
    {
      for (angle2 = 90 ; angle2 < 180 ; angle2++)
      {
        servo2.write(angle2);
        delay(10);
      }
      current_time2 = millis();
      button2 = 1;
      thisChar ="";
    }
    if(thisChar == "20")
    {
      for (angle2 = 175 ; angle2 > 90 ; angle2--)
      {
        servo2.write(angle2);
        delay(10);
      }
      button2 = 0;
      thisChar ="";
    }

    if(thisChar == "31")
    {
      for (angle3 = 90 ; angle3 < 180 ; angle3++)
      {
        servo3.write(angle3);
        delay(10);
      }
      current_time3 = millis();
      button3 = 1;
      thisChar ="";
    }

    if(thisChar == "30")
    {
      for (angle3 = 175 ; angle3 > 90 ; angle3--)
      {
        servo3.write(angle3);
        delay(10);
      }
      button3 = 0;
      thisChar ="";
    }//end of thisCar == 30
    
  }//end of if (Serial.available() > 0)
}// end of loop
