package com.isep.lab2todoapi.persistence.csvfiles;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.application.ITodoRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoCsvFilesRepository implements ITodoRepository {

    private final static String PATH = "storage/todos.csv";

    private final static String SEPARATOR = ",";


    @Override
    public void addTodo(Todo todo) {
        createCsvFile();
        writeTodoToCsvFile(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return readTodoCsvFile();
    }


    private boolean createCsvFile() {
        try {
            File file = new File(PATH);
            if (!file.exists() && file.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean writeTodoToCsvFile(Todo todo) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(PATH));
            writer.write(todo.toString());
            writer.write(System.lineSeparator());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String TodoStringCsv(Todo todo) {
        return todo.getName() + SEPARATOR + todo.getDate().toString();
    }

    private List<Todo> readTodoCsvFile() {
        List<Todo> todos = new ArrayList<>();
        try {
            InputStreamReader reader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Todo todo = parseTodo(line);
                todos.add(todo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    private Todo parseTodo(String line) {
        String[] data = line.split(SEPARATOR);
        String name = data[0];


        LocalDate localdate = LocalDate.parse(data[1]);
        Date date = new Date(localdate.getYear(), localdate.getMonthValue(), localdate.getDayOfMonth());

        return new Todo(name, date);
    }
}
