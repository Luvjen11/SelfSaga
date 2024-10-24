package jennifer.SelfSaga.Task;

public enum TaskType {
    DAILY(5),
    ROUTINE(10);

    private int xpValue;

    TaskType(int xpValue) {
        this.xpValue =xpValue;
    }

    public int getXpValue() {
        return xpValue;
    }
}
