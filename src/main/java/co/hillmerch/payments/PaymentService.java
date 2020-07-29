package co.hillmerch.payments;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {

	private static final Logger logger = LoggerFactory.getLogger( PaymentService.class );

	/**
	 * A partir de un archivo plano con los pagos recibidos se registra el pago
	 * se cambia el estado al producto
	 *
	 * @param paymentsFile contiene un archivo plano, cada linea contiene un número de factura,
	 * el valor y la fecha (DD/MM/YYYY) del pago, los treas valores separados por espacios en blanco.
	 */
	public void processPayments(File paymentsFile){
		try (Scanner sc = new Scanner( paymentsFile)){
			while (sc.hasNextLine()) {
				String[] paymentLine = sc.nextLine().split( " " );
				Invoice invoice = findInvoice(paymentLine[0]);
				Payment payment = savedPayment( invoice , paymentLine[1], paymentLine[2]);
				logger.info( payment.toString() );
			}
		}catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	public void processPaymentsAsync(File paymentsFile){
		try (Scanner sc = new Scanner( paymentsFile)){
			ExecutorService executorService = Executors.newFixedThreadPool( 50);
			while (sc.hasNextLine()) {
				String[] paymentLine = sc.nextLine().split( " " );
				CompletableFuture
						.supplyAsync( ()-> findInvoice( paymentLine[0] ), executorService )
						.thenApplyAsync( invoice -> savedPayment( invoice , paymentLine[1], paymentLine[2]) )
						.thenAccept( payment -> logger.info( payment.toString() ));
			}
		}catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private Invoice findInvoice(String reference){
		// Este método simula la consulta de la factura generalmente sobre una base de datos
		// Se genera un valor aleatorio para simular el total de la factura

		Invoice invoice = new Invoice();
		invoice.setReference( reference );
		Random random = new Random();

		invoice.setTotal( (double) random.ints( 100, 5000).findAny().getAsInt() );

		this.sleep( 1_000);//Una pausa de 1 segundo para simular el tiempo que
								// podría durar el cargue de la factura desde la base de datos

		return invoice;
	}

	private Payment savedPayment(Invoice invoice, String value, String dateOfPayment){
		// Este método simula el registro del pago generalmente sobre una base de datos
		Payment payment = new Payment();
		payment.setInvoice( invoice );
		payment.setValue( Double.parseDouble( value ));

		String[] dateOfPaymentArray = dateOfPayment.split( "/" );
		payment.setDateOfPayment( LocalDate.of( Integer.parseInt(dateOfPaymentArray[2]) ,
												Integer.parseInt(dateOfPaymentArray[1]),
												Integer.parseInt(dateOfPaymentArray[0])));

		this.sleep( 2_000);//Una pausa de 2 segundos para simular el tiempo que
								// podría durar el registro del pago en la base de datos

		return payment;
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
