# 🎮 Java Tic Tac Toe Projects — Normal & Dynamic

This repository contains two console-based Java implementations of the classic game Tic Tac Toe, created as part of a university assignment at McMaster University. Both projects emphasize object-oriented design, modular code structure, and clean logic handling.

---

## 🗂️ Projects

### ✅ Project 1 — Normal (Classic Tic Tac Toe)

A traditional 3x3 grid version of Tic Tac Toe, featuring two-player gameplay with win and draw detection.

**Key Features:**
- Fixed 3x3 board
- Two-player mode
- Win and draw detection
- Input validation
- Object-oriented structure with separate classes for:
  - `Board`: Handles the game board state and win checks
  - `Player`: Stores player symbols and turn logic
  - `Game`: Manages the game flow
  - `Main`: Entry point

---

### 🔄 Project 2 — Dynamic (Customizable Tic Tac Toe)

An enhanced version of the game where players can specify the board size and how many tiles are needed in a row to win (e.g., 4 in a row on a 5x5 board).

**Key Features:**
- User-defined board size (e.g., 4x4, 5x5, 7x7, etc.)
- Custom win condition (e.g., 3, 4, or 5 in a row)
- Scalable logic for win detection in all directions:
  - Horizontal
  - Vertical
  - Diagonal (both directions)
- Reuses and extends the same clean object-oriented structure from Project 1

---

## 🧠 Educational Purpose

These projects were completed as part of a programming course at McMaster University (Assignment 06). The goal was to reinforce key programming concepts through hands-on implementation, including:

- Modular object-oriented programming in Java
- Encapsulation and reusability
- Custom input handling and validation
- Algorithmic thinking for win condition checks

---

## 🛠️ Technologies Used

| Category         | Tools/Languages                         |
|------------------|------------------------------------------|
| Programming      | Java                                     |
| Build Tool       | Maven                                    |
| Execution        | Command-line interface                   |
| IDE              | IntelliJ IDEA / Eclipse / VS Code        |
| Project Layout   | Standard Maven project structure         |

---

## ▶️ How to Run the Projects

Both projects use Maven for compilation and execution.

```bash
cd Normal
mvn compile
mvn exec:java -Dexec.mainClass="myproject.Main"

cd Dynamic
mvn compile
mvn exec:java -Dexec.mainClass="myproject.Main"

