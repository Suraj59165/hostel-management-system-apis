package com.hostel.service;

import com.hostel.dtos.HostelDto;
import com.hostel.entities.Hostel;
import com.hostel.payloads.PageableResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HostelService {
    public HostelDto addHostels(HostelDto hostelDto);

    public HostelDto updateHostel(HostelDto hostelDto, String hostelId);

    public void deleteHostel(String hotelId);

    public PageableResponse<HostelDto> getAllHotels(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public PageableResponse<HostelDto> searchAnyData(String keyword, Object data, int pageNumber, int pageSize);


}
