package com.hostel.controllers;

import com.hostel.dtos.HostelDto;
import com.hostel.entities.Hostel;
import com.hostel.payloads.ApiResponse;
import com.hostel.payloads.PageableResponse;
import com.hostel.service.HostelService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hostel")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    public ResponseEntity<HostelDto> addHostel(@Valid @RequestBody HostelDto hostelDto) {
        return new ResponseEntity<>(hostelService.addHostels(hostelDto), HttpStatus.CREATED);
    }

    @PutMapping("/{hostel-id}")
    public ResponseEntity<HostelDto> updateHostel(@RequestBody HostelDto hostelDto, @PathVariable(value = "hostel-id") String hostelId) {
        return new ResponseEntity<>(hostelService.updateHostel(hostelDto, hostelId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<HostelDto>> getAllHostels(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "location", required = false) String sortBy, @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection) {
        return new ResponseEntity<>(hostelService.getAllHotels(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/{hostel-id}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable(value = "hostel-id") String hostelId) {
        hostelService.deleteHostel(hostelId);
        return new ResponseEntity<>(ApiResponse.builder().response("Hotel deleted successfully").isSuccess(true).httpStatusCode(HttpStatus.OK).build(), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<PageableResponse<HostelDto>> searchAnyData(@PathVariable(value = "keyword", required = false) String keyword, @RequestParam(value = "data") Object data,
                                                                     @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize

    ) {
        return new ResponseEntity<>(hostelService.searchAnyData(keyword, data, pageNumber, pageSize), HttpStatus.OK);
    }


}
