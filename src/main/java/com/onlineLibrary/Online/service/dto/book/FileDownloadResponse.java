package com.onlineLibrary.Online.service.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileDownloadResponse {
    private Resource resource;
    private String contentType;
    private String filename;
}
