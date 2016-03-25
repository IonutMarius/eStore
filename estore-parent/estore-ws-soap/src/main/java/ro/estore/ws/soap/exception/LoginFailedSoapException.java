package ro.estore.ws.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "The username or password is incorrect")
public class LoginFailedSoapException extends RuntimeException {
	private static final long serialVersionUID = -7395158854322257139L;

}
