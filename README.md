# Dice Game Project (Java)

This project is a Java console-based dice game application

The application consists of four dice-based mini-games designed to practise core
programming concepts such as control flow, data structures, algorithms, and
object-oriented design.

---
## Main Menu Game
When the program starts, a console-based menu is displayed:
        
   === Dice Game Menu ===
   1. Dice Patterns Challenge
   2. Dice Grid Puzzle
   3. Dice Codebreaker
   4. Dice Battle
   5. Exit
   Choose:

How It Works
The user enters a number (1-5).
The system launches the selected game.
After the game ends, control returns to the main menu.
The program runs in a loop until the user selects 5 (Exit).

---

## 🎮 Games Included

### 1. Dice Patterns Challenge
A pattern-based dice game where players roll five dice and score points based on
patterns such as pairs, full houses, and straights.

### 2. Dice Grid Puzzle
Players place dice rolls into a 3×3 grid and score points based on combinations formed
in rows and columns.

### 3. Dice Codebreaker
A logic-based guessing game where players attempt to crack a secret four-dice code.
Feedback is provided after each guess to guide the player.

### 4. Dice Battle (Round-Based Combat)
A round-based dice combat game where the player battles a computer-controlled opponent
using attack, defend, and heal actions.

---

## 🎲 Dice Patterns Challenge – Game Rules

**Game Type:** Pattern-based dice scoring with rerolls  
**Victory Condition:** Maximise score from five dice after up to two rerolls  

### Starting Conditions
- The system rolls 5 dice (values 1–6).
- The initial roll is displayed.

---

### Round Structure
1. **Initial Roll**  
   Five dice are rolled and shown.

2. **Reroll Phase (up to 2 times)**  
   - The player is asked: `Re-roll? (y/n)`
   - If `y`, the player enters dice indices to reroll (`0–4`), ending input with `-1`.
   - Only selected dice are rerolled and the current dice are displayed.

3. **Scoring**  
   The final dice are scored using the highest-ranking valid pattern.

---

### Scoring System
- Five of a Kind → **50**
- Four of a Kind → **40**
- Full House (3 + 2) → **35**
- Straight (1–5 or 2–6) → **30**
- Three of a Kind → **25**
- Two Pairs → **20**
- One Pair → **10**
- No Pattern → **0**

---

## 🎲 Dice Grid Puzzle – Game Rules

**Game Type:** Grid-based placement puzzle  
**Victory Condition:** Achieve the highest total score after 9 turns  

### Starting Conditions
- A 3×3 grid starts empty.
- The game lasts 9 turns.
- Each turn rolls one die (1–6).

---

### Round Structure
1. The system rolls a die.  
2. The grid is displayed.  
3. The player enters `row col (0–2)` to place the value.  
   - The cell must be empty.  

---

### Scoring System
After the grid is full, all 3 rows and 3 columns are scored:

- Three of a kind → **15 points**  
- Straight (consecutive numbers) → **12 points**  
- One pair → **8 points**  
- No match → **5 points**

---

### End Conditions
- The final grid is displayed.
- The total score is printed.

---


## 🎲 Dice Codebreaker – Game Rules

**Game Type:** Logic-based deduction game  
**Victory Condition:** Guess the secret four-dice code within 10 attempts  

### Starting Conditions
- A hidden 4-number code is generated (values 1–6).
- Duplicate numbers are allowed.
- The player has 10 attempts.

---

### Round Structure
Each guess follows three steps:

1. **Guess Input**  
   The player enters four numbers.

2. **Position Check**  
   Numbers that match both value and position are counted as  
   **Correct position**.

3. **Number Check**  
   Remaining matching numbers in different positions are counted as  
   **Correct number (wrong position)**.

Each number can only be matched once.

---

### End Conditions
- The player wins if all 4 positions are correct.
- The player loses after 10 unsuccessful attempts.
- The secret code is revealed if the player fails.


---

## 🎲 Dice Battle – Game Rules

**Game Type:** Round-based dice combat  
**Victory Condition:** Opponent HP reaches zero or below  

### Starting Conditions
- Player HP: 20  
- Computer HP: 20  

---

### Round Structure
Each round is resolved in three phases:

1. **Action Selection**  
   Both the player and the computer choose an action:
   - Attack
   - Defend
   - Heal

2. **Dice Rolling**  
   Dice are rolled based on the selected actions.

3. **Resolution**  
   Damage reduction, healing, and HP updates are applied simultaneously.

---

### Available Actions

#### Attack
- Roll 1–3 dice.
- Damage equals the total of the dice.
- Rolling all sixes triggers a critical hit, doubling the damage.

#### Defend
- Applies immediately within the same round.
- All incoming damage during that round is reduced by 50%.
- Defend mitigates damage but does not prevent it.

#### Heal
- Roll exactly 2 dice.
- Restore HP equal to the total of the dice.

---

### Computer AI Behaviour
- Prioritises healing when HP is critically low.
- Uses defensive actions probabilistically to reduce predictability.
- Attacks by default to maintain consistent combat flow.

---

## 👤 Player Data and Statistics

- Players are identified using a unique username.
- Player data is stored persistently in a text file.
- The system tracks the following information:
  - Total number of games played
  - Highest score achieved in each game
  - Most recent score for each game
  - Date and time of the last gameplay session

This ensures that player progress is preserved between sessions.

---

## 🧭 User Interface

- The application uses a **menu-driven console interface**.
- Players can select which dice game to play from the main menu.

---

## 🛠 Technical Features

- Written entirely in Java
- Modular class-based design
- Menu-driven console interface
- Round-based combat resolution for fairness and clarity
- Persistent data storage using text files

---

## 🔧 Development Workflow

- The project was developed collaboratively using **Git** for version control.
- **GitHub** was used to manage the shared repository.
- **GitHub Codespaces** provided a consistent development environment for all team members.
- Regular commits and code reviews were used throughout development.

---

## ▶️ How to Run

1. Compile all `.java` files.
2. Run `Menu.java`.
3. Select a game from the menu.

---

## 📌 Learning Outcomes

- Designed and implemented multiple dice-based games
- Applied object-oriented programming principles
- Improved understanding of game logic and timing
- Practised debugging, testing, and iterative refinement
- Gained experience using Git in a collaborative project

---

## 📄 Author

Sina🍷

Feel free to leave a comment! 
