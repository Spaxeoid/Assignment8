4,4			//This line contains the (w=width,h=height) of the labyrinth in terms of # of squares
1,1,1,1		//The next h rows should be w long.  Each value corresponds to the wall ABOVE a square. 1=WALL, 0=NO WALL
0,0,0,0
1,0,0,0
0,0,1,1
1,1,0,1		//The next h rows should be w long.  Each value corresponds to the wall LEFT of a square. 1=WALL, 0=NO WALL
1,0,1,1
1,0,1,0
1,1,0,0

//The maze above ends up looking something like this:
@@@@@@@@@
@ @   @ @
@   @ @ @
@@@ @   @
@   @@@@@
@ @     @
@@@@@@@@@

//The weighted maze will have a number > 1 to reprement the weight of the walk way. 
4,4        //This line contains the (w=width,h=height) of the labyrinth in terms of # of squares
1,1,1,1    //The next h rows should be w long.  Each value corresponds to the wall ABOVE a square. 1=WALL, 0=NO WALL
2,2,2,2
1,2,20,2
2,5,2,1    //The next h rows should be w long.  Each value corresponds to the wall LEFT of a square. 1=WALL, 0=NO WALL
1,1,2,2
1,2,1,1
1,2,1,20
1,1,2,2

//The maze above ends up looking something like this:

|---|---|---|---|
| . | . 2 . 2 . |   
| 2 | 2 | 2 | 2 |
| . 2 . 1 . 1 . |
|---| 2 | 20| 2 |
| . 2 . 1 . 20. |
| 2 | 5 | 2 | 1 |
| . | . 2 . 2 . |
|---|---|---|---|
  
//The maze above ends up looking something like this in terms of vertex (.) and the edge. 
                             
  .   . 2 . 2 .     
  2   2   2   2  
  . 2 . 1 . 1 .  
         2   20  2  
  . 2 . 1 . 20.  
  2   5   2   1  
  .   . 2 . 2 .  
                 
          
// the room coordinate for the rooms are: 
                             
(0,0) (1,0) (2,0) (3,0)
(0,1) (1,1) (2,1) (3,1)
(0,2) (1,2) (2,2) (3,2)
(0,3) (1,3) (2,3) (3,3)

  
 