package components;

import mediator.*;

public interface Component {
	void setMediator(Mediator mediator);
	String getName();
}
