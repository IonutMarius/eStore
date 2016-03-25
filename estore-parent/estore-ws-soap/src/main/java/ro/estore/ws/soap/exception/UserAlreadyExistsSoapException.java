package ro.estore.ws.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "The user already exists")
public class UserAlreadyExistsSoapException extends RuntimeException {

	private static final long serialVersionUID = -3847921314315594570L;

}
