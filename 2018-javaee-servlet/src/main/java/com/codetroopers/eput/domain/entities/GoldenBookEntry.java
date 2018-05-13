package com.codetroopers.eput.domain.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
public class GoldenBookEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String content;
    private Integer note;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public GoldenBookEntry() {
        this.createdAt = new Date();
        this.note=0;
    }

    public GoldenBookEntry(final String author, final String content) {
        this(author, content, new Date());
    }

    public GoldenBookEntry(final String author, final String content, final Date createdAt) {
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
       
    }
    
    public GoldenBookEntry(final String author, final String content,final int note, final Date createdAt) {
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.note=note;
    }

    /************************** GETTER / SETTERS ****************************/
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}
    
    
}
