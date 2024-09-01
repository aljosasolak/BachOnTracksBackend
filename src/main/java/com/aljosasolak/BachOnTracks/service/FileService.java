package com.aljosasolak.BachOnTracks.service;

import com.aljosasolak.BachOnTracks.mapper.MapperImpl;
import com.aljosasolak.BachOnTracks.model.File;
import com.aljosasolak.BachOnTracks.model.Project;
import com.aljosasolak.BachOnTracks.repository.FileRepository;
import com.aljosasolak.BachOnTracks.repository.ProjectRepository;
import com.aljosasolak.BachOnTracks.resource.FileResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileService {

    private final String FILE_PATH = "C:\\Users\\aljos\\Documents\\BachOnTracksDocs\\";
    private final Path rootLocation = Paths.get(FILE_PATH);

    private final FileRepository fileRepository;
    private final MapperImpl mapper;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    public FileService(FileRepository fileRepository, MapperImpl mapper, ProjectRepository projectRepository, ProjectService projectService) {
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }


    public FileResource createDbEntry(String fileName, String mimeType, String projectId) {
        File entity = new File();
        entity.setFileName(fileName);
        entity.setMimeType(mimeType);
        entity.setPathname(FILE_PATH);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setProject(project);
        return mapper.map(fileRepository.save(entity), FileResource.class);
    }

/*
    public List<FileResource> getFiles() {
        Sort sort = Sort.by("fileName"); //TODO: Sort by mimeType as well?
        return mapper.mapList(fileRepository.findAll(sort), FileResource.class);
    }
*/

    public List<FileResource> filterByProjectId(String id) {
        return mapper.mapList(fileRepository.filterByProjectId(id), FileResource.class);
    }

    public Boolean deleteFile(String id) {
        try {
            File entity = fileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Files.delete(Paths.get(entity.getPathname()).resolve(entity.getFileName()));
            fileRepository.delete(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    public ResponseEntity<Resource> downloadFile(String fileId) {

        File file = fileRepository.findById(fileId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            Path path = Paths.get(file.getPathname()).resolve(file.getFileName());
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
