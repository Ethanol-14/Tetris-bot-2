# Tetris-bot-2
Now it tries to go for tetrises instead of just clearing lines

SIGNIFICANTLY BETTER than Tetris bot

Tetris bot 2 is able to achieve roughly the same stacking quality as tetris bot 1  
Except it doesn't need to hold to do so  
Which is way better because holding makes your life way easier but this bot can kind of manage without it  
(Although, that's not saying much, since the stacking quality of tetris bot 1 is total shit)  
Just wait till it gets previews :)  
The cost function is alos more sophisticated  

Anyways here's some good ol reliable values that worked well for korea stacking  
private static short holeCost = 30;  
private static short holeSeverity = 10;  
private static short holeCostDecayRate = 3;  
private static short bumpCost = 3;  
private static short lowCost = 2;  
private static short wellCost = 8;  
private static short lineClearReward = 8;  
