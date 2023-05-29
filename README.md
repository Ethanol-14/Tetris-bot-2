# Tetris-bot-2
This branch is for a large variation in the way the algorithm is coded. The following changes are planning to be made:  
-The algorithm decides how to stack via weights, and not board counting  
-The boardstates will be objects, stored in the state after the piece is placed and the line is cleared, including which piece was placed and where  
-There will be more sophisticated weights for the cost function  
-The bot will look at the queue to make better decisions  
-The program will screenshot a larger portion of the screen, not just the board, to enable reading the current piece and piece queue in one screenshot  

Anyways here's some good ol reliable values that worked well for korea stacking  
private static short holeCost = 30;  
private static short holeSeverity = 10;  
private static short holeCostDecayRate = 3;  
private static short bumpCost = 3;  
private static short lowCost = 2;  
private static short wellCost = 8;  
private static short lineClearReward = 8;  
