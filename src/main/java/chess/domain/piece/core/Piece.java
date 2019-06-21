package chess.domain.piece.core;

import chess.domain.direction.Navigator;
import chess.domain.direction.Route;
import chess.domain.direction.core.Square;

import java.util.Objects;

public abstract class Piece {
    Team team;
    Type type;
    protected Navigator navigator;

    public Piece(Team team, Type type) {
        this(team, type, null);
    }

    public Piece(Team team, Type type, Navigator navigator) {
        this.team = team;
        this.type = type;
        this.navigator = navigator;
    }

    public Route getRoute(Square source, Square target) {
        Route route = navigator.getWay(source, target);
        if (route == null) {
            throw new IllegalArgumentException("갈 수 없습니다.");
        }
        return route;
    }

    public Team getTeam() {
        return team;
    }

    public Type getType(){
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                type == piece.type &&
                Objects.equals(navigator, piece.navigator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, type, navigator);
    }
}
