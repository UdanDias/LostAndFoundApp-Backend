package lk.ijse.cmjd109.LostAndFoundApp.controller;

import lk.ijse.cmjd109.LostAndFoundApp.service.RequestStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/requeststats")
@RequiredArgsConstructor
public class RequestStatsController {
    private final RequestStatsService requestStatsService;
    @GetMapping
    public Map<String, Long> getRequestStatsByUserId(@RequestParam("userId") String userId) {
        return requestStatsService.getRequestStatsByUserId(userId);
    }
}
