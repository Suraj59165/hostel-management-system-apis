package com.hostel.service.impl;

import com.hostel.dtos.HostelDto;
import com.hostel.entities.Hostel;
import com.hostel.exceptions.ResourceNotFoundException;
import com.hostel.helper.Helper;
import com.hostel.payloads.PageableResponse;
import com.hostel.repositories.HostelRepo;
import com.hostel.service.HostelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelRepo hostelRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HostelDto addHostels(HostelDto hostelDto) {
        hostelDto.setId(UUID.randomUUID().toString());
        return modelMapper.map(hostelRepo.save(modelMapper.map(hostelDto, Hostel.class)), HostelDto.class);

    }

    @Override
    public HostelDto updateHostel(HostelDto hostelDto, String hostelId) {
        Hostel hostel = hostelRepo.findById(hostelId).orElseThrow(() -> new ResourceNotFoundException("The hostel you want to update with id: " + hostelId + " not found"));
        hostel.setHostelCode(hostelDto.getHostelCode());
        hostel.setFees(hostelDto.getFees());
        hostel.setAvailable(hostelDto.isAvailable());
        hostel.setClosesAt(hostelDto.getClosesAt());
        hostel.setLocation(hostelDto.getLocation());
        hostel.setOpensAt(hostelDto.getOpensAt());
        hostel.setNumberOfBeds(hostelDto.getNumberOfBeds());
        hostel.setAvailable(hostelDto.isAvailable());
        return modelMapper.map(hostelRepo.save(hostel), HostelDto.class);
    }

    @Override
    public void deleteHostel(String hostelId) {
        hostelRepo.delete(hostelRepo.findById(hostelId).orElseThrow(() -> new ResourceNotFoundException("The hostel you want to delete with id: " + hostelId + " not found")));

    }

    @Override
    public PageableResponse<HostelDto> getAllHotels(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return Helper.getPageableResponse(hostelRepo.findAll(PageRequest.of(pageNumber, pageSize, (sortDirection.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending()))), HostelDto.class);

    }

    @Override
    public PageableResponse<HostelDto> searchAnyData(String keyword, Object data, int pageNumber, int pageSize) {
        if (keyword.equals("location")) {
            return Helper.getPageableResponse(hostelRepo.findByLocationContaining(data.toString(), PageRequest.of(pageNumber, pageSize)), HostelDto.class);
        }
        if (keyword.equalsIgnoreCase("hostelCode")) {
            return Helper.getPageableResponse(hostelRepo.findByHostelCodeContaining(data.toString(), PageRequest.of(pageNumber, pageSize)), HostelDto.class);
        } else
            throw new ResourceNotFoundException("oops the data you want to get with that field " + keyword + "is not available");

    }
}
