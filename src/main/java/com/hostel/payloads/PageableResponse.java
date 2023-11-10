package com.hostel.payloads;

import com.hostel.dtos.HostelDto;
import com.hostel.entities.Hostel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class PageableResponse<T> {
    private List<T> content;
    private int pageNumber;
    private long pageSize;
    private int totalPages;
    private long totalElements;
    private Boolean isLastPage;
}
