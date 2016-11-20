package org.test.dbservice.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Taras on 20.11.2016.
 */
@Entity
@Table(name = "Files", schema = "project_cornar", catalog = "cornar")
public class FilesEntity {
    private int fileid;
    private String fileName;
    private byte[] fileData;

    @Id
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
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_data", nullable = true)
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (fileid != that.fileid) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (!Arrays.equals(fileData, that.fileData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileid;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(fileData);
        return result;
    }
}
