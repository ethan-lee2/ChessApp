package com.example.androidchess.pieces;


public class Queen extends Piece {
    public Queen(boolean player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.setPieceName("Queen");
    }

    /**
     * Moves the piece from the starting destination to final destination if the move is valid
     * Updates the board accordingly and returns the updated board
     *
     * @param input The string input entered by the user in the main
     * @param chessBoard the chessBoard at the current state of the game
     * @return returns the updated chessBoard with the new move
     */
    public Piece[][] movePiece(String input, Piece[][] chessBoard)
    {
        String[]moveInput = input.split(" ");
        char startingColInput = moveInput[0].charAt(0);
        char startingRowInput = moveInput[0].charAt(1);
        char endingColInput = moveInput[1].charAt(0);
        char endingRowInput = moveInput[1].charAt(1);

        int startingColIndex = letterToIndex(startingColInput) - 1;
        int startingRowIndex = ((startingRowInput - '0') - 8) * -1;
        int endingColIndex = letterToIndex(endingColInput) - 1;
        int endingRowIndex = ((endingRowInput - '0') - 8) * -1;

        this.x = endingRowIndex;
        this.y = endingColIndex;
        if((startingRowIndex % 2 != 0 && startingColIndex % 2 == 0) || (startingRowIndex % 2 == 0 && startingColIndex % 2 != 0))
            chessBoard[startingRowIndex][startingColIndex] = new Tile(false, startingRowIndex, startingColIndex);
        if((startingRowIndex % 2 != 0 && startingColIndex % 2 != 0) || (startingRowIndex % 2 == 0 && startingColIndex % 2 == 0))
            chessBoard[startingRowIndex][startingColIndex] = new Tile(true, startingRowIndex, startingColIndex);


        chessBoard[endingRowIndex][endingColIndex] = this;

        return chessBoard;
    }

    /**
     * Checks if the Queen move is valid
     *
     * @param startingColIndex Column index of the starting coordinate
     * @param startingRowIndex Row index of the starting coordinate
     * @param endingColIndex Column index of the destination coordinate
     * @param endingRowIndex Row index of the destination coordinate
     * @param chessBoard the chessBoard at the current state of the game
     * @return returns true if the move is valid and false if it is invalid
     */
    public boolean validMove(int startingColIndex, int startingRowIndex, int endingColIndex, int endingRowIndex, Piece[][] chessBoard)
    {
        boolean inc = true;

        if((endingColIndex < 0 && endingColIndex > 8) || (endingRowIndex < 0 && endingRowIndex > 8))
            return false;
        if(chessBoard[startingRowIndex][startingColIndex].getPlayer() == chessBoard[endingRowIndex][endingColIndex].getPlayer() &&  !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile"))
            return false;
        //Vertical Movement
        if((startingRowIndex == endingRowIndex) && (startingColIndex != endingColIndex))
        {
            if(startingColIndex < endingColIndex)
                inc = true;
            if(startingColIndex > endingColIndex)
                inc = false;

            if(inc == true)
            {
                for(int i = startingColIndex+1; i < endingColIndex; i++)
                {
                    if(!chessBoard[startingRowIndex][i].getPieceName().equals("Tile"))
                    {
                        return false;
                    }
                }
            }
            else if(inc == false)
            {
                for(int i = startingColIndex-1; i > endingColIndex; i--)
                {
                    if(!chessBoard[startingRowIndex][i].getPieceName().equals("Tile"))
                    {
                        return false;
                    }
                }
            }

        }

        //Horizontal movement
        if((startingRowIndex != endingRowIndex) && (startingColIndex == endingColIndex))
        {
            if(startingRowIndex < endingRowIndex)
                inc = true;
            if(startingRowIndex > endingRowIndex)
                inc = false;

            if(inc == true)
            {
                for(int i = startingRowIndex+1; i < endingRowIndex; i++)
                {
                    if(!chessBoard[i][startingColIndex].getPieceName().equals("Tile"))
                    {
                        return false;
                    }
                }
            }
            else if (inc == false)
            {
                for(int i = startingRowIndex-1; i > endingRowIndex; i--)
                {
                    if(!chessBoard[i][startingColIndex].getPieceName().equals("Tile"))
                    {
                        return false;
                    }
                }
            }

        }

        //Diagonal Movement
        if((startingRowIndex != endingRowIndex) && (startingColIndex != endingColIndex))
        {
            if(Math.abs(startingRowIndex - endingRowIndex) != Math.abs(startingColIndex - endingColIndex))
                return false;
            if(startingRowIndex < endingRowIndex)
                inc = true;
            if(startingRowIndex > endingRowIndex)
                inc = false;

            if(startingRowIndex < endingRowIndex && startingColIndex < endingColIndex)
            {
                int countRow = startingRowIndex +1;
                int countCol = startingColIndex +1;
                while(countRow<endingRowIndex && countCol<endingColIndex)
                {
                    if(!chessBoard[countRow][countCol].getPieceName().equals("Tile"))
                    {

                        return false;

                    }
                    countRow++;
                    countCol++;
                }
            }
            else if(startingRowIndex > endingRowIndex && startingColIndex > endingColIndex)
            {
                int countRow = startingRowIndex -1;
                int countCol = startingColIndex -1;
                while(countRow>endingRowIndex && countCol>endingColIndex)
                {
                    if(!chessBoard[countRow][countCol].getPieceName().equals("Tile"))
                    {

                        return false;

                    }
                    countRow--;
                    countCol--;
                }
            }
            else if(startingRowIndex < endingRowIndex && startingColIndex > endingColIndex)
            {
                int countRow = startingRowIndex +1;
                int countCol = startingColIndex -1;
                while(countRow<endingRowIndex && countCol>endingColIndex)
                {
                    if(!chessBoard[countRow][countCol].getPieceName().equals("Tile"))
                    {

                        return false;

                    }
                    countRow++;
                    countCol--;
                }
            }
            else if(startingRowIndex > endingRowIndex && startingColIndex < endingColIndex)
            {
                int countRow = startingRowIndex -1;
                int countCol = startingColIndex +1;
                while(countRow>endingRowIndex && countCol<endingColIndex)
                {
                    if(!chessBoard[countRow][countCol].getPieceName().equals("Tile"))
                    {

                        return false;

                    }
                    countRow--;
                    countCol++;
                }
            }
        }

        return true;
    }

    public String getPieceID()
    {
        String t="";
        if(this.player == true)
            t = "w";
        else
            t = "b";
        return t+"q";

    }


    /**
     * Prints the Queen piece with color in the console
     */
    public void printPiece()
    {
        if(player == true)
            System.out.print("wQ");
        else
            System.out.print("bQ");
    }

}
