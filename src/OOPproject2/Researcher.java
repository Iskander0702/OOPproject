import java.util.Comparator;
import java.util.List;

public interface Researcher {
    int getHIndex();
    List<ResearchPaper> getPapers();

    default void printPapers(Comparator<ResearchPaper> c) {
        List<ResearchPaper> papers = getPapers();
        papers.sort(c);
        for (ResearchPaper p : papers) {
            System.out.println(p);
        }
    }
}