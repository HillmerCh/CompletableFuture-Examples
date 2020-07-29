package co.hillmerch.payments;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PaymentServiceTest {

	private static final Logger logger = LoggerFactory.getLogger( PaymentServiceTest.class);

	@Test
	void testingProcessPayments() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método
		File paymentsFile = new File( "src/test/resources/payments.txt");
		PaymentService paymentService = new PaymentService();
		paymentService.processPayments( paymentsFile );

		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());
	}

	@Test
	void testingProcessPaymentsAsync() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método
		File paymentsFile = new File( "src/test/resources/payments.txt");
		PaymentService paymentService = new PaymentService();
		paymentService.processPaymentsAsync( paymentsFile );
		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());

		logger.info( "Mensajes generados durante la ejecución Asíncrona ");
		this.sleep( 10_000 );//Una pausa de 10 segundos para poder ver la ejecución Asíncrona
	}

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}