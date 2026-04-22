import java.util.Comparator;

public class SortByCitations implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return b.getCitations() - a.getCitations();
    }
}