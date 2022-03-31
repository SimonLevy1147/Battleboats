public class Cell {
    public int row;
    public int col;
    public char status;

    public Cell(int row, int col, char status) {
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public char get_status() {
        return status;
    }

    public void set_status(char c) {
        this.status = c;
    }

    @Override
    public String toString() {
        return  "row:" + row +
                " col:" + col +
                " status:" + status;
    }
}