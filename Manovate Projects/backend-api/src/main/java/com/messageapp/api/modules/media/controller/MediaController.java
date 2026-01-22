package com.messageapp.api.modules.media.controller;

import com.messageapp.api.modules.media.dto.MediaUploadResponseDto;
import com.messageapp.api.modules.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<MediaUploadResponseDto> upload(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal String phone
    ) throws Exception {

        return ResponseEntity.ok(
                mediaService.upload(file, phone)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getMedia(
            @PathVariable Long id,
            @AuthenticationPrincipal String phone
    ) throws Exception {

        return ResponseEntity.ok(
                mediaService.getMedia(id, phone)
        );
    }
}
