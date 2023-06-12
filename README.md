# Tetris-bot-2
Now it tries to go for tetrises instead of just clearing lines  
(This comes with the lovely added problem of the bot greeding, making holes and then dying)  

SIGNIFICANTLY BETTER than Tetris bot  

Tetris bot 2 is able to achieve roughly the same stacking quality as tetris bot 1  
Except it doesn't need to hold to do so  
Which is way better because holding makes your life way easier but this bot can kind of manage without it  
(Although, that's not saying much, since the stacking quality of tetris bot 1 is total shit)  
Just wait till it gets previews :)  
The cost function is also more sophisticated  

Anyways here's some good ol reliable values that worked well for korea stacking  
private static final int hole = -80;  
private static final int holeCover = -10;  
private static final int bump = -6;  
private static final int stackSize = -3;  
private static final int stackSizeSquared = -3;  
private static final int[] clears = {0, -70, -50, 20, 40};  
