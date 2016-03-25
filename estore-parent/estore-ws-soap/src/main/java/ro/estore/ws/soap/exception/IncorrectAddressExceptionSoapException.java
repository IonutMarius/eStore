package ro.estore.ws.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "The provided address is incorrect")
public class IncorrectAddressExceptionSoapException extends RuntimeException {

	private static final long serialVersionUID = -3847921314315594570L;

}
