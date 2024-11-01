package jennifer.SelfSaga.Goal;

public enum GoalType {

    SIMPLE(100),
    MODERATE(250),
    COMPLEX(500);

    private final int points;

    GoalType(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
