package ru.itis.demo.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.models.FileInfo;
import ru.itis.demo.models.State;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.FileInfoRepository;
import ru.itis.demo.repositories.UsersRepository;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;
import ru.itis.demo.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public String saveFile(MultipartFile file) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        System.out.println(userDetails.getUserId());
        Optional<User> userOptional = usersRepository.findUserById(userDetails.getUserId());
        System.out.println(userOptional);
        if (userOptional.isPresent()) {
            System.out.println("User Found");
            User user = userOptional.get();
            user.setAvatar(fileInfo);
            usersRepository.save(user);
        } else
        {
            System.out.println("User Not Found");
        }
        return fileInfo.getStorageFileName();
    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findOneByStorageFileName(fileName);
        response.setContentType(file.getType());
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
