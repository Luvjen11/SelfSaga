package jennifer.SelfSaga.Task;

public enum TaskType {

    DAILY(10),
    ROUTINE(15);

    private int xpValue;

    TaskType(int xpValue) {
        this.xpValue =xpValue;
    }

    public int getXpValue() {
        return xpValue;
    }
}
