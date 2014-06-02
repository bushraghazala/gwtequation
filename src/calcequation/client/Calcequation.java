package calcequation.client;

import java.awt.TextField;

import org.apache.tools.ant.taskdefs.Javadoc.Html;

import calcequation.client.*;
import calcequation.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.java.swing.plaf.windows.resources.windows;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calcequation implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final calculateAsync com = GWT.create(calculate.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button calcButton = new Button("calculate");
		final TextBox aField = new TextBox();
		final TextBox bField = new TextBox();
		final TextBox cField = new TextBox();
		final Label resLabel = new Label();
		final HTML solLabel = new HTML();

		// We can add style names to widgets
		calcButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("calcButton").add(calcButton);
		RootPanel.get("a").add(aField);
		RootPanel.get("b").add(bField);
		RootPanel.get("c").add(cField);

		// Create a handler for the calcButton
		class MyHandler implements ClickHandler, KeyPressHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendToServer();
			}

			buttonCallBack bb = new buttonCallBack();

			private void sendToServer() {

				int a, b, c;
				if (aField.getText().equals(""))
					a = 0;
				else
					a = Integer.parseInt(aField.getText());
				if (bField.getText().equals(""))
					b = 0;
				else
					b = Integer.parseInt(bField.getText());
				if (cField.getText().equals(""))
					c = 0;
				else
					c = Integer.parseInt(cField.getText());
				com.cal(a, b, c, bb);
			}

			class buttonCallBack implements AsyncCallback<double[]> {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("error" + caught.toString());
				}

				public void onSuccess(double[] result) {
					// TODO Auto-generated method stub
					solLabel.setHTML("<h1></h1>");
					if (result[0] == 0) {
						resLabel.setText("There is no solution !!!");
						solLabel.setHTML("<h1></h1>");

					}

					else if (result[0] == 1) {
						resLabel.setText("There is one solution ");
						solLabel.setHTML("<h1>sol=" + result[1] + "</h1>");
					} else if (result[0] == 2) {
						resLabel.setText("There is 2 solutions");
						solLabel.setHTML("<h1>sol1=" + result[1]
								+ "  <br>sol2= " + result[2] + "</h1>");
					}
					RootPanel.get("res").add(resLabel);
					RootPanel.get("sol").add(solLabel);
				}

			}

			@Override
			public void onKeyPress(KeyPressEvent event) {
				// TODO Auto-generated method stub

				if (!((event.getCharCode() >= '0' && event.getCharCode() <= '9'))) {
					Window.alert("you must enter numbers only ");
					event.preventDefault();

				}

			}

		}
		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		calcButton.addClickHandler(handler);
		aField.addKeyPressHandler(handler);
		bField.addKeyPressHandler(handler);
		cField.addKeyPressHandler(handler);
	}
}
