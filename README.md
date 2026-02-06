# Dice Game Project (Java)

This project is a Java console-based dice game application

The application consists of four dice-based mini-games designed to practise core
programming concepts such as control flow, data structures, algorithms, and
object-oriented design.

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
