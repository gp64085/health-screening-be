package com.panwar.healthcheck.common.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.panwar.healthcheck.common.exceptions.ResourceNotFoundException;
import com.panwar.healthcheck.models.dto.ApiResponse;

public interface GenericCrudController<Req, Res, ID> {
    /**
     * Get element by id
     * @param id
     * @return
     */
    ApiResponse<Res> getById(ID id) throws ResourceNotFoundException;

    /**
     * Return all the elements
     * @return
     */
    ApiResponse<List<Res>> getAll();

    default ApiResponse<Res> create(Req requestDto) {
        throw new UnsupportedOperationException("Create operation is not supported");
    }

    default ApiResponse<Res> update(ID id, Req requestDto) {
        throw new UnsupportedOperationException("Update operation is not supported");
    }

    default ApiResponse<Void> delete(ID id) {
        throw new UnsupportedOperationException("Delete operation is not supported");
    }
}