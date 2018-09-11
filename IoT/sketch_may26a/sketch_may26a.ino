#include <Servo.h>

Servo servo4;
Servo servo5;
Servo servo6;

int servopin4 = 6;
int angle4=0;

int servopin5 = 7;
int angle5 = 0;

int servopin6 = 8;
int angle6 = 0;


int button4 = 0;
int button5 = 0;
int button6 = 0;

unsigned long current_time4 ;
unsigned long current_time5 ;
unsigned long current_time6 ;

String thisChar = "";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servo4.attach(servopin4);
  servo5.attach(servopin5);
  servo6.attach(servopin6);
}


void close4(unsigned long prev_time)
{
  unsigned long current_time44 = millis();

   if(current_time44 - prev_time > 30000)
   {
    button4 = 0;
    for (angle4 = 175 ; angle4 > 90 ; angle4--)
      {
        servo4.write(angle4);
        delay(10);
      }
    }  
}

void close5(unsigned long prev_time)
{
  unsigned long current_time55 = millis();
  if(current_time55 - prev_time > 30000)
  {
    button5 = 0;
    for (angle5 = 175 ; angle5 > 90 ; angle5--)
      {
        servo5.write(angle5);
        delay(10);
      }
    }  
}
void close6(unsigned long prev_time)
{
  unsigned long current_time66 = millis();
   if(current_time66 - prev_time > 30000)
   {
    button6 = 0;
    for (angle6 = 175 ; angle6 > 90 ; angle6--)
      {
        servo6.write(angle6);
        delay(10);
      }
   }
}


void loop()
{
  if(button4 ==1 )
  {
    close4(current_time4);
  }
   if(button5 ==1 )
  {
    close5(current_time5);
  }
   if(button6 ==1 )
  {
    close6(current_time6);
  }
  if (Serial.available() > 0)
  {
    thisChar += (char)Serial.read();
    //Serial.write(thisChar);
     if(thisChar == "41")
    {
      for (angle4 = 90 ; angle4 < 180 ; angle4++)
      {
        servo4.write(angle4);
        delay(10);
      }
      current_time4 = millis();
      button4 = 1;
      thisChar ="";
    }
    if(thisChar == "40")
    {
      for (angle4 = 175 ; angle4 > 90 ; angle4--)
      {
        servo4.write(angle4);
        delay(10);
      }
      button4 = 0;
      thisChar ="";
    }

    if(thisChar == "51")
    {
      for (angle5 = 90 ; angle5 < 180 ; angle5++)
      {
        servo5.write(angle5);
        delay(10);
      }
      current_time5 = millis();
      button5 = 1;
      thisChar ="";
    }
    if(thisChar == "50")
    {
      for (angle5 = 175 ; angle5 > 90 ; angle5--)
      {
        servo5.write(angle5);
        delay(10);
      }
      button5 = 0;
      thisChar ="";
    }

    if(thisChar == "61")
    {
      for (angle6 = 90 ; angle6 < 180 ; angle6++)
      {
        servo6.write(angle6);
        delay(10);
      }
      current_time6 = millis();
      button6 = 1;
      thisChar ="";
    }

    if(thisChar == "60")
    {
      for (angle6 = 175 ; angle6 > 90 ; angle6--)
      {
        servo6.write(angle6);
        delay(10);
      }
      button6 = 0;
      thisChar ="";
    }//end of thisCar == 60
    
  }//end of if (Serial.available() > 0)
}// end of loop
