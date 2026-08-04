package ru.skypro.homework.dto;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data

public class Comments {
    private Integer count;
    private List<Comment> results;
}
