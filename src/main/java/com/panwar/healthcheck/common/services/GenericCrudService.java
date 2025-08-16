package com.panwar.healthcheck.common.services;

import java.sql.SQLException;
import java.util.List;

import com.panwar.healthcheck.common.exceptions.ResourceNotFoundException;
import com.panwar.healthcheck.models.dto.ApiResponse;

public interface GenericCrudService<Req, Res, ID> {
    
    /**
     * Get element by id
     * @param id
     * @return return element
     */
    ApiResponse<Res> getById(ID id) throws ResourceNotFoundException;

    /**
     * Get all elements
     * @return return all elements
    */
    ApiResponse<List<Res>> getAll();

    default ApiResponse<Res> create(Req requestDto) throws SQLException {
        throw new UnsupportedOperationException("Create operation is not supported");
    }

    default ApiResponse<Res> update(ID id, Req requestDto) {
        throw new UnsupportedOperationException("Update operation is not supported");
    }

    default ApiResponse<Void> delete(ID id) {
        throw new UnsupportedOperationException("Delete operation is not supported");
    }
}
