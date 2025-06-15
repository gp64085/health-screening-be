package com.panwar.healthcheck.common;

import java.util.List;

public interface GenericCrudController<Req, Res, ID> {
    /**
     * Get element by id
     * @param id
     * @return
     */
    Res getById(ID id);

    /**
     * Return all the elements
     * @return
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