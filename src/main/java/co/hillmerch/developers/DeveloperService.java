package co.hillmerch.developers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DeveloperService {

	public List<Developer> generateDeveloperCurrentStatus(){
		List<Developer> developerList = this.findDevelopers();
		for(Developer developer:developerList){
			developer = this.loadNumberOfTasks( developer );
		}
		return developerList;
	}

	public List<Developer> generateDeveloperCurrentStatusAsync(){
		List<Developer> developerList = this.findDevelopers();
		List<CompletableFuture<Developer>>  cfList = new ArrayList<>();
		for(Developer developer:developerList){
			CompletableFuture cf = CompletableFuture.supplyAsync( () -> this.loadNumberOfTasks( developer ));
			cfList.add( cf );
		}
		CompletableFuture.allOf( cfList.toArray(new CompletableFuture[0])).join();
		developerList = cfList.stream()
				.filter( CompletableFuture::isDone  )
				.map( CompletableFuture::join )
				.collect( Collectors.toList() );
		return developerList;
	}

	private List<Developer> findDevelopers(){
		// Este método simula la consulta de los desarrolladores generalmente sobre una base de datos
		return List
				.of(Developer.of( 1L, "Developer 1" ),
					Developer.of( 2L, "Developer 2" ),
					Developer.of( 3L, "Developer 3" ),
					Developer.of( 4L, "Developer 4" ),
					Developer.of( 5L, "Developer 5" ),
					Developer.of( 6L, "Developer 6" ),
					Developer.of( 7L, "Developer 7" ),
					Developer.of( 8L, "Developer 8" ),
					Developer.of( 9L, "Developer 9" ),
					Developer.of( 10L, "Developer 10" )
					);
	}

	private Developer loadNumberOfTasks(Developer developer){
		// Este método simula la consulta del número de tareas asignadas a un desarrollador
		// Se genera un valor aleatorio para simular el número de tareas pendientes, en progreso y finalizadas
		// por cada desarrollador
		Random random = new Random();
		developer.setTaskToDo( random.ints( 0, 20).findAny().getAsInt() );
		developer.setTaskDone( random.ints( 0, 20).findAny().getAsInt() );
		developer.setTaskInProgress( random.ints( 0, 20).findAny().getAsInt() );

		this.sleep( 2_000);//Una pausa de 1 segundo para simular el tiempo que demora la carga de las tareas
		return developer;
	}

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
