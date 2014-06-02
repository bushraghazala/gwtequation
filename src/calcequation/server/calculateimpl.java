package calcequation.server;

import calcequation.client.calculate;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class calculateimpl extends RemoteServiceServlet implements calculate {

	public double[] cal(int a, int b, int c) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		double[] res = { 0, 0, 0 };
		double d = (b * b) - 4 * a * c;
		if (d == 0) {
			res[0] = 1;
			res[1] = (-b+(Math.sqrt(d))) / (2 * a);
			return res;
		}                        
		else if (d > 0) {
			res[0] =2;
			res[1] = (-b+(Math.sqrt(d))) / (2 * a);
			res[2] = (-b-(Math.sqrt(d))) / (2 * a);
			return res;
		}
		return res;
	}

}  
