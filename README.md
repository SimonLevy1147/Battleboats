# Battleboats

Battleboats is a probability-based board game that challenges the user to locate enemy boats hidden on a rectangular grid. The purpose of the game is to locate and destroy every enemy Boat in the least number of guesses.

    The user starts by defining the size of the gameboard, anywhere from 3x3 to 10x10. The sides do not have to be square. 

    The number and size of the boats placed on the board depends on the size of the board. The locations and orientations 
    of the boats is randomized every game.

    Every turn the player gets a chance to fire and try to hit one of the ships. If they fire out of bounds or at a spot 
    already fired at, there is a penalty. The objective of the game is to sink all the enemy ships by hitting all the cells 
    that compose the ship in as few shots as possible.

    There are also two powerups the player can use: the missile, which hits a 3x3 square centered on the coordinates entered, 
    and the drone, which scans a row or column the player chooses and tells them how many boat cells are in that row, 
    including previously hit and/or sunk cells.

    There is also a debug mode which prints the entire map, including the locations of all the ships every turn. The player 
    is prompted to enter debug mode before the game starts. If the player is not in debug mode, only previously fired on or 
    scanned cells are revealed on the map printed after every turn.
