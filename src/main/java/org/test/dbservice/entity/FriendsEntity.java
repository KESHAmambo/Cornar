package org.test.dbservice.entity;

import javax.persistence.*;

/**
 * Created by Taras on 09.12.2016.
 */
@Entity
@Table(name = "friends", schema = "project_cornar", catalog = "cornar")
public class FriendsEntity {
    private int num;
    private int user_id;
    private int friend_id;

    @Id
    @Column(name = "num", nullable = false)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "friend_id", nullable = true)
    public int getFriendId() {
        return friend_id;
    }

    public void setFriendId(int friend_id) {
        this.friend_id = friend_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsEntity that = (FriendsEntity) o;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + user_id;
        result = 31 * result + friend_id;
        return result;
    }
}
