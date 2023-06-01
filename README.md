# Tetris-bot-2
The branch is for the new way the algorithm will be working  
Changes to be made:  
-Boardstate is stored as an object, not a 2D array to allow for more sophisticated things like having piece movement and score all in one datatype  
-Cost is now score, so that bad things have negative values and good things have positive values, just makes things simpler  
-Holes will be calculated for in a better way, not that I know what way that is yet  
-Wells will probably not be calculated anymore, due to the algorithm covering them up cause of how painful it is to have a well  
-Lookahead will actually be implemented  
-More weights inspired from coldclear will be added such as the genius scoring based on number of lines cleared and scoreing based on board height  
-The algorithm will screenshot a larger window as to find board, hold, current, and queue all in one go
