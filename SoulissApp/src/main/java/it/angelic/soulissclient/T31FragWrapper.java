package it.angelic.soulissclient;

import static junit.framework.Assert.assertTrue;
import it.angelic.soulissclient.fragments.T31HeatingFragment;
import it.angelic.soulissclient.model.SoulissTypical;
import android.content.res.Configuration;
import android.os.Bundle;

public class T31FragWrapper extends AbstractStatusedFragmentActivity {
	private SoulissTypical collected;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (opzioni.isLightThemeSelected())
			setTheme(R.style.LightThemeSelector);
		else
			setTheme(R.style.DarkThemeSelector);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main_detailwrapper);
		// recuper nodo da extra
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// If the screen is now in landscape mode, we can show the
			// dialog in-line with the list so we don't need this activity.
			finish();
			return;
		}
		Bundle extras = getIntent().getExtras();

		// collected.setCtx(getActivity());

		if (extras != null && extras.get("TIPICO") != null)
			collected = (SoulissTypical) extras.get("TIPICO");
		assertTrue("TIPICO NULLO", collected != null);
		setActionBarInfo(collected.getNiceName());
		if (savedInstanceState == null) {
			// During initial setup, plug in the details fragment.
			T31HeatingFragment details = T31HeatingFragment.newInstance(collected.getTypicalDTO().getSlot(),
					collected);
			details.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction().replace(R.id.detailPane, details).commit();
		}
	}

	// meccanismo per timeout detection
	/*
	 * private BroadcastReceiver timeoutReceiver = new BroadcastReceiver() {
	 * 
	 * @Override public void onReceive(Context context, Intent intent) {
	 * Log.w(TAG, "Posting timeout from " + intent.toString()); Bundle extras =
	 * intent.getExtras(); int delay = extras.getInt("REQUEST_TIMEOUT_MSEC"); }
	 * };
	 */

}
