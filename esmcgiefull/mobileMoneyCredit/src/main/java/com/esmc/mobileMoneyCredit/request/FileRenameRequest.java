package com.esmc.mobileMoneyCredit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRenameRequest {
//    private String oldName;
//    private String newName;

//    public Path getOldName() {
//        return null;
//    }
//
//    public Path getNewName() {
//        return null;
//    }
    // getters and setters
private String fileName;
    private String filePath;
    private String newName;
    private String destinationFolder;
}
