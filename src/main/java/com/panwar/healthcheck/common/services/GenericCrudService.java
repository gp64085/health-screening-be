package com.panwar.healthcheck.common.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.panwar.healthcheck.common.exceptions.ResourceNotFoundException;
import com.panwar.healthcheck.models.dto.ApiResponse;

public interface GenericCrudService<Req, Res, ID> {

    /**
     * Get element by id
     * 
     * @param id
     * @return return element
     */
    ResponseEntity<ApiResponse<Res>> getById(ID id) throws ResourceNotFoundException;

    /**
     * Get all elements
     * 
     * @return return all elements
     */
    ResponseEntity<ApiResponse<List<Res>>> getAll();

    default ResponseEntity<ApiResponse<Res>> create(Req requestDto) {
        throw new UnsupportedOperationException("Create operation is not supported");
    }

    default ResponseEntity<ApiResponse<Res>> update(ID id, Req requestDto) {
        throw new UnsupportedOperationException("Update operation is not supported");
    }

    default ResponseEntity<ApiResponse<Void>> delete(ID id) {
        throw new UnsupportedOperationException("Delete operation is not supported");
    }
}
