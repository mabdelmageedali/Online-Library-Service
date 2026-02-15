package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.repository.AuthorRepository;
import com.onlineLibrary.Online.service.service.AuthorService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {

        if (authorRepository.existsByAuthorName(author.getAuthorName())) {
            throw new BadRequestException("Author already exists");
        }

        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getAuthorById(Integer id) {

        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {

        return authorRepository.findAll();
    }

    @Override

    public List<Author> searchAuthors(String keyword) {

        return authorRepository.findByAuthorNameContaining(keyword);
    }

    @Override
    public Author updateAuthor(Integer id, Author author) {

        Author oldAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        oldAuthor.setAuthorName(author.getAuthorName());
        oldAuthor.setBiography(author.getBiography());
        oldAuthor.setBirthDate(author.getBirthDate());
        oldAuthor.setDeathDate(author.getDeathDate());

        return authorRepository.save(oldAuthor);
    }

    @Override
    public void deleteAuthor(Integer id) {

        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found");
        }

        authorRepository.deleteById(id);
    }
}
