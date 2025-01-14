package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.BodyType;
import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import com.jongsik2.training.gymate.repository.BodyTypeRepository;
import com.jongsik2.training.gymate.service.BodyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyTypeServiceImpl implements BodyTypeService {
    private final BodyTypeRepository bodyTypeRepository;

    @Override
    public List<BodyTypeResponse> getBodyTypeList() {
        return bodyTypeRepository.findAll().
                stream().
                map(BodyType::toDto).
                toList();
    }
}
