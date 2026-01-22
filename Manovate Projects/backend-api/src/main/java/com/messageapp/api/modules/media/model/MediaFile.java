package com.messageapp.api.modules.media.model;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

import java.nio.file.Path;
import java.time.LocalDateTime;

@Entity
@Table(name = "media_files")
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "uploaded_by", nullable = false)
    private Long uploadedBy;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    public void setFileName(String storedFileName) {
    }

    public void setFileType(@Nullable String contentType) {
    }

    public void setFileSize(long size) {
    }

    public void setUploadedBy(Long id) {
    }

    public void setUploadedAt(LocalDateTime now) {
    }

    public Long getId() {
        return 0L;
    }

    public String getFileType() {
        return "";
    }

    public Object getUploadedBy() {
        return null;
    }

    public Path getFileName() {
        return null;
    }

    // getters & setters
}
