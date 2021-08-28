# Poker Hand Sorter

### To Play with the Code
#### To build the solution
Run the command below from the project directory
```shell
./gradlew clean build jar
```
Or run this command on Windows
```shell
gradle.bat clean build jar
```

#### To run the code
After building the project, run from the project directory
```shell
$ cat poker-hands.txt | java -jar build/libs/poker-hand-sorter-1.0-SNAPSHOT.jar
Player 0: 263
Player 1: 237
Tie: 0
```

### Dive into the Code
#### Card combination
[Combination](src/main/java/org/example/model/Combination.java) defines all 
combinations of five cards. Other than the description of combination,
`Combination` also has a value to indicate the rank between combinations, which
can be used for comparing two hands.

### Value of a hand
[HandValue](src/main/java/org/example/model/HandValue.java) represents the value
of five cards. It contains three key information:
* An instance of `Combination`, telling us the type of the five cards
* An integer `primaryValue`, which is
  * the value of the 4 same cards in "Four of a kind"
  * the value of the 3 same cards in "Full house" or "Three of a kind"
  * the value of the bigger pair in "Two pairs"
  * the value of the pair in "Pair"
* An integer `secondaryValue`, which is only used for "Two pairs" and whose value
is the smaller pair

The value of a hand is decided by the implementations of 
[HandValueDecider](src/main/java/org/example/sorter/HandValueDecider.java), while
the implementations of [HandSorter](src/main/java/org/example/sorter/HandSorter.java)
compares two hands to decide which one wins.

### The game
[Game](src/main/java/org/example/model/Game.java) is the abstraction of the whole
game. It allows "any" (at least 2) players to participate the game, and takes a
string that represents the cards for all players. When accepted all inputs, we can
get the final results printed, including points of all players and counts of tie games.