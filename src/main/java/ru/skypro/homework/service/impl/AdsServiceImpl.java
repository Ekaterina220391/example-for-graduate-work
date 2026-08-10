package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        Ads ads = new Ads();
        ads.setCount(adEntities.size());
        ads.setResults(adMapper.toAdDtoList(adEntities));
        return ads;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, org.apache.tomcat.util.net.openssl.ciphers.Authentication authentication) {
        return null;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        return null;
    }

    @Transactional
    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        UserEntity author = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AdEntity adEntity = adMapper.toEntity(properties);
        adEntity.setAuthor(author);


        adEntity.setImage(image.getOriginalFilename());

        adRepository.save(adEntity);
        return adMapper.toDto(adEntity);
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
        return adMapper.toExtendedDto(ad);
    }

    @Transactional
    @Override
    public void removeAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd properties) {
        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setDescription(properties.getDescription());

        return adMapper.toDto(adRepository.save(ad));
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {
        List<AdEntity> myAds = adRepository.findAllByAuthorEmail(authentication.getName());
        Ads ads = new Ads();
        ads.setCount(myAds.size());
        ads.setResults(adMapper.toAdDtoList(myAds));
        return ads;
    }

    @Transactional
    @Override
    public byte[] updateImage(Integer id, MultipartFile image) {
        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        try {
            byte[] imageBytes = image.getBytes();
            ad.setImage(image.getOriginalFilename()); // Или путь к файлу
            adRepository.save(ad);
            return imageBytes;
        } catch (IOException e) {
            log.error("Error updating image", e);
            throw new RuntimeException("Failed to update image");
        }
    }
}
