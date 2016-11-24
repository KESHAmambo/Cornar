package org.test.dbservice.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by Taras on 20.11.2016.
 */
@Entity
@Table(name = "files", schema = "project_cornar", catalog = "cornar")
public class FilesEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int fileid;
    private String file_name;
    private byte[] file_data;
    private Date creation_date;

    @Column(name = "fileid", nullable = false)
    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = -1)
    public String getFileName() {
        return file_name;
    }

    public void setFileName(String file_name) {
        this.file_name = file_name;
    }

    @Basic
    @Column(name = "file_data", nullable = true)
    public byte[] getFileData() {
        return file_data;
    }

    public void setFileData(byte[] fileData) {
        this.file_data = fileData;
    }

    @Basic
    @Column(name="creation_date", nullable = true)
    public Date getCreation_date() {return creation_date;}

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (fileid != that.fileid) return false;
        if (file_name != null ? !file_name.equals(that.file_name) : that.file_name != null) return false;
        if (!Arrays.equals(file_data, that.file_data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileid;
        result = 31 * result + (file_name != null ? file_name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(file_data);
        return result;
    }
}
