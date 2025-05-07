package com.isep.lab2todoapi.persistence.csvfiles;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.application.ITodoRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoCsvFilesRepository implements ITodoRepository {

    private static final String PATH = "./storage/todos.csv";
    private static final String SEPARATOR = ",";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public TodoCsvFilesRepository() {
        initCsvFile();
    }

    private void initCsvFile() {
        Path path = Paths.get(PATH);
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    writer.write("name,date");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("It's impossible to create the CSV file", e);
        }
    }

    @Override
    public void addTodo(Todo todo) {
        Path path = Paths.get(PATH);
        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
        )) {
            writer.write(toCsvLine(todo));
            writer.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException("It's impossible to write the todo in the CSV file", e);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        Path path = Paths.get(PATH);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            // skip the header
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                todos.add(parseCsvLine(line));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("It's impossible to read the CSV file", e);
        }
        return todos;
    }

    private String toCsvLine(Todo todo) {
        String dateStr = "";
        if (todo.getDate() != null) {
            LocalDate ld = todo.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dateStr = ld.format(DATE_FORMATTER);
        }
        return todo.getName() + SEPARATOR + dateStr;
    }

    private Todo parseCsvLine(String line) {
        String[] parts = line.split(SEPARATOR, -1);
        String name = parts[0];
        Date date = null;
        if (parts.length > 1 && !parts[1].isEmpty()) {
            LocalDate ld = LocalDate.parse(parts[1], DATE_FORMATTER);
            date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return new Todo(name, date);
    }
}
