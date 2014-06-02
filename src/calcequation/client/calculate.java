package calcequation.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calculate")
public interface calculate extends RemoteService {
	double[] cal(int a,int b , int c) throws IllegalArgumentException;
} 
 