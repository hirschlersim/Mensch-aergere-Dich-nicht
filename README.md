# Mensch, Ärgere Dich Nicht (Digital Ludo Game)

An object-oriented Java implementation of the classic board game *"Mensch, ärgere dich nicht"* (Ludo), developed as a collaborative group project for the **Software Engineering (SS23)** university course.

## 👥 Team & Course Information

- **Course:** Software Engineering (Summer Semester 2023)
- **Group:** Gruppe 4
    

## 🚀 Project Overview & Features

The core objective of this project was to develop a digital, text-based implementation of the traditional "Mensch, ärgere dich nicht" board game using Java.

### Key Features

- **Multiplayer & CPU Support:** The game accommodates multiple players, allowing fluid combinations of human players and automated CPU opponents.
- **Authentic Rule Enforcement:** Includes fully implemented game rules such as rolling a 6 to move pieces out of the base, consecutive turns on rolling a 6, safe home row constraints, and capturing/kicking opponent pieces back to the base.
- **Simulated Human Pacing:** CPU-controlled opponents feature artificial calculation delays between actions to simulate a more natural gameplay rhythm.
- **Dynamic Console Output:** A text representation of the game board updates dynamically and prints out to the console directly after every single action.
    

## 📐 Architecture & System Design

The application follows object-oriented design principles to separate game state, player behavior, and core mechanics. The codebase is structured around the following key classes:

- **`Game`**: Acts as the main orchestrator that sets up the game environment, coordinates player turns, and handles user inputs.
- **`Board`**: Represents the game board as an array of spaces, handles movement logic, evaluates valid steps, and renders the current layout.
- **`Player`**: An abstract base class defining core player attributes and behaviors.
- **`Human`**: Subclass tailored to manage human input choices and move validations.
- **`CPU`**: Subclass controlling automated opponents with custom target evaluations and delays.
- **`Figure`**: Tracks individual game pieces, identifying their assigned team and positional status (base, field, or home).
- **`Dice`**: Simulates random dice rolls using a random number generator and holds pacing delays.
    

During the architectural blueprint phase, our team fully visualised these dependencies and operational paths through detailed **Use-Case Diagrams**, **Activity Diagrams**, and structural **Class Diagrams**.

## 📈 Team Development Process

### 1. Phased Lifecycle Model

The project development was systematically divided into explicit phases to manage progress effectively:

- **Planning & Design:** Focused on analyzing constraints, compiling the formal *Pflichtenheft* (Requirements Specification), and creating visual UML structural blueprints.
- **Implementation:** Dedicated to setting up the core repository framework and building out the concrete game logic in Java.
- **Testing & Refinement:** Centered on resolving edge-case software bugs, reviewing execution logs, and refining operational details.
- **Project Conclusion:** Culminated in a technical presentation delivered at the end of the semester to showcase the finished system architecture.
    

### 2. Collaboration & Coordination

- **Role Ownership:** Each project phase was managed by designated team members who coordinated the respective tasks and ensured accountability.
- **Sequential Task Planning:** We followed a strict dependency-based schedule where complex tasks were addressed only after preceding foundational milestones were fully cleared.
- **Schedule Alignment:** Team members actively communicated and aligned on individual task blocks, clarifying personal schedules and expected delivery timelines.
- **Progress Syncs:** Regular group meetings were held to review overall progress, discuss ongoing implementations, and collectively resolve integration roadblocks.
- **Version Control:** All technical code modifications and project documentation were regularly committed and synchronized via a shared GitHub repository.
    

## 🛡️ Quality Assurance & Documentation

To ensure high software quality and adherence to strict engineering standards, our group utilized rigorous QA metrics:

- **Static Code Analysis:** Automated tools such as **Checkstyle** and **FindBugs** were integrated into the workflow to enforce consistent formatting standards and proactively catch potential runtime flaws.
- **Comprehensive Testing:** The game was comprehensively verified through manual gameplay playthroughs alongside automated rules checks to ensure faultless behavior.
- **Maintained Documentation:** Complete deliverables were established and committed, containing the full *Pflichtenheft*, visual diagram workflows, and standard code documentation for the primary system classes.
    

## ⚙️ Getting Started

To locally deploy and execute this project, follow these structural execution steps:

1. **Clone the repository** to your local machine.
2. **Open the project** in your preferred Java IDE.
3. **Build the project** to compile the source code framework.
4. **Run the `Game` class** to initialize and start the runtime game loop.
    

## 🎮 How to Play

The execution mechanics follow traditional board layout rules adapted for terminal interactions:

- **Initial State:** The game environment dynamically initializes with all players' pieces situated inside their respective team bases.
- **Turn Rotation:** Players take sequential turns rolling the dice and advancing their pieces across the board array.
- **Piece Selection:** To move a specific piece, input the targeted piece index when prompted by the action console.
- **Core Objective:** The ultimate goal is to safely transition all four assigned pieces from the initial base into the designated home row.
- **Victory Condition:** The first player to successfully guide all four pieces into their final home row wins the game.
    

## 📝 Lessons Learned

Through this collaborative project, our team gained significant insights into:

- Transforming abstract, text-based requirements definitions into functional, object-oriented applications.
- Synchronizing task management, deadlines, and operational expectations within a five-person developer team.
- Leveraging automated code format checkers and static analyzers to ensure uniform standards across decentralized team commits.
    

*Note: This repository is a static archive intended solely for academic portfolio and project showcase purposes.*
