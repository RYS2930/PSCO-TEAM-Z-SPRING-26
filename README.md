# 🎲 Dice Game Project (Java)

A Java console-based mini game system that includes **four dice games**, **persistent player profiles**, and **statistics tracking**.

This project was developed using **Java**, **Git**, and **GitHub**, with a focus on:

* Object-oriented programming
* Input validation
* Random game logic
* File handling
* Reusable class design

---

## 📌 Project Overview

When the program starts, the player enters a **username**. The system then either:

* loads an existing profile, or
* creates a new one.

After login, the main menu is displayed.

### Main Menu

```text
=== Dice Game Menu ===
1. Dice Patterns Challenge
2. Dice Grid Puzzle
3. Dice Codebreaker
4. Dice Battle
5. View Player Stats
0. Exit
```

### Menu Behaviour

* **1–4** → Start one of the four games
* **5** → Open the player statistics menu
* **0** → Save user data and exit the current session
* After exiting, another player can log in, or the user can type **-1** to fully close the program

This means the system supports **multiple players with separate saved profiles**.

---

## 🎮 Games Included

| No. | Game                        | Description                                               |
| --- | --------------------------- | --------------------------------------------------------- |
| 1   | **Dice Patterns Challenge** | Roll and reroll dice to create the best scoring pattern   |
| 2   | **Dice Grid Puzzle**        | Place dice into a 3×3 grid and score rows and columns     |
| 3   | **Dice Codebreaker**        | Guess a secret 4-number code within limited attempts      |
| 4   | **Dice Battle**             | Fight the computer using attack, defend, and heal actions |

---

## 1️⃣ Dice Patterns Challenge

### Objective

Get the **highest possible score** by building the best pattern from **five dice**.

### How It Works

* The game starts by rolling **5 dice**
* The player may reroll up to **2 times**
* In each reroll round, the player chooses which dice indices to reroll
* Valid dice indices are **0 to 4**
* Input ends with **-1**
* The final pattern is scored automatically

### Scoring Rules

| Pattern               | Score |
| --------------------- | ----: |
| Five of a Kind        |    50 |
| Four of a Kind        |    40 |
| Full House            |    35 |
| Straight (1–5 or 2–6) |    30 |
| Three of a Kind       |    25 |
| Two Pairs             |    20 |
| One Pair              |    10 |
| No Pattern            |     0 |

### Validation Features

* Prevents invalid reroll input
* Prevents out-of-range indices
* Prevents rerolling the same die twice in one round

---

## 2️⃣ Dice Grid Puzzle

### Objective

Fill a **3×3 grid** and earn the highest total score from all rows and columns.

### How It Works

* The game lasts **9 turns**
* Each turn rolls **1 die**
* The player chooses a position using **row** and **column** values
* Valid coordinates are **0 to 2**
* The selected cell must be empty
* After all 9 turns, the game scores all **3 rows** and **3 columns**

### Line Scoring Rules

| Result                           | Score |
| -------------------------------- | ----: |
| Three of a kind                  |    15 |
| Straight (3 consecutive numbers) |    12 |
| One pair                         |     8 |
| No match                         |     5 |

### Validation Features

* Rejects non-numeric input
* Rejects coordinates outside **0–2**
* Rejects already occupied cells

---

## 3️⃣ Dice Codebreaker

### Objective

Guess the hidden **4-number code** in at most **10 attempts**.

### How It Works

* The system generates a secret code of **4 numbers**
* Each number is between **1 and 6**
* Duplicate numbers are allowed
* After each guess, feedback is shown:

  * **Correct position** = correct number in the correct place
  * **Correct number (wrong position)** = correct number but in the wrong place
* Each number can only be matched once during feedback calculation

### Accepted Input Formats

```text
2334
2 3 3 4
```

### End Conditions

* The player wins by getting **4 correct positions**
* The player loses after **10 unsuccessful attempts**
* If the player loses, the secret code is displayed

