package lk.ijse.cmjd109.LostAndFoundApp.service;

import lk.ijse.cmjd109.LostAndFoundApp.dto.ItemDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.RequestDTO;

import java.util.List;

public interface RequestService {
    void addRequest(RequestDTO requestDTO);
    void updateRequest(String requestId,RequestDTO requestDTO);
    void deleteRequest(String requestId);
    RequestDTO getRequest(String requestId);
    List<RequestDTO> getAllRequests();
}
