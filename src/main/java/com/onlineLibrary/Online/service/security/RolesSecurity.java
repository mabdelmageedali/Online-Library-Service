package com.onlineLibrary.Online.service.security;


import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.enums.Role;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.exception.UnauthorizedException;
import com.onlineLibrary.Online.service.repository.BookRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component("rolesSecurity")
@RequiredArgsConstructor
public class RolesSecurity {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public boolean isAuthor() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ROLE_AUTHOR;
    }


    public boolean isUser() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ROLE_USER;
    }


    public boolean isUserOrAuthor() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ROLE_USER ||
                currentUser.getRole() == Role.ROLE_AUTHOR;
    }


    public boolean isBookOwner(Integer bookId) {
        User currentUser = getCurrentUser();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        return book.getAuthors() != null &&
                book.getAuthors().stream()
                        .anyMatch(author -> author.getId().equals(currentUser.getId()));
    }


    public boolean isAuthorAndOwnsBook(Integer bookId) {
        return isAuthor() && isBookOwner(bookId);
    }

    public void requireAuthor() {
        if (!isAuthor()) {
            throw new UnauthorizedException("Only authors can perform this action");
        }
    }

    public void requireBookOwnership(Integer bookId) {
        if (!isBookOwner(bookId)) {
            throw new UnauthorizedException("You can only modify your own books");
        }
    }

    public void requireAuthorAndOwnership(Integer bookId) {
        requireAuthor();
        requireBookOwnership(bookId);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }

        String username = authentication.getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }

        return authentication.getName();
    }
}