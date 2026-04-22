import java.util.Comparator;

public class SortByDate implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return b.getPublishDate().compareTo(a.getPublishDate());
    }
}