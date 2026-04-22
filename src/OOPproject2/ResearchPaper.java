import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResearchPaper implements Serializable {
    private String title;
    private List<String> authors;
    private String journal;
    private int pages;
    private Date publishDate;
    private int citations;
    private String doi;

    public ResearchPaper(String title, List<String> authors, String journal,
                         int pages, Date publishDate, int citations, String doi) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.publishDate = publishDate;
        this.citations = citations;
        this.doi = doi;
    }

    public int getCitations() {
        return citations;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return title + " | citations: " + citations + " | pages: " + pages;
    }
}