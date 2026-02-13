package com.onlineLibrary.Online.Library.service.impl;

import com.onlineLibrary.Online.Library.entity.Author;
import com.onlineLibrary.Online.Library.repository.AuthorRepository;
import com.onlineLibrary.Online.Library.service.AuthorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {

        if (authorRepository.existsByAuthorName(author.getAuthorName())) {
            throw new RuntimeException("Author already exists");
        }

        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getAuthorById(Integer id) {

        return authorRepository.findById(id);
    }

    @Override
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
                .orElseThrow(() -> new RuntimeException("Author not found"));

        oldAuthor.setAuthorName(author.getAuthorName());
        oldAuthor.setBiography(author.getBiography());
        oldAuthor.setBirthDate(author.getBirthDate());
        oldAuthor.setDeathDate(author.getDeathDate());

        return authorRepository.save(oldAuthor);
    }

    @Override
    public void deleteAuthor(Integer id) {

        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found");
        }

        authorRepository.deleteById(id);
    }
}
