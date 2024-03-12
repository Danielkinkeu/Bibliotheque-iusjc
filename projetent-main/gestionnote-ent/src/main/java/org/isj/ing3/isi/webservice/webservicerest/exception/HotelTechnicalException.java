package org.isj.ing3.isi.webservice.webservicerest.exception;

import org.zalando.problem.Status;

/**
 * The Class HotelTechnicalException.
 */
public class HotelTechnicalException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private final Status httpStatus;

	/**
	 * Instantiates a new one logic technical exception.
	 */
	public HotelTechnicalException(Status httpStatus) {
		super();
		this.httpStatus=httpStatus;
	}

	/**
	 * @param msg
	 * @param httpStatus
	 */
	public HotelTechnicalException(String msg, Status httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}

	/**
	 * @param cause
	 * @param httpStatus
	 */
	public HotelTechnicalException(Throwable cause, Status httpStatus) {
		super(cause);
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the httpStatus
	 */
	public Status getHttpStatus() {
		return httpStatus;
	}





}