---

## 4️⃣ Dice Battle

### Objective

Defeat the computer by reducing its HP to **0** before your own HP reaches **0**.

### Starting Conditions

* **Player HP = 20**
* **Computer HP = 20**

### Available Actions

#### Attack

* Rolls **1 to 3 dice**
* Damage = total of the dice
* If all rolled dice are **6**, the attack becomes a **critical hit** and the damage is doubled

#### Defend

* Reduces incoming damage by **50%** for that round

#### Heal

* Rolls exactly **2 dice**
* Restores HP equal to the total rolled

### Turn Resolution

* Both sides choose an action each round
* The player’s attack is resolved first
* If the computer is defeated immediately, it cannot attack back in that round
* Healing is applied after attack resolution
* Defend status is cleared at the end of the round

### Computer Behaviour

The computer uses simple logic:

* more likely to **heal** when HP is low
* may **defend** when under pressure
* otherwise, it usually **attacks**

---

## 👤 Player Profiles and Statistics

The system includes a **persistent profile feature**.

### Username System

* At startup, the player enters a username
* Existing usernames are loaded from storage
* New usernames create a new profile

### Stored Data

Each player profile keeps track of:

* Username
* Total games played
* Highest and latest score in **Dice Patterns Challenge**
* Highest and latest score in **Dice Grid Puzzle**
* Best and latest result in **Dice Codebreaker**
* Wins and losses in **Dice Battle**
* Last played date and time
* Recent history for each game (**up to 10 entries**)

### Data Storage

* Data is saved in **`users.txt`**
* Player information is updated after gameplay and on exit

---

## 📊 View Player Stats Menu

```text
=== View Player Stats ===
1. Dice Pattern
2. Dice Grid
3. Dice Codebreaker
4. Dice Battle
0. Back
```

### What the Player Can View

* **Dice Pattern** → highest score, latest score, recent 10 scores
* **Dice Grid** → highest score, latest score, recent 10 scores
* **Dice Codebreaker** → best result, latest result, recent 10 outcomes
* **Dice Battle** → wins, losses, recent 10 battle records

This feature makes the project more complete because it tracks progress over time instead of only running games once.

---

## 🧩 Main Classes

| Class                      | Purpose                                                        |
| -------------------------- | -------------------------------------------------------------- |
| `Menu.java`                | Handles login, menu navigation, stats menu, and game launching |
| `DicePatternGame.java`     | Contains the logic for Dice Patterns Challenge                 |
| `DiceGridGame.java`        | Contains the logic for Dice Grid Puzzle                        |
| `DiceCodebreakerGame.java` | Contains the logic for Dice Codebreaker                        |
| `DiceBattle.java`          | Contains the battle game logic                                 |
| `BattlePlayer.java`        | Represents a battle participant with HP and defend status      |
| `UserProfile.java`         | Stores player statistics and game history                      |
| `UserManager.java`         | Loads and saves player profiles using file storage             |

---

## 💡 Key Programming Features

This project demonstrates:

* object-oriented programming
* menu-driven console interaction
* reusable classes and methods
* random number generation
* input validation
* arrays and collections
* simple AI behaviour
* file handling and persistence

---

## ▶️ How to Run the Program

1. Open the project in **IntelliJ IDEA** or another Java IDE
2. Make sure all source files are in the **`src`** folder
3. Run **`Menu.java`**
4. Enter a username
5. Choose a game or view player statistics

---

## ✅ Conclusion

This Dice Game Project is a **multi-game Java console application** with:

* four playable mini games
* multiple user profiles
* persistent saved data
* statistics tracking across sessions

Compared with a basic game menu, this version is more complete because it supports **player progress**, **history tracking**, and **data storage between runs**.

Overall, the project demonstrates practical use of Java programming concepts such as **classes**, **methods**, **validation**, **random generation**, and **file handling**.

Sina, Harvey, Ege, Thai Son
