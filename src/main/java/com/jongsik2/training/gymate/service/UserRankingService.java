package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.dto.UserRankingResponse;

import java.util.List;

public interface UserRankingService {
    List<UserRankingResponse> getUserRanking();
}
