
package com.doublechaintech.retailscm.view;
//import com.doublechaintech.retailscm.EntityNotFoundException;
import com.doublechaintech.retailscm.RetailscmException;
import com.doublechaintech.retailscm.Message;
import java.util.List;

public class ViewManagerException extends RetailscmException {
	private static final long serialVersionUID = 1L;
	public ViewManagerException(String string) {
		super(string);
	}
	public ViewManagerException(Message message) {
		super(message);
	}
	public ViewManagerException(List<Message> messageList) {
		super(messageList);
	}

}


