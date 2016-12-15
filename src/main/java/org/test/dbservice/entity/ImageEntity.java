package org.test.dbservice.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Taras on 15.12.2016.
 */
@Entity
@Table(name = "image", schema = "project_cornar", catalog = "cornar")
public class ImageEntity {
    private int imageId;
    private Integer userId;
    private byte[] data;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "data", nullable = true)
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (imageId != that.imageId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (!Arrays.equals(data, that.data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
