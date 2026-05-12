# Connect Four - Requirements and Design Notes

## Requirements Summary
- Two players take turns dropping discs into a 7-column, 6-row board.
- A disc falls to the lowest available row in the chosen column.
- The game ends when:
  - A player gets four discs in a row (vertical, horizontal, or diagonal).
  - The board is full (draw).
- Invalid moves must be rejected:
  - Dropping in a full column.
  - Moving out of turn.
  - Moving after the game is over.
- Single game instance, backend logic only.
- No move history or undo.
- Board size is fixed at 7x6.

## Core Entities
### Game
**Responsibility:** Orchestrates the game. Owns the board, tracks turns, validates moves, updates game state.

**Attributes:**
- `board: Board`
- `player1: Player`
- `player2: Player`
- `state: GameState` (IN_PROGRESS, WON, DRAW)
- `winner: Player | null`

**Methods:**
- `makeMove(player, column): boolean`
- `getGameState(): GameState`
- `getCurrentPlayer(): Player`
- `getWinner(): Player | null`

### Board
**Responsibility:** Manages the 7x6 grid, places discs, checks for win conditions.

**Attributes:**
- `rows: int = 6`
- `columns: int = 7`
- `grid: DiscColor[rows][columns]`

**Methods:**
- `canPlace(column): boolean`
- `placeDisc(column, color): int` (returns row)
- `isFull(): boolean`
- `checkWin(row, column, color): boolean`
- `getCell(row, column): DiscColor | null`

### Player
**Responsibility:** Simple data holder for identity and disc color.

**Attributes:**
- `name: String`
- `color: DiscColor`

**Methods:**
- `Player(name, color)`
- `getName(): String`
- `getColor(): DiscColor`

## Enums
### GameState
- `IN_PROGRESS`
- `WON`
- `DRAW`

### DiscColor
- `RED`
- `YELLOW`

## Direction Vectors (row, col) for Win Checks

We use `(row, col)` with `row` increasing **downward** and `col` increasing **to the right`**.
That means a direction is a pair `(dr, dc)`.

Legend:
- `(0, 1)`  = move right (horizontal)
- `(1, 0)`  = move down (vertical)
- `(1, 1)`  = move down-right (diagonal)
- `(1, -1)` = move down-left (diagonal)

ASCII grid (numbers are `row,col`):
```
        col ->  0      1      2      3
row 0       (0,0)  (0,1)  (0,2)  (0,3)
     1       (1,0)  (1,1)  (1,2)  (1,3)
     2       (2,0)  (2,1)  (2,2)  (2,3)
     3       (3,0)  (3,1)  (3,2)  (3,3)

row increases downward, col increases to the right
```

Vector arrows from a cell `(r,c)`:
```
          (-1,0)
             ^
             |
(-1,-1)  <- (r,c) ->  (0,1)
             |
             v
           (1,0)

Diagonal down-right: (1,1)
Diagonal down-left : (1,-1)
```

For Connect Four, check 4 lines by scanning both directions around the last move:
- Horizontal: `(0,1)` and `(0,-1)`
- Vertical: `(1,0)` and `(-1,0)`
- Diagonal \ : `(1,1)` and `(-1,-1)`
- Diagonal / : `(1,-1)` and `(-1,1)`

Tip: in code, define the 4 base directions and check forward + backward.
