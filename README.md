# TypingRaceSimulator

Object Oriented Programming Project — ECS414U

## Project Structure

```
TypingRaceSimulator/
├── Part1/    # Textual simulation (Java, command-line)
└── Part2/    # GUI simulation (to be completed)
```

## Part 1 — Textual Simulation

### How to compile 

javac Part2/*.java Part1/*.java

### How to run

#### Part 1

java -cp Part1:Part2 TypingRace

OR
 
run compilePart1.sh in the root folder

OR

rewrite main() to call startRace() manually.

## Part 2 — GUI Simulation

java -cp Part1:Part2 RaceManager 

OR

run compilePart2.sh in the root folder

OR

run startRaceGUI somewhere along your code.

## Dependencies

- Java Development Kit (JDK) 11 or higher
- No external libraries required for Part 1 & Part 2
- Part 2 only requires Java Swing

## Notes

- All code should compile and run using standard command-line tools without any IDE-specific configuration.
- The starter code in Part1 was originally written by Ty Posaurus. It contains known issues — finding and fixing them is part of the coursework.
