package com.example.Controller;


import com.example.Service.CenterService;
import com.example.models.Center;
import com.example.models.Branches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<Center> createCenter(@RequestBody Center center) {
        Center createdCenter = centerService.createCenter(center);
        return new ResponseEntity<>(createdCenter, HttpStatus.OK);
    }

    @PostMapping("/{centerId}/branches")
    public ResponseEntity<List<Branches>> addBranchesToCenter(@PathVariable Long centerId, @RequestBody List<Branches> branches) {
        List<Branches> addedBranches = centerService.addBranchesToCenter(centerId, branches);
        if (addedBranches == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(addedBranches, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Center>> searchByLocationName(@RequestBody Map<String, String> requestBody) {
        String locationName = requestBody.get("locationName");
        if (locationName == null || locationName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Center> centers = centerService.searchTestCentersByName(locationName);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/representative/{representativeId}")
    public ResponseEntity<Center> getCenterByRepresentativeId(@PathVariable Long representativeId) {
        Center center = centerService.getCenterByRepresentativeId(representativeId);
        if (center == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(center, HttpStatus.OK);
    }


}
