package com.messageapp.api.modules.media.repository;

import com.messageapp.api.modules.media.model.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaFile, Long> {
}
