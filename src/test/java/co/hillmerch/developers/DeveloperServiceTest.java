package co.hillmerch.developers;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DeveloperServiceTest {

	private static final Logger logger = LoggerFactory.getLogger( DeveloperServiceTest.class);

	@Test
	void testingGenerateDeveloperCurrentStatus() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método

		DeveloperService developerService = new DeveloperService();
		List<Developer> developerList = developerService.generateDeveloperCurrentStatus();
		logger.info( developerList.toString() );

		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());
	}

	@Test
	void testingGenerateDeveloperCurrentStatusAsync() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método

		DeveloperService developerService = new DeveloperService();
		List<Developer> developerList = developerService.generateDeveloperCurrentStatusAsync();
		logger.info( developerList.toString() );

		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());
	}

	@Test
	void testingGenerateDeveloperCurrentStatusAsyncWithExecutors() {
		Instant start = null;//Variable usada para contar el tiempo en que se tiene respuesta del método

		DeveloperService developerService = new DeveloperService();

		logger.info( "Prueba con Executor: Single Thread");
		start = Instant.now();
		List<Developer> developerList = developerService.generateDeveloperCurrentStatusAsyncWithExecutor( Executors.newSingleThreadExecutor());
		logger.info( "Tiempo de respuesta (Single Thread): " + Duration.between( start, Instant.now()).toString());

		logger.info( "Prueba con Executor: Common Pool");
		start = Instant.now();
		List<Developer> developerList2 = developerService.generateDeveloperCurrentStatusAsyncWithExecutor(ForkJoinPool.commonPool());
		logger.info( "Tiempo de respuesta (Common Pool): " + Duration.between( start, Instant.now()).toString());

		logger.info( "Prueba con Executor: Fixed Thread");
		start = Instant.now();
		List<Developer> developerList3 = developerService.generateDeveloperCurrentStatusAsyncWithExecutor(Executors.newFixedThreadPool(50));
		logger.info( "Tiempo de respuesta (Fixed Thread): " + Duration.between( start, Instant.now()).toString());
	}

}