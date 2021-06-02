package com.myproject.novel.local.db.entities;

import com.myproject.novel.model.AuthorModel;
import com.myproject.novel.model.ChapterModel;
import com.myproject.novel.model.CommentModel;
import com.myproject.novel.model.TagModel;

public class NovelEntity {


    private int novelId;
    private String novelTitle;
    private String novelDescription;
    private String novelCoverPhotoUrl;
    private int novelTotalChapter;
    private String novelView;
    private int novelStatus;
    private AuthorModel author;
    private TagModel tag;
    private float novelRate;
    private String novelLike;

    private CommentModel commentModel;
    private ChapterModel chapterModel;

    public NovelEntity() {
    }

    public NovelEntity(int novelId, String novelTitle, String novelDescription, String novelCoverPhoto) {
        this.novelId = novelId;
        this.novelTitle = novelTitle;
        this.novelDescription = novelDescription;
        this.novelCoverPhotoUrl = novelCoverPhoto;
    }

    public NovelEntity(int novelId, String novelTitle, String novelDescription, String novelCoverPhoto, int novelTotalChapter, String novelView, int novelStatus, AuthorModel author, TagModel tag, float novelRate, String novelLike) {
        this.novelId = novelId;
        this.novelTitle = novelTitle;
        this.novelDescription = novelDescription;
        this.novelCoverPhotoUrl = novelCoverPhoto;
        this.novelTotalChapter = novelTotalChapter;
        this.novelView = novelView;
        this.novelStatus = novelStatus;
        this.author = author;
        this.tag = tag;
        this.novelRate = novelRate;
        this.novelLike = novelLike;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    public String getNovelTitle() {
        return novelTitle;
    }

    public void setNovelTitle(String novelTitle) {
        this.novelTitle = novelTitle;
    }

    public String getNovelDescription() {
        return novelDescription;
    }

    public void setNovelDescription(String novelDescription) {
        this.novelDescription = novelDescription;
    }

    public String getNovelCoverPhotoUrl() {
        return novelCoverPhotoUrl;
    }

    public void setNovelCoverPhotoUrl(String novelCoverPhoto) {
        this.novelCoverPhotoUrl = novelCoverPhoto;
    }

    public int getNovelTotalChapter() {
        return novelTotalChapter;
    }

    public void setNovelTotalChapter(int novelTotalChapter) {
        this.novelTotalChapter = novelTotalChapter;
    }

    public String getNovelView() {
        return novelView;
    }

    public void setNovelView(String novelView) {
        this.novelView = novelView;
    }

    public int getNovelStatus() {
        return novelStatus;
    }

    public void setNovelStatus(int novelStatus) {
        this.novelStatus = novelStatus;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public TagModel getTag() {
        return tag;
    }

    public void setTag(TagModel tag) {
        this.tag = tag;
    }

    public float getNovelRate() {
        return novelRate;
    }

    public void setNovelRate(float novelRate) {
        this.novelRate = novelRate;
    }

    public String getNovelLike() {
        return novelLike;
    }

    public void setNovelLike(String novelLike) {
        this.novelLike = novelLike;
    }
}
