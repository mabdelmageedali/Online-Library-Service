package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.book.*;
import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.Category;
import com.onlineLibrary.Online.service.enums.FileType;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.BookMapper;
import com.onlineLibrary.Online.service.repository.AuthorRepository;
import com.onlineLibrary.Online.service.repository.BookRepository;
import com.onlineLibrary.Online.service.repository.CategoryRepository;
import com.onlineLibrary.Online.service.service.BookService;
import com.onlineLibrary.Online.service.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;
    private final BookMapper bookMapper;

    private static final long MAX_FILE_SIZE = 10485760; // 10MB
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of("application/pdf", "text/html");
    private static final List<String> ALLOWED_EXTENSIONS = List.of(".pdf", ".html");

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        validateBookFile(dto.getBookFile());

        if (bookRepository.existsByTitle(dto.getTitle())) {
            throw new BadRequestException("Book already exists with title: " + dto.getTitle());
        }

        String filePath = fileStorageService.storeFile(dto.getBookFile(), "books");
        FileType fileType = FileType.fromMimeType(dto.getBookFile().getContentType());

        Book book = bookMapper.toEntity(dto);
        book.setFilePath(filePath);
        book.setFileType(fileType);
        book.setFileSize(dto.getBookFile().getSize());

        if (dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
            List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
            if (authors.size() != dto.getAuthorIds().size()) {
                throw new NotFoundException("One or more authors not found");
            }
            book.setAuthors(authors);
        }

        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
            book.setCategories(categories);
        }

        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDTO(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDTO getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        return bookMapper.toResponseDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toResponseDTOList(books);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookSummaryDTO> searchBooksByTitle(String keyword) {
        List<Book> books = bookRepository.findByTitleContaining(keyword);
        return bookMapper.toSummaryDTOList(books);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookSummaryDTO> getBooksByCategory(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category not found with id: " + categoryId);
        }
        List<Book> books = bookRepository.findByCategoriesId(categoryId);
        return bookMapper.toSummaryDTOList(books);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookSummaryDTO> getBooksByAuthor(Integer authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }
        List<Book> books = bookRepository.findByAuthorsId(authorId);
        return bookMapper.toSummaryDTOList(books);
    }

    @Override
    public BookResponseDTO updateBook(Integer id, BookUpdateDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        bookMapper.updateEntityFromDTO(dto, book);

        if (dto.getBookFile() != null && !dto.getBookFile().isEmpty()) {
            validateBookFile(dto.getBookFile());

            if (book.getFilePath() != null) {
                fileStorageService.deleteFile(book.getFilePath());
            }

            String newFilePath = fileStorageService.storeFile(dto.getBookFile(), "books");
            FileType newFileType = FileType.fromMimeType(dto.getBookFile().getContentType());

            book.setFilePath(newFilePath);
            book.setFileType(newFileType);
            book.setFileSize(dto.getBookFile().getSize());
        }

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponseDTO(updatedBook);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        if (book.getFilePath() != null) {
            fileStorageService.deleteFile(book.getFilePath());
        }

        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public FileDownloadResponse downloadBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        if (book.getFilePath() == null) {
            throw new NotFoundException("Book file not found");
        }

        Resource resource = fileStorageService.loadFileAsResource(book.getFilePath());

        return new FileDownloadResponse(
                resource,
                book.getFileType().getMimeType(),
                book.getTitle() + book.getFileType().getExtension()
        );
    }

    private void validateBookFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Book file is required");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BadRequestException("File size must not exceed 10MB");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new BadRequestException("Only PDF and HTML files are allowed");
        }

        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new BadRequestException("Invalid filename");
        }

        String lowerFilename = filename.toLowerCase();
        boolean validExtension = ALLOWED_EXTENSIONS.stream()
                .anyMatch(lowerFilename::endsWith);

        if (!validExtension) {
            throw new BadRequestException("File must have .pdf or .html extension");
        }
    }
}