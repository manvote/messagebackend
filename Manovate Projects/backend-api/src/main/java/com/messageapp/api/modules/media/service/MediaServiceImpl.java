package com.messageapp.api.modules.media.service;

import com.messageapp.api.common.exceptions.ApiException;
import com.messageapp.api.modules.media.dto.MediaUploadResponseDto;
import com.messageapp.api.modules.media.model.MediaFile;
import com.messageapp.api.modules.media.repository.MediaRepository;
import com.messageapp.api.modules.user.model.User;
import com.messageapp.api.modules.user.repository.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final BlockService.UserService userService;

    private final Path uploadDir = Paths.get("uploads");

    @Override
    public MediaUploadResponseDto upload(MultipartFile file, String phone) throws Exception {

        if (file.isEmpty()) {
            throw new ApiException("FILE_EMPTY");
        }

        Files.createDirectories(uploadDir);

        User user = userService.getByPhone(phone);

        String storedFileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path target = uploadDir.resolve(storedFileName);
        Files.copy(file.getInputStream(), target);

        MediaFile media = new MediaFile();
        media.setFileName(storedFileName);
        media.setFileType(file.getContentType());
        media.setFileSize(file.getSize());
        media.setUploadedBy(user.getId());
        media.setUploadedAt(LocalDateTime.now());

        mediaRepository.save(media);

        return new MediaUploadResponseDto(
                media.getId(),
                media.getFileType(),
                "/api/media/" + media.getId()
        );
    }

    @Override
    public Resource getMedia(Long mediaId, String phone) throws Exception {

        MediaFile media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new ApiException("MEDIA_NOT_FOUND"));

        User user = userService.getByPhone(phone);

        if (!media.getUploadedBy().equals(user.getId())) {
            throw new ApiException("ACCESS_DENIED");
        }

        Path path = uploadDir.resolve(media.getFileName());
        return new UrlResource(path.toUri());
    }
}
