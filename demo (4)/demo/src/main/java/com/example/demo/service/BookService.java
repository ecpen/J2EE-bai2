package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(1, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(2, "1984", "George Orwell"));
        books.add(new Book(3, "Clean Code", "Robert C. Martin"));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book getBookById(int id) {
        return books.stream() // no optional used to keep controller simple
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean updateBook(int id, Book updatedBook) {
        Optional<Book> existing = books.stream().filter(book -> book.getId() == id).findFirst();
        if (existing.isPresent()) {
            Book book = existing.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setId(updatedBook.getId());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
