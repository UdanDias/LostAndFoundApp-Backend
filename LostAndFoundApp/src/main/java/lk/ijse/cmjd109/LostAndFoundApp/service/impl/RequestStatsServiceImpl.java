package lk.ijse.cmjd109.LostAndFoundApp.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.LostAndFoundApp.dao.RequestDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.RequestStatus;
import lk.ijse.cmjd109.LostAndFoundApp.service.RequestStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@Transactional
@RequiredArgsConstructor
public class RequestStatsServiceImpl implements RequestStatsService {

    private final RequestDao requestDao;

    @Override
    public Map<String, Long> getRequestStatsByUserId(String userId) {
        long total = requestDao.countTotalRequestsByUserId(userId);
        long active = requestDao.countActiveRequestsByUserId(userId);
        long pending = requestDao.countByRequestStatusAndUserId(userId, RequestStatus.PENDING);
        long approved = requestDao.countByRequestStatusAndUserId(userId, RequestStatus.APPROVED);
        long rejected = requestDao.countByRequestStatusAndUserId(userId, RequestStatus.REJECTED);

        return Map.of(
                "totalRequests", total,
                "activeRequests", active,
                "pendingRequests", pending,
                "approvedRequests", approved,
                "rejectedRequests", rejected
        );
    }


}
