package com.hostel.helper;

import com.hostel.dtos.HostelDto;
import com.hostel.entities.Hostel;
import com.hostel.payloads.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {


    public static <E, D> PageableResponse<D> getPageableResponse(Page<E> page, Class<D> dtoClass) {
        List<E> entity = page.getContent();
        List<D> dto = entity.stream().map(object -> new ModelMapper().map(object, dtoClass)).collect(Collectors.toList());
        PageableResponse<D> pageableResponse = new PageableResponse<>();
        pageableResponse.setPageNumber(page.getNumber());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setIsLastPage(page.isLast());
        pageableResponse.setContent(dto);
        return pageableResponse;

    }
}
