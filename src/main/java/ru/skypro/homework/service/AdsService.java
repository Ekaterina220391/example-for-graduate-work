package ru.skypro.homework.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Ad; // Добавьте этот импорт
import ru.skypro.homework.dto.Ads;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdsService {
    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);

    @Transactional
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, org.springframework.security.core.Authentication authentication);

    ExtendedAd getAds(Integer id);

    @Transactional
    void removeAd(Integer id);

    @Transactional
    Ad updateAds(Integer id, CreateOrUpdateAd properties);

    Ads getAdsMe(org.springframework.security.core.Authentication authentication);

    @Transactional
    byte[] updateImage(Integer id, MultipartFile image);
}
