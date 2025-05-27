package lk.ijse.cmjd109.LostAndFoundApp.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.LostAndFoundApp.dao.ItemDao;
import lk.ijse.cmjd109.LostAndFoundApp.dao.RequestDao;
import lk.ijse.cmjd109.LostAndFoundApp.dao.UserDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.ItemDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.RequestDTO;
import lk.ijse.cmjd109.LostAndFoundApp.entities.ItemEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.UserEntity;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.ItemNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.RequestNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.RequestService;
import lk.ijse.cmjd109.LostAndFoundApp.util.EntityDTOConvert;
import lk.ijse.cmjd109.LostAndFoundApp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDao;
    private final EntityDTOConvert entityDTOConvert;
    private final UserDao userDao;
    private final ItemDao itemDao;
    @Override
    public void addRequest(RequestDTO requestDTO) {
        requestDTO.setRequestId(UtilData.generateRequestId());
        requestDTO.setRequestedDate(UtilData.generateTodayDate());
        requestDTO.setRequestedTime(UtilData.generateCurrentTime());
        requestDTO.setIsActiveRequest(true);

        UserEntity userEntity=userDao.findById(requestDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        ItemEntity itemEntity=itemDao.findById(requestDTO.getItemId()).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        var requestEntity=entityDTOConvert.convertRequestDTOToRequestEntity(requestDTO);
        requestEntity.setUser(userEntity);
        requestEntity.setItem(itemEntity);
        requestDao.save(requestEntity);


    }

    @Override
    public void updateRequest(String requestId, RequestDTO requestDTO) {
        var requestEntity=requestDao.findById(requestId).orElseThrow(()->new RequestNotFoundException("Request Not Found"));
        requestEntity.setIsActiveRequest(requestDTO.getIsActiveRequest());
        requestEntity.setRequestStatus(requestDTO.getRequestStatus());
        requestEntity.setRequestedDate(requestDTO.getRequestedDate());
        requestEntity.setRequestedTime(requestDTO.getRequestedTime());
        requestEntity.setReward(requestDTO.getReward());
    }

    @Override
    public void deleteRequest(String requestId) {
        requestDao.findById(requestId).orElseThrow(()->new RequestNotFoundException("Request Not Found"));
        requestDao.deleteById(requestId);
    }

    @Override
    public RequestDTO getRequest(String requestId) {
        var requestEntity=requestDao.findById(requestId).orElseThrow(()->new RequestNotFoundException("Request Not Found"));
        return entityDTOConvert.convertRequestEntityToRequestDTO(requestDao.getReferenceById(requestId));
    }

    @Override
    public List<RequestDTO> getAllRequests() {
        return entityDTOConvert.convertRequestEntityListToRequestDTOList(requestDao.findAll());
    }
}
