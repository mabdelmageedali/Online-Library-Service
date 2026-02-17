package com.onlineLibrary.Online.service.enums;

import lombok.Getter;

@Getter
public enum FileType {

    PDF("application/pdf", ".pdf"),
    HTML("text/html", ".html");

    private final String mimeType;
    private final String extension;

    FileType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }


    public static FileType fromMimeType(String mimeType) {
        for (FileType type : values()) {
            if (type.mimeType.equals(mimeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported file type: " + mimeType);
    }
}
