package org.test.dbservice.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Taras on 15.12.2016.
 */
@Entity
@Table(name = "inbox", schema = "project_cornar", catalog = "cornar")
public class InboxEntity {
    private int messageId;
    private int sender_id;
    private int receiver_id;
    private Timestamp date;
    private String theme;
    private String text;
    private Boolean wasRead;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "sender_id", nullable = true)
    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int senderId) {
        this.sender_id = senderId;
    }

    @Basic
    @Column(name = "receiver_id", nullable = true)
    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "theme", nullable = true, length = -1)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "text", nullable = true, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "was_read", nullable = true)
    public Boolean getWasRead() {
        return wasRead;
    }

    public void setWasRead(Boolean wasRead) {
        this.wasRead = wasRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InboxEntity that = (InboxEntity) o;

        if (messageId != that.messageId) return false;
        if (sender_id != that.sender_id) return false;
        if (receiver_id != that.receiver_id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (wasRead != null ? !wasRead.equals(that.wasRead) : that.wasRead != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + sender_id;
        result = 31 * result + receiver_id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (wasRead != null ? wasRead.hashCode() : 0);
        return result;
    }
}
