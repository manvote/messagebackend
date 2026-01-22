package com.messageapp.api.modules.media.service;

import com.messageapp.api.modules.media.dto.MediaUploadResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaUploadResponseDto upload(MultipartFile file, String phone) throws Exception;

    Resource getMedia(Long mediaId, String phone) throws Exception;
}
