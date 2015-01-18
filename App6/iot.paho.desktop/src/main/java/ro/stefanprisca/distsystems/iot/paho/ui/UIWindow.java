package ro.stefanprisca.distsystems.iot.paho.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ro.stefanprisca.distsystems.iot.paho.constants.Constants;
import ro.stefanprisca.distsytems.iot.paho.desktop.MQTTSample;
import ro.stefanprisca.distsytems.iot.paho.desktop.MyMqttCallback;




public class UIWindow {

	private MQTTSample mqtt;
	
	private void createContents(Shell sh){
		Composite contents = new Composite(sh, SWT.DEFAULT);
		contents.setLayout(new GridLayout(4, false));
		
		Button connect = new Button(contents, SWT.PUSH);
		connect.setText("Connect");
		connect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				mqtt.initConnection();
			}
			});
		Button disconnect = new Button(contents, SWT.PUSH);
		disconnect.setText("disconnect");
		disconnect.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent arg0) {
				mqtt.disconect();
			}
		});
		Button publish = new Button(contents, SWT.PUSH);
		publish.setText("Publish");
		publish.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent arg0) {
				mqtt.publish(Constants.TOPIC, Constants.CONTENT);
			}
			
		});
		
		Button subscribe = new Button(contents, SWT.PUSH);
		subscribe.setText("Subscribe");
		subscribe.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent arg0) {
				mqtt.subscribe(Constants.TOPIC);
			}
			
		});
		contents.pack();
		sh.pack();
	}
	
	private void init(){
		this.mqtt = new MQTTSample();
		Display display = new Display();
		Shell shell = new Shell(display);
		this.createContents(shell);
		shell.open();

		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch())
		   {
			  display.sleep();
		   }
		}
		display.dispose(); 
	}
	
	public static UIWindow window;
	
	public static void main(String [] args){
		window = new UIWindow();
		window.init();
	}
}
