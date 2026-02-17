package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.author.AuthorRequestDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorResponseDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorUpdateDTO;

import java.util.List;

public interface AuthorService {

    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);

    AuthorResponseDTO getAuthorById(Integer id);

    List<AuthorResponseDTO> getAllAuthors();

    List<AuthorSummaryDTO> searchAuthors(String keyword);

    AuthorResponseDTO updateAuthor(Integer id, AuthorUpdateDTO dto);

    void deleteAuthor(Integer id);
}