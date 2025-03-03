package be.vinci.ipl.cae.cae_exercices_exemple.controllers;

import be.vinci.ipl.cae.cae_exercices_exemple.models.NewTask;
import be.vinci.ipl.cae.cae_exercices_exemple.models.Task;
import be.vinci.ipl.cae.cae_exercices_exemple.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Afficher toutes les tâches")
    @GetMapping({"", "/"})
    @ApiResponse(responseCode = "200", description = "L'affichage de toutes les tâches a réussi")
    public Iterable<Task> findAll(
        @Parameter(description = "Boolean qui permet de savoir si il faut afficher seulement les tâches completés")
        @RequestParam(required = false) Boolean completed) {
        if (completed != null) return taskService.findByCompleted(completed);
        return taskService.findAll();
    }

    @Operation(summary = "Afficher une tâche grâce à son ID")
    @GetMapping("/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tâche trouvé"),
        @ApiResponse(responseCode = "404", description = "Tâche invalide"),
    })
    public Task findOne(
        @Parameter(description = "ID de la tâche")
        @PathVariable Long id) {
        Task task = taskService.findOne(id);
        if (task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return task;
    }

    @Operation(summary = "Création d'une tâche")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tâche crée"),
        @ApiResponse(responseCode = "404", description = "Requête non valide"),
        @ApiResponse(responseCode = "409", description = "Conflit : Un autre titre existant existe déjà en DB")
    })
    public Task create(
        @Parameter(description = "La nouvelle tâche à ajouter")
        @RequestBody NewTask newTask) {
        String title = newTask.getTitle();
        if (!taskService.validTitle(title)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (taskService.conflictExists(title)) throw new ResponseStatusException(HttpStatus.CONFLICT);
        return taskService.create(title);
    }

    @Operation(summary = "Supprimer une tâche")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tâche supprimée"),
        @ApiResponse(responseCode = "404", description = "Tâche non trouvée")
    })
    public void delete(
        @Parameter(description = "ID de la tâche")
        @PathVariable Long id) {
        if (taskService.doesNotExist(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        taskService.delete(id);
    }

    @Operation(summary = "Marqué une tâche comme complétée")
    @PatchMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tâche completée"),
        @ApiResponse(responseCode = "404", description = "Tâche invalide"),
        @ApiResponse(responseCode = "400", description = "Tâche déjà completée")
    })
    public void markCompleted(
        @Parameter(description = "ID de la tâche")
        @PathVariable Long id) {
        if (taskService.doesNotExist(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (taskService.isCompleted(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        taskService.markCompleted(id);
    }

}
