package platform.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "code_snippet")
public class Code {

    @Id
    @Column(name = "snippet_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "time")
    private Long time;

    @Column(name = "views")
    private Long views;

    @Column(name = "is_secret")
    private Boolean isSecret;

    public Code() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Boolean getSecret() {
        return isSecret;
    }

    public void setSecret(Boolean secret) {
        isSecret = secret;
    }
}
