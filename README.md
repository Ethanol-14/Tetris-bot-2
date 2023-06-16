# Tetris-bot-2
The branch is for the new way the algorithm will be working  
Changes to be made:  
-Boardstate is stored as an object, not a 2D array to allow for more sophisticated things like having piece movement and score all in one datatype (done)  
-Cost is now score, so that bad things have negative values and good things have positive values, just makes things simpler (done)  
-Holes will be calculated for in a better way, not that I know what way that is yet (done)  
-Wells will probably not be calculated anymore, due to the algorithm covering them up cause of how painful it is to have a well (done)  
-Lookahead will actually be implemented (done)  
-More weights inspired from coldclear will be added such as the genius scoring based on number of lines cleared and scoreing based on board height  
-The algorithm will screenshot a larger window as to find board, hold, current, and queue all in one go  

Anyways here's some good ol reliable values that worked well for korea stacking  
private static final int hole = -80;  
private static final int holeCover = -10;  
private static final int bump = -6;  
private static final int stackSize = -3;  
private static final int stackSizeSquared = -2;  
private static final int[] clears = {0, -70, -50, -30, 80};  
