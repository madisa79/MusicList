package com.bignerdranch.android.musiclist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pop {

    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("trackPrice")
    @Expose
    private Float trackPrice;
    @SerializedName("collectionPrice")
    @Expose
    private Float collectionPrice;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;

    public Boolean getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Float trackPrice) {
        this.trackPrice = trackPrice;
    }

    public Float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

}