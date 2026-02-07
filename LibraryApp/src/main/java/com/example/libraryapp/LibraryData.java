package com.example.libraryapp;

import library.Library;

public class LibraryData {
    // Single shared Library instance
    private static final Library library = new Library();

    // Public method ,jate shared instance gula pete pari
    public static Library getLibrary() {
        return library;
    }
}
