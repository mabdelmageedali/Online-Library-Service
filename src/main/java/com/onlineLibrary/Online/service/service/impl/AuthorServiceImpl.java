package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.author.AuthorRequestDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorResponseDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorUpdateDTO;
import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.AuthorMapper;
import com.onlineLibrary.Online.service.repository.AuthorRepository;
import com.onlineLibrary.Online.service.service.AuthorService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        if (authorRepository.existsByAuthorName(dto.getAuthorName())) {
            throw new BadRequestException("Author already exists with name: " + dto.getAuthorName());
        }

        Author author = authorMapper.toEntity(dto);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toResponseDTO(savedAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponseDTO getAuthorById(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + id));
        return authorMapper.toResponseDTO(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toResponseDTOList(authors);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorSummaryDTO> searchAuthors(String keyword) {
        List<Author> authors = authorRepository.findByAuthorNameContaining(keyword);
        return authorMapper.toSummaryDTOList(authors);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Integer id, AuthorUpdateDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + id));

        authorMapper.updateEntityFromDTO(dto, author);
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.toResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found with id: " + id);
        }

        authorRepository.deleteById(id);
    }
}