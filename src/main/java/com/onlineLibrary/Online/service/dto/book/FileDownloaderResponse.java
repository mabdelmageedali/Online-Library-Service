package com.onlineLibrary.Online.service.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;

@AllArgsConstructor
@Getter
public class FileDownloaderResponse {
    private Resource resource;
    private String contentType;
    private String filename;
}
