import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> publishedPapers;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    public void addParticipant(Object person) throws NotResearcherException {
        if (!(person instanceof Researcher)) {
            throw new NotResearcherException("this person is not a researcher!");
        }
        participants.add((Researcher) person);
    }

    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    @Override
    public String toString() {
        return "project: " + topic + ", participants: " + participants.size();
    }
}