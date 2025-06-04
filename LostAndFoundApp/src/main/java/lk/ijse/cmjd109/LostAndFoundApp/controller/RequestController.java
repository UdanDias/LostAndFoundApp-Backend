package lk.ijse.cmjd109.LostAndFoundApp.controller;

import lk.ijse.cmjd109.LostAndFoundApp.dto.RequestDTO;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.RequestNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRequest(@RequestBody RequestDTO requestDTO) {
        if(requestDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            requestService.addRequest(requestDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleterequest")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<Void> deleteRequest(@RequestParam("requestId") String requestId) {
        if(requestId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            requestService.deleteRequest(requestId);
            return ResponseEntity.noContent().build();
        }catch(RequestNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateRequest(@RequestParam("requestId") String id,@RequestBody RequestDTO requestDTO) {
        if(id==null||requestDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            requestService.updateRequest(id, requestDTO);
            return ResponseEntity.noContent().build();
        }catch(RequestNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<RequestDTO> getRequest(@RequestParam("requestId") String requestId) {
        if(requestId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(requestService.getRequest(requestId));
        }catch(RequestNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getallrequests")
    public ResponseEntity<List<RequestDTO>> getAllRequests() {

        try {
            return ResponseEntity.ok(requestService.getAllRequests());
        }catch(RequestNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getrequestsbyuserid")
    public ResponseEntity<List<RequestDTO>> getRequestsByUserId(@RequestParam("userId") String userId) {
        List<RequestDTO> requests = requestService.getRequestsByUserId(userId);
        return ResponseEntity.ok(requests);
    }

}
