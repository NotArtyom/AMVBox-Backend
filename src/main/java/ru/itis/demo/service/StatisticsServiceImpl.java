package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.PostRepository;
import ru.itis.demo.repositories.UsersRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendStats() {

        executorService.submit(() -> {

            List<User> users = usersRepository.findAll();

            for (User user : users) {

                Long totalLikes = postRepository.likeCountByUserId(user.getId());

                emailService.sendTextMail("Weekly statistics from AMVBox",
                        "Your posts currently have " + totalLikes + " likes in total", user.getEmail());
            }
        });
    }
}
