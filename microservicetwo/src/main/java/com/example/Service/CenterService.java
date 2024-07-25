package com.example.Service;


import com.example.models.Branches;
import com.example.models.Center;
import com.example.repos.BranchRepository;
import com.example.repos.CenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CenterService {

    @Autowired
    private CenterRepo centerRepo;
    @Autowired
    private BranchRepository branchRepository;

    public Center createCenter(Center center) {
        return centerRepo.save(center);
    }
    public Center findById(Long id) {
        return centerRepo.findById(id).orElse(null);
    }

    public Center save(Center center) {
        return centerRepo.save(center);
    }


public List<Branches> addBranchesToCenter(Long centerId, List<Branches> branches) {
    Center center = centerRepo.findById(centerId).orElse(null);
    if (center == null) {
        return null;
    }
    for (Branches branch : branches) {
        branch.setCenter(center);
    }

    List<Branches> savedBranches = branchRepository.saveAll(branches);

    return savedBranches;
    }

    public List<Center> searchTestCentersByName(String locationName) {
        return centerRepo.findByLocationContainingIgnoreCase(locationName);
    }
    public Center getCenterByRepresentativeId(Long representativeId) {
        return centerRepo.findByRepresentativeId(representativeId);
    }


}
