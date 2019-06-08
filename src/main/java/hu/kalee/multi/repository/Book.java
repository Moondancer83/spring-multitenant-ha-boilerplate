package hu.kalee.multi.repository;


import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.kalee.multi.tenant.TenantContext;

@Entity
public class Book {
    private static final Logger LOG = LoggerFactory.getLogger(Book.class);
    @Id
    private Long id;
    private String title;
    private String writer;

    public Book() {
        LOG.info("Current tenant: {}", TenantContext.getCurrentTenant());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(final String writer) {
        this.writer = writer;
    }
}
