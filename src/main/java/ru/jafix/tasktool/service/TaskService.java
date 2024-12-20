package ru.jafix.tasktool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.tasktool.entity.Task;
import ru.jafix.tasktool.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    protected TaskRepository taskRepository;

    public Task saveTask(Task taskToSave) {
        return taskRepository.save(taskToSave);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException(String.format("Задача с id = %s не найдена", id));
        }

        return optionalTask.get();
    }

    public Task editTask(Task taskToEdit, UUID id) {
        findById(id);
        taskToEdit.setId(id);
        return taskRepository.save(taskToEdit);
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
