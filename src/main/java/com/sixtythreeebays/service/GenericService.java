package com.sixtythreeebays.service;

import com.sixtythreeebays.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}