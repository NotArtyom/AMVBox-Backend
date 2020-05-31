package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
}
