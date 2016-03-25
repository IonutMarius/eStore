package ro.estore.ws.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER)
public class ProductOutOfStockSoapException extends RuntimeException {

	private static final long serialVersionUID = -5217647413540898640L;

	public ProductOutOfStockSoapException(String message){
		super(message);
	}
}
