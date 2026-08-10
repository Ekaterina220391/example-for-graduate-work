package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    // В мапперах используй имя аргумента. Если аргумент adEntity, то adEntity.id
    @Mapping(target = "image", expression = "java(\"/ads/image/\" + adEntity.getId())")
    Ad toDto(AdEntity adEntity);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", expression = "java(\"/ads/image/\" + adEntity.getId())")
    ExtendedAd toExtendedDto(AdEntity adEntity);
}
