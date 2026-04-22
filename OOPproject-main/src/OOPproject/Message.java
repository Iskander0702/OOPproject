package OOPproject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String        messageId;
    private final String        senderLogin;
    private final String        recipientLogin;
    private final String        subject;
    private final String        body;
    private final LocalDateTime sentAt;
    private final boolean       isComplaint;
    private       boolean       isRead;

    public Message(String senderLogin, String recipientLogin,
                   String subject, String body, boolean isComplaint) {
        this.messageId      = java.util.UUID.randomUUID().toString();
        this.senderLogin    = senderLogin;
        this.recipientLogin = recipientLogin;
        this.subject        = subject;
        this.body           = body;
        this.sentAt         = LocalDateTime.now();
        this.isComplaint    = isComplaint;
        this.isRead         = false;
    }
    // ── Getters ───────────────────────────────────────────────────────────────
    public String        getMessageId()      { return messageId; }
    public String        getSenderLogin()    { return senderLogin; }
    public String        getRecipientLogin() { return recipientLogin; }
    public String        getSubject()        { return subject; }
    public String        getBody()           { return body; }
    public LocalDateTime getSentAt()         { return sentAt; }
    public boolean       isComplaint()       { return isComplaint; }
    public boolean       isRead()            { return isRead; }
    public void markAsRead() { this.isRead = true; }
    // ── Object overrides ──────────────────────────────────────────────────────
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(messageId, ((Message) o).messageId);
    }
    @Override
    public int hashCode() { return Objects.hash(messageId); }
    @Override
    public String toString() {
        return String.format("%s | From: %-15s  To: %-15s | Subject: %s%s",
                sentAt.format(FMT),
                senderLogin,
                recipientLogin,
                subject,
                isComplaint ? "  [COMPLAINT]" : "");
    }
}
