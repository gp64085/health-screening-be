package com.panwar.healthcheck.common;

import java.util.List;

public interface GenericCrudService<Req, Res, ID> {
    
    /**
     * Get element by id
     * @param id
     * @return return element
     */
    Res getById(ID id);

    /**
     * Get all elements
     * @return return all elements
    */
    List<Res> getAll();

    default Res create(Req requestDto) {
        throw new UnsupportedOperationException("Create operation is not supported");
    }
    
    default Res update(ID id, Req requestDto) {
        throw new UnsupportedOperationException("Update operation is not supported");
    }

    default void delete(ID id) {
        throw new UnsupportedOperationException("Delete operation is not supported");
    }
}
