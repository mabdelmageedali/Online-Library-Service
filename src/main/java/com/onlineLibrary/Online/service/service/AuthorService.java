package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.author.AuthorRequestDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorResponseDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorUpdateDTO;

import java.util.List;

public interface AuthorService {

    // Create new author
    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);

    // Get author by ID
    AuthorResponseDTO getAuthorById(Integer id);

    // Get all authors
    List<AuthorResponseDTO> getAllAuthors();

    // Search authors by name
    List<AuthorSummaryDTO> searchAuthors(String keyword);

    // Update author
    AuthorResponseDTO updateAuthor(Integer id, AuthorUpdateDTO dto);

    // Delete author
    void deleteAuthor(Integer id);
}