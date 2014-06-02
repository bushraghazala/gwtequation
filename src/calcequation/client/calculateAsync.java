package calcequation.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface calculateAsync {

	void cal(int a, int b, int c, AsyncCallback<double[]> callback);

}
