package jennifer.SelfSaga.Chapter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jennifer.SelfSaga.Quest.Quest;
import jennifer.SelfSaga.Task.Task;

@Entity
@Table(name="chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String narrative;
    private String storyOutline;
    private String goalSummary;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Chapter() {

    }

    public Chapter(String title, String narrative, String storyOutline, String goalSummary, boolean completed) {

        this.title = title;
        this.narrative = narrative;
        this.storyOutline = storyOutline;
        this.goalSummary = goalSummary;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNarrative() {
        return narrative;
    }

    public String getStoryOutline() {
        return storyOutline;
    }

    public String getGoalSummary() {
        return goalSummary;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setIsCompleted(boolean completed) {
        this.completed = completed;
    }
}
