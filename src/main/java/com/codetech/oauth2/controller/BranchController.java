package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.BranchDTO;
import com.codetech.oauth2.service_impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Supun Dilshan.
 * Date: 09/04/2019
 * Time: 10:48 PM
 */

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    BranchServiceImpl branchServiceImpl;

    //add branches to business profile
    @PostMapping("/branch/{id}")
    public ResponseEntity<?> saveBranch(@Valid @RequestBody BranchDTO branchDTO, BindingResult result , @PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(branchServiceImpl.addBranch(branchDTO, id));
    }

    //update branch
    @PutMapping("/branch/{id}")  //passing the branch id
    public ResponseEntity<?> updateBranch(@Valid @RequestBody BranchDTO branchDTO, BindingResult result ,@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(branchServiceImpl.updateBranch(branchDTO, id));
    }


    //delete branch
    @DeleteMapping("/branch/{id}")
    public @ResponseBody ResponseEntity DeleteUser(@Valid @PathVariable String id) throws ClassNotFoundException {
        branchServiceImpl.deleteBranch(id);
        return ResponseEntity.ok("Branch is deleted successfully ");
    }

    //retrieve all branches
    @GetMapping("/branchAll")
    public ResponseEntity<List> retrieveAllBranches() throws ClassNotFoundException {
        return ResponseEntity.ok(branchServiceImpl.listAllBranches());
    }

    //retrieve by id
    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchDTO> retrieveBranchByID(@PathVariable String id) throws ClassNotFoundException{
        return ResponseEntity.ok(branchServiceImpl.retrieveBranchById(id));
    }

    //retrieve all branches of a particular company
    @GetMapping("/branchByBusiness/{id}")
    public ResponseEntity<?> retrieveAllBranchByID(@PathVariable String id) throws ClassNotFoundException {

      return ResponseEntity.ok(branchServiceImpl.retrieveAllBranchByBusinessProfile(id));
    }

}
