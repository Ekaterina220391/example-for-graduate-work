package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad; // Добавьте этот импорт
import ru.skypro.homework.dto.Ads;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;

public interface AdsService {
    Ads getAllAds();

    // Добавьте эту строку:
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);
}
