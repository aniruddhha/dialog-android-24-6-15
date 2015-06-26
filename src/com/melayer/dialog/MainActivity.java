package com.melayer.dialog;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	private Button btnAlert, btnDate, btnTime, btnCustom, btnProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAlert = (Button) findViewById(R.id.btnAlert);
		btnAlert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				FragmentManager manager = getSupportFragmentManager();
				MyDialog dialog = new MyDialog();
				dialog.show(manager, MyDialog.KEY_ALERT_DIALOG);
			}
		});

		btnDate = (Button) findViewById(R.id.btnDate);
		btnDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				FragmentManager manager = getSupportFragmentManager();
				MyDialog dialog = new MyDialog();
				dialog.show(manager, MyDialog.KEY_DATE_PICKER_DIALOG);
			}
		});

		btnTime = (Button) findViewById(R.id.btnTime);
		btnTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				FragmentManager manager = getSupportFragmentManager();
				MyDialog dialog = new MyDialog();
				dialog.show(manager, MyDialog.KEY_TIME_PICKER_DIALOG);
			}
		});

		btnCustom = (Button) findViewById(R.id.btnCutom);
		btnCustom.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				FragmentManager manager = getSupportFragmentManager();
				MyDialog dialog = new MyDialog();
				dialog.show(manager, MyDialog.KEY_OUR_DIALOG);

			}
		});

		btnProgress = (Button) findViewById(R.id.btnProgress);
		btnProgress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				/*MyTask task = new MyTask();
				task.execute();*/
				
				MyDialog dialog = new MyDialog();
				FragmentManager manager = getSupportFragmentManager();
				dialog.show(manager, "");
				

				return;
				/*
				 * problem 1
				 * 
				 * for(int i = 0 ; i <=10 ;i++){
				 * 
				 * try { Thread.sleep(500); } catch (InterruptedException e) {
				 * // TODO Auto-generated catch block e.printStackTrace(); }
				 * btnProgress.setText(""+i); }
				 */

				/*
				 * 
				 * problem 2 Thread thread = new Thread(new Runnable() {
				 * 
				 * @Override public void run() { for (int i = 0; i <= 10; i++) {
				 * 
				 * try { Thread.sleep(500); } catch (InterruptedException e) {
				 * // TODO Auto-generated catch block e.printStackTrace(); }
				 * //btnProgress.setText("" + i); } } });
				 * 
				 * thread.start();
				 */
			}
		});
	}

	private class MyTask extends AsyncTask<Void, Integer, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params) {

			for (int i = 0; i < 100; i++) {

				 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				// onProgressUpdate(i);
				publishProgress(i);
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			btnProgress.setText("" + values[0]);
		}
	}
}
