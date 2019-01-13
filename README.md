# sunset-melee
A *(poor)* Java implementation of the Twilight Struggle board game


### How to run the game:
The game runs the same if you run vui the console or GUI

To run the game via console set the Object if UI to be UIText in the Controller Class
```java
UIText UI = new UIText(game);
```

To run the game via GUI set the Object if UI to be UIGraphic in the Controller Class
```java
UIGraphic UI = new UIGraphic(game);
```

### How to play the game:

Run the game with your choice of UI.  If you use text it will consist of user input, mosty with the 3-digit ISO Codes, found (with exceptions) at
```
https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3
```
Most intractions will require manual input.

For the Graphical User Interface, it consists of JOptionPanes.  The play should be mostly self explanitory, just follow the prompts.

### Goals

Pick a player to build up your influence to try and dominate the world.
###### **DO NOT TRY TO USE THINGS OUTSIDE OF EUROPE!**
Althouht they work, it is not optimized for this version.

You can lose by the following circumstances: Vicotry Points goes to +/- 20, if Defcon hits 0, or if you hold on to a scoring card through turns.


Anything else can be found here
```
http://www.gmtgames.com/nnts/TS_Rules-2015.pdf
```
As the game follows the rule book.




Happy World Domination!
