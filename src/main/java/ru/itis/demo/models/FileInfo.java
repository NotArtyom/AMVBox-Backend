package ru.itis.demo.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // название файла в хранилище
    private String storageFileName;
    // название файла оригинальное
    private String originalFileName;
    // размер файла
    private Long size;
    // тип файла (MIME)
    private String type;
    // по какому URL можно получить файл
    private String url;
}
