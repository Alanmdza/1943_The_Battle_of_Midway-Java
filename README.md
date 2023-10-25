# 1943: The battle of midway- Java Game

## Project Description

This project serves as the final project for the Object-Oriented Programming subject of the Computer Engineering degree. The goal of this project was to create a Java game inspired by the classic game "1943" for the NES.

The project was developed in response to the following challenge:

### Final project for the Object-Oriented Programming 2023
**1943: The battle of midway**

The objective of this project is to construct a software platform that allows the execution of various video games. This platform should implement basic elements common to all video games, making it easy to develop new games and add them to the platform. One of the games to be developed as part of this project is "1943: La Batalla de Midway."

In "1943: The battle of midway" the player takes control of a U.S. Air Force warplane, tasked with eliminating computer-controlled enemies belonging to the Empire of Japan. The goal is to attack and destroy enemy air force planes, progressing through different game levels to reach and destroy the flagship of the fleet: Yamato.

Each mission consists of two phases: an approach phase and an attack phase on the target. To advance to the next mission, the player's P-38 must inflict damage on the "Final Boss" between 70% and 100%. A lower percentage requires repeating the mission. After completing each mission, the energy tank is refilled, and reward points are awarded based on the percentage of destroyed enemies.

The game ends when the player destroys the flagship or when their energy is depleted. The player's plane loses energy when:
- Hit by enemy ammunition
- Collides with another plane
- Mission time elapses
- Special attacks (lightning and tsunami) are used

The player can enhance their plane by "capturing" different bonuses, including power-ups or limited-duration weapons (captured bonuses should be indicated on-screen). Collecting the same type of bonus twice increases its power and duration. The types of bonuses include:

**Power-ups:**
- POW: Limited energy recharge.
- Auto: Rapid-fire shots.
- Super Shell: Continuous fire by holding down the shoot key.
- Ninja Star: Fully replenishes energy.

**Weapons:**
- Shotgun: Wide range, destroys enemy ammunition.
- 3-Barrel Machine Gun: Wide range, similar to basic fire.
- Laser: Deals the most damage, penetrates armor.
- Reinforcements: Two reinforcement planes fly alongside the P-38 and fire together with the player's plane. They can be destroyed by the enemy.

### Controls

The player's warplane can be controlled using the following keys:
1. Arrow keys: Movement in all eight possible directions.
2. Key X: Shoots (bullet type varies depending on the weapon).
3. Key Z: Activates special attacks (Lightning and Tsunami).
4. Spacebar: Pauses the game.
   
All keys can be reconfigured as needed.

### Special Attacks

Using any of these special attacks depletes the energy level:
- Lightning: Only works during aerial combat. Effect: Destroys all small planes.
- Tsunami: Only works during surface combat. Effect: Destroys all small planes, damages ships, and momentarily stops vertical screen movement.

### Enemies

Enemies attack in formations of planes and various-sized ships.

### Red Special Aircraft

These special aircraft fly but do not shoot ammunition. Destroying the entire formation causes them to drop an "Item" that provides a power-up. If you keep shooting the power-up, it alternates between different supplies that can be captured by the player-controlled warplane.

### End-of-Level Bosses (Mission Objectives)

The game features multiple end-of-level bosses, each serving as an objective for a mission. They include:
- Tone (Heavy Cruiser)
- Kaga (Aircraft Carrier)
- Ayako 1 (Heavy Bomber)
- Fuso (Battleship)
- Akagi (Aircraft Carrier)
- Daihiryu 1 (Squadron Leader Aircraft)
- Ise (Battleship)
- Hiryu (Aircraft Carrier)
- Ayako 2 (Heavy Bomber)
- Mutsu (Battleship)
- Daihiryu 2 (Squadron Leader Aircraft)
- Yamashiro (Battleship)
- Soryu (Aircraft Carrier)
- Ayako 3 (Heavy Bomber)
- Nagato (Battleship)
- Yamato (Battleship and FINAL BOSS, mandatory)

### Configuration

The game should include a configuration screen for various game parameters, which must be stored. The configured values should only take effect when starting a new game. Configurable parameters should include:
1. Full-screen or windowed mode (default: windowed).
2. Sound on/off (default: on).
3. Plane selection (default: the original, with predefined options).
4. Keybindings (default: arrow keys for movement, space for pause, X for shooting, Z for special attack, etc.).
5. Music selection (default: original theme).

The interface should also feature a "RESET" button to revert all parameters to their default values.

### Ranking

The game should save all obtained scores and display only the top 10. These should be shown in the interface before starting the game and at the end of each game. Each ranking entry should include:
- Score
- Player's name
- Date

### Optional Features

1. Hidden items that are uncovered by shooting a specific section of the screen. Collecting them allows the player to advance to the next level.
2. The player's plane can perform a "loop" (using the combination of keys X + Z), during which it can dodge enemies and their bullets.
3. 
![image](https://github.com/Alanmdza/1943_The_Battle_of_Midway-Java/assets/126357766/57d014c8-034e-4e54-8335-1f6ed76cb5ec)

## Getting Started

To get started with the game, follow these steps:

1. Clone the repository to your local machine.
2. Use the Run Task of Gradle
3. Select 1943 and click the play button

Enjoy the nostalgic experience of "1943: La Batalla de Midway" and relive the classic NES gameplay in a Java environment.

![nivel0](https://github.com/Alanmdza/1943_The_Battle_of_Midway-Java/blob/main/src/main/resources/images/1984/Nivel0.png)


