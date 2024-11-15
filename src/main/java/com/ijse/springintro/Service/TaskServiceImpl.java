package com.ijse.springintro.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springintro.Entity.Task;
import com.ijse.springintro.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /* public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    } */
    
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        //view task array fetched from database
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);

        if(existingTask == null) {
            return null;
        } else {
            existingTask.setTaskName(task.getTaskName());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            return taskRepository.save(existingTask);
        }

    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}