package com.android.news.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This model class used to create the columns in tables.
 */
@Entity(tableName = "model", indices = @Index(value = {"id"}, unique = true))
public class Model {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    public String id;
    @ColumnInfo(name = "by")
    @SerializedName("by")
    @Expose
    private String by;
    @ColumnInfo(name = "descendants")
    @SerializedName("descendants")
    @Expose
    private Integer descendants;
//    @SerializedName("kids")
//    @Expose
//    private List<Integer> kids = null;
    @ColumnInfo(name = "score")
    @SerializedName("score")
    @Expose
    private Integer score;
    @ColumnInfo(name = "time")
    @SerializedName("time")
    @Expose
    private Integer time;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name = "type")
    @SerializedName("type")
    @Expose
    private String type;
    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

//    public List<Integer> getKids() {
//        return kids;
//    }
//
//    public void setKids(List<Integer> kids) {
//        this.kids = kids;
//    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
