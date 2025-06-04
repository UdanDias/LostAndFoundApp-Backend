package lk.ijse.cmjd109.LostAndFoundApp.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface RequestStatsService {
    public Map<String,Long> getRequestStatsByUserId(String userId);
}
