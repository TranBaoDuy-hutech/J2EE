package com.travel3d.vietlutravel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Culture")
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Title", nullable = false, length = 500)
    private String title;

    @Column(name = "Thumbnail", length = 255)
    private String thumbnail;

    @Column(name = "ShortDescription", length = 1000)
    private String shortDescription;

    @Column(name = "VideoUrl", length = 500)
    private String videoUrl;

    @Column(name = "FullDescription", columnDefinition = "TEXT")
    private String fullDescription;

    // ===== Getter & Setter =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
