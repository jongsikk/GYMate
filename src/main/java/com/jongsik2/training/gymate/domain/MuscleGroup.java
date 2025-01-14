package com.jongsik2.training.gymate.domain;

import com.jongsik2.training.gymate.dto.MuscleGroupResponse;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "muscle_groups")
public class MuscleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    private String name;

    public MuscleGroupResponse toDto() {
        return MuscleGroupResponse.builder()
                .id(this.id)
                .bodyTypeName(this.bodyType.getName())
                .name(this.name)
                .build();
    }
}
