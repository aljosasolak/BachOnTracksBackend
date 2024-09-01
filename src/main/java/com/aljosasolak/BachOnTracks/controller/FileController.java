package com.aljosasolak.BachOnTracks.controller;

import com.aljosasolak.BachOnTracks.resource.FileResource;
import com.aljosasolak.BachOnTracks.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/files/")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // TODO: METHODE NICHT FERTIG; FILE_CREATE_RESOURCE EINBAUEN?
    @GetMapping("{projectId}")
    List<FileResource> getFiles(@PathVariable String projectId) {
        return this.fileService.filterByProjectId(projectId);
    }

    @PostMapping("{projectId}")
    public void createFile(@RequestParam("file") MultipartFile file, @PathVariable String projectId) {
        fileService.store(file);
        fileService.createDbEntry(file.getOriginalFilename(), file.getContentType(), projectId);
    }

    // TODO: EINBAUEN UND TESTEN
    @GetMapping("{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        return fileService.downloadFile(fileId);
    }

    // TODO: EINBAUEN UND TESTEN
    @DeleteMapping("{fileId}")
    Boolean deleteFile(@PathVariable String fileId) {
        return fileService.deleteFile(fileId);
    }

}


