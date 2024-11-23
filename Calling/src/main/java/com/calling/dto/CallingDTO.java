package com.calling.dto;

public class CallingDTO {
    private Long id;
    private String subject;
    private String description;

    public CallingDTO() {
    }

    public CallingDTO(Long id, String subject, String description) {
        this.id = id;
        this.subject = subject;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
