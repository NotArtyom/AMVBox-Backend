package ru.itis.demo.service;

import ru.itis.demo.dto.RateDTO;
import ru.itis.demo.dto.RateResultDTO;

public interface RateService {

    RateResultDTO ratePost(RateDTO dto);
}
