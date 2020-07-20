/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author carlo
 */
public class LibraryDaoFileImpl implements LibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> library = new HashMap<>();

    private void loadLibrary() throws LibraryDaoException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));

        } catch (FileNotFoundException ex) {
            throw new LibraryDaoException("Could not Load Library Data.", ex);
        }

        String currentLine;
        DVD currentDVD;

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentDVD = unmarshallLibrary(currentLine);
            library.put(currentDVD.getTitle(), currentDVD);

        }

        sc.close();

    }

    private void writeLibrary() throws LibraryDaoException {
        PrintWriter pw;

        try {
            pw = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException ex) {
            throw new LibraryDaoException("Could not Save DVD Data.", ex);

        }

        String libraryAsText;
        List<DVD> libraryList = this.getAllDVD();
        
        for (DVD currentDVD : libraryList) {
            libraryAsText = marshallDVD(currentDVD);
            pw.println(libraryAsText);
            pw.flush();
        }
        pw.close();

    }

    private String marshallDVD(DVD aDVD) {
        String libraryAsText = aDVD.getTitle() + DELIMITER;
        libraryAsText += aDVD.getReleaseYear() + DELIMITER;
        libraryAsText += aDVD.getMpaaRating() + DELIMITER;
        libraryAsText += aDVD.getDirectorName() + DELIMITER;
        libraryAsText += aDVD.getStudio() + DELIMITER;
        libraryAsText += aDVD.getUserComments();
        return libraryAsText;

    }

    public DVD unmarshallLibrary(String libraryAsText) {
        String[] dvdInfoPart = libraryAsText.split(DELIMITER);
        String title = dvdInfoPart[0];

        DVD dvdFromFile = new DVD(title);

        dvdFromFile.setReleaseYear(dvdInfoPart[1]);
        dvdFromFile.setMpaaRating(dvdInfoPart[2]);
        dvdFromFile.setDirectorName(dvdInfoPart[3]);
        dvdFromFile.setStudio(dvdInfoPart[4]);
        dvdFromFile.setUserComments(dvdInfoPart[5]);

        return dvdFromFile;

    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws LibraryDaoException {
        loadLibrary();

        DVD newDVD = library.put(title, dvd);
        writeLibrary();
        return newDVD;

    }

    @Override
    public List<DVD> getAllDVD() throws LibraryDaoException {
        loadLibrary();
        return new ArrayList<>(library.values());
    }

    @Override
    public DVD getDVD(String title) throws LibraryDaoException {
        loadLibrary();
        return library.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws LibraryDaoException {
        loadLibrary();
        DVD removedDVD = library.remove(title);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title) throws LibraryDaoException {
        loadLibrary();
        DVD editDVD = library.remove(title);
        writeLibrary();
        return  editDVD;
        
    }

  

}
