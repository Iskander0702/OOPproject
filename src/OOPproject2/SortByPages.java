import java.util.Comparator;

public class SortByPages implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return b.getPages() - a.getPages();
    }
}