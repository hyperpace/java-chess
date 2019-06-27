package chess.domain.board;

import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.direction.core.TargetStatus;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static chess.domain.board.InitBoard.GENERAL;
import static chess.domain.board.InitBoard.SOLDIER;
import static chess.domain.piece.core.Team.BLACK;
import static chess.domain.piece.core.Team.WHITE;

public class Board {
    static final int MIN_SIZE = 0;
    static final int MAX_SIZE = 8;
    private Map<Square, Piece> board;
    private Team team;

    public Board(Map<Square, Piece> board, Team team) {
        this.board = board;
        this.team = team;
    }

    public static Board drawBoard() {
        Map<Square, Piece> board = Maps.newHashMap();
        board.putAll(GENERAL.generate(0, BLACK));
        board.putAll(SOLDIER.generate(1, BLACK));
        board.putAll(SOLDIER.generate(6, WHITE));
        board.putAll(GENERAL.generate(7, WHITE));

        return drawBoard(board, WHITE);
    }

    public static Board drawBoard(Map<Square, Piece> board, Team team) {
        return new Board(board, team);
    }

    public boolean hasPiece(Square source) {
        return board.containsKey(source);
    }

    boolean hasPiece(Square source, Team team) {
        return board.containsKey(source) && getPiece(source).isTeam(team);
    }

    boolean hasPiece(Square source, Team team, Type type) {
        return board.containsKey(source) && getPiece(source).isTeam(team) && getPiece(source).isType(type);
    }

    boolean hasPiece(Team team, Type type) {
        return board.entrySet().stream()
                .filter(e -> e.getValue().isTeam(team) && e.getValue().isType(type))
                .count() > 0;
    }

    public Piece getPiece(Square source) {
        return board.get(source);
    }

    public TargetStatus getTargetStatus(Square source, Square target) {
        return TargetStatus.valuesOf(getPiece(source), getPiece(target));
    }

    public Board changeBoard(Route route) {
        Piece selectedPiece = board.get(route.getSourceSquare());
        if (!selectedPiece.isTeam(team)) {
            throw new IllegalArgumentException(team.getTeam() + "팀의 말만 움직일 수 있습니다.");
        }
        if (!route.canMove(this)) {
            throw new IllegalArgumentException(selectedPiece.toString() + "말을 움직일 수 없습니다.");
        }
        return movePiece(route.getSourceSquare(), route.getTargetSquare());
    }

    private Board movePiece(Square source, Square target) {
        Map<Square, Piece> copyBoard = board.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        copyBoard.put(target, copyBoard.get(source).move());
        copyBoard.remove(source);

        return drawBoard(copyBoard, team.equals(WHITE) ? BLACK : WHITE);
    }

    public Team getTeam() {
        return this.team;
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
