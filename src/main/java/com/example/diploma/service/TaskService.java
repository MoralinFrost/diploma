package com.example.diploma.service;

import com.example.diploma.Entity.*;
import com.example.diploma.repos.TaskRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepos taskRepos;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private CommentService commentService;

    public void addTask(Task task) {
        taskRepos.save(task);
    }

    public List<Task> findAll(){
        return taskRepos.findAll();
    }

    public Task getTaskById(Integer id) {return taskRepos.getTaskById(id);}

    public void updateTask(Task task, String title, String description, LocalDate deadline, Workflow workflow, Project project, List<MultipartFile> files, User principal) {
        String taskTitle = task.getTitle();
        String taskDescription = task.getDescription();
        LocalDate taskDeadline = task.getDeadline();
        Workflow taskWorkflow = task.getWorkflow();
        Project taskProject = task.getProject();
        List<Attachment> currentAttachments=task.getAttachment();

        boolean isTitleChanged=(title != null && !title.equals(taskTitle)) ||
                (taskTitle != null && !taskTitle.equals(title));
        boolean isDescriptionChanged=(description != null && !description.equals(taskDescription)) ||
                (taskDescription != null && !taskDescription.equals(description));
        boolean isDeadlineChanged=(deadline != null && !deadline.equals(taskDeadline)) ||
                (taskDeadline != null && !taskDeadline.equals(deadline));
        boolean isWorkflowChanged=(workflow != null && !workflow.equals(taskWorkflow)) ||
                (taskWorkflow != null && !taskWorkflow.equals(workflow));
        boolean isSpaceChanged=(project != null && !project.equals(taskProject)) ||
                (taskProject != null && !taskProject.equals(project));


        if(isTitleChanged)
        {
            task.setTitle(title);
        }
        if(isDescriptionChanged)
        {
            task.setDescription(description);
        }
        if(isDeadlineChanged)
        {
            task.setDeadline(deadline);
        }
        if (isWorkflowChanged)
        {
            task.setWorkflow(workflow);
        }
        if (isSpaceChanged)
        {
            task.setProject(project);
        }
        List<Attachment> attachments=attachmentService.saveTask(files, task);
        task.setAttachment(attachments);
        task.setPrincipal(principal);
        taskRepos.save(task);
    }

    public List<Task> findAllByPrincipal(User principal) {return taskRepos.findAllByPrincipal(principal);}

    public Iterable<Task> findByProject_Namespace(String filter){
        return taskRepos.findByProject_Namespace(filter);
    }
    public Optional<Task> findById(Integer id){
        return taskRepos.findById(id);
    }
    public void updateTaskProject(Integer taskId, Project project) {
        Task task = taskRepos.findById(taskId).orElseThrow(()->new RuntimeException("Task not found"));
        task.setProject(project);
        taskRepos.save(task);
    }
    public void deleteTaskById(Integer id) {
        taskRepos.deleteById(id);
    }
    public void deleteTaskAndAttachmentById(Integer id) {
        Task task=taskRepos.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        attachmentService.deleteAttachmentByTaskId(id);
        taskRepos.deleteById(id);
    }



}
