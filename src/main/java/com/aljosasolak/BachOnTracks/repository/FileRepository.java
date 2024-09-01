package com.aljosasolak.BachOnTracks.repository;
import com.aljosasolak.BachOnTracks.model.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends BaseRepository<File, String> {

    @Query(value = "SELECT * FROM bach_on_tracks.files WHERE project_id = :id ORDER BY file_name", nativeQuery = true)
    public List<File> filterByProjectId(String id);


    @Query(value = "INSERT INTO bach_on_tracks.files (file_name, pathname, mime_type) VALUES (:filename, :pathname, :mimeType)", nativeQuery = true)
    public void createDbEntry(String filename, String pathname, String mimeType);

}
