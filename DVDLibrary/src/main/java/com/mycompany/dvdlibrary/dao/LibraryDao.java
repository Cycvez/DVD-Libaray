/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface LibraryDao {

    DVD addDVD(String title, DVD dvd)
            throws LibraryDaoException;

    List<DVD> getAllDVD()
            throws LibraryDaoException;

    DVD getDVD(String title)
            throws LibraryDaoException;

    DVD removeDVD(String title)
            throws LibraryDaoException;

    DVD editDVD(String title)
            throws LibraryDaoException;

}
