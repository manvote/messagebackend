package com.messageapp.api.modules.media.dto;

public class MediaUploadResponseDto {

    private Long id;
    private String fileType;
    private String url;

    public MediaUploadResponseDto(Long id, String fileType, String url) {
        this.id = id;
        this.fileType = fileType;
        this.url = url;
    }

    public Long getId() { return id; }
    public String getFileType() { return fileType; }
    public String getUrl() { return url; }
}
