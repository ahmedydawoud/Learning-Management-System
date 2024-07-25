package com.example.repos;

import com.example.models.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface CenterRepo extends JpaRepository<Center, Long> {
    Center findByName(String centerName);
    List<Center> findByLocationContainingIgnoreCase(String locationName);

    Center findByRepresentativeId(Long representativeId);
}

