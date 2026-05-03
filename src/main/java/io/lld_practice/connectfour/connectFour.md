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

