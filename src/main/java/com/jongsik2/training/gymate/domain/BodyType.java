package com.jongsik2.training.gymate.domain;

import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "body_types")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public BodyTypeResponse toDto() {
        return BodyTypeResponse.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
