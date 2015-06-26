package com.melayer.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialog extends DialogFragment {

	private Dialog dialogRoot;

	private DialogClick click = new DialogClick();
	private OnDateSetListener dateListener = new DateGrabber();

	private EditText edtDialogName;
	private Button btnDialogOkay;

	public static final String KEY_ALERT_DIALOG = "KEY_ALERT_DIALOG";
	public static final String KEY_DATE_PICKER_DIALOG = "KEY_DATE_PICKER_DIALOG";
	public static final String KEY_TIME_PICKER_DIALOG = "KEY_TIME_PICKER_DIALOG";
	public static final String KEY_OUR_DIALOG = "KEY_OUR_DIALOG";
	public static final String KEY_PROGRESS_DIALOG = "KEY_PROGRESS_DIALOG";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		super.onCreateDialog(savedInstanceState);

		if (getTag().equals(KEY_ALERT_DIALOG)) {

			Log.i("########## IN DIALOG ######", "Got it");

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Title");
			builder.setMessage("Message");
			builder.setIcon(R.drawable.ic_launcher);
			builder.setPositiveButton("Yes", click);
			builder.setNegativeButton("No", click);

			dialogRoot = builder.create();
		} else if (getTag().equals(KEY_DATE_PICKER_DIALOG)) {

			DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
					dateListener, 2015, 5, 24);

			dialogRoot = datePicker;

		} else if (getTag().equals(KEY_TIME_PICKER_DIALOG)) {

			TimePickerDialog timePicker = new TimePickerDialog(getActivity(),
					null, 10, 22, false);

			dialogRoot = timePicker;
		} else if (getTag().equals(KEY_OUR_DIALOG)) {

			dialogRoot = new Dialog(getActivity());
			dialogRoot.setTitle("Title");
			dialogRoot.setContentView(R.layout.dialog_our);

			edtDialogName = (EditText) dialogRoot
					.findViewById(R.id.edtDialogName);

			btnDialogOkay = (Button) dialogRoot.findViewById(R.id.btnOkay);

		} else {

			ProgressDialog dialog = new ProgressDialog(getActivity());
			/*
			 * dialog.setIcon(R.drawable.ic_launcher); dialog.setTitle("Title");
			 * dialog.setMessage("Message");
			 * dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			 */

			dialogRoot = dialog;

			MyProgressTask task = new MyProgressTask(dialog);
			task.execute();

		}

		return dialogRoot;
	}

	private class MyProgressTask extends AsyncTask<Void, Integer, Void> {

		private ProgressDialog pd;

		public MyProgressTask(ProgressDialog pd) {

			super();

			this.pd = pd;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pd.setIcon(R.drawable.ic_launcher);
			pd.setTitle("Title");
			pd.setMessage("Message");
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		}

		@Override
		protected Void doInBackground(Void... params) {

			for (int i = 0; i < 100; i++) {

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				publishProgress(i);

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			pd.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			pd.setProgress(values[0]);
		}
	}

	private class DialogClick implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {

			if (which == DialogInterface.BUTTON_POSITIVE) {

				Toast.makeText(getActivity(), "+ve button", Toast.LENGTH_LONG)
						.show();
			} else if (which == DialogInterface.BUTTON_NEGATIVE) {

				Toast.makeText(getActivity(), "-ve button", Toast.LENGTH_LONG)
						.show();
			} else {

				Toast.makeText(getActivity(), "== button", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	private class DateGrabber implements OnDateSetListener {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			String date = "" + dayOfMonth + " - " + (monthOfYear + 1) + " - "
					+ year;

			Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
		}
	}
}
