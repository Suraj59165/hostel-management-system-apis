package com.hostel.repositories;

import com.hostel.entities.Hostel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HostelRepo extends JpaRepository<Hostel, String> {

    Page<Hostel> findByLocationContaining(String keyword, Pageable pageable);

    Page<Hostel> findByHostelCodeContaining(String keyword, Pageable pageable);

}
