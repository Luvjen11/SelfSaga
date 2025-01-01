package jennifer.SelfSaga.Task;

public enum TaskType {

    DAILY(10),
    ROUTINE(15),
    JOURNALING_PROMPTS(20),
    CREATIVE_ACTIVITIES(15),
    AFFIRMATIONS(5),
    MINDFULNESS_MEDITATION(20),
    SELF_CARE_ROUTINES(10),
    GRATITUDE_EXERCISES(10),
    CONNECTING_WITH_NATURE(15),
    HEALTHY_BOUNDARIES(20),
    LETTER_WRITING(25);

    private int xpValue;

    TaskType(int xpValue) {
        this.xpValue =xpValue;
    }

    public int getXpValue() {
        return xpValue;
    }
}
